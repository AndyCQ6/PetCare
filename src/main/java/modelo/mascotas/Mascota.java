package modelo.mascotas;

import modelo.clinica.HistorialClinico;
import modelo.personas.Propietario;

/**
 * Clase abstracta Mascota. Contiene los datos que comparten todas las mascotas de la
 * clínica: nombre, edad, peso, dueño e historial clínico.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?:
 * - Herencia: Perro, Gato y AveExotica son sus clases hijas.
 * - Polimorfismo: cada hija implementa hacerSonido() a su manera.
 * - Composición: el historial clínico se crea con la mascota y desaparece con ella.
 * Reglas de negocio relacionadas:
 * - Regla 1: la edad y el peso deben ser mayores que cero.
 * - Regla 6: los cobros de la mascota se consultan desde Clinica.
 */
public abstract class Mascota {
    // protected: las clases hijas pueden usar el nombre directamente
    protected String nombre;
    // Los demás atributos son private (encapsulamiento): se usan con getters y setters
    private int edad;
    private double peso;
    // Asociación: cada mascota conoce a su dueño
    private Propietario propietario;
    private HistorialClinico historial;

    /**
     * Constructor de Mascota. Las clases hijas lo llaman con super(...). También crea el
     * historial clínico de la mascota.
     *
     * @param nombre nombre de la mascota
     * @param edad edad de la mascota (mayor que cero)
     * @param peso peso de la mascota (mayor que cero)
     * @param propietario dueño responsable de la mascota
     */
    public Mascota(String nombre, int edad, double peso, Propietario propietario) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.propietario = propietario;
        // Composición: el historial se crea aquí y pertenece solo a esta mascota
        this.historial = new HistorialClinico();
    }

    /** Obtiene el nombre. */
    public String getNombre() {
        return nombre;
    }

    /** Establece el nombre. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** Obtiene la edad. */
    public int getEdad() {
        return edad;
    }

    /** Establece la edad; debe ser mayor que cero (regla 1). */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /** Obtiene el peso. */
    public double getPeso() {
        return peso;
    }

    /** Establece el peso; debe ser mayor que cero (regla 1). */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /** Obtiene el dueño de la mascota. */
    public Propietario getDueno() {
        return propietario;
    }

    /** Establece el dueño de la mascota. */
    public void setDueno(Propietario propietario) {
        this.propietario = propietario;
    }

    /** Obtiene el historial clínico de la mascota. */
    public HistorialClinico getHistorial() {
        return historial;
    }

    /**
     * Método abstracto: cada tipo de mascota define el sonido que emite (polimorfismo).
     *
     * @return sonido de la mascota
     */
    public abstract String hacerSonido();

    /**
     * Sobrescribe toString() de Object para mostrar los datos básicos de la mascota.
     *
     * @return cadena con los datos de la mascota
     */
    @Override
    public String toString() {
        return null;
    }
}
