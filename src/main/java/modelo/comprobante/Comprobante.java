package modelo.comprobante;

import java.math.BigDecimal;

import modelo.clinica.Cita;

/**
 * Clase Comprobante. Es el documento de cobro que se genera cuando se atiende una cita.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?: Mediante la asociación con Cita. Se usa BigDecimal para el total porque representa
 * dinero con mayor precisión que double.
 * Reglas de negocio relacionadas:
 * - Regla 5: el total no puede ser negativo.
 * - Regla 6: el cobro debe poder consultarse por mascota y por veterinario.
 */
public class Comprobante {
    private String folio;
    private BigDecimal total;
    private Cita cita;

    /**
     * Constructor. Recibe la cita que se va a cobrar; el folio y el total se establecen
     * después con sus setters.
     *
     * @param cita cita a la que pertenece el comprobante
     */
    public Comprobante(Cita cita) {
        this.cita = cita;
    }

    /** Obtiene el folio del comprobante. */
    public String getFolio() {
        return folio;
    }

    /** Establece el folio del comprobante. */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /** Obtiene el total cobrado. */
    public BigDecimal getTotal() {
        return total;
    }

    /** Establece el total cobrado; no puede ser negativo (regla 5). */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /** Obtiene la cita del comprobante. */
    public Cita getCita() {
        return cita;
    }

    /** Establece la cita del comprobante. */
    public void setCita(Cita cita) {
        this.cita = cita;
    }

    /**
     * Arma el texto del comprobante para mostrarlo en consola.
     *
     * @return texto del comprobante
     */
    public String imprimir() {
        return null;
    }
}
