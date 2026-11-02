package modelo.servicios;

import java.math.BigDecimal;

/**
 * Clase Vacunacion. Es el servicio de vacunación; su costo es el precio de la vacuna más
 * la tarifa por aplicarla.
 *
 * Conceptos de POO: hereda de Servicio e implementa la interfaz Facturable.
 * Regla de negocio relacionada: regla 5, ni los costos ni el total pueden ser negativos.
 */
public class Vacunacion extends Servicio implements Facturable {
    private BigDecimal costoVacuna;
    private BigDecimal tarifaAplicacion;

    /**
     * Constructor. Envía el nombre fijo del servicio a Servicio con super(...) y guarda los
     * dos costos.
     *
     * @param costoVacuna precio de la vacuna (no negativo)
     * @param tarifaAplicacion tarifa por aplicar la vacuna (no negativa)
     */
    public Vacunacion(BigDecimal costoVacuna, BigDecimal tarifaAplicacion) {
        super("Vacunación");
        this.costoVacuna = costoVacuna;
        this.tarifaAplicacion = tarifaAplicacion;
    }

    /** Obtiene el costo de la vacuna. */
    public BigDecimal getCostoVacuna() {
        return costoVacuna;
    }

    /** Establece el costo de la vacuna; no puede ser negativo (regla 5). */
    public void setCostoVacuna(BigDecimal costoVacuna) {
        this.costoVacuna = costoVacuna;
    }

    /** Obtiene la tarifa de aplicación. */
    public BigDecimal getTarifaAplicacion() {
        return tarifaAplicacion;
    }

    /** Establece la tarifa de aplicación; no puede ser negativa (regla 5). */
    public void setTarifaAplicacion(BigDecimal tarifaAplicacion) {
        this.tarifaAplicacion = tarifaAplicacion;
    }

    /**
     * Implementación de calcularCosto() de Facturable. El costo es la suma de la vacuna y
     * su aplicación.
     *
     * @return costo total de la vacunación
     */
    @Override
    public BigDecimal calcularCosto() {
        return null;
    }

    /**
     * Sobrescribe toString() para mostrar los datos de la vacunación.
     *
     * @return cadena con los datos de la vacunación
     */
    @Override
    public String toString() {
        return null;
    }
}
