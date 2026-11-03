package modelo.personas;

/**
 * Clase abstracta Persona. Agrupa los datos que comparten el dueño, el veterinario y el
 * recepcionista: nombre, apellido, teléfono y correo.
 *
 * Conceptos de POO: herencia y abstracción. No se pueden crear objetos de Persona; solo de
 * sus clases hijas, que además deben implementar describirRol().
 * Regla de negocio: ningún dato puede estar vacío, el teléfono tiene 10 dígitos y el
 * correo debe tener un formato válido (se lanza IllegalArgumentException si no).
 */
public abstract class Persona {
    // Atributos protected: las clases hijas pueden usarlos directamente
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String correo;

    /**
     * Constructor de Persona. Las clases hijas lo llaman con super(...). Valida cada dato
     * para que el objeto nazca siempre en un estado válido.
     *
     * @param nombre nombre de la persona
     * @param apellido apellido de la persona
     * @param telefono teléfono de contacto (10 dígitos)
     * @param correo correo electrónico
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public Persona(String nombre, String apellido, String telefono, String correo) {
        this.nombre = validarTexto(nombre, "nombre");
        this.apellido = validarTexto(apellido, "apellido");
        this.telefono = validarTelefono(telefono);
        this.correo = validarCorreo(correo);
    }

    /**
     * Valida que un texto no sea nulo ni esté vacío. Es protected y static para que las
     * clases hijas lo reutilicen con sus propios campos.
     *
     * @param valor texto que se revisa
     * @param campo nombre del campo, para armar el mensaje de error
     * @return el texto sin espacios sobrantes
     * @throws IllegalArgumentException si el texto es nulo o está vacío
     */
    protected static String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo '" + campo + "' no puede estar vacío.");
        }
        return valor.trim();
    }

    private static String validarTelefono(String telefono) {
        String limpio = validarTexto(telefono, "teléfono");
        if (!limpio.matches("\\d{10}")) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 10 dígitos.");
        }
        return limpio;
    }

    private static String validarCorreo(String correo) {
        String limpio = validarTexto(correo, "correo");
        if (!limpio.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            throw new IllegalArgumentException("El correo no tiene un formato válido.");
        }
        return limpio;
    }

    /**
     * Modifica el teléfono y el correo de la persona en una sola operación. Si alguno es
     * inválido no se cambia ninguno.
     *
     * @param telefono teléfono nuevo
     * @param correo correo nuevo
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public void actualizarContacto(String telefono, String correo) {
        String telefonoValido = validarTelefono(telefono);
        String correoValido = validarCorreo(correo);
        this.telefono = telefonoValido;
        this.correo = correoValido;
    }

    /** Obtiene el nombre. */
    public String getNombre() {
        return nombre;
    }

    /** Establece el nombre (no puede estar vacío). */
    public void setNombre(String nombre) {
        this.nombre = validarTexto(nombre, "nombre");
    }

    /** Obtiene el apellido. */
    public String getApellido() {
        return apellido;
    }

    /** Establece el apellido (no puede estar vacío). */
    public void setApellido(String apellido) {
        this.apellido = validarTexto(apellido, "apellido");
    }

    /** Obtiene el teléfono. */
    public String getTelefono() {
        return telefono;
    }

    /** Establece el teléfono (10 dígitos). */
    public void setTelefono(String telefono) {
        this.telefono = validarTelefono(telefono);
    }

    /** Obtiene el correo electrónico. */
    public String getCorreo() {
        return correo;
    }

    /** Establece el correo electrónico (formato válido). */
    public void setCorreo(String correo) {
        this.correo = validarCorreo(correo);
    }

    /**
     * Obtiene el nombre y el apellido juntos.
     *
     * @return nombre completo
     */
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    /**
     * Método abstracto: cada clase hija lo implementa a su manera para explicar qué papel
     * tiene en la clínica (polimorfismo por sobrescritura).
     *
     * @return texto con el rol de la persona
     */
    public abstract String describirRol();
}
