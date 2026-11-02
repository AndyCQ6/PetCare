package modelo.servicios;

import java.math.BigDecimal;

/**
 * Interfaz Facturable. Es un contrato: toda clase que la implemente debe poder calcular
 * su costo. Así Clinica o Cita pueden pedir el costo de cualquier servicio sin saber de
 * cuál se trata (polimorfismo).
 *
 * Regla de negocio relacionada: regla 5, el costo no puede ser negativo.
 */
public interface Facturable {
    /**
     * Calcula el costo del servicio. Cada clase que implemente la interfaz lo hace a su
     * manera.
     *
     * @return costo del servicio (nunca debe ser negativo)
     */
    BigDecimal calcularCosto();
}
