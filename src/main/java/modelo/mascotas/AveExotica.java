package modelo.mascotas;

import modelo.personas.Propietario;

/**
 * Clase AveExotica. Es una mascota que además registra la longitud de sus alas.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?: hereda de Mascota y sobrescribe hacerSonido().
 */
public class AveExotica extends Mascota {
    private double longitudAlas;

    /**
     * Constructor. Envía los datos comunes a Mascota con super(...) y guarda la longitud
     * de las alas.
     *
     * @param nombre nombre del ave
     * @param edad edad del ave
     * @param peso peso del ave
     * @param propietario dueño del ave
     * @param longitudAlas longitud de las alas del ave
     */
    public AveExotica(String nombre, int edad, double peso, Propietario propietario, double longitudAlas) {
        super(nombre, edad, peso, propietario);
        this.longitudAlas = longitudAlas;
    }

    /** Obtiene la longitud de las alas. */
    public double getLongitudAlas() {
        return longitudAlas;
    }

    /** Establece la longitud de las alas. */
    public void setLongitudAlas(double longitudAlas) {
        this.longitudAlas = longitudAlas;
    }

    /**
     * Implementación de hacerSonido() para el ave (sobrescritura).
     *
     * @return sonido que emite el ave
     */
    @Override
    public String hacerSonido() {
        return null;
    }

    /**
     * Sobrescribe toString() para mostrar los datos del ave.
     *
     * @return cadena con los datos del ave
     */
    @Override
    public String toString() {
        return null;
    }
}
