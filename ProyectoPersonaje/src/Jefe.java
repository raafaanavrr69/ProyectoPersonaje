/**
 * Clase que representa a un enemigo de tipo Jefe (Boss).
 * Hereda de la clase Enemigo pero posee características mejoradas, como mayor vida
 * y ataques especiales críticos.
 *
 * Se utiliza para el enfrentamiento final del juego.
 *
 * @author Carlos Fernández Gavino
 * @version 1.0
 */
public class Jefe extends Enemigo {

    /**
     * Constructor del Jefe.
     * Inicializa al enemigo llamando al constructor de la clase padre (Enemigo)
     * y posteriormente modifica sus estadísticas para hacerlo más resistente.
     *
     * @param nombre Nombre del jefe (ej: "Lord Malacor").
     * @param nivel  Nivel de dificultad, que determinará su daño base.
     */
    public Jefe(String nombre, int nivel) {
        // Llamada al constructor de la clase padre
        super(nombre, nivel);

        // Lógica exclusiva del Jefe:
        // Obtenemos la vida base calculada por la clase Enemigo y la duplicamos.
        double vidaBoss = this.getPuntosVidaMax() * 2;
        this.setPuntosVidaMax(vidaBoss);
        this.setPuntosVida(vidaBoss); // Rellenamos la vida al máximo
    }

    /**
     * Sobrescribe el método de ataque para incluir una mecánica de golpe crítico.
     *
     * El jefe tiene un 20% de probabilidad de realizar un ataque potente (1.5x daño).
     * En el 80% restante de los casos, realiza un ataque normal usando la lógica de la clase padre.
     *
     * @param objetivo El personaje (jugador) que recibirá el ataque.
     */
    @Override
    public void atacar(Personaje objetivo) {
        // Probabilidad del 20% de ataque crítico
        if (Math.random() < 0.20) {

            // --- EFECTO VISUAL DE IMPACTO ---
            System.out.println("\n   💥 ¡ALERTA! EL ENEMIGO SE ENFURECE 💥");

            try {
                // Un pequeño "lag" intencional para crear tensión
                for(int i = 0; i < 3; i++) {
                    System.out.print("  ⚠️  ");
                    Thread.sleep(150);
                }
                System.out.println();
            } catch (InterruptedException e) {}

            // --- CARTEL DE DAÑO CRÍTICO ---
            double danioBase = this.getNivel() * 4;
            double danioFuerte = danioBase * 1.5;

            System.out.println("   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("   ┃  🔥 ¡GOLPE CRÍTICO DE %-12s! ┃%n", getNombre().toUpperCase());
            System.out.printf("   ┃  🗡️  Daño infligido: %-16.0f ┃%n", danioFuerte);
            System.out.println("   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

            objetivo.recibirDanio(danioFuerte);

        } else {
            // Ataque normal
            super.atacar(objetivo);
        }
    }

    /**
     * Define el botín que suelta el jefe al morir.
     *
     * @param jugador Personaje que derrota al jefe.
     * @return null, ya que la derrota del jefe final implica terminar el juego,
     * no recoger objetos.
     */
    @Override
    public Item soltarBotin(Personaje jugador) {
        return null;
    }
}