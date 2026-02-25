/**
 * Clase que representa una sala en el juego con diferentes efectos y dificultades.
 * Integra la generación de enemigos.
 *
 * @author Rafa Navarro
 * @version 1.1
 * @since 2026.01.23
 */
public class Sala {

    /**
     * Tipo de efecto ambiental.
     */
    private TipoSala tipo;
    /**
     * Descripción del efecto ambiental.
     */
    private String descripcion;
    /**
     * Dificultad de la sala.
     */
    private Dificultad dificultad;
    /**
     * Nivel sugerido para el jugador en esta sala.
     */
    private int nivelSugerido;

    /**
     * Constructor de la sala.
     * Asigna dificultad y efecto ambiental aleatorio.
     *
     * @param numeroSala   Número identificativo de la sala (1, 2, 3).
     * @param nivelJugador Nivel actual del jugador para ajustar la dificultad.
     */
    public Sala(int numeroSala, int nivelJugador) {
        /**
         * Asignación de Dificultad y Nivel Sugerido.
         */
        if (numeroSala == 1) {
            this.dificultad = Dificultad.FACIL; // Antes "FÁCIL"
            this.nivelSugerido = Math.max(1, nivelJugador - 2);
        } else if (numeroSala == 2) {
            this.dificultad = Dificultad.MEDIA; // Antes "MEDIA"
            this.nivelSugerido = nivelJugador;
        } else {
            this.dificultad = Dificultad.DIFICIL; // Antes "DIFÍCIL"
            this.nivelSugerido = nivelJugador + 2;
        }

        /**
         * Asignación de Efecto Ambiental Aleatorio.
         */
        int azar = (int) (Math.random() * 4);
        // Usando Switch Expression (Java moderno)
        this.tipo = switch (azar) {
            case 0  -> TipoSala.ESCARCHA;
            case 1  -> TipoSala.NIEBLA;
            case 2  -> TipoSala.BENDICION;
            default -> TipoSala.NORMAL;
        };

        this.descripcion = switch (this.tipo) {
            case ESCARCHA  -> "❄️  Un frío sepulcral que congela tus reservas.";
            case NIEBLA    -> "🌫️  Vapores venenosos que corroen tu piel.";
            case BENDICION -> "✨  Un rayo de luz celestial que purifica tus heridas.";
            case NORMAL    -> "🧱  Una estancia de piedra fría sin peligros aparentes.";
        };
    }

    /**
     * Generador de enemigos.
     * Ajusta las probabilidades de aparición según la dificultad de la sala.
     */
    public Enemigo generarEnemigo() {
        double probabilidad = Math.random();

        // Comparamos directamente con el Enum usando ==
        if (this.dificultad == Dificultad.FACIL) {
            /**
             * 80% Orco, 15% Espectro, 5% Jefe
             */
            if (probabilidad < 0.05) return new Enemigo("Comandante Orco (Mini-Jefe)", nivelSugerido + 2);
            if (probabilidad < 0.20) return new Enemigo("Espectro Débil", nivelSugerido + 1);
            return new Enemigo("Orco Raso", nivelSugerido);

        } else if (this.dificultad == Dificultad.MEDIA) {
            /**
             * 50% Orco, 35% Espectro, 15% Jefe
             */
            if (probabilidad < 0.15) return new Enemigo("Caballero Espectral (Mini-Jefe)", nivelSugerido + 2);
            if (probabilidad < 0.50) return new Enemigo("Espectro Acechador", nivelSugerido + 1);
            return new Enemigo("Orco Guerrero", nivelSugerido);

        } else {
            /**
             * 20% Orco, 50% Espectro, 30% Jefe
             */
            if (probabilidad < 0.30) return new Enemigo("SEÑOR DE LAS SOMBRAS (JEFE)", nivelSugerido + 3);
            if (probabilidad < 0.80) return new Enemigo("Caballero Corrupto", nivelSugerido + 2);
            return new Enemigo("Orco Berserker", nivelSugerido + 1);
        }
    }

    /**
     * Aplica el efecto ambiental de la sala al personaje.
     *
     * @param p Personaje que entra en la sala.
     */
    public void aplicarEfecto(Personaje p) {
        // 1. Cabecera de Sala (Elegante)
        System.out.println("\n   ◈ ENTRANDO: " + tipo + " ◈");
        System.out.println("   " + "─".repeat(40));

        // 2. Animación de "Ambiente" (Efecto de carga rápido)
        System.out.print("   Sintiendo el entorno... ");
        try {
            for(int i=0; i<3; i++) { Thread.sleep(300); System.out.print("• "); }
            System.out.println("\n   💬 " + descripcion);
        } catch (InterruptedException e) {}

        // 3. Procesar el Efecto con Estética
        switch (this.tipo) {
            case ESCARCHA:
                System.out.println("   ❄️  [EFECTO: CONGELACIÓN]");
                if (p instanceof Mago) {
                    Mago m = (Mago) p;
                    m.setPuntosMana(m.getPuntosMana() - 15);
                    System.out.println("   🧊 El frío drena tus reservas mágicas (-15 Maná).");
                } else if (p instanceof Picaro) {
                    Picaro pic = (Picaro) p;
                    pic.setEnergia(pic.getEnergia() - 15);
                    System.out.println("   🧊 Tus músculos se entumecen (-15 Energía).");
                }
                break;

            case NIEBLA:
                System.out.println("   🌫️  [EFECTO: CORROSIÓN]");
                System.out.print("   ☣️  La niebla te asfixia: ");
                p.recibirDanio(10); // Tu método recibirDanio ya debería imprimir el golpe
                break;

            case BENDICION:
                System.out.println("   ✨  [EFECTO: PURIFICACIÓN]");
                p.setPuntosVida(p.getPuntosVida() + 15);
                System.out.println("   💖 Una calidez divina cierra tus heridas (+15 PV).");
                break;

            default:
                System.out.println("   🧱  No ocurre nada inusual.");
                break;
        }
        System.out.println("   " + "─".repeat(40));
    }

    /**
     * Getters de la sala.
     *
     * @return Atributos de la sala.
     */
    public TipoSala getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public int getNivelSugerido() {
        return nivelSugerido;
    }

    /**
     * Representación en cadena de la sala.
     *
     * @return Detalles de la sala.
     */
    @Override
    public String toString() {
        return "Sala{" +
                "tipo=" + tipo + // Se imprime el nombre del Enum automáticamente
                ", descripcion='" + descripcion + '\'' +
                ", dificultad=" + dificultad +
                ", nivelSugerido=" + nivelSugerido +
                '}';
    }
}