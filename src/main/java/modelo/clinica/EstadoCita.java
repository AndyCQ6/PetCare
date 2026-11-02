package modelo.clinica;

/**
 * Enumeración EstadoCita. Define los únicos valores que puede tener el estado de una cita,
 * lo que evita usar textos sueltos como "atendida" o "cancelada".
 */
public enum EstadoCita {
    // Estado inicial: la cita ya se agendó pero todavía no ocurre
    PROGRAMADA,
    // La cita se realizó
    ATENDIDA,
    // La cita se canceló; ya no puede pasar a ATENDIDA (regla 3)
    CANCELADA
}
