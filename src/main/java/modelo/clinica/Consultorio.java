package modelo.clinica;

/**
 * Clase Consultorio. Representa un espacio numerado de la clínica donde se atiende a las
 * mascotas.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?: Mediante la composición con Clinica. El consultorio pertenece a la clínica y, si la
 * clínica desaparece, también el consultorio.
 * Regla de negocio relacionada: regla 4, no puede usarse en dos citas al mismo tiempo.
 */
public class Consultorio {
    private int numero;

    /**
     * Constructor. Guarda el número que identifica al consultorio.
     *
     * @param numero número del consultorio
     */
    public Consultorio(int numero) {
        this.numero = numero;
    }

    /** Obtiene el número del consultorio. */
    public int getNumero() {
        return numero;
    }

    /** Establece el número del consultorio. */
    public void setNumero(int numero) {
        this.numero = numero;
    }
}
