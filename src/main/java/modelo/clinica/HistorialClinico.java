package modelo.clinica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase HistorialClinico. Guarda las citas atendidas de una mascota.
 *
 * Conceptos de POO: composición con Mascota. El historial no tiene sentido sin su mascota,
 * así que se elimina junto con ella. Para no romper esa idea, nunca entrega su lista
 * interna: solo vistas no modificables.
 */
public class HistorialClinico {
    private List<Cita> citas;

    /**
     * Constructor por defecto. Crea un historial vacío reutilizando el otro constructor
     * mediante this(...).
     */
    public HistorialClinico() {
        this(new ArrayList<>());
    }

    /**
     * Constructor que recibe las citas. Es una sobrecarga del constructor anterior
     * porque cambian los parámetros. Guarda una copia para no compartir la lista de afuera.
     *
     * @param citas citas que ya forman parte del historial
     * @throws IllegalArgumentException si la lista es nula
     */
    public HistorialClinico(List<Cita> citas) {
        if (citas == null) {
            throw new IllegalArgumentException("La lista de citas no puede ser nula.");
        }
        this.citas = new ArrayList<>(citas);
    }

    /** Obtiene las citas del historial (vista no modificable). */
    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }

    /**
     * Registra una cita atendida en el historial. Si ya estaba registrada, no la duplica.
     *
     * @param cita cita atendida que se agrega
     * @throws IllegalArgumentException si la cita es nula
     * @throws IllegalStateException si la cita no está atendida
     */
    public void registrarAtencion(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede ser nula.");
        }
        if (cita.getEstado() != EstadoCita.ATENDIDA) {
            throw new IllegalStateException("Solo las citas atendidas entran al historial.");
        }
        if (!citas.contains(cita)) {
            citas.add(cita);
        }
    }

    /**
     * Consulta las citas atendidas de la mascota.
     *
     * @return lista no modificable de citas
     */
    public List<Cita> listarCitas() {
        return getCitas();
    }
}
