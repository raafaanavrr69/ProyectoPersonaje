/**
 * Subclase de Personaje especializada en el uso de magia.
 * Utiliza 'Maná' como recurso para realizar ataques potentes.
 *
 * @author Javier Fernández Gavino
 * @version 1.0
 */
public class Mago extends Personaje {
    /**
     * Puntos de maná actuales del mago.
     */
    private double puntosMana;

    /**
     * Puntos de maná máximos del mago.
     */
    private double puntosManaMax;

    /**
     * Crea un Mago a nivel 1 con estadísticas base bajas pero capacidad mágica.
     * @param nombre Nombre del mago.
     */
    public Mago(String nombre) {
        super(nombre, 1, 80, 1); // Vida baja, defensa baja
        this.puntosManaMax = 100;
        this.puntosMana = 100;
    }

    /**
     * Obtiene los puntos de maná actuales.
     * @return Puntos de maná.
     */
    public double getPuntosMana() { return puntosMana; }

    /**
     * Establece los puntos de maná actuales, asegurando que no excedan el máximo ni sean negativos.
     * @param puntosMana Nuevos puntos de maná.
     */
    public void setPuntosMana(double puntosMana) {
        this.puntosMana = Math.max(0, Math.min(puntosManaMax, puntosMana));
    }

    /**
     * Restaura puntos de maná sin exceder el máximo.
     * @param cantidad Cantidad de maná a recuperar.
     */
    public void recuperarMana(double cantidad) {
        double manaAntes = this.puntosMana;
        this.puntosMana += cantidad;
        if (this.puntosMana > this.puntosManaMax) this.puntosMana = this.puntosManaMax;

        System.out.println(getNombre() + " recupera " + (int)(this.puntosMana - manaAntes) + " maná.");
    }

    /**
     * Ataque especial del mago que consume maná para infligir daño.
     * Si no hay suficiente maná, realiza un ataque débil.
     * @param objetivo El personaje que recibe el ataque.
     */
    @Override
    public void atacar(Personaje objetivo) {
        if (!this.estaVivo()) return;

        if (puntosMana >= 10) {
            int inteligencia = 35 + (5 * getNivel());
            puntosMana -= 10;

            // --- CARTEL: LANZAMIENTO DE HECHIZO ---
            System.out.println("\n   🔥 ¡CONJURO ARCANO! 🔥");
            System.out.println("   .──────────────────────────────────────.");
            System.out.printf("    » %s invoca una BOLA DE FUEGO %n", getNombre().toUpperCase());
            System.out.printf("    » Impacto mágico: %-3d puntos de daño %n", inteligencia);
            System.out.printf("    » [ ✨ Maná restante: %-2d ] %n", (int)puntosMana);
            System.out.println("   '──────────────────────────────────────'");

            objetivo.recibirDanio(inteligencia);
        } else {
            // --- CARTEL: ATAQUE DESESPERADO ---
            System.out.println("\n   💠 [ SIN MANÁ ]");
            System.out.printf("   %s intenta canalizar energía... ¡pero falla!%n", getNombre());
            System.out.printf("   🔨 Golpe torpe con bastón: %d daño.%n", (2 + getNivel()));

            objetivo.recibirDanio(2 + getNivel());
        }
    }

    /**
     * Sube de nivel al mago, aumentando su vida y maná máximos.
     */
    @Override
    public void subirNivel() {
        super.subirNivel(); // Muestra el cartel de nivel general
        setPuntosVidaMax(getPuntosVidaMax() + 10);
        setPuntosVida(getPuntosVidaMax());
        this.puntosManaMax += 25;
        this.puntosMana = this.puntosManaMax;

        // --- CARTEL DE EXPANSIÓN MÁGICA ---
        System.out.println("\n          ✨  I L U M I N A C I Ó N  ✨          ");
        System.out.println("   .────────────────────────────────────────────.");
        System.out.printf("    » %s alcanza un nuevo plano de poder.%n", getNombre().toUpperCase());
        System.out.println("    |                                            |");
        System.out.printf("    |  🔷 MANÁ MÁXIMO:   +25  (Total: %-3d)      |%n", (int)puntosManaMax);
        System.out.printf("    |  ❤️  VIDA MÁXIMA:   +10  (Total: %-3d)      |%n", (int)getPuntosVidaMax());
        System.out.println("   '────────────────────────────────────────────'");
    }

    /**
     * Descansa para recuperar vida y maná completamente.
     */
    @Override
    public void descansar() {
        super.descansar(); // Recupera vida (y muestra su cartel de salud)
        this.puntosMana = this.puntosManaMax;

        // --- CARTEL DE MEDITACIÓN ---
        System.out.println("\n   ✨  M E D I T A C I Ó N   A R C A N A  ✨");
        System.out.println("   ╭─────────────────────────────────────────╮");
        System.out.printf("    │  %-37s  │%n", getNombre() + " canaliza el flujo del cosmos...");
        System.out.printf("    │  🔷 MANÁ: [%.0f/%.0f]  (RESTAURADO)     │%n", puntosMana, puntosManaMax);
        System.out.println("   ╰─────────────────────────────────────────╯");
    }
}