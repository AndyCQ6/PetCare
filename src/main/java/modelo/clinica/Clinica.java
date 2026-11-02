package modelo.clinica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.comprobante.Comprobante;
import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;

/**
 * Clase Clinica. Es la clase que organiza los consultorios, los veterinarios y las citas.
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

    /**
     * Constructor. Guarda el nombre e inicia las tres listas vacías.
     *
     * @param nombre nombre de la clínica
     */
    public Clinica(String nombre) {
        this.nombre = nombre;
        this.consultorios = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    /** Obtiene el nombre de la clínica. */
    public String getNombre() {
        return nombre;
    }

    /** Establece el nombre de la clínica. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** Obtiene la lista de consultorios. */
    public List<Consultorio> getConsultorios() {
        return consultorios;
    }

    /** Obtiene la lista de veterinarios. */
    public List<Veterinario> getVeterinarios() {
        return veterinarios;
    }

    /** Obtiene la lista de citas registradas. */
    public List<Cita> getCitas() {
        return citas;
    }

    /**
     * Crea un consultorio dentro de la clínica (composición: se instancia aquí y no desde
     * afuera).
     *
     * @param numero número que tendrá el consultorio
     * @return el consultorio creado
     */
    public Consultorio crearConsultorio(int numero) {
        return null;
    }

    /**
     * Agrega un veterinario que ya existe al equipo de la clínica (agregación).
     *
     * @param veterinario veterinario que se va a incorporar
     */
    public void agregarVeterinario(Veterinario veterinario) {
    }

    /**
     * Agenda una cita solo si se cumplen las reglas 2 y 4.
     *
     * @param cita cita que se quiere agendar
     */
    public void agendarCita(Cita cita) {
    }

    /**
     * Busca una cita por su folio. Está sobrecargado: existe otra versión que busca por
     * fecha.
     *
     * @param folio folio de la cita
     * @return la cita encontrada, o {@code null} si no existe
     */
    public Cita buscarCita(String folio) {
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
        return null;
    }

    /**
     * Revisa si el veterinario no tiene otra cita a esa hora (regla 2).
     *
     * @param veterinario veterinario que se quiere revisar
     * @param fechaHora fecha y hora que se propone
     * @return {@code true} si está libre en ese horario
     */
    public boolean validarDisponibilidad(Veterinario veterinario, LocalDateTime fechaHora) {
        return false;
    }

    /**
     * Calcula cuánto ha cobrado un veterinario (regla 6).
     *
     * @param veterinario veterinario que se quiere consultar
     * @return suma de los cobros de ese veterinario
     */
    public BigDecimal consultarTotalCobradoPorVeterinario(Veterinario veterinario) {
        return null;
    }

    /**
     * Obtiene los comprobantes de una mascota (regla 6).
     *
     * @param mascota mascota que se quiere consultar
     * @return lista de comprobantes de esa mascota
     */
    public List<Comprobante> consultarCobrosPorMascota(Mascota mascota) {
        return null;
    }
}
