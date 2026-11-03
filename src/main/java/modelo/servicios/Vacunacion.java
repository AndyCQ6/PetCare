package modelo.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase Vacunacion. Es el servicio de vacunación; su costo suma la vacuna y la aplicación.
 *
 * Conceptos de POO: hereda de Servicio e implementa la interfaz Facturable.
 * Regla de negocio relacionada: regla 5, los montos y el costo no pueden ser negativos.
 */
public class Vacunacion extends Servicio implements Facturable {
    private BigDecimal costoVacuna;
    private BigDecimal tarifaAplicacion;

    /**
     * Constructor. Envía el nombre fijo del servicio a Servicio con super(...) y guarda los
     * montos que forman el cobro.
     *
     * @param costoVacuna precio de la vacuna (no negativo)
     * @param tarifaAplicacion tarifa por aplicarla (no negativa)
     * @throws IllegalArgumentException si algún monto es nulo o negativo
     */
    public Vacunacion(BigDecimal costoVacuna, BigDecimal tarifaAplicacion) {
        super("Vacunación");
        this.costoVacuna = validarMonto(costoVacuna, "costo de la vacuna");
        this.tarifaAplicacion = validarMonto(tarifaAplicacion, "tarifa de aplicación");
    }

    /** Obtiene el costo de la vacuna. */
    public BigDecimal getCostoVacuna() {
        return costoVacuna;
    }

    /** Establece el costo de la vacuna; no puede ser negativo (regla 5). */
    public void setCostoVacuna(BigDecimal costoVacuna) {
        this.costoVacuna = validarMonto(costoVacuna, "costo de la vacuna");
    }

    /** Obtiene la tarifa de aplicación. */
    public BigDecimal getTarifaAplicacion() {
        return tarifaAplicacion;
    }

    /** Establece la tarifa de aplicación; no puede ser negativa (regla 5). */
    public void setTarifaAplicacion(BigDecimal tarifaAplicacion) {
        this.tarifaAplicacion = validarMonto(tarifaAplicacion, "tarifa de aplicación");
    }

    /**
     * Implementación de calcularCosto() de Facturable. El costo es la vacuna más su
     * aplicación.
     *
     * @return costo de la vacunación
     */
    @Override
    public BigDecimal calcularCosto() {
        return costoVacuna.add(tarifaAplicacion).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Sobrescribe toString() para mostrar los datos de la vacunación.
     *
     * @return cadena con los datos de la vacunación
     */
    @Override
    public String toString() {
        return nombre + " ($" + calcularCosto() + ")";
    }
}
