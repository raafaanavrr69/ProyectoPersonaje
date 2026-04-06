import java.util.ArrayList;

/**
 * Gestiona la colección de objetos que posee un personaje.
 * @author Javier Fernández Gavino
 * @version 1.0
 */
public class Inventario {
    /**
     * Lista de objetos en el inventario.
     */
    private ArrayList<Item> listaItems;

    /**
     * Crea un inventario vacío.
     */
    public Inventario() {
        this.listaItems = new ArrayList<>();
    }

    /**
     * Añade un objeto al final del inventario.
     * @param item El objeto a guardar.
     */
    public void anadirItem(Item item) {
        listaItems.add(item);
    }

    /**
     * Muestra por consola el contenido numerado del inventario.
     */
    public void mostrarContenido() {
        System.out.println("\n   ╔═══════════════════════════════════════╗");
        System.out.println("   ║           🎒 MI INVENTARIO            ║");
        System.out.println("   ╠═══════════════════════════════════════╣");

        if (listaItems.isEmpty()) {
            System.out.println("   ║        ( La mochila está vacía )      ║");
        } else {
            for (int i = 0; i < listaItems.size(); i++) {
                System.out.printf("   ║  %2d. %-32s ║%n", i + 1, listaItems.get(i).getNombre());
            }
        }
        System.out.println("   ╚═══════════════════════════════════════╝");
    }

    /**
     * Intenta usar el objeto en la posición indicada sobre el personaje.
     * Si el objeto reporta éxito (return true), se elimina de la lista.
     * Si falla, permanece en el inventario.
     * * @param indice Posición del objeto en la lista.
     * @param objetivo Personaje que usa el objeto.
     */
    public void usarYConsumir(int indice, Personaje objetivo) {
        if (indice >= 0 && indice < listaItems.size()) {
            Item item = listaItems.get(indice);
            boolean exito = item.usarItem(objetivo);

            if (exito) {
                listaItems.remove(indice);
            }
        } else {
            System.out.println("   [!] No existe ningún objeto en la posición " + indice);
        }
    }
}