/**
 * Clase Evento que permite al jugador avanzar en la sala para completar la misión.
 * Solo es necesario crear un evento por Partida. Es posible crear múltiples si se
 * requiere (Cambio de Sala, Mision, ...).
 * -
 * Se gestionan los eventos de forma aleatoria, puede ser cambiado.
 *
 * @author Juan María Alanís Rodríguez
 * @version 1.0
 */
public class Evento {

    // Atributos
    /**
     * Sala en la que se encuentra el jugador.
     */
    private Sala sala;
    /**
     * Mision ha completar.
     */
    private Mision mision;
    /**
     * Personaje del Jugador.
     */
    private Personaje jugador;
    /**
     * Contador de veces que se ha avanzado.
     */
    int movimientos;

    // Constructores
    /**
     * Crear evento
     *
     * @param sala      Sala en la que ocurren eventos.
     * @param mision    Mision a completar.
     * @param jugador   Personaje del Jugador.
     */
    public Evento(Sala sala, Mision mision, Personaje jugador) {
        this.sala = sala;
        this.mision = mision;
        this.jugador = jugador;
        this.movimientos = 0;
    }

    // Getters
    public Sala getSala() {
        return this.sala;
    }
    public Mision getMision() {
        return this.mision;
    }
    public Personaje getJugador() {
        return this.jugador;
    }
    public  int getMovimientos() {
        return this.movimientos;
    }

    // Setters
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    public void setMision(Mision mision) {
        this.mision = mision;
    }
    public void setJugador(Personaje jugador) {
        this.jugador = jugador;
    }
    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    // Métodos

    /**
     * Función para que pasen cosas.
     *
     * @param gestor   Gestor de misiones del Jugador
     */
    /**
     * Función para que pasen cosas.
     * Ahora devuelve un Enemigo si hay combate, o null si no pasa nada.
     */
    public Enemigo avanzar(GestorMisiones gestor) {
        this.movimientos++;
        // sala.aplicarEfecto(jugador); // Descomenta si Sala tiene este método

        // Encuentro aleatorio (60% de probabilidad)
        if (Math.random() < .6) {
            // Aquí devolvemos el enemigo que crea iniciarEncuentro
            return iniciarEncuentro();
        }
        else if (Math.random() < .8) {
            System.out.println("Exploras los alrededores pero no encuentras enemigos...");
        }
        else if (Math.random() < .9) {
            encontrarAlgo(gestor);
        }
        else {
            System.out.println("Encuentras un lugar seguro para recuperar fuerzas.");
            jugador.descansar();
        }

        return null; // Si no hubo combate, devolvemos null
    }

    /**
     * Genera un encuentro y devuelve el enemigo.
     * IMPORTANTE: Ya no gestiona el combate ni la recompensa aquí dentro.
     * Devuelve el enemigo al Main para que el jugador pueda pelear interactivamente.
     */
    public Enemigo iniciarEncuentro() {
        Enemigo enemigoActual = sala.generarEnemigo();
        System.out.println("¡Súbitamente aparece un " + enemigoActual.getNombre() + "!");
        return enemigoActual;
    }

    /**
     * Gambling
     *
     * @param gestor   Gestor de misiones del Jugador
     */
    private void encontrarAlgo(GestorMisiones gestor) {
        System.out.println("\n[EXPLORACIÓN] Te detienes a observar el entorno...");
        if (Math.random() < .4) {
            // 40% Texto que no hace nada
            System.out.println("   > ¡Has encontrado rastros frescos de tu objetivo!");
            System.out.println("   > Tu conocimiento de la zona aumenta.");
        }
        else if (Math.random() < .7) {
            // 30% - Suministros
            System.out.println("   > Entre la maleza encuentras un pequeño suministro abandonado.");
            // Generar una poción básica (Se puede crear un Pool de Items)
            Pocion chisme = new Pocion("Poción de Supervivencia", 20, TipoPocion.VIDA);
            jugador.recogerItem(chisme);
            gestor.notificarEvento(chisme);
        }
        else {
            // 30% - Momento texto 2
            System.out.println("   > " + sala.getDescripcion());
            System.out.println("   > No parece haber nada útil aquí, pero el ambiente es inquietante.");
        }
    }
}
