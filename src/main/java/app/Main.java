package app;

import java.util.Scanner;
import modelo.clinica.Clinica;

public class Main {

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
        System.out.print("Elige una opción: ");
    }

    public static void main(String[] argumentos) {
        Scanner entrada = new Scanner(System.in);
        Clinica clinica = new Clinica("PetCare Coatzacoalcos");
        int opcion;

        do {
            mostrarMenu();

            // Lectura sencilla de enteros sin validaciones avanzadas
            opcion = entrada.nextInt();
            entrada.nextLine(); // Limpiar el salto de línea pendiente

            // Evaluar opción
            switch (opcion) {
                case 1:
                    // Por hacer: Registrar dueño y mascota
                    break;
                case 2:
                    // Por hacer: Registrar veterinario
                    break;
                case 3:
                    // Por hacer: Agendar una cita
                    break;
                case 4:
                    // Por hacer: Atender o cancelar una cita
                    break;
                case 5:
                    // Por hacer: Generar comprobante
                    break;
                case 6:
                    // Por hacer: Consultar historial clínico
                    break;
                case 7:
                    // Por hacer: Listar mascotas y polimorfismo
                    break;
                case 8:
                    // Por hacer: Ver total cobrado
                    break;
                case 9:
                    // Por hacer: Dar de baja una mascota
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, Solo puede elegir números entre el 0 y el 9.");
                    break;
            }

        } while (opcion != 0);

        entrada.close(); // Buena práctica
    }
}