package modelo.mascotas;

import modelo.clinica.HistorialClinico;
import modelo.personas.Propietario;

/**
 * Clase abstracta Mascota. Contiene los datos que comparten todas las mascotas de la
 * clínica: nombre, edad, peso, dueño e historial clínico.
 *
 * Conceptos de POO:
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
     * Constructor de Mascota. Las clases hijas lo llaman con super(...). Valida los datos
     * y crea el historial clínico de la mascota.
     *
     * @param nombre nombre de la mascota
     * @param edad edad de la mascota (mayor que cero)
     * @param peso peso de la mascota (mayor que cero)
     * @param propietario dueño responsable de la mascota
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public Mascota(String nombre, int edad, double peso, Propietario propietario) {
        this.nombre = validarTexto(nombre, "nombre");
        this.edad = validarEdad(edad);
        this.peso = validarPeso(peso);
        this.propietario = validarPropietario(propietario);
        // Composición: el historial se crea aquí y pertenece solo a esta mascota
        this.historial = new HistorialClinico();
    }

    /**
     * Valida que un texto no sea nulo ni esté vacío. Las clases hijas lo reutilizan.
     *
     * @param valor texto que se revisa
     * @param campo nombre del campo, para el mensaje de error
     * @return el texto sin espacios sobrantes
     * @throws IllegalArgumentException si el texto es nulo o está vacío
     */
    protected static String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo '" + campo + "' no puede estar vacío.");
        }
        return valor.trim();
    }

    private static int validarEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor que cero (regla 1).");
        }
        return edad;
    }

    private static double validarPeso(double peso) {
        if (peso <= 0 || Double.isNaN(peso)) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero (regla 1).");
        }
        return peso;
    }

    private static Propietario validarPropietario(Propietario propietario) {
        if (propietario == null) {
            throw new IllegalArgumentException("La mascota debe tener un propietario.");
        }
        return propietario;
    }

    /** Obtiene el nombre. */
    public String getNombre() {
        return nombre;
    }

    /** Establece el nombre (no puede estar vacío). */
    public void setNombre(String nombre) {
        this.nombre = validarTexto(nombre, "nombre");
    }

    /** Obtiene la edad. */
    public int getEdad() {
        return edad;
    }

    /** Establece la edad; debe ser mayor que cero (regla 1). */
    public void setEdad(int edad) {
        this.edad = validarEdad(edad);
    }

    /** Obtiene el peso. */
    public double getPeso() {
        return peso;
    }

    /** Establece el peso; debe ser mayor que cero (regla 1). */
    public void setPeso(double peso) {
        this.peso = validarPeso(peso);
    }

    /** Obtiene el dueño de la mascota. */
    public Propietario getDueno() {
        return propietario;
    }

    /**
     * Cambia el dueño de la mascota. Si la mascota ya estaba en la lista del dueño
     * anterior, la mueve a la lista del nuevo para que ambos lados sigan coherentes.
     *
     * @param nuevoPropietario nuevo dueño (no puede ser nulo)
     */
    public void setDueno(Propietario nuevoPropietario) {
        Propietario validado = validarPropietario(nuevoPropietario);
        if (propietario.getMascotas().contains(this)) {
            propietario.quitarMascota(this);
            validado.agregarMascota(this);
        }
        this.propietario = validado;
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
        return String.format("%s %s (%d año(s), %.1f kg) - dueño: %s",
                getClass().getSimpleName(), nombre, edad, peso, propietario.getNombreCompleto());
    }
}
