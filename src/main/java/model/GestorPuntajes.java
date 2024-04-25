package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorPuntajes {
    private List<Puntaje> puntajes;
    private final String archivoJSON = "puntajes.json";

    // Constructor
    public GestorPuntajes() {
        this.puntajes = new ArrayList<>();
        cargarPuntajesDesdeArchivo();
    }

    // Método para agregar un puntaje al historial y guardar en archivo
    public void agregarPuntaje(Puntaje puntaje) {
        puntajes.add(puntaje);
        guardarPuntajesEnArchivo();
    }

    // Método para obtener los mejores puntajes (top) desde el historial
    public List<Puntaje> obtenerTopPuntajes(int cantidad) {
        puntajes.sort((p1, p2) -> Integer.compare(p2.getPuntaje(), p1.getPuntaje())); // Orden descendente
        return puntajes.subList(0, Math.min(cantidad, puntajes.size()));
    }

    // Método privado para cargar puntajes desde el archivo JSON
    private void cargarPuntajesDesdeArchivo() {
        try (Reader reader = new FileReader(archivoJSON)) {
            Gson gson = new Gson();
            Puntaje[] puntajesArray = gson.fromJson(reader, Puntaje[].class);
            puntajes.clear();
            for (Puntaje puntaje : puntajesArray) {
                puntajes.add(puntaje);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar puntajes desde archivo: " + e.getMessage());
        }
    }

    // Método privado para guardar puntajes en el archivo JSON
    private void guardarPuntajesEnArchivo() {
        try (Writer writer = new FileWriter(archivoJSON)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(puntajes, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar puntajes en archivo: " + e.getMessage());
        }
    }
}

