/**
 * Item consumible que restaura estadísticas del personaje.
 * Utiliza un Enum para definir si afecta a la Vida o al Maná.
 *
 * @author Javier Fernández Gavino
 * @version 1.1
 */
public class Pocion extends Item {
    /**
     * Cantidad de puntos que restaura la poción.
     */
    private double cantidad;

    /**
     * Tipo de poción (VIDA o MANA).
     */
    private TipoPocion tipo; // VIDA o MANA

    /**
     * Crea una poción.
     * @param nombre Nombre (ej: "Poción Menor").
     * @param cantidad Puntos que restaura.
     * @param tipo El tipo de estadística que restaura (TipoPocion.VIDA o TipoPocion.MANA).
     */
    public Pocion(String nombre, double cantidad, TipoPocion tipo) {
        super(nombre);
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    /**
     * Usa la poción sobre un personaje.
     * @return true si la poción tuvo efecto (y debe gastarse), false si no.
     */
    @Override
    public boolean usarItem(Personaje objetivo) {
        switch (tipo) {
            case VIDA:
                double vidaAntes = objetivo.getPuntosVida();
                objetivo.setPuntosVida(vidaAntes + cantidad);

                // Cartel de Sanación
                System.out.println("\n   ➕ [ CURACIÓN ]");
                System.out.printf("   %s usa %s%n", objetivo.getNombre(), getNombre());
                System.out.printf("   ❤️  Salud: %.1f ⮕ %.1f%n", vidaAntes, objetivo.getPuntosVida());
                return true;

            case MANA:
                if (objetivo instanceof Mago) {
                    Mago m = (Mago) objetivo;
                    m.recuperarMana(cantidad);

                    // Cartel de Maná
                    System.out.println("\n   ✨ [ RESTAURACIÓN ]");
                    System.out.printf("   %s bebe %s%n", objetivo.getNombre(), getNombre());
                    System.out.printf("   🔷 Maná restaurado: +%.0f puntos%n", (double)cantidad);
                    return true;
                } else {
                    // Cartel de Error de Clase
                    System.out.println("\n   ❌ [ USO FALLIDO ]");
                    System.out.printf("   %s no puede procesar el maná (Clase incompatible).%n", objetivo.getNombre());
                    return false;
                }

            default:
                return false;
        }
    }

    /**
     * Representación en cadena de la poción.
     * @return Nombre y efecto de la poción.
     */
    @Override
    public String toString() {
        return getNombre() + " (+" + (int) cantidad + " " + tipo + ")";
    }
}