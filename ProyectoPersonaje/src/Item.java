/**
 * Clase abstracta que representa cualquier objeto que un personaje pueda poseer.
 * Define la estructura básica para armas y pociones.
 *
 * @author Javier Fernández Gavino
 * @version 1.0
 */
public abstract class Item {
    /**
     * Nombre identificativo del objeto.
     */
    private String nombre;

    /**
     * Constructor del ítem.
     * @param nombre El nombre identificativo del objeto.
     */
    public Item(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del objeto.
     * @return El nombre del objeto.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establece el nombre del objeto.
     * @param nombre El nuevo nombre del objeto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método abstracto que define el uso del objeto sobre un personaje.
     * @param objetivo El personaje sobre el que se usa el objeto.
     * @return true si el objeto fue consumido o equipado con éxito; false si no tuvo efecto.
     */
    public abstract boolean usarItem(Personaje objetivo);

    /**
     * Representación en cadena del objeto.
     * @return El nombre del objeto.
     */
    @Override
    public String toString() {
        return nombre;
    }
}