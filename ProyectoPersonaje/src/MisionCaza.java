/**
 * Clase MisionCaza que representa una misión de caza.
 * Hereda de la clase Mision.
 *
 * @author Juan María Alanís Rodríguez
 * @version 1.0
 */
public class MisionCaza extends Mision {

    // Atributos
    /**
     * Nombre del enemigo que debe ser cazado para completar la misión.
     */
    private Enemigo enemigo;
    // Constructores
    /**
     * Crea una misión de caza con los detalles proporcionados.
     *
     * @param titulo    Título de la misión.
     * @param desc      Descripción de la misión.
     * @param cantidad  Cantidad de enemigos a cazar.
     * @param enemigo   Nombre del enemigo a cazar.
     * @param xp        Experiencia otorgada al completar la misión.
     * @param item      Objeto otorgado como recompensa al completar la misión.
     */
    public MisionCaza(String titulo, String desc, int cantidad, Enemigo enemigo, int xp, Item item, Sala sala) {
        super(titulo, desc, cantidad, xp, item, sala);
        this.enemigo = enemigo;
    }

    // Getters
    public Enemigo getEnemigo() {
        return this.enemigo;
    }

    // Setters
    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
    }

    // Métodos
    /**
     * Actualiza el progreso de la misión al cazar un enemigo.
     *
     * @param datos Nombre del enemigo cazado o instancia de Enemigo.
     */
    @Override
    public void actualizarProgreso(Object datos) {
        // Verificamos si es un Enemigo y si se llama igual que el objetivo
        if (datos instanceof Enemigo) {
            Enemigo enemigoCazado = (Enemigo) datos;
            if (enemigoCazado.getNombre().equalsIgnoreCase(this.enemigo.getNombre())) {
                setProgresoActual(getProgresoActual() + 1);
            }
        }
    }
}
