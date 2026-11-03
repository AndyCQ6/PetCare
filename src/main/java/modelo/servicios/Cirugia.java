package modelo.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
     * @param complejidad nivel de complejidad de la cirugía: baja, media o alta
     * @param horasQuirofano horas que se ocupa el quirófano (mayor que cero)
     * @param tarifaPorHora tarifa por cada hora (no negativa)
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public Cirugia(String complejidad, double horasQuirofano, BigDecimal tarifaPorHora) {
        super("Cirugía");
        this.complejidad = validarComplejidad(complejidad);
        this.horasQuirofano = validarHoras(horasQuirofano);
        this.tarifaPorHora = validarMonto(tarifaPorHora, "tarifa por hora");
    }

    private static String validarComplejidad(String complejidad) {
        if (complejidad == null) {
            throw new IllegalArgumentException("La complejidad es obligatoria.");
        }
        String limpia = complejidad.trim().toLowerCase();
        if (!limpia.equals("baja") && !limpia.equals("media") && !limpia.equals("alta")) {
            throw new IllegalArgumentException("La complejidad debe ser baja, media o alta.");
        }
        return limpia;
    }

    private static double validarHoras(double horas) {
        if (horas <= 0 || Double.isNaN(horas)) {
            throw new IllegalArgumentException("Las horas de quirófano deben ser mayores que 0.");
        }
        return horas;
    }

    /** Obtiene la complejidad. */
    public String getComplejidad() {
        return complejidad;
    }

    /** Establece la complejidad (baja, media o alta). */
    public void setComplejidad(String complejidad) {
        this.complejidad = validarComplejidad(complejidad);
    }

    /** Obtiene las horas de quirófano. */
    public double getHorasQuirofano() {
        return horasQuirofano;
    }

    /** Establece las horas de quirófano (mayores que cero). */
    public void setHorasQuirofano(double horasQuirofano) {
        this.horasQuirofano = validarHoras(horasQuirofano);
    }

    /** Obtiene la tarifa por hora. */
    public BigDecimal getTarifaPorHora() {
        return tarifaPorHora;
    }

    /** Establece la tarifa por hora; no puede ser negativa (regla 5). */
    public void setTarifaPorHora(BigDecimal tarifaPorHora) {
        this.tarifaPorHora = validarMonto(tarifaPorHora, "tarifa por hora");
    }

    // La complejidad multiplica el costo base: baja x1.0, media x1.5, alta x2.0
    private BigDecimal factorComplejidad() {
        switch (complejidad) {
            case "media":
                return new BigDecimal("1.5");
            case "alta":
                return new BigDecimal("2.0");
            default:
                return BigDecimal.ONE;
        }
    }

    /**
     * Implementación de calcularCosto() de Facturable. El costo se obtiene con las horas de
     * quirófano, la tarifa por hora y la complejidad.
     *
     * @return costo de la cirugía
     */
    @Override
    public BigDecimal calcularCosto() {
        return tarifaPorHora
                .multiply(BigDecimal.valueOf(horasQuirofano))
                .multiply(factorComplejidad())
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Sobrescribe toString() para mostrar los datos de la cirugía.
     *
     * @return cadena con los datos de la cirugía
     */
    @Override
    public String toString() {
        return nombre + " de complejidad " + complejidad + " ($" + calcularCosto() + ")";
    }
}
