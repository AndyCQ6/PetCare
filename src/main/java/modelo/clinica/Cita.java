package modelo.clinica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import modelo.comprobante.Comprobante;
import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Servicio;

/**
 * Clase Cita. Une a una mascota con un veterinario en una fecha y hora, dentro de un
 * consultorio y para un servicio.
 *
 * Conceptos de POO: asociación con Mascota, Veterinario, Consultorio, Servicio y
 * Comprobante. La clase coordina a todos estos objetos.
 * Reglas de negocio relacionadas:
 * - Regla 2: el veterinario no puede tener dos citas programadas a la misma hora.
 * - Regla 3: una cita cancelada no puede pasar a atendida.
 * - Regla 4: el consultorio no puede usarse en dos citas al mismo tiempo.
 * - Regla 6: los cobros se consultan por mascota y por veterinario.
 * Las reglas 2 y 4 necesitan conocer todas las citas, por eso las valida Clinica; los
 * setters que podrían saltárselas son de paquete (solo Clinica puede llamarlos).
 */
public class Cita {
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String citaId;
    private LocalDateTime fechaHora;
    // Toma sus valores de la enumeración EstadoCita; cambia con atender() y cancelar()
    private EstadoCita estado;
    private Mascota mascota;
    private Veterinario veterinario;
    private Consultorio consultorio;
    private Servicio servicio;
    private String observaciones;
    private Comprobante comprobante;

    /**
     * Constructor sin consultorio. Llama al otro constructor con this(...) y envía null en
     * el consultorio, que se podrá asignar después.
     *
     * @param mascota mascota que será atendida
     * @param veterinario veterinario responsable
     * @param fechaHora fecha y hora de la cita
     * @param servicio servicio que se solicita
     * @throws IllegalArgumentException si falta algún dato obligatorio
     */
    public Cita(Mascota mascota, Veterinario veterinario, LocalDateTime fechaHora,
            Servicio servicio) {
        this(mascota, veterinario, fechaHora, servicio, null);
    }

    /**
     * Constructor completo. Es una sobrecarga del anterior: recibe también el consultorio.
     * Toda cita nueva empieza con el estado PROGRAMADA.
     *
     * @param mascota mascota que será atendida
     * @param veterinario veterinario responsable
     * @param fechaHora fecha y hora de la cita
     * @param servicio servicio que se solicita
     * @param consultorio consultorio asignado, o {@code null} si todavía no tiene
     * @throws IllegalArgumentException si falta algún dato obligatorio
     */
    public Cita(Mascota mascota, Veterinario veterinario, LocalDateTime fechaHora,
            Servicio servicio, Consultorio consultorio) {
        this.mascota = requerir(mascota, "mascota");
        this.veterinario = requerir(veterinario, "veterinario");
        this.fechaHora = requerir(fechaHora, "fecha y hora");
        this.servicio = requerir(servicio, "servicio");
        this.consultorio = consultorio;
        this.estado = EstadoCita.PROGRAMADA;
    }

    // Revisa que un dato obligatorio no sea null; <T> permite usarlo con cualquier tipo
    private static <T> T requerir(T valor, String campo) {
        if (valor == null) {
            throw new IllegalArgumentException("El dato '" + campo + "' es obligatorio.");
        }
        return valor;
    }

    /** Obtiene el citaId; es {@code null} si aún no se asigna. */
    public String getCitaId() {
        return citaId;
    }

    /** Establece el citaId (lo asigna la Clinica al agendar la cita). */
    public void setCitaId(String citaId) {
        this.citaId = citaId;
    }

    /** Obtiene la fecha y hora de la cita. */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /** Establece la fecha y hora; es de paquete porque cambiarla afecta la regla 2 y 4. */
    void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = requerir(fechaHora, "fecha y hora");
    }

    /** Obtiene el estado actual; no tiene setter porque cambia con atender() y cancelar(). */
    public EstadoCita getEstado() {
        return estado;
    }

    /** Obtiene la mascota de la cita. */
    public Mascota getMascota() {
        return mascota;
    }

    /** Establece la mascota de la cita. */
    public void setMascota(Mascota mascota) {
        this.mascota = requerir(mascota, "mascota");
    }

    /** Obtiene el veterinario de la cita. */
    public Veterinario getVeterinario() {
        return veterinario;
    }

    /** Establece el veterinario; es de paquete porque cambiarlo afecta la regla 2. */
    void setVeterinario(Veterinario veterinario) {
        this.veterinario = requerir(veterinario, "veterinario");
    }

    /** Obtiene el consultorio; es {@code null} si todavía no tiene uno asignado. */
    public Consultorio getConsultorio() {
        return consultorio;
    }

    /** Establece el consultorio; es de paquete porque cambiarlo afecta la regla 4. */
    void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    /** Obtiene el servicio de la cita. */
    public Servicio getServicio() {
        return servicio;
    }

    /** Establece el servicio de la cita. */
    public void setServicio(Servicio servicio) {
        this.servicio = requerir(servicio, "servicio");
    }

    /** Obtiene las observaciones; es {@code null} si no se han registrado. */
    public String getObservaciones() {
        return observaciones;
    }

    /** Establece las observaciones de la cita. */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /** Obtiene el comprobante; es {@code null} si todavía no se genera. */
    public Comprobante getComprobante() {
        return comprobante;
    }

    /**
     * Asigna un consultorio a la cita. Es de paquete: la regla 4 (consultorio libre en ese
     * horario) la revisa Clinica.asignarConsultorio(), que es quien conoce todas las citas.
     *
     * @param consultorio consultorio que se quiere asignar
     * @throws IllegalArgumentException si el consultorio es nulo
     * @throws IllegalStateException si la cita ya no está programada
     */
    void asignarConsultorio(Consultorio consultorio) {
        requerir(consultorio, "consultorio");
        if (estado != EstadoCita.PROGRAMADA) {
            throw new IllegalStateException(
                    "Solo una cita programada puede cambiar de consultorio.");
        }
        this.consultorio = consultorio;
    }

    /**
     * Cambia el estado a ATENDIDA. No debe permitirse si la cita está cancelada (regla 3).
     * Al atenderla se guarda en el historial clínico de la mascota.
     *
     * @throws IllegalStateException si la cita está cancelada o ya fue atendida
     */
    public void atender() {
        if (estado == EstadoCita.CANCELADA) {
            throw new IllegalStateException("Una cita cancelada no puede atenderse (regla 3).");
        }
        if (estado == EstadoCita.ATENDIDA) {
            throw new IllegalStateException("La cita ya fue atendida.");
        }
        estado = EstadoCita.ATENDIDA;
        mascota.getHistorial().registrarAtencion(this);
    }

    /**
     * Cambia el estado de la cita a CANCELADA.
     *
     * @throws IllegalStateException si la cita ya fue atendida o ya estaba cancelada
     */
    public void cancelar() {
        if (estado == EstadoCita.ATENDIDA) {
            throw new IllegalStateException("Una cita atendida ya no puede cancelarse.");
        }
        if (estado == EstadoCita.CANCELADA) {
            throw new IllegalStateException("La cita ya estaba cancelada.");
        }
        estado = EstadoCita.CANCELADA;
    }

    /**
     * Guarda lo que ocurrió durante la consulta.
     *
     * @param observaciones notas de la atención
     * @throws IllegalArgumentException si las notas están vacías
     */
    public void registrarObservaciones(String observaciones) {
        if (observaciones == null || observaciones.trim().isEmpty()) {
            throw new IllegalArgumentException("Las observaciones no pueden estar vacías.");
        }
        this.observaciones = observaciones.trim();
    }

    /**
     * Crea el comprobante de cobro de la cita, siempre que ya haya sido atendida. Si ya se
     * había generado, devuelve el mismo para no cobrar dos veces.
     *
     * @return comprobante generado
     * @throws IllegalStateException si la cita no está atendida
     */
    public Comprobante generarComprobante() {
        if (estado != EstadoCita.ATENDIDA) {
            throw new IllegalStateException("Solo una cita atendida puede generar comprobante.");
        }
        if (comprobante == null) {
            comprobante = new Comprobante(this);
        }
        return comprobante;
    }

    /**
     * Sobrescribe toString() para mostrar la cita como texto.
     *
     * @return cadena con los datos principales de la cita
     */
    @Override
    public String toString() {
        String sala = (consultorio == null) ? "sin consultorio" : consultorio.toString();
        return String.format("[%s] %s | %s con %s | %s | %s | %s", citaId,
                fechaHora.format(FORMATO), mascota.getNombre(), veterinario.getNombreCompleto(),
                servicio.getNombre(), sala, estado);
    }
}
