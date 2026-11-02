package modelo.clinica;

import java.time.LocalDateTime;
import modelo.comprobante.Comprobante;
import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Servicio;

/**
 * Clase Cita. Une a una mascota con un veterinario en una fecha y hora, dentro de un
 * consultorio y para un servicio.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?: Mediante la asociación con Mascota, Veterinario, Consultorio, Servicio y
 * Comprobante. La clase coordina a todos estos objetos.
 * Reglas de negocio relacionadas:
 * - Regla 2: el veterinario no puede tener dos citas programadas a la misma hora.
 * - Regla 3: una cita cancelada no puede pasar a atendida.
 * - Regla 4: el consultorio no puede usarse en dos citas al mismo tiempo.
 * - Regla 6: los cobros se consultan por mascota y por veterinario.
 */

public class Cita {
    private String citaId;
    private LocalDateTime fechaHora;
    // Toma sus valores de la enumeración EstadoCita
    private final EstadoCita estado;
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
     */
    public Cita(Mascota mascota, Veterinario veterinario, LocalDateTime fechaHora,
            Servicio servicio, Consultorio consultorio) {
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fechaHora = fechaHora;
        this.servicio = servicio;
        this.consultorio = consultorio;
        this.estado = EstadoCita.PROGRAMADA;
    }

    /** Obtiene el citaId; es {@code null} si aún no se asigna. */
    public String getCitaId() {
        return citaId;
    }

    /** Establece el citaId. */
    public void setCitaId(String citaId) {
        this.citaId = citaId;
    }

    /** Obtiene la fecha y hora de la cita. */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /** Establece la fecha y hora de la cita. */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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
        this.mascota = mascota;
    }

    /** Obtiene el veterinario de la cita. */
    public Veterinario getVeterinario() {
        return veterinario;
    }

    /** Establece el veterinario de la cita. */
    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    /** Obtiene el consultorio; es {@code null} si todavía no tiene uno asignado. */
    public Consultorio getConsultorio() {
        return consultorio;
    }

    /** Establece el consultorio de la cita. */
    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    /** Obtiene el servicio de la cita. */
    public Servicio getServicio() {
        return servicio;
    }

    /** Establece el servicio de la cita. */
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
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
     * Asigna un consultorio a la cita. Debe revisarse que esté libre en ese horario
     * (regla 4).
     *
     * @param consultorio consultorio que se quiere asignar
     */
    public void asignarConsultorio(Consultorio consultorio) {
    }

    /**
     * Cambia el estado a ATENDIDA. No debe permitirse si la cita está cancelada (regla 3).
     */
    public void atender() {
    }

    /** Cambia el estado de la cita a CANCELADA. */
    public void cancelar() {
    }

    /**
     * Guarda lo que ocurrió durante la consulta.
     *
     * @param observaciones notas de la atención
     */
    public void registrarObservaciones(String observaciones) {
    }

    /**
     * Crea el comprobante de cobro de la cita, siempre que ya haya sido atendida.
     *
     * @return comprobante generado
     */
    public Comprobante generarComprobante() {
        return null;
    }
}
