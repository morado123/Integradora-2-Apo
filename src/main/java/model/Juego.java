package model;

import java.util.Random;
import java.util.Scanner;

public class Juego {
    private Tablero tablero;
    private Jugador rick;
    private Jugador morty;
    private GestorPuntajes gestorPuntajes;

    // Constructor
    public Juego(int numFilas, int numColumnas, int numEnlaces, int numSemillas) {
        tablero = new Tablero(numFilas, numColumnas);
        tablero.crearEnlacesAleatorios(numEnlaces);

        // Posicionar jugadores en casillas aleatorias
        Random random = new Random();
        Casilla casillaRick = tablero.getCasillaPorNumero(random.nextInt(numFilas * numColumnas) + 1);
        Casilla casillaMorty = tablero.getCasillaPorNumero(random.nextInt(numFilas * numColumnas) + 1);

        rick = new Jugador("Rick", casillaRick);
        morty = new Jugador("Morty", casillaMorty);

        gestorPuntajes = new GestorPuntajes();
    }

    // Método principal para iniciar el juego
    public void iniciarJuego() {
        Scanner scanner = new Scanner(System.in);

        Jugador jugadorActual = rick; // Comienza Rick

        while (!juegoTerminado()) {
            System.out.println("¡Es el turno de " + jugadorActual.getNombre() + "!");

            // Mostrar opciones al jugador
            mostrarMenu();

            // Leer la opción del jugador
            int opcion = scanner.nextInt();

            // Ejecutar la opción seleccionada
            switch (opcion) {
                case 1:
                    tirarDado(jugadorActual);
                    break;
                case 2:
                    tablero.mostrarTablero();
                    break;
                case 3:

                    break;
                case 4:
                    mostrarMarcador();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elige una opción válida.");
                    break;
            }

            // Cambiar turno al siguiente jugador
            jugadorActual = (jugadorActual == rick) ? morty : rick;
        }

        // Mostrar resultados finales
        mostrarResultadosFinales();
    }

    // Método para simular el lanzamiento de un dado y mover al jugador
    private void tirarDado(Jugador jugador) {
        Random random = new Random();
        int dado = random.nextInt(6) + 1; // Simula el lanzamiento de un dado de 6 caras

        System.out.println(jugador.getNombre() + " ha sacado un " + dado);

        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Avanzar (A) o Retroceder (R)?");
        String opcion = scanner.nextLine();

        Casilla casillaActual = jugador.getCasillaActual();
        Casilla nuevaCasilla = (opcion.equalsIgnoreCase("A")) ? avanzarCasillas(casillaActual, dado) : retrocederCasillas(casillaActual, dado);

        jugador.setCasillaActual(nuevaCasilla);

        // Recolectar semilla si hay una en la casilla actual
        recolectarSemilla(jugador, nuevaCasilla);
    }

    private Casilla avanzarCasillas(Casilla casillaActual, int pasos) {
        Casilla nuevaCasilla = casillaActual;
        for (int i = 0; i < pasos; i++) {
            nuevaCasilla = nuevaCasilla.getSiguiente();
            if (nuevaCasilla == null) {
                nuevaCasilla = tablero.getInicio(); // Regresar al inicio del tablero circularmente
            }
        }
        return nuevaCasilla;
    }

    private Casilla retrocederCasillas(Casilla casillaActual, int pasos) {
        Casilla nuevaCasilla = casillaActual;
        for (int i = 0; i < pasos; i++) {
            nuevaCasilla = nuevaCasilla.getAnterior();
            if (nuevaCasilla == null) {
                nuevaCasilla = tablero.getFin(); // Ir al final del tablero circularmente
            }
        }
        return nuevaCasilla;
    }

    // Método para recolectar semilla si está presente en la casilla actual
    private void recolectarSemilla(Jugador jugador, Casilla casilla) {
        if (casilla != null && casilla.getContenido().equals("*")) {
            casilla.setContenido(" "); // Quitar la semilla de la casilla
            jugador.recolectarSemilla();
        }
    }

    // Método para verificar si el juego ha terminado
    private boolean juegoTerminado() {
        // El juego termina cuando todas las semillas han sido recolectadas
        return tablero.todasSemillasRecolectadas();
    }

    // Método para mostrar el menú de opciones
    private void mostrarMenu() {
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Tirar dado");
        System.out.println("2. Ver tablero");
        System.out.println("3. Ver enlaces");
        System.out.println("4. Ver marcador");
        System.out.print("Selecciona una opción: ");
    }

    // Método para mostrar el marcador de semillas recolectadas por cada jugador
    private void mostrarMarcador() {
        System.out.println("Marcador:");
        System.out.println("Rick: " + rick.getSemillasRecolectadas() + " semillas");
        System.out.println("Morty: " + morty.getSemillasRecolectadas() + " semillas");
    }

    // Método para mostrar los resultados finales del juego y guardar puntajes
    public void mostrarResultadosFinales() {
        Jugador ganador = (rick.getSemillasRecolectadas() > morty.getSemillasRecolectadas()) ? rick : morty;
        System.out.println(ganador.getNombre() + " ha ganado recolectando " + ganador.getSemillasRecolectadas() + " semillas");

        // Guardar puntajes en archivo histórico
        Puntaje puntaje = new Puntaje(ganador.getNombre(), ganador.getSemillasRecolectadas());
        gestorPuntajes.agregarPuntaje(puntaje);

        // Mostrar top 5 puntajes históricos
        System.out.println("Top 5 Puntajes Históricos:");
        for (Puntaje p : gestorPuntajes.obtenerTopPuntajes(5)) {
            System.out.println(p.getNombreJugador() + ": " + p.getPuntaje() + " puntos");
        }
    }


}
