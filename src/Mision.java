/**
 * Clase abstracta que representa una misión y sus características.
 * Para gestionar misiones específicas, se debe usar la clase GestorMisiones.
 *
 * @author Juan María Alanís Rodríguez
 * @version 1.2
 */
public abstract class Mision {

    // Atributos
    /**
     *  Título de la misión.
     */
    private String titulo;
    /**
     * Descripción de la misión.
     */
    private String descripcion;
    /**
     * Cantidad objetivo para completar la misión.
     */
    private int objetivoCantidad;
    /**
     * Progreso actual de la misión.
     */
    private int progresoActual;
    /**
     * Experiencia otorgada al completar la misión.
     */
    private int xpRecompensa;
    /**
     * Objeto otorgado como recompensa al completar la misión.
     */
    private Item itemRecompensa;
    /**
     * Estado actual de la misión.
     */
    private EstadoMision estado;
    /**
     * Sala asociada a la misión.
     */
    private Sala sala;

    // Constructores
    /**
     * Crea una misión con los detalles proporcionados.
     *
     * @param titulo      Título de la misión.
     * @param descripcion Descripción de la misión.
     * @param objetivo    Cantidad objetivo para completar la misión.
     * @param xp          Experiencia otorgada al completar la misión.
     * @param item        Objeto otorgado como recompensa al completar la misión.
     */
    public Mision(String titulo, String descripcion, int objetivo, int xp, Item item, Sala sala) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivoCantidad = objetivo;
        this.xpRecompensa = xp;
        this.itemRecompensa = item;
        this.estado = EstadoMision.DISPONIBLE;
        this.progresoActual = 0;
        this.sala = sala;
    }

    // Getters
    public String getTitulo() {
        return this.titulo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public int getObjetivoCantidad() {
        return this.objetivoCantidad;
    }
    public int getProgresoActual() {
        return this.progresoActual;
    }
    public int getXpRecompensa() {
        return this.xpRecompensa;
    }
    public Item getItemRecompensa() {
        return this.itemRecompensa;
    }
    public EstadoMision getEstado() {
        return this.estado;
    }
    public Sala getSala() {
        return this.sala;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setProgresoActual(int progreso) {
        this.progresoActual = progreso;
    }
    public void setObjetivoCantidad(int objetivoCantidad) {
        this.objetivoCantidad = objetivoCantidad;
    }
    public void setXpRecompensa(int xpRecompensa) {
        this.xpRecompensa = xpRecompensa;
    }
    public  void setItemRecompensa(Item itemRecompensa) {
        this.itemRecompensa = itemRecompensa;
    }
    public void setEstado(EstadoMision estado) {
        this.estado = estado;
    }
    public  void setSala(Sala sala) {
        this.sala = sala;
    }

    // Métodos
    //
    /**
     * Actualiza el progreso de la misión basado en los datos proporcionados.
     * Se requiere implementación específica en las subclases.
     * -
     * Solo ha de ser sobreescrita por las clases que heredan de Misión.
     *
     * @param datos Datos relevantes para actualizar el progreso.
     */
    public abstract void actualizarProgreso(Object datos);

    //
    // ------- Métodos que se usan UNICAMENTE para la clase GestorMisiones -------
    //
    /**
     * Verifica si la misión está lista para entregar la recompensa.
     * -
     * Aplicable solo en clase GestorMisiones.
     *
     * @return true si la misión está completa y lista para entregar, false en caso contrario.
     */
    protected boolean estaListaParaEntregar() {
        return (progresoActual >= objetivoCantidad) &&
                (estado == EstadoMision.EN_CURSO || estado == EstadoMision.COMPLETADA);
    }
    /**
     * Entrega la recompensa al jugador si la misión está completa.
     * -
     * Aplicable solo en clase GestorMisiones.
     *
     * @param jugador Personaje que recibe la recompensa.
     */
    protected void entregarRecompensa(Personaje jugador) {
        if (estaListaParaEntregar()) {
            System.out.println("\n   ⭐  G E S T A   C O M P L E T A D A  ⭐");
            System.out.println("   ╔══════════════════════════════════════════╗");
            System.out.printf("   ║ %-40s ║%n", "Misión: " + titulo.toUpperCase());
            System.out.println("   ╠══════════════════════════════════════════╣");

            // Recompensa de Experiencia
            System.out.printf("   ║ ✨ Experiencia: +%-25d ║%n", xpRecompensa);
            jugador.ganarExperiencia(xpRecompensa);

            // Recompensa de Item
            if (itemRecompensa != null) {
                System.out.printf("   ║ 🎁 Objeto: %-30s ║%n", itemRecompensa.getNombre());
                jugador.recogerItem(itemRecompensa);
            }

            System.out.println("   ╚══════════════════════════════════════════╝\n");

            this.estado = EstadoMision.RECOMPENSA_ENTREGADA;
        }
    }
    /**
     * Acepta la misión para el jugador.
     * -
     * Aplicable solo en clase GestorMisiones.
     *
     * @param jugador Personaje que acepta la misión.
     */
    protected void aceptar(Personaje jugador) {
        if (this.estado == EstadoMision.DISPONIBLE) {
            this.estado = EstadoMision.EN_CURSO;

            System.out.println("\n   📜  N U E V O   D E S T I N O  ");
            System.out.println("   .──────────────────────────────────────────.");
            System.out.printf("    |  » MISION: %-28s |%n", titulo.toUpperCase());
            System.out.println("    |                                          |");
            System.out.println("    |   ¡El deber te llama, " + String.format("%-17s", jugador.getNombre() + "!") + "   |");
            System.out.println("   '──────────────────────────────────────────'");
        }
    }
    /**
     * Procesa un evento relevante para la misión.
     * -
     * Aplicable solo en clase GestorMisiones.
     *
     * @param evento Evento que ocurrió en el juego.
     */
    protected void procesarEvento(Object evento) {
        if (this.estado == EstadoMision.EN_CURSO) {
            actualizarProgreso(evento);

            // --- VISUALIZACIÓN DEL PROGRESO ---
            mostrarBarraProgreso();

            if (progresoActual >= objetivoCantidad) {
                this.estado = EstadoMision.COMPLETADA;
                anunciarMisionCompletada();
            }
        }
    }

    private void mostrarBarraProgreso() {
        int tamanoBarra = 20;
        // Calculamos cuántos bloques llenar
        int progreso = (int) ((double) progresoActual / objetivoCantidad * tamanoBarra);
        progreso = Math.min(progreso, tamanoBarra); // Evitar que se pase del 100%

        String visual = "🔸".repeat(progreso) + "🔹".repeat(tamanoBarra - progreso);

        // Usamos \r para que si el progreso cambia rápido, se vea fluido
        System.out.print("\r   [ PROGRESO: " + visual + " ] " + progresoActual + "/" + objetivoCantidad);

        // Si acaba de completarse, forzamos un pequeño delay para que el jugador lo vea
        if (progresoActual >= objetivoCantidad) {
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            System.out.println(); // Salto de línea final
        }
    }

    private void anunciarMisionCompletada() {
        String oro = "\u001B[33m";
        String verde = "\u001B[32m";
        String reset = "\u001B[0m";

        System.out.println("\n   " + verde + "Check! ✔️ " + reset + "¡Objetivo alcanzado!");
        System.out.println("   " + oro + "╔" + "═".repeat(40) + "╗");
        System.out.printf("   ║ %-38s ║%n", "¡MISION LISTA PARA ENTREGAR!");
        System.out.printf("   ║ %-38s ║%n", "Título: " + titulo);
        System.out.println("   ╚" + "═".repeat(40) + "╝" + reset);
    }
}
