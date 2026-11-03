package modelo.clinica;

/**
 * Clase Consultorio. Representa un espacio numerado de la clínica donde se atiende a las
 * mascotas.
 *
 * Conceptos de POO: composición con Clinica. El consultorio pertenece a la clínica y, si la
 * clínica desaparece, también el consultorio. Por eso su constructor es de paquete (sin
 * public): solo Clinica, que está en el mismo paquete, puede crearlo.
 * Regla de negocio relacionada: regla 4, no puede usarse en dos citas al mismo tiempo.
 */
public class Consultorio {
    private int numero;

    /**
     * Constructor de acceso de paquete. Guarda el número que identifica al consultorio.
     *
     * @param numero número del consultorio (mayor que cero)
     * @throws IllegalArgumentException si el número no es mayor que cero
     */
    Consultorio(int numero) {
        this.numero = validarNumero(numero);
    }

    private static int validarNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de consultorio debe ser mayor que 0.");
        }
        return numero;
    }

    /** Obtiene el número del consultorio. */
    public int getNumero() {
        return numero;
    }

    /** Establece el número del consultorio (mayor que cero). */
    public void setNumero(int numero) {
        this.numero = validarNumero(numero);
    }

    /**
     * Sobrescribe toString() para mostrar el consultorio como texto.
     *
     * @return cadena con el número del consultorio
     */
    @Override
    public String toString() {
        return "Consultorio " + numero;
    }
}
