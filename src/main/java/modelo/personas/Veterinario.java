package modelo.personas;

/**
 * Clase Veterinario. Es el profesional que atiende las citas de las mascotas.
 *
 * Conceptos de POO: hereda de Persona. Existe de forma independiente a la clínica, por eso
 * la relación con Clinica es una agregación (puede trabajar en otra clínica).
 * Reglas de negocio relacionadas:
 * - Regla 2: no puede tener dos citas programadas a la misma hora.
 * - Regla 6: sus cobros deben poder consultarse.
 */
public class Veterinario extends Persona {
    private String cedulaProfesional;
    private String especialidad;

    /**
     * Constructor. Envía los datos comunes a Persona con super(...) y guarda la cédula y
     * la especialidad.
     *
     * @param nombre nombre del veterinario
     * @param apellido apellido del veterinario
     * @param telefono teléfono de contacto
     * @param correo correo electrónico
     * @param cedulaProfesional número de cédula profesional
     * @param especialidad área en la que se especializa
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public Veterinario(String nombre, String apellido, String telefono, String correo,
            String cedulaProfesional, String especialidad) {
        super(nombre, apellido, telefono, correo);
        this.cedulaProfesional = validarTexto(cedulaProfesional, "cédula profesional");
        this.especialidad = validarTexto(especialidad, "especialidad");
    }

    /** Obtiene la cédula profesional. */
    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    /** Establece la cédula profesional (no puede estar vacía). */
    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = validarTexto(cedulaProfesional, "cédula profesional");
    }

    /** Obtiene la especialidad. */
    public String getEspecialidad() {
        return especialidad;
    }

    /** Establece la especialidad (no puede estar vacía). */
    public void setEspecialidad(String especialidad) {
        this.especialidad = validarTexto(especialidad, "especialidad");
    }

    /**
     * Implementación de describirRol() de Persona (sobrescritura).
     *
     * @return texto que explica el rol del veterinario
     */
    @Override
    public String describirRol() {
        return "Veterinario de " + especialidad + " (cédula " + cedulaProfesional + ")";
    }

    /**
     * Sobrescribe toString() de Object para mostrar los datos del veterinario.
     *
     * @return cadena con los datos del veterinario
     */
    @Override
    public String toString() {
        return "Dr(a). " + getNombreCompleto() + " - " + especialidad;
    }
}
