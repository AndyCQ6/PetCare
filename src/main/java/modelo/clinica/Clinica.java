package modelo.clinica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modelo.comprobante.Comprobante;
import modelo.mascotas.Mascota;
import modelo.personas.Propietario;
import modelo.personas.Veterinario;

/**
 * Clase Clinica. Es la clase que organiza los consultorios, los veterinarios, las mascotas
 * y las citas. Es el único lugar que conoce todas las citas, por eso aquí viven las reglas
 * de agenda.
 *
 * Relaciones entre clases:
 * - Composición con Consultorio: los consultorios se crean dentro de la clínica y
 *   desaparecen con ella.
 * - Agregación con Veterinario: los veterinarios existen aunque la clínica cierre.
 * Reglas de negocio relacionadas:
 * - Regla 2: un veterinario no puede tener dos citas programadas a la misma hora.
 * - Regla 4: un consultorio no puede usarse en dos citas al mismo tiempo.
 * - Regla 6: los cobros se consultan por mascota y por veterinario.
 */
public class Clinica {
    private String nombre;
    private List<Consultorio> consultorios;
    private List<Veterinario> veterinarios;
    private List<Cita> citas;
    private List<Mascota> mascotas;
    private int ultimoFolioCita;

    /**
     * Constructor. Guarda el nombre e inicia las listas vacías.
     *
     * @param nombre nombre de la clínica
     * @throws IllegalArgumentException si el nombre está vacío
     */
    public Clinica(String nombre) {
        this.nombre = validarNombre(nombre);
        this.consultorios = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.mascotas = new ArrayList<>();
        this.ultimoFolioCita = 0;
    }

    private static String validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la clínica no puede estar vacío.");
        }
        return nombre.trim();
    }

    /** Obtiene el nombre de la clínica. */
    public String getNombre() {
        return nombre;
    }

    /** Establece el nombre de la clínica (no puede estar vacío). */
    public void setNombre(String nombre) {
        this.nombre = validarNombre(nombre);
    }

    /** Obtiene la lista de consultorios (vista no modificable). */
    public List<Consultorio> getConsultorios() {
        return Collections.unmodifiableList(consultorios);
    }

    /** Obtiene la lista de veterinarios (vista no modificable). */
    public List<Veterinario> getVeterinarios() {
        return Collections.unmodifiableList(veterinarios);
    }

    /** Obtiene la lista de citas registradas (vista no modificable). */
    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }

    /** Obtiene la lista de mascotas registradas (vista no modificable). */
    public List<Mascota> getMascotas() {
        return Collections.unmodifiableList(mascotas);
    }

    /**
     * Obtiene los propietarios de las mascotas registradas, sin repetirlos.
     *
     * @return lista de propietarios
     */
    public List<Propietario> getPropietarios() {
        List<Propietario> propietarios = new ArrayList<>();
        for (Mascota mascota : mascotas) {
            if (!propietarios.contains(mascota.getDueno())) {
                propietarios.add(mascota.getDueno());
            }
        }
        return propietarios;
    }

    /**
     * Crea un consultorio dentro de la clínica (composición: se instancia aquí y no desde
     * afuera).
     *
     * @param numero número que tendrá el consultorio
     * @return el consultorio creado
     * @throws IllegalArgumentException si el número es inválido o ya existe
     */
    public Consultorio crearConsultorio(int numero) {
        for (Consultorio existente : consultorios) {
            if (existente.getNumero() == numero) {
                throw new IllegalArgumentException("Ya existe el consultorio " + numero + ".");
            }
        }
        Consultorio nuevo = new Consultorio(numero);
        consultorios.add(nuevo);
        return nuevo;
    }

    /**
     * Agrega un veterinario que ya existe al equipo de la clínica (agregación).
     *
     * @param veterinario veterinario que se va a incorporar
     * @throws IllegalArgumentException si es nulo o ya forma parte del equipo
     */
    public void agregarVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("El veterinario no puede ser nulo.");
        }
        for (Veterinario existente : veterinarios) {
            boolean mismaCedula = existente.getCedulaProfesional()
                    .equalsIgnoreCase(veterinario.getCedulaProfesional());
            if (existente == veterinario || mismaCedula) {
                throw new IllegalArgumentException("Ese veterinario ya está en la clínica.");
            }
        }
        veterinarios.add(veterinario);
    }

    /**
     * Registra una mascota en la clínica y la anota en la lista de su propietario.
     *
     * @param mascota mascota que se registra
     * @throws IllegalArgumentException si es nula o ya está registrada
     */
    public void registrarMascota(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula.");
        }
        if (mascotas.contains(mascota)) {
            throw new IllegalArgumentException("Esa mascota ya está registrada.");
        }
        mascotas.add(mascota);
        mascota.getDueno().agregarMascota(mascota);
    }

    /**
     * Da de baja una mascota: cancela sus citas pendientes y la saca de la clínica y de la
     * lista de su dueño. Las citas ya atendidas se conservan porque sus comprobantes son
     * parte de la contabilidad; por eso la mascota sigue referenciada desde esas citas.
     *
     * @param mascota mascota que se da de baja
     * @return cuántas citas pendientes se cancelaron
     * @throws IllegalArgumentException si la mascota no está registrada
     */
    public int darDeBajaMascota(Mascota mascota) {
        if (!mascotas.contains(mascota)) {
            throw new IllegalArgumentException("Esa mascota no está registrada en la clínica.");
        }
        int canceladas = 0;
        for (Cita cita : citas) {
            if (cita.getMascota() == mascota && cita.getEstado() == EstadoCita.PROGRAMADA) {
                cita.cancelar();
                canceladas++;
            }
        }
        mascotas.remove(mascota);
        mascota.getDueno().quitarMascota(mascota);
        return canceladas;
    }

    /**
     * Agenda una cita solo si se cumplen las reglas 2 y 4.
     *
     * @param cita cita que se quiere agendar
     * @throws IllegalArgumentException si la cita, el veterinario, la mascota o el
     *         consultorio no pertenecen a la clínica
     * @throws IllegalStateException si el veterinario o el consultorio están ocupados
     */
    public void agendarCita(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede ser nula.");
        }
        if (citas.contains(cita)) {
            throw new IllegalStateException("Esa cita ya fue agendada.");
        }
        if (!veterinarios.contains(cita.getVeterinario())) {
            throw new IllegalArgumentException("El veterinario no pertenece a esta clínica.");
        }
        if (!mascotas.contains(cita.getMascota())) {
            throw new IllegalArgumentException("La mascota no está registrada en la clínica.");
        }
        if (!validarDisponibilidad(cita.getVeterinario(), cita.getFechaHora())) {
            throw new IllegalStateException(
                    "El veterinario ya tiene una cita a esa hora (regla 2).");
        }
        if (cita.getConsultorio() != null) {
            verificarConsultorio(cita.getConsultorio(), cita.getFechaHora(), null);
        }
        ultimoFolioCita++;
        cita.setCitaId(String.format("CITA-%04d", ultimoFolioCita));
        citas.add(cita);
    }

    /**
     * Asigna un consultorio a una cita ya agendada, respetando la regla 4.
     *
     * @param cita cita agendada en esta clínica
     * @param consultorio consultorio de esta clínica
     * @throws IllegalArgumentException si la cita o el consultorio no pertenecen a la clínica
     * @throws IllegalStateException si el consultorio está ocupado a esa hora
     */
    public void asignarConsultorio(Cita cita, Consultorio consultorio) {
        if (cita == null || !citas.contains(cita)) {
            throw new IllegalArgumentException("La cita no está agendada en esta clínica.");
        }
        verificarConsultorio(consultorio, cita.getFechaHora(), cita);
        cita.asignarConsultorio(consultorio);
    }

    // Comprueba que el consultorio sea de la clínica y esté libre (regla 4).
    // "excluir" es la cita que se está reasignando, para que no choque consigo misma.
    private void verificarConsultorio(Consultorio consultorio, LocalDateTime fechaHora,
            Cita excluir) {
        if (consultorio == null || !consultorios.contains(consultorio)) {
            throw new IllegalArgumentException("El consultorio no pertenece a esta clínica.");
        }
        for (Cita otra : citas) {
            boolean mismoLugar = otra.getConsultorio() == consultorio;
            boolean mismaHora = otra.getFechaHora().equals(fechaHora);
            boolean activa = otra.getEstado() != EstadoCita.CANCELADA;
            if (otra != excluir && mismoLugar && mismaHora && activa) {
                throw new IllegalStateException(
                        "El consultorio está ocupado a esa hora (regla 4).");
            }
        }
    }

    /**
     * Busca una cita por su folio. Está sobrecargado: existe otra versión que busca por
     * fecha.
     *
     * @param folio folio de la cita
     * @return la cita encontrada, o {@code null} si no existe
     */
    public Cita buscarCita(String folio) {
        for (Cita cita : citas) {
            if (cita.getCitaId() != null && cita.getCitaId().equalsIgnoreCase(folio)) {
                return cita;
            }
        }
        return null;
    }

    /**
     * Busca todas las citas de un día. Es la sobrecarga de buscarCita(String): cambia el
     * tipo del parámetro y también lo que regresa.
     *
     * @param fecha día que se quiere consultar
     * @return citas que tienen esa fecha
     */
    public List<Cita> buscarCita(LocalDate fecha) {
        List<Cita> encontradas = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getFechaHora().toLocalDate().equals(fecha)) {
                encontradas.add(cita);
            }
        }
        return encontradas;
    }

    /**
     * Revisa si el veterinario no tiene otra cita a esa hora (regla 2). Las citas
     * canceladas no ocupan horario.
     *
     * @param veterinario veterinario que se quiere revisar
     * @param fechaHora fecha y hora que se propone
     * @return {@code true} si está libre en ese horario
     */
    public boolean validarDisponibilidad(Veterinario veterinario, LocalDateTime fechaHora) {
        for (Cita cita : citas) {
            boolean mismoVeterinario = cita.getVeterinario() == veterinario;
            boolean mismaHora = cita.getFechaHora().equals(fechaHora);
            if (mismoVeterinario && mismaHora && cita.getEstado() != EstadoCita.CANCELADA) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula cuánto ha cobrado un veterinario (regla 6).
     *
     * @param veterinario veterinario que se quiere consultar
     * @return suma de los cobros de ese veterinario
     */
    public BigDecimal consultarTotalCobradoPorVeterinario(Veterinario veterinario) {
        BigDecimal total = BigDecimal.ZERO;
        for (Cita cita : citas) {
            Comprobante comprobante = cita.getComprobante();
            if (cita.getVeterinario() == veterinario && comprobante != null) {
                total = total.add(comprobante.getTotal());
            }
        }
        return total;
    }

    /**
     * Obtiene los comprobantes de una mascota (regla 6).
     *
     * @param mascota mascota que se quiere consultar
     * @return lista de comprobantes de esa mascota
     */
    public List<Comprobante> consultarCobrosPorMascota(Mascota mascota) {
        List<Comprobante> cobros = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getMascota() == mascota && cita.getComprobante() != null) {
                cobros.add(cita.getComprobante());
            }
        }
        return cobros;
    }
}
