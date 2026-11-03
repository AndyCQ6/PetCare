package modelo.mascotas;

import modelo.personas.Propietario;

/**
 * Clase AveExotica. Es una mascota con una longitud de alas.
 *
 * Conceptos de POO: hereda de Mascota y sobrescribe hacerSonido().
 */
public class AveExotica extends Mascota {
    private double longitudAlas;

    /**
     * Constructor. Envía los datos comunes a Mascota con super(...) y guarda la longitud de
     * las alas.
     *
     * @param nombre nombre del ave
     * @param edad edad del ave
     * @param peso peso del ave
     * @param propietario dueño del ave
     * @param longitudAlas longitud de las alas en centímetros (mayor que cero)
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public AveExotica(String nombre, int edad, double peso, Propietario propietario,
            double longitudAlas) {
        super(nombre, edad, peso, propietario);
        this.longitudAlas = validarLongitud(longitudAlas);
    }

    private static double validarLongitud(double longitud) {
        if (longitud <= 0 || Double.isNaN(longitud)) {
            throw new IllegalArgumentException("La longitud de alas debe ser mayor que cero.");
        }
        return longitud;
    }

    /** Obtiene la longitud de las alas. */
    public double getLongitudAlas() {
        return longitudAlas;
    }

    /** Establece la longitud de las alas (mayor que cero). */
    public void setLongitudAlas(double longitudAlas) {
        this.longitudAlas = validarLongitud(longitudAlas);
    }

    /**
     * Implementación de hacerSonido() para el ave (sobrescritura).
     *
     * @return sonido que emite el ave
     */
    @Override
    public String hacerSonido() {
        return "Pío pío";
    }

    /**
     * Sobrescribe toString() para mostrar los datos del ave.
     *
     * @return cadena con los datos del ave
     */
    @Override
    public String toString() {
        return super.toString() + String.format(", alas de %.1f cm", longitudAlas);
    }
}
