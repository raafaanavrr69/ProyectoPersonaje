# ⚔️ Leyendas Olvidadas

**Leyendas Olvidadas** es un juego de rol (RPG) de texto por consola. El jugador debe adentrarse en una mazmorra, superar niveles con dificultad creciente y derrotar al Jefe Final.

## 🎮 Descripción del Juego
Eres un héroe solitario en una mazmorra generada aleatoriamente. Para avanzar de nivel, debes completar **Misiones de Caza** (ej: "Elimina 3 Orcos"). Si mueres, pierdes la partida.

## 🦸 Clases Disponibles
* **🛡️ Guerrero:** Mucha vida y defensa. Daño físico constante.
* **🔮 Mago:** Poca vida pero ataques mágicos fuertes. Usa **Maná** (se gasta).
* **🗡️ Pícaro:** Rápido y letal. Usa **Energía** y puede llevar dos armas.

## ⚙️ Características

### 🌍 Salas y Ambientes
Cada nivel genera salas con efectos aleatorios:
* **Normal:** Sin efectos.
* **❄️ Escarcha:** Pierdes Maná o Energía al entrar.
* **☠️ Niebla:** Recibes daño por veneno.
* **✨ Bendición:** Recuperas vida automáticamente.

### ⚔️ Sistema de Combate
Turnos rotativos con tres opciones:
1.  **Atacar:** Daño basado en nivel y armas.
2.  **Usar Objeto:** Pociones de vida/maná o equipar armas nuevas.
3.  **Descansar:** Recuperas recursos y pasas turno.

### 🎒 Inventario
* Los enemigos sueltan botín (Loot) al morir.
* Gestión automática de armas: al equipar una nueva, la vieja vuelve a la mochila.

## 🚀 Instrucciones de Ejecución

Requisitos: Tener instalado **Java (JDK)**.

1.  **Compilar el código:**
    ```bash
    javac Main.java
    ```

2.  **Iniciar el juego:**
    ```bash
    java Main
    ```

3.  **Controles:**
    * Usa los números (`1`, `2`, `3`...) del teclado para seleccionar opciones en los menús.

