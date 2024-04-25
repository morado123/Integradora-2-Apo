package model;

// Clase Puntaje para almacenar los puntajes de los jugadores
public class Puntaje implements Comparable<Puntaje> {
    private String nombreJugador;
    private int puntaje;

    public Puntaje(String nombreJugador, int puntaje) {
        this.nombreJugador = nombreJugador;
        this.puntaje = puntaje;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getPuntaje() {
        return puntaje;
    }

    @Override
    public int compareTo(Puntaje otroPuntaje) {
        // Comparación basada en el puntaje (orden descendente)
        return Integer.compare(otroPuntaje.puntaje, this.puntaje);
    }
}