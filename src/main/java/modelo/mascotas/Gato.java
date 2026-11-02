package modelo.mascotas;

import modelo.personas.Propietario;

/**
 * Clase Gato. Es una mascota que además indica si es doméstico o no.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?: hereda de Mascota y sobrescribe hacerSonido().
 */
public class Gato extends Mascota {
    private boolean domestico;

    /**
     * Constructor. Envía los datos comunes a Mascota con super(...) y guarda si el gato es
     * doméstico.
     *
     * @param nombre nombre del gato
     * @param edad edad del gato
     * @param peso peso del gato
     * @param propietario dueño del gato
     * @param domestico true si el gato es doméstico, false en caso contrario
     */
    public Gato(String nombre, int edad, double peso, Propietario propietario, boolean domestico) {
        super(nombre, edad, peso, propietario);
        this.domestico = domestico;
    }

    /**
     * Consulta si el gato es doméstico. Se llama isDomestico porque es un atributo boolean.
     *
     * @return true si es doméstico
     */
    public boolean isDomestico() {
        return domestico;
    }

    /** Establece si el gato es doméstico. */
    public void setDomestico(boolean domestico) {
        this.domestico = domestico;
    }

    /**
     * Implementación de hacerSonido() para el gato (sobrescritura).
     *
     * @return sonido que emite el gato
     */
    @Override
    public String hacerSonido() {
        return null;
    }

    /**
     * Sobrescribe toString() para mostrar los datos del gato.
     *
     * @return cadena con los datos del gato
     */
    @Override
    public String toString() {
        return null;
    }
}
