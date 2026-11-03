package modelo.comprobante;

import java.math.BigDecimal;

import modelo.clinica.Cita;
import modelo.clinica.EstadoCita;

/**
 * Clase Comprobante. Es el documento de cobro que se genera cuando se atiende una cita.
 *
 * Conceptos de POO: asociación con Cita y un miembro static (contador de folios). Se usa
 * BigDecimal para el total porque representa dinero con mayor precisión que double.
 * Reglas de negocio relacionadas:
 * - Regla 5: el total no puede ser negativo.
 * - Regla 6: el cobro debe poder consultarse por mascota y por veterinario.
 */
public class Comprobante {
    // static: pertenece a la clase, no a un objeto; así los folios son consecutivos
    private static int contadorFolios = 0;

    private String folio;
    private BigDecimal total;
    private Cita cita;

    /**
     * Constructor. Recibe la cita atendida que se va a cobrar, asigna el siguiente folio y
     * calcula el total con el servicio de la cita (polimorfismo: no importa si es consulta,
     * vacunación o cirugía).
     *
     * @param cita cita atendida a la que pertenece el comprobante
     * @throws IllegalArgumentException si la cita es nula
     * @throws IllegalStateException si la cita todavía no está atendida
     */
    public Comprobante(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita del comprobante no puede ser nula.");
        }
        if (cita.getEstado() != EstadoCita.ATENDIDA) {
            throw new IllegalStateException("Solo una cita atendida puede generar comprobante.");
        }
        this.cita = cita;
        contadorFolios++;
        this.folio = String.format("COMP-%04d", contadorFolios);
        this.total = cita.getServicio().calcularCosto();
    }

    /** Obtiene el folio del comprobante. */
    public String getFolio() {
        return folio;
    }

    /** Establece el folio del comprobante (no puede estar vacío). */
    public void setFolio(String folio) {
        if (folio == null || folio.trim().isEmpty()) {
            throw new IllegalArgumentException("El folio no puede estar vacío.");
        }
        this.folio = folio.trim();
    }

    /** Obtiene el total cobrado. */
    public BigDecimal getTotal() {
        return total;
    }

    /** Establece el total cobrado; no puede ser negativo (regla 5). */
    public void setTotal(BigDecimal total) {
        if (total == null || total.signum() < 0) {
            throw new IllegalArgumentException("El total no puede ser nulo ni negativo.");
        }
        this.total = total;
    }

    /** Obtiene la cita del comprobante. */
    public Cita getCita() {
        return cita;
    }

    /** Establece la cita del comprobante (no puede ser nula). */
    public void setCita(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita del comprobante no puede ser nula.");
        }
        this.cita = cita;
    }

    /**
     * Arma el texto del comprobante para mostrarlo en consola.
     *
     * @return texto del comprobante
     */
    public String imprimir() {
        return String.join("\n",
                "--------- COMPROBANTE " + folio + " ---------",
                "Cita:        " + cita.getCitaId(),
                "Mascota:     " + cita.getMascota().getNombre(),
                "Veterinario: " + cita.getVeterinario().getNombreCompleto(),
                "Servicio:    " + cita.getServicio().getNombre(),
                "TOTAL:       $" + total,
                "-----------------------------------");
    }
}
