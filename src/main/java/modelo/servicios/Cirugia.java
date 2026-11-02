package modelo.servicios;

import java.math.BigDecimal;

/**
 * Clase Cirugia. Es el servicio de cirugía; su costo depende de la complejidad y de las
 * horas de quirófano que se usen a cierta tarifa por hora.
 *
 * Conceptos de POO: hereda de Servicio e implementa la interfaz Facturable.
 * Regla de negocio relacionada: regla 5, la tarifa y el costo no pueden ser negativos.
 */
public class Cirugia extends Servicio implements Facturable {
    private String complejidad;
    private double horasQuirofano;
    private BigDecimal tarifaPorHora;

    /**
     * Constructor. Envía el nombre fijo del servicio a Servicio con super(...) y guarda los
     * datos que se usan para el cobro.
     *
     * @param complejidad nivel de complejidad de la cirugía
     * @param horasQuirofano horas que se ocupa el quirófano
     * @param tarifaPorHora tarifa por cada hora (no negativa)
     */
    public Cirugia(String complejidad, double horasQuirofano, BigDecimal tarifaPorHora) {
        super("Cirugía");
        this.complejidad = complejidad;
        this.horasQuirofano = horasQuirofano;
        this.tarifaPorHora = tarifaPorHora;
    }

    /** Obtiene la complejidad. */
    public String getComplejidad() {
        return complejidad;
    }

    /** Establece la complejidad. */
    public void setComplejidad(String complejidad) {
        this.complejidad = complejidad;
    }

    /** Obtiene las horas de quirófano. */
    public double getHorasQuirofano() {
        return horasQuirofano;
    }

    /** Establece las horas de quirófano. */
    public void setHorasQuirofano(double horasQuirofano) {
        this.horasQuirofano = horasQuirofano;
    }

    /** Obtiene la tarifa por hora. */
    public BigDecimal getTarifaPorHora() {
        return tarifaPorHora;
    }

    /** Establece la tarifa por hora; no puede ser negativa (regla 5). */
    public void setTarifaPorHora(BigDecimal tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    /**
     * Implementación de calcularCosto() de Facturable. El costo se obtiene con las horas de
     * quirófano, la tarifa por hora y la complejidad.
     *
     * @return costo de la cirugía
     */

    @Override
    public BigDecimal calcularCosto() {
        return null;
    }

    /**
     * Sobrescribe toString() para mostrar los datos de la cirugía.
     *
     * @return cadena con los datos de la cirugía
     */

    @Override
    public String toString() {
        return null;
    }
}
