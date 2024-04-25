package model;

public class Jugador {
    private String nombre;
    private Casilla casillaActual;
    private int semillasRecolectadas;

    // Constructor
    public Jugador(String nombre, Casilla casillaInicial) {
        this.nombre = nombre;
        this.casillaActual = casillaInicial;
        this.semillasRecolectadas = 0;
    }

    // Métodos getters y setters
    public String getNombre() {
        return nombre;
    }

    public Casilla getCasillaActual() {
        return casillaActual;
    }

    public void setCasillaActual(Casilla casillaActual) {
        this.casillaActual = casillaActual;
    }

    public int getSemillasRecolectadas() {
        return semillasRecolectadas;
    }

    public void recolectarSemilla() {
        this.semillasRecolectadas++;
    }
}
