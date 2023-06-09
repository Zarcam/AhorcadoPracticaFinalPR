package com.practicafinal;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    public static void crearJugador(String nombre, String imagenURL, ObservableList<Jugador> jugadores, Image imagenAlt){
        Image imagen;

        try {
            if(imagenURL.contains(";")){
                throw new IllegalArgumentException("El URL contiene un ;");
            }

            imagen = new Image(imagenURL, 50, 50, true, true);
        }catch (NullPointerException | IllegalArgumentException ex){
            imagen = new Image(imagenAlt.getUrl(), 50, 50, true, true);
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
}
