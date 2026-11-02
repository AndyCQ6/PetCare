package modelo.personas;

import modelo.clinica.Cita;

/**
 * Clase Recepcionista. Es el personal administrativo que se encarga de registrar citas.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?: hereda de Persona y usa la clase Cita (dependencia).
 * Reglas de negocio relacionadas:
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
     */
    public Recepcionista(String nombre, String apellido, String telefono, String correo, String turno) {
        super(nombre, apellido, telefono, correo);
        this.turno = turno;
    }

    /** Obtiene el turno de trabajo. */
    public String getTurno() {
        return turno;
    }

    /** Establece el turno de trabajo. */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Registra una cita en el sistema. Aquí deben respetarse las reglas 2 y 4.
     *
     * @param cita cita que se va a registrar
     */
    public void registrarCita(Cita cita) {
    }

    /**
     * Implementación de describirRol() de Persona (sobrescritura).
     *
     * @return texto que explica el rol del recepcionista
     */
    @Override
    public String describirRol() {
        return null;
    }
}
