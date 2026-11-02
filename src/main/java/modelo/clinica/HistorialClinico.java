package modelo.clinica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase HistorialClinico. Guarda las citas atendidas de una mascota.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?: Mediante la composición con Mascota. El historial no tiene sentido sin su mascota,
 * así que se elimina junto con ella.
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
     * porque cambian los parámetros.
     *
     * @param citas citas que ya forman parte del historial
     */
    public HistorialClinico(List<Cita> citas) {
        this.citas = citas;
    }

    /** Obtiene la lista de citas. */
    public List<Cita> getCitas() {
        return citas;
    }

    /**
     * Agrega una cita atendida al historial.
     *
     * @param cita cita que ya fue atendida
     */
    public void registrarAtencion(Cita cita) {
    }

    /**
     * Consulta las citas guardadas en el historial.
     *
     * @return lista de citas atendidas
     */
    public List<Cita> listarCitas() {
        return null;
    }
}
