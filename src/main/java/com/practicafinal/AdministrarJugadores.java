package com.practicafinal;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class AdministrarJugadores {
    public static ArrayList<Jugador> extraerJugadoresFichero(){
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        try {
            File archivoJugadores = new File(AdministrarJugadores.class.getResource("jugadores.txt").getPath());
            Scanner lector = new Scanner(archivoJugadores);

            while (lector.hasNext()){
                String[] datos = lector.nextLine().split(";");
                Image imagen = new Image(datos[4], 50, 50, true, true);

                listaJugadores.add(new Jugador(datos[0], Integer.parseInt(datos[1].split("=")[1]), Integer.parseInt(datos[2].split("=")[1]), Integer.parseInt(datos[3].split("=")[1]), imagen));
            }
        }catch (FileNotFoundException | NullPointerException ex){
            System.out.println("Archivo de jugadores no encontrado");
        }
        return listaJugadores;
    }

    public static void editarJugador(Jugador jugador, String nuevoNombre, String nuevaImagen, String imagenAlt){
        Image imagen;

        try {
            if(nuevaImagen.contains(";")){
                throw new IllegalArgumentException("El URL contiene un ;");
            }

            imagen = new Image(nuevaImagen, 50, 50, true, true);
        }catch (NullPointerException | IllegalArgumentException ex){
            imagen = new Image(imagenAlt, 50, 50, true, true);
        }

        try {
            File archivoJugadores = new File(AdministrarJugadores.class.getResource("jugadores.txt").getPath());

            String nuevosDatos = nuevoNombre.concat(";");
            nuevosDatos = nuevosDatos.concat("ganadas=").concat(jugador.PartidasGanadasProperty().getValue().toString()).concat(";");
            nuevosDatos = nuevosDatos.concat("perdidas=").concat(jugador.PartidasPerdidasProperty().getValue().toString()).concat(";");
            nuevosDatos = nuevosDatos.concat("puntos=").concat(jugador.PuntosProperty().getValue().toString()).concat(";").concat(imagen.getUrl());

            Utilidades.reemplazarLinea(archivoJugadores, jugador.NombreProperty().getValue()+";.*", nuevosDatos);

            jugador.setNombre(nuevoNombre);
            jugador.setImagen(imagen);
        }catch (NullPointerException ex){
            throw new RuntimeException(ex);
        }
    }

    public static void crearJugador(String nombre, String imagenURL, ObservableList<Jugador> jugadores, String imagenAlt){
        Image imagen;

        try {
            if(imagenURL.contains(";")){
                throw new IllegalArgumentException("El URL contiene un ;");
            }

            imagen = new Image(imagenURL, 50, 50, true, true);
        }catch (NullPointerException | IllegalArgumentException ex){
            imagen = new Image(imagenAlt, 50, 50, true, true);
        }

        try {
            Jugador nuevoJugador = new Jugador(nombre, 0, 0, 0, imagen);
            jugadores.add(nuevoJugador);

            FileWriter writer = new FileWriter(AnadirJugadorController.class.getResource("jugadores.txt").getPath(), true);
            String texto = nuevoJugador.NombreProperty().getValue().concat(";ganadas=0;perdidas=0;puntos=0;").concat(imagen.getUrl());

            writer.write(texto);
            writer.write("\n");

            writer.close();
        }catch (IOException | NullPointerException ex){
            throw new RuntimeException(ex);
        }
    }

    public static void borrarJugador(Jugador jugador, ObservableList<Jugador> listaJugadores){
        try {
            listaJugadores.remove(jugador);

            File archivoJugadores = new File(AdministrarJugadores.class.getResource("jugadores.txt").getPath());

            Utilidades.reemplazarLinea(archivoJugadores, jugador.NombreProperty().getValue()+";.*", "");
        }catch (NullPointerException ex){
            System.out.println("No se ha podido eliminar al jugador");
        }
    }

    public static ArrayList<Jugador> buscarRankingJugadores(){
        ArrayList<Jugador> jugadores = extraerJugadoresFichero();
        ArrayList<Jugador> ranking = new ArrayList<>();

        int cont = 1;
        while(jugadores.size() > 0 && ranking.size() < 10) {
            Jugador mayorPuntaje = jugadores.get(0);
            for (Jugador i : jugadores) {
                if (i.PuntosProperty().getValue() > mayorPuntaje.PuestoProperty().getValue()) {
                    mayorPuntaje = i;
                }
            }
            mayorPuntaje.setPuestoRanking(cont);
            ranking.add(mayorPuntaje);
            jugadores.remove(mayorPuntaje);
            cont++;
        }

        return ranking;
    }

    public static void actualizarPuntos(Jugador jugador){
        String lineaActualizada = jugador.NombreProperty().getValue().concat(";ganadas=").concat(jugador.PartidasGanadasProperty().getValue().toString()).concat(";");
        lineaActualizada = lineaActualizada.concat("perdidas=").concat(jugador.PartidasPerdidasProperty().getValue().toString()).concat(";");
        lineaActualizada = lineaActualizada.concat("puntos=").concat(jugador.PuntosProperty().getValue().toString()).concat(";");
        lineaActualizada = lineaActualizada.concat(jugador.ImagenProperty().getValue().getUrl());

        try {
            File file = new File(AdministrarJugadores.class.getResource("jugadores.txt").getPath());

            Utilidades.reemplazarLinea(file, jugador.NombreProperty().getValue()+";.*", lineaActualizada);
        }catch (NullPointerException ex){
            System.out.println("Archivo de jugadores no encontrado");
        }
    }
}
