package modelo.servicios;

/**
 * Clase abstracta Servicio. Es la clase padre de los servicios que ofrece la clínica
 * (ConsultaGeneral, Vacunacion y Cirugia). Solo guarda el nombre, que es lo común.
 *
 * Concepto de POO: herencia (segunda jerarquía del proyecto, junto con Persona y Mascota).
 * El cálculo del costo se declara en la interfaz Facturable, que implementan las hijas.
 * Regla de negocio relacionada: regla 5, ningún costo puede ser negativo.
 */
public abstract class Servicio {
    // protected: las clases hijas pueden usar el nombre directamente
    protected String nombre;

    /**
     * Constructor de Servicio. Las clases hijas lo llaman con super(...) enviando el nombre
     * del servicio.
     *
     * @param nombre nombre del servicio
     */
    public Servicio(String nombre) {
        this.nombre = nombre;
    }

    /** Obtiene el nombre del servicio. */
    public String getNombre() {
        return nombre;
    }

    /** Establece el nombre del servicio. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
