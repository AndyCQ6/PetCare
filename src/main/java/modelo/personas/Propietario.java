package modelo.personas;

import java.util.ArrayList;
import java.util.List;

import modelo.mascotas.Mascota;

/**
 * Clase Propietario. Es la persona responsable de una o varias mascotas.
 *
 * NOTA: Decidí cambiar el nombre "dueño" por el de propietario, con el fin de evitar el uso de caracteres especiales del español, como la letra eñe.
 *
 * ¿Cómo se relaciona con la programación orientada a objetos?: hereda de Persona y se asocia con Mascota (un dueño puede tener muchas).
 */
public class Propietario extends Persona {
    private String direccion;
    // Lista de mascotas del dueño; se crea vacía en el constructor
    private List<Mascota> mascotas;

    /**
     * Constructor. Los datos comunes se envían a Persona con super(...) y los propios del
     * dueño se guardan aquí. Inicia sin mascotas registradas.
     *
     * @param nombre nombre del dueño
     * @param apellido apellido del dueño
     * @param telefono teléfono de contacto
     * @param correo correo electrónico
     * @param direccion domicilio del dueño
     */
    public Propietario(String nombre, String apellido, String telefono, String correo,
                       String direccion) {
        super(nombre, apellido, telefono, correo);
        this.mascotas = new ArrayList<>();
        this.direccion = direccion;
    }

    /** Obtiene la dirección del dueño. */
    public String getDireccion() {
        return direccion;
    }

    /** Establece la dirección del dueño. */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /** Obtiene la lista de mascotas del dueño. */
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    /**
     * Añade una mascota a la lista del dueño.
     * @param mascota mascota que se quiere registrar con este dueño
     */
    public void agregarMascota(Mascota mascota) {
    }

    /**
     * Consulta las mascotas que pertenecen al dueño.
     *
     * @return lista de mascotas del dueño
     */
    public List<Mascota> listarMascotas() {
        return null;
    }

    /**
     * Implementación de describirRol() de Persona (sobrescritura).
     *
     * @return texto que explica el rol del dueño
     */
    @Override
    public String describirRol() {
        return null;
    }

    /**
     * Sobrescribe toString() de Object para mostrar los datos del dueño como texto.
     *
     * @return cadena con los datos del dueño
     */
    @Override
    public String toString() {
        return null;
    }
}
