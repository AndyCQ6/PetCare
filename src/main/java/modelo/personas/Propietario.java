package modelo.personas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modelo.mascotas.Mascota;

/**
 * Clase Propietario. Es la persona responsable de una o varias mascotas.
 *
 * NOTA: Decidí cambiar el nombre "dueño" por el de propietario, con el fin de evitar el
 * uso de caracteres especiales del español, como la letra eñe.
 *
 * Conceptos de POO: hereda de Persona y se asocia con Mascota (un dueño puede tener muchas).
 * La lista de mascotas se devuelve como vista no modificable para que nadie la altere
 * desde afuera.
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
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public Propietario(String nombre, String apellido, String telefono, String correo,
                       String direccion) {
        super(nombre, apellido, telefono, correo);
        this.mascotas = new ArrayList<>();
        this.direccion = validarTexto(direccion, "dirección");
    }

    /** Obtiene la dirección del dueño. */
    public String getDireccion() {
        return direccion;
    }

    /** Establece la dirección del dueño (no puede estar vacía). */
    public void setDireccion(String direccion) {
        this.direccion = validarTexto(direccion, "dirección");
    }

    /** Obtiene la lista de mascotas del dueño (vista no modificable). */
    public List<Mascota> getMascotas() {
        return Collections.unmodifiableList(mascotas);
    }

    /**
     * Añade una mascota a la lista del dueño. Si ya estaba, no la duplica.
     *
     * @param mascota mascota que se quiere registrar con este dueño
     * @throws IllegalArgumentException si la mascota es nula
     */
    public void agregarMascota(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula.");
        }
        if (!mascotas.contains(mascota)) {
            mascotas.add(mascota);
        }
    }

    /**
     * Quita una mascota de la lista del dueño (por ejemplo, al darla de baja).
     *
     * @param mascota mascota que se quiere quitar
     */
    public void quitarMascota(Mascota mascota) {
        mascotas.remove(mascota);
    }

    /**
     * Consulta las mascotas que pertenecen al dueño.
     *
     * @return lista no modificable de mascotas del dueño
     */
    public List<Mascota> listarMascotas() {
        return getMascotas();
    }

    /**
     * Implementación de describirRol() de Persona (sobrescritura).
     *
     * @return texto que explica el rol del dueño
     */
    @Override
    public String describirRol() {
        return "Propietario responsable de " + mascotas.size() + " mascota(s)";
    }

    /**
     * Sobrescribe toString() de Object para mostrar los datos del dueño como texto.
     *
     * @return cadena con los datos del dueño
     */
    @Override
    public String toString() {
        return getNombreCompleto() + " (Propietario) - Tel. " + telefono;
    }
}
