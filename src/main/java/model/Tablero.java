package model;

import model.Casilla;

import java.util.Random;

public class Tablero {
    private Casilla inicio;
    private Casilla fin;
    private int numFilas;
    private int numColumnas;

    public Tablero(int numFilas, int numColumnas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.inicio = crearTablero(numFilas, numColumnas);
        this.fin = obtenerUltimaCasilla();
    }

    private Casilla crearTablero(int filas, int columnas) {
        Casilla primeraCasilla = null;
        Casilla anterior = null;

        for (int i = 1; i <= filas * columnas; i++) {
            Casilla nuevaCasilla = new Casilla(i);

            if (primeraCasilla == null) {
                primeraCasilla = nuevaCasilla;
            }

            if (anterior != null) {
                anterior.setSiguiente(nuevaCasilla);
                nuevaCasilla.setAnterior(anterior);
            }

            anterior = nuevaCasilla;
        }

        return primeraCasilla;
    }

    private Casilla obtenerUltimaCasilla() {
        Casilla actual = inicio;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public Casilla getCasillaPorNumero(int numero) {
        return getCasillaRecursivo(inicio, numero);
    }

    private Casilla getCasillaRecursivo(Casilla casilla, int numero) {
        if (casilla == null) {
            return null;
        }

        if (casilla.getNumero() == numero) {
            return casilla;
        }

        return getCasillaRecursivo(casilla.getSiguiente(), numero);
    }

    public void mostrarTablero() {
        Casilla actual = inicio;
        int contador = 0;

        while (actual != null) {
            System.out.print("[" + actual.getContenido() + "] ");
            contador++;

            if (contador == numColumnas) {
                System.out.println(); // Cambiar de línea después de cada fila
                contador = 0;
            }

            actual = actual.getSiguiente();
        }
    }

    public void crearEnlacesAleatorios(int numEnlaces) {
        Random random = new Random();
        int enlacesCreados = 0;

        while (enlacesCreados < numEnlaces) {
            int posicionA = random.nextInt(numFilas * numColumnas) + 1;
            int posicionB = random.nextInt(numFilas * numColumnas) + 1;

            Casilla casillaA = getCasillaPorNumero(posicionA);
            Casilla casillaB = getCasillaPorNumero(posicionB);

            if (casillaA != null && casillaB != null && casillaA.getSiguiente() == null && casillaB.getSiguiente() == null && casillaA != casillaB) {
                casillaA.setSiguiente(casillaB);
                casillaB.setAnterior(casillaA);
                enlacesCreados++;
            }
        }
    }

    public Casilla getInicio() {
        return inicio;
    }

    public Casilla getFin() {
        return fin;
    }

    public boolean todasSemillasRecolectadas() {
        Casilla actual = inicio;

        while (actual != null) {
            if (actual.getContenido().equals("*")) {
                return false;
            }
            actual = actual.getSiguiente();
        }

        return true;
    }
}
