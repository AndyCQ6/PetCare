package modelo.servicios;

import java.math.BigDecimal;

/**
 * Clase abstracta Servicio. Es la clase padre de los servicios que ofrece la clínica
 * (ConsultaGeneral, Vacunacion y Cirugia). Solo guarda el nombre, que es lo común.
 *
 * Conceptos de POO: herencia (segunda jerarquía del proyecto, junto con Persona y Mascota).
 * Servicio implementa la interfaz Facturable sin definir calcularCosto(): al ser abstracta,
 * obliga a cada hija a implementarlo. Así una Cita que guarda un Servicio puede calcular su
 * costo con polimorfismo, sin preguntar de qué tipo es.
 * Regla de negocio relacionada: regla 5, ningún costo puede ser negativo.
 */
public abstract class Servicio implements Facturable {
    // protected: las clases hijas pueden usar el nombre directamente
    protected String nombre;

    /**
     * Constructor de Servicio. Las clases hijas lo llaman con super(...) enviando el nombre
     * del servicio.
     *
     * @param nombre nombre del servicio
     * @throws IllegalArgumentException si el nombre es nulo o está vacío
     */
    public Servicio(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del servicio no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Valida que un monto exista y no sea negativo (regla 5). Las hijas lo reutilizan.
     *
     * @param valor monto que se revisa
     * @param campo nombre del campo, para el mensaje de error
     * @return el mismo monto, ya validado
     * @throws IllegalArgumentException si el monto es nulo o negativo
     */
    protected static BigDecimal validarMonto(BigDecimal valor, String campo) {
        if (valor == null) {
            throw new IllegalArgumentException("El campo '" + campo + "' es obligatorio.");
        }
        if (valor.signum() < 0) {
            throw new IllegalArgumentException("El campo '" + campo + "' no puede ser negativo.");
        }
        return valor;
    }

    /** Obtiene el nombre del servicio. */
    public String getNombre() {
        return nombre;
    }

    /** Establece el nombre del servicio (no puede estar vacío). */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del servicio no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }
}
