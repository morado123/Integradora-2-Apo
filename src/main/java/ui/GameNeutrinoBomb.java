package ui;

import java.util.Scanner;
import model.Juego;

public class GameNeutrinoBomb {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Pedir dimensiones del tablero y número de semillas
        System.out.println("¡Bienvenido al juego de Rick y Morty!");
        System.out.println("Por favor, ingresa las dimensiones del tablero (filas y columnas) y el número de semillas:");

        System.out.print("Número de filas: ");
        int numFilas = scanner.nextInt();

        System.out.print("Número de columnas: ");
        int numColumnas = scanner.nextInt();

        System.out.print("Número de semillas: ");
        int numSemillas = scanner.nextInt();

        // Crear juego con las dimensiones y semillas proporcionadas
        Juego juego = new Juego(numFilas, numColumnas, numFilas * numColumnas / 2, numSemillas); // Establecer numEnlaces como la mitad del tablero

        // Iniciar el juego
        juego.iniciarJuego();

        // Mostrar resultados finales y puntajes históricos al finalizar el juego
        System.out.println("\n¡Juego terminado! Mostrando resultados finales y puntajes históricos:");
        juego.mostrarResultadosFinales();

        // Cerrar scanner al finalizar
        scanner.close();
    }
}
