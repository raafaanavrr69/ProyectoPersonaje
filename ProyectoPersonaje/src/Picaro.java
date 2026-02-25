/**
 * Subclase de Personaje que utiliza Energía y agilidad.
 * Puede equipar dos armas y atacar con ambas en el mismo turno.
 *
 * @author Javier Fernández Gavino
 * @version 1.0
 */
public class Picaro extends Personaje {
    /**
     * Energía actual del Pícaro.
     */
    private int energia;

    /**
     * Energía máxima del Pícaro.
     */
    private int energiaMaxima;

    /**
     * Arma equipada en la mano derecha.
     */
    private Arma arma1;

    /**
     * Arma equipada en la mano izquierda.
     */
    private Arma arma2;


    /**
     * Crea un Pícaro.
     *
     * @param nombre Nombre del personaje.
     * @param arma1  Arma de mano derecha (puede ser null).
     * @param arma2  Arma de mano izquierda (puede ser null).
     */
    public Picaro(String nombre, Arma arma1, Arma arma2) {
        super(nombre, 1, 100, 3);
        this.arma1 = arma1;
        this.arma2 = arma2;
        this.energiaMaxima = 50;
        this.energia = this.energiaMaxima;
    }

    // --- Getters y Setters Específicos ---

    /**
     * Obtiene la energía actual del Pícaro.
     *
     * @return La energía actual.
     */
    public int getEnergia() {
        return energia;
    }

    /**
     * Establece la energía actual del Pícaro.
     *
     * @param energia La nueva energía (se ajusta entre 0 y el máximo).
     */
    public void setEnergia(int energia) {
        this.energia = Math.max(0, Math.min(energiaMaxima, energia));
    }

    /**
     * Obtiene el arma equipada en la mano derecha.
     *
     * @return El arma de la mano derecha.
     */
    public Arma getArma1() {
        return arma1;
    }

    /**
     * Establece el arma equipada en la mano derecha.
     *
     * @param arma1 El nuevo arma para la mano derecha.
     */
    public void setArma1(Arma arma1) {
        this.arma1 = arma1;
    }

    /**
     * Obtiene el arma equipada en la mano izquierda.
     *
     * @return El arma de la mano izquierda.
     */
    public Arma getArma2() {
        return arma2;
    }

    /**
     * Establece el arma equipada en la mano izquierda.
     *
     * @param arma2 El nuevo arma para la mano izquierda.
     */
    public void setArma2(Arma arma2) {
        this.arma2 = arma2;
    }

    // --- Lógica de Combate ---

    /**
     * Ataca a un personaje objetivo utilizando ambas armas si es posible.
     * Consume energía al atacar.
     *
     * @param objetivo El personaje que será atacado.
     */
    @Override
    public void atacar(Personaje objetivo) {
        if (!this.estaVivo()) return;

        int coste = 10;
        if (energia >= coste) {
            // Cálculo de daño
            double d1 = (arma1 != null) ? arma1.getDanioExtra() : 0;
            double d2 = (arma2 != null) ? arma2.getDanioExtra() : 0;
            double total = d1 + d2 + (5 * getNivel());

            // --- CARTEL DE ATAQUE SOMBRÍO ---
            System.out.println("\n   " + " 👤 ".repeat(5));
            System.out.println("   .─────────────────────────────────────.");
            System.out.printf("    » %s se desvanece... %n", getNombre().toUpperCase());
            System.out.printf("    » ¡ATAQUE VELOZ CON %-10.0f DAÑO! %n", total);
            System.out.println("   '─────────────────────────────────────'");

            objetivo.recibirDanio(total);
            setEnergia(energia - coste);

        } else {
            // --- CARTEL DE AGOTAMIENTO ---
            System.out.println("\n   ⚠️  [ ¡EXHAUSTO! ]");
            System.out.printf("   %s jadea por aire... (Energía insuficiente)%n", getNombre());
        }
    }

    /**
     * Descansa para recuperar energía al máximo.
     */
    @Override
    public void descansar() {
        super.descansar();
        setEnergia(this.energiaMaxima);

        // --- CARTEL DE RECUPERACIÓN ---
        System.out.println("\n   ✨  M O M E N T O   D E   P A Z  ✨");
        System.out.println("   ╭───────────────────────────────────╮");
        System.out.printf("    │  %-31s  │%n", getNombre() + " recupera el aliento...");
        System.out.printf("    │  ⚡ ENERGÍA: [%d/%d]  (MAX)     │%n", energia, energiaMaxima);
        System.out.println("   ╰───────────────────────────────────╯");
    }

    /**
     * Sube de nivel, mejorando vida y energía.
     */
    @Override
    public void subirNivel() {
        super.subirNivel(); // Esto incrementa el nivel y muestra tu cartel de nivel
        setPuntosVidaMax(getPuntosVidaMax() + 15);
        setPuntosVida(getPuntosVidaMax());
        this.energiaMaxima += 15;
        this.energia = this.energiaMaxima;

        // --- CARTEL DE MEJORA DE ESTADÍSTICAS ---
        System.out.println("\n          🎉 ¡ESTADÍSTICAS MEJORADAS! 🎉          ");
        System.out.println("   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("   ┃  👣  CLASE: PÍCARO  |  NIVEL ACTUAL: %-2d  ┃%n", getNivel());
        System.out.println("   ┠────────────────────────────────────────────┨");
        System.out.println("   ┃  📈 TUS SENTIDOS SE AGUDIZAN:              ┃");
        System.out.printf("   ┃     ❤️  Vida Máx:    +15  (Total: %3d)     ┃%n", getPuntosVidaMax());
        System.out.printf("   ┃     ⚡ Energía Máx: +15  (Total: %3d)     ┃%n", energiaMaxima);
        System.out.println("   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}