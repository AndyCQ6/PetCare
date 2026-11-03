package modelo.personas;

import modelo.clinica.Cita;
import modelo.clinica.Clinica;

/**
 * Clase Recepcionista. Es el personal administrativo que se encarga de registrar citas.
 *
 * Conceptos de POO: hereda de Persona y usa las clases Clinica y Cita (dependencia).
 * Reglas de negocio relacionadas (las valida la Clinica, no la recepcionista):
 * - Regla 2: un veterinario no puede tener dos citas programadas a la misma hora.
 * - Regla 4: un consultorio no puede usarse en dos citas al mismo tiempo.
 */
public class Recepcionista extends Persona {
    private String turno;

    /**
     * Constructor. Envía los datos comunes a Persona con super(...) y guarda el turno.
     *
     * @param nombre nombre del recepcionista
     * @param apellido apellido del recepcionista
     * @param telefono teléfono de contacto
     * @param correo correo electrónico
     * @param turno turno en el que trabaja (por ejemplo, matutino o vespertino)
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public Recepcionista(String nombre, String apellido, String telefono, String correo,
            String turno) {
        super(nombre, apellido, telefono, correo);
        this.turno = validarTexto(turno, "turno");
    }

    /** Obtiene el turno de trabajo. */
    public String getTurno() {
        return turno;
    }

    /** Establece el turno de trabajo (no puede estar vacío). */
    public void setTurno(String turno) {
        this.turno = validarTexto(turno, "turno");
    }

    /**
     * Registra una cita en la clínica. La recepcionista no valida nada por su cuenta: le
     * pasa la cita a la Clinica, que es quien conoce todas las citas y aplica las reglas 2
     * y 4.
     *
     * @param clinica clínica donde se registra la cita
     * @param cita cita que se va a registrar
     * @throws IllegalArgumentException si la clínica es nula o la cita no es válida
     * @throws IllegalStateException si se viola una regla de agenda
     */
    public void registrarCita(Clinica clinica, Cita cita) {
        if (clinica == null) {
            throw new IllegalArgumentException("La clínica no puede ser nula.");
        }
        clinica.agendarCita(cita);
    }

    /**
     * Implementación de describirRol() de Persona (sobrescritura).
     *
     * @return texto que explica el rol del recepcionista
     */
    @Override
    public String describirRol() {
        return "Recepcionista del turno " + turno + ": registra las citas";
    }
}
