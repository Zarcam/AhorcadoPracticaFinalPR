package com.practicafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JuegoAhorcadoController implements Initializable {
    @FXML
    private Label textoPalabra;
    @FXML
    private ImageView imagenAhorcado;

    //private File arhivoPalabra;

    private String palabraOculta;
    private char[] solucion;
    private char[] letrasDescubiertas;

    @FXML
    private void pulsadoBoton(ActionEvent event){
        Button boton = (Button) event.getSource();

        char letra = boton.getText().charAt(0);

        if(Utilidades.arrayContiene(letra, solucion)){
            for(int i : Utilidades.encontrarTodosLosIndicesDe(letra, solucion)){
                letrasDescubiertas[i] = letra;
            }

            mostrarDescubierto();
        }else{
            System.out.println("Fallo");
        }

        boton.setDisable(true);
    }

    private void mostrarDescubierto(){
        String textoAMostrar = "";

        for(int i : letrasDescubiertas){
            textoAMostrar = textoAMostrar.concat(Character.toString(i));
            textoAMostrar = textoAMostrar.concat(" ");
        }

        textoPalabra.setText(textoAMostrar);
    }

    /*private String generarPalabra(){
        try {
            Scanner lector = new Scanner(arhivoPalabra);

            int totLineas = 0;
            while (lector.hasNext()){
                totLineas++;
                lector.nextLine();
            }

            lector.close();

            Scanner buscador = new Scanner(arhivoPalabra);
            String palabra = "";

            int numAleatorio = Utilidades.aleatorioEntre(1, totLineas);
            for(int i = 0; i < numAleatorio; i++){
                palabra = buscador.nextLine();
            }

            System.out.println(palabra);

            if(palabra.matches(".*[^ñáéíóúÁÉÍÓÚa-zA-Z].*") || palabra.length() < 3 || palabra.length() > 20){
                return generarPalabra();
            }else{
                String[] tildes = {"á", "é", "í", "ó", "ú"};
                String[] sinTildes = {"a", "e", "i", "o", "u"};

                for(int i = 0; i < tildes.length; i++){
                    if(palabra.contains(tildes[i])){
                        palabra = palabra.replace(tildes[i], sinTildes[i]);
                    }
                }

                return palabra;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void obtenerArchivoPalabras(){
        try {
            Scanner lector = new Scanner(new File(JuegoAhorcadoController.class.getResource("opciones.txt").getPath()));
            String archivo = "castellano.txt";
            String linea;

            while (lector.hasNext()) {
                linea = lector.nextLine();
                if (linea.matches("archivoPalabras.*")) {
                    archivo = linea.split("=")[1];
                }
            }

            lector.close();

            arhivoPalabra = new File(Objects.requireNonNull(JuegoAhorcadoController.class.getResource("archivosDePalabras/" + archivo)).getPath());
        }catch (FileNotFoundException ex){
            System.out.println("Archivo opciones no encontrado");
        }catch (NullPointerException ex){
            System.out.println("Archivo opciones no econtrado");
        }
    }*/

    public void initPalabra(String palabra){
        palabraOculta = palabra.toUpperCase();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(palabraOculta);

        solucion = palabraOculta.toCharArray();
        letrasDescubiertas = new char[solucion.length];
        Arrays.fill(letrasDescubiertas, '_');
        mostrarDescubierto();
    }
}
