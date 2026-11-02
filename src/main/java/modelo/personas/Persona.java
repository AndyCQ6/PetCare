package modelo.personas;

/**
 * Clase abstracta Persona. Agrupa los datos que comparten el dueño, el veterinario y el
 * recepcionista: nombre, apellido, teléfono y correo.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?: Mediante la herencia y abstracción. No se pueden crear objetos de Persona; solo de
 * sus clases hijas, que además deben implementar describirRol().
 */
public abstract class Persona {
    // Atributos protected: las clases hijas pueden usarlos directamente
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String correo;

    /**
     * Constructor de Persona. Las clases hijas lo llaman con super(...).
     *
     * @param nombre nombre de la persona
     * @param apellido apellido de la persona
     * @param telefono teléfono de contacto
     * @param correo correo electrónico
     */
    public Persona(String nombre, String apellido, String telefono, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    /**
     * Modifica el teléfono y el correo de la persona en una sola operación.
     *
     * @param telefono teléfono nuevo
     * @param correo correo nuevo
     */
    public void actualizarContacto(String telefono, String correo) {
        this.telefono = telefono;
        this.correo = correo;
    }

    /** Obtiene el nombre. */
    public String getNombre() {
        return nombre;
    }

    /** Establece el nombre. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** Obtiene el apellido. */
    public String getApellido() {
        return apellido;
    }

    /** Establece el apellido. */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /** Obtiene el teléfono. */
    public String getTelefono() {
        return telefono;
    }

    /** Establece el teléfono. */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /** Obtiene el correo electrónico. */
    public String getCorreo() {
        return correo;
    }

    /** Establece el correo electrónico. */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Método abstracto: cada clase hija lo implementa a su manera para explicar qué papel
     * tiene en la clínica (polimorfismo por sobrescritura).
     *
     * @return texto con el rol de la persona
     */
    public abstract String describirRol();
}
