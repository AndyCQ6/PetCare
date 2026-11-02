package modelo.servicios;

import java.math.BigDecimal;

/**
 * Clase ConsultaGeneral. Es el servicio de consulta veterinaria; su costo es la tarifa base.
 *
 * Conceptos de POO: hereda de Servicio e implementa la interfaz Facturable.
 * Regla de negocio relacionada: regla 5, la tarifa y el costo no pueden ser negativos.
 */
public class ConsultaGeneral extends Servicio implements Facturable {
    private BigDecimal tarifaBase;

    /**
     * Constructor. Envía el nombre fijo del servicio a Servicio con super(...) y guarda la
     * tarifa.
     *
     * @param tarifaBase tarifa de la consulta (no negativa)
     */
    public ConsultaGeneral(BigDecimal tarifaBase) {
        super("Consulta general");
        this.tarifaBase = tarifaBase;
    }

    /** Obtiene la tarifa base. */
    public BigDecimal getTarifaBase() {
        return tarifaBase;
    }

    /** Establece la tarifa base; no puede ser negativa (regla 5). */
    public void setTarifaBase(BigDecimal tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    /**
     * Implementación de calcularCosto() de Facturable. Aquí el costo es la tarifa base.
     *
     * @return costo de la consulta
     */
    @Override
    public BigDecimal calcularCosto() {
        return null;
    }

    /**
     * Sobrescribe toString() para mostrar los datos de la consulta.
     *
     * @return cadena con los datos de la consulta
     */
    @Override
    public String toString() {
        return null;
    }
}
