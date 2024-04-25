package model;

public class Casilla {
    private int numero;
    private Casilla siguiente;
    private Casilla anterior;
    private String contenido; // Puede ser "*" (semilla), "R" (Rick), "M" (Morty) u otro

    public Casilla(int numero) {
        this.numero = numero;
        this.siguiente = null;
        this.anterior = null;
        this.contenido = " "; // Inicialmente la casilla está vacía
    }

    public int getNumero() {
        return numero;
    }

    public Casilla getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Casilla siguiente) {
        this.siguiente = siguiente;
    }

    public Casilla getAnterior() {
        return anterior;
    }

    public void setAnterior(Casilla anterior) {
        this.anterior = anterior;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
