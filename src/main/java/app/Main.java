package app;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import modelo.clinica.Cita;
import modelo.clinica.Clinica;
import modelo.clinica.Consultorio;
import modelo.clinica.EstadoCita;
import modelo.comprobante.Comprobante;
import modelo.mascotas.AveExotica;
import modelo.mascotas.Gato;
import modelo.mascotas.Mascota;
import modelo.mascotas.Perro;
import modelo.personas.Propietario;
import modelo.personas.Veterinario;
import modelo.servicios.Cirugia;
import modelo.servicios.ConsultaGeneral;
import modelo.servicios.Servicio;
import modelo.servicios.Vacunacion;

/**
 * Programa principal de PetCare: menú de consola para gestionar la clínica. Toda entrada
 * incorrecta se captura y se avisa sin cerrar el programa.
 * Para cargar datos de ejemplo: java -cp out app.Main demo
 */
public class Main {
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final BigDecimal TARIFA_CONSULTA = new BigDecimal("350.00");
    private static final BigDecimal COSTO_VACUNA = new BigDecimal("200.00");
    private static final BigDecimal TARIFA_APLICACION = new BigDecimal("50.00");
    private static final BigDecimal TARIFA_QUIROFANO = new BigDecimal("800.00");

    /**
     * Muestra las opciones del menú en la consola.
     */
    private static void mostrarMenu() {
        System.out.println("\n=== Gestion PetCare ===");
        System.out.println("1. Registrar dueño y mascota.");
        System.out.println("2. Registrar veterinario y asignarlo a la clínica.");
        System.out.println("3. Agendar una cita.");
        System.out.println("4. Atender o cancelar una cita.");
        System.out.println("5. Generar el comprobante de una cita atendida.");
        System.out.println("6. Consultar el historial clínico de una mascota.");
        System.out.println("7. Listar todas las mascotas y su sonido.");
        System.out.println("8. Ver el total cobrado por veterinario.");
        System.out.println("9. Dar de baja una mascota.");
        System.out.println("0. Salir...");
    }

    /**
     * Punto de entrada del programa.
     *
     * @param argumentos si el primero es "demo", se cargan datos de ejemplo
     */
    public static void main(String[] argumentos) {
        Scanner entrada = new Scanner(System.in);
        Clinica clinica = new Clinica("PetCare Coatzacoalcos");
        clinica.crearConsultorio(1);
        clinica.crearConsultorio(2);
        if (argumentos.length > 0 && argumentos[0].equalsIgnoreCase("demo")) {
            cargarDatosDemo(clinica);
        }
        try {
            int opcion;
            do {
                mostrarMenu();
                opcion = leerEntero(entrada, "Elige una opción: ");
                ejecutarOpcion(opcion, entrada, clinica);
            } while (opcion != 0);
        } catch (NoSuchElementException e) {
            // Ocurre si se cierra la entrada (por ejemplo, con Ctrl+Z): se sale sin error
            System.out.println("\nEntrada finalizada. Saliendo del sistema...");
        }
        entrada.close();
    }

    // Ejecuta la opción elegida. Cualquier error de datos o de reglas se muestra y el
    // programa continúa (multi-catch: ambas excepciones se manejan igual).
    private static void ejecutarOpcion(int opcion, Scanner entrada, Clinica clinica) {
        try {
            switch (opcion) {
                case 1 -> registrarDuenoYMascota(entrada, clinica);
                case 2 -> registrarVeterinario(entrada, clinica);
                case 3 -> agendarCita(entrada, clinica);
                case 4 -> atenderOCancelarCita(entrada, clinica);
                case 5 -> generarComprobante(entrada, clinica);
                case 6 -> consultarHistorial(entrada, clinica);
                case 7 -> listarMascotasYSonido(clinica);
                case 8 -> verTotalPorVeterinario(clinica);
                case 9 -> darDeBajaMascota(entrada, clinica);
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida, solo puede elegir números "
                        + "entre el 0 y el 9.");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("No se pudo completar la operación: " + e.getMessage());
        }
    }

    // ---------- Lectura segura de datos ----------

    // Lee una línea completa; evita el problema de nextInt() + nextLine()
    private static String leerTexto(Scanner entrada, String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine();
    }

    // Repite la pregunta hasta que se escriba un entero válido
    private static int leerEntero(Scanner entrada, String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(entrada, mensaje).trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida: escribe un número entero.");
            }
        }
    }

    // Repite la pregunta hasta que se escriba un número decimal válido
    private static double leerDecimal(Scanner entrada, String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(entrada, mensaje).trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida: escribe un número (ejemplo: 12.5).");
            }
        }
    }

    // Repite la pregunta hasta que se escriba una fecha con el formato esperado
    private static LocalDateTime leerFechaHora(Scanner entrada) {
        while (true) {
            try {
                String texto = leerTexto(entrada, "Fecha y hora (aaaa-MM-dd HH:mm): ");
                return LocalDateTime.parse(texto.trim(), FORMATO);
            } catch (DateTimeParseException e) {
                System.out.println("Formato no válido. Ejemplo: 2026-11-05 10:30");
            }
        }
    }

    // Muestra una lista numerada y devuelve el elemento elegido (<T> sirve para cualquier tipo)
    private static <T> T elegir(Scanner entrada, List<T> opciones, String etiqueta) {
        if (opciones.isEmpty()) {
            throw new IllegalStateException("No hay registros para elegir " + etiqueta + ".");
        }
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i));
        }
        int numero = leerEntero(entrada, "Elige " + etiqueta + " (número): ");
        if (numero < 1 || numero > opciones.size()) {
            throw new IllegalArgumentException("El número elegido no está en la lista.");
        }
        return opciones.get(numero - 1);
    }

    // ---------- Opciones del menú ----------

    private static void registrarDuenoYMascota(Scanner entrada, Clinica clinica) {
        Propietario dueno = elegirOCrearPropietario(entrada, clinica);
        Mascota mascota = crearMascota(entrada, dueno);
        clinica.registrarMascota(mascota);
        System.out.println("Mascota registrada: " + mascota);
    }

    private static Propietario elegirOCrearPropietario(Scanner entrada, Clinica clinica) {
        List<Propietario> existentes = clinica.getPropietarios();
        if (!existentes.isEmpty()) {
            for (int i = 0; i < existentes.size(); i++) {
                System.out.println((i + 1) + ". " + existentes.get(i));
            }
            int numero = leerEntero(entrada, "Propietario existente (número, 0 = nuevo): ");
            if (numero >= 1 && numero <= existentes.size()) {
                return existentes.get(numero - 1);
            }
            if (numero != 0) {
                throw new IllegalArgumentException("El número elegido no está en la lista.");
            }
        }
        return new Propietario(leerTexto(entrada, "Nombre: "), leerTexto(entrada, "Apellido: "),
                leerTexto(entrada, "Teléfono (10 dígitos): "), leerTexto(entrada, "Correo: "),
                leerTexto(entrada, "Dirección: "));
    }

    private static Mascota crearMascota(Scanner entrada, Propietario dueno) {
        int tipo = leerEntero(entrada, "Tipo (1 = perro, 2 = gato, 3 = ave exótica): ");
        String nombre = leerTexto(entrada, "Nombre de la mascota: ");
        int edad = leerEntero(entrada, "Edad (años): ");
        double peso = leerDecimal(entrada, "Peso (kg): ");
        return switch (tipo) {
            case 1 -> new Perro(nombre, edad, peso, dueno, leerTexto(entrada, "Raza: "),
                    leerTexto(entrada, "Nivel de energía (alto, medio, bajo): "));
            case 2 -> new Gato(nombre, edad, peso, dueno,
                    leerTexto(entrada, "¿Es doméstico? (s/n): ").equalsIgnoreCase("s"));
            case 3 -> new AveExotica(nombre, edad, peso, dueno,
                    leerDecimal(entrada, "Longitud de alas (cm): "));
            default -> throw new IllegalArgumentException("Tipo de mascota no válido.");
        };
    }

    private static void registrarVeterinario(Scanner entrada, Clinica clinica) {
        Veterinario veterinario = new Veterinario(leerTexto(entrada, "Nombre: "),
                leerTexto(entrada, "Apellido: "), leerTexto(entrada, "Teléfono (10 dígitos): "),
                leerTexto(entrada, "Correo: "), leerTexto(entrada, "Cédula profesional: "),
                leerTexto(entrada, "Especialidad: "));
        clinica.agregarVeterinario(veterinario);
        System.out.println("Veterinario registrado: " + veterinario);
    }

    private static void agendarCita(Scanner entrada, Clinica clinica) {
        Mascota mascota = elegir(entrada, clinica.getMascotas(), "una mascota");
        Veterinario veterinario = elegir(entrada, clinica.getVeterinarios(), "un veterinario");
        Servicio servicio = elegirServicio(entrada);
        LocalDateTime fechaHora = leerFechaHora(entrada);
        Consultorio consultorio = elegirConsultorio(entrada, clinica);
        Cita cita = new Cita(mascota, veterinario, fechaHora, servicio, consultorio);
        clinica.agendarCita(cita);
        System.out.println("Cita agendada: " + cita);
    }

    // Devuelve un Servicio; la variable es del tipo padre y el objeto, de un tipo hijo
    private static Servicio elegirServicio(Scanner entrada) {
        int tipo = leerEntero(entrada, "Servicio (1 = consulta, 2 = vacunación, 3 = cirugía): ");
        return switch (tipo) {
            case 1 -> new ConsultaGeneral(TARIFA_CONSULTA);
            case 2 -> new Vacunacion(COSTO_VACUNA, TARIFA_APLICACION);
            case 3 -> new Cirugia(leerTexto(entrada, "Complejidad (baja, media, alta): "),
                    leerDecimal(entrada, "Horas de quirófano: "), TARIFA_QUIROFANO);
            default -> throw new IllegalArgumentException("Tipo de servicio no válido.");
        };
    }

    private static Consultorio elegirConsultorio(Scanner entrada, Clinica clinica) {
        int numero = leerEntero(entrada, "Consultorio (número, 0 = asignar después): ");
        if (numero == 0) {
            return null;
        }
        for (Consultorio consultorio : clinica.getConsultorios()) {
            if (consultorio.getNumero() == numero) {
                return consultorio;
            }
        }
        throw new IllegalArgumentException("No existe el consultorio " + numero + ".");
    }

    // Devuelve las citas de la clínica que están en el estado indicado
    private static List<Cita> citasConEstado(Clinica clinica, EstadoCita estado) {
        List<Cita> filtradas = new ArrayList<>();
        for (Cita cita : clinica.getCitas()) {
            if (cita.getEstado() == estado) {
                filtradas.add(cita);
            }
        }
        return filtradas;
    }

    private static void atenderOCancelarCita(Scanner entrada, Clinica clinica) {
        List<Cita> pendientes = citasConEstado(clinica, EstadoCita.PROGRAMADA);
        Cita cita = elegir(entrada, pendientes, "una cita programada");
        int accion = leerEntero(entrada, "1 = atender, 2 = cancelar: ");
        if (accion == 1) {
            cita.atender();
            cita.registrarObservaciones(leerTexto(entrada, "Observaciones: "));
            System.out.println("Cita atendida: " + cita);
        } else if (accion == 2) {
            cita.cancelar();
            System.out.println("Cita cancelada: " + cita);
        } else {
            throw new IllegalArgumentException("Acción no válida.");
        }
    }

    private static void generarComprobante(Scanner entrada, Clinica clinica) {
        List<Cita> atendidas = citasConEstado(clinica, EstadoCita.ATENDIDA);
        Cita cita = elegir(entrada, atendidas, "una cita atendida");
        System.out.println(cita.generarComprobante().imprimir());
    }

    private static void consultarHistorial(Scanner entrada, Clinica clinica) {
        Mascota mascota = elegir(entrada, clinica.getMascotas(), "una mascota");
        System.out.println("Historial clínico de " + mascota.getNombre() + ":");
        List<Cita> historial = mascota.getHistorial().listarCitas();
        if (historial.isEmpty()) {
            System.out.println("  (sin atenciones registradas)");
        }
        for (Cita cita : historial) {
            System.out.println("  " + cita);
        }
        for (Comprobante cobro : clinica.consultarCobrosPorMascota(mascota)) {
            System.out.println("  Cobro " + cobro.getFolio() + ": $" + cobro.getTotal());
        }
    }

    // Polimorfismo: la misma llamada hacerSonido() responde distinto según el tipo real
    private static void listarMascotasYSonido(Clinica clinica) {
        if (clinica.getMascotas().isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        }
        for (Mascota mascota : clinica.getMascotas()) {
            System.out.println(mascota.getNombre() + " dice: " + mascota.hacerSonido());
        }
    }

    private static void verTotalPorVeterinario(Clinica clinica) {
        if (clinica.getVeterinarios().isEmpty()) {
            System.out.println("No hay veterinarios registrados.");
        }
        for (Veterinario veterinario : clinica.getVeterinarios()) {
            BigDecimal total = clinica.consultarTotalCobradoPorVeterinario(veterinario);
            System.out.println(veterinario.getNombreCompleto() + ": $" + total);
        }
    }

    private static void darDeBajaMascota(Scanner entrada, Clinica clinica) {
        Mascota mascota = elegir(entrada, clinica.getMascotas(), "una mascota");
        String respuesta = leerTexto(entrada, "¿Dar de baja a " + mascota.getNombre()
                + "? (s/n): ");
        if (respuesta.equalsIgnoreCase("s")) {
            int canceladas = clinica.darDeBajaMascota(mascota);
            System.out.println("Mascota dada de baja. Citas pendientes canceladas: " + canceladas);
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    // ---------- Datos de demostración (ficticios) ----------

    private static void cargarDatosDemo(Clinica clinica) {
        Veterinario vet1 = new Veterinario("Laura", "Ríos", "5550000001", "laura@example.com",
                "CED-1001", "Medicina general");
        Veterinario vet2 = new Veterinario("Mario", "Soto", "5550000002", "mario@example.com",
                "CED-1002", "Cirugía");
        clinica.agregarVeterinario(vet1);
        clinica.agregarVeterinario(vet2);
        Propietario ana = new Propietario("Ana", "Pérez", "5550000003", "ana@example.com",
                "Calle Uno 10");
        Propietario luis = new Propietario("Luis", "Gómez", "5550000004", "luis@example.com",
                "Calle Dos 20");
        Mascota firulais = new Perro("Firulais", 3, 12.5, ana, "Labrador", "alto");
        Mascota michi = new Gato("Michi", 2, 4.2, ana, true);
        Mascota kiwi = new AveExotica("Kiwi", 1, 0.4, luis, 18.0);
        clinica.registrarMascota(firulais);
        clinica.registrarMascota(michi);
        clinica.registrarMascota(kiwi);
        cargarCitasDemo(clinica, vet1, vet2, firulais, michi, kiwi);
    }

    private static void cargarCitasDemo(Clinica clinica, Veterinario vet1, Veterinario vet2,
            Mascota firulais, Mascota michi, Mascota kiwi) {
        Consultorio c1 = clinica.getConsultorios().get(0);
        LocalDateTime hora = LocalDateTime.of(2026, 11, 5, 10, 0);
        Cita cita1 = new Cita(firulais, vet1, hora, new ConsultaGeneral(TARIFA_CONSULTA), c1);
        Cita cita2 = new Cita(michi, vet2, hora.plusHours(1),
                new Cirugia("alta", 1.5, TARIFA_QUIROFANO), c1);
        Cita cita3 = new Cita(kiwi, vet1, hora.plusHours(2),
                new Vacunacion(COSTO_VACUNA, TARIFA_APLICACION));
        clinica.agendarCita(cita1);
        clinica.agendarCita(cita2);
        clinica.agendarCita(cita3);
        cita1.atender();
        cita2.atender();
        cita1.generarComprobante();
        cita2.generarComprobante();
        System.out.println("Datos de demostración cargados (3 citas, 2 atendidas).");
    }
}
