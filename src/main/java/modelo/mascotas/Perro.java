package modelo.mascotas;

import modelo.personas.Propietario;

/**
 * Clase Perro. Es una mascota con raza y nivel de energía.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?: hereda de Mascota y sobrescribe hacerSonido().
 */
public class Perro extends Mascota {
    private String raza;
    private String nivelEnergia;

    /**
     * Constructor. Envía los datos comunes a Mascota con super(...) y guarda los propios
     * del perro.
     *
     * @param nombre nombre del perro
     * @param edad edad del perro
     * @param peso peso del perro
     * @param propietario dueño del perro
     * @param raza raza del perro
     * @param nivelEnergia nivel de energía (por ejemplo, alto, medio o bajo)
     */
    public Perro(String nombre, int edad, double peso, Propietario propietario, String raza,
                 String nivelEnergia) {
        super(nombre, edad, peso, propietario);
        this.raza = raza;
        this.nivelEnergia = nivelEnergia;
    }

    /** Obtiene la raza. */
    public String getRaza() {
        return raza;
    }

    /** Establece la raza. */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /** Obtiene el nivel de energía. */
    public String getNivelEnergia() {
        return nivelEnergia;
    }

    /** Establece el nivel de energía. */
    public void setNivelEnergia(String nivelEnergia) {
        this.nivelEnergia = nivelEnergia;
    }

    /**
     * Implementación de hacerSonido() para el perro (sobrescritura).
     *
     * @return sonido que emite el perro
     */
    @Override
    public String hacerSonido() {
        return null;
    }

    /**
     * Sobrescribe toString() para mostrar los datos del perro.
     *
     * @return cadena con los datos del perro
     */
    @Override
    public String toString() {
        return null;
    }
}
