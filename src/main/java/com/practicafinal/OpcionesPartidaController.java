package com.practicafinal;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class OpcionesPartidaController implements Initializable {
    @FXML
    Button botonOk;
    @FXML
    Button botonAleatoria;
    @FXML
    Button botonArchivo;
    @FXML
    TextField fieldPalabra;
    @FXML
    ChoiceBox cajaJugador;
    @FXML
    Tooltip pistaField;


    private File arhivoPalabra;

    private Timeline animacionBarra = new Timeline();

    @FXML
    private void pulsadoBoton(ActionEvent event){
        Button boton = (Button) event.getSource();
        switch(boton.getId()){
            case "botonOk":
                String palabra = fieldPalabra.getText();

                if(comprobarPalabraValida(palabra)){
                    JuegoAhorcadoController.abrirJugar((Stage) boton.getScene().getWindow(), reemplazarTildes(palabra));
                }else{
                    animacionBarra.play();
                    System.out.println("MAAL");
                }
                break;
            case "botonAleatoria":
                JuegoAhorcadoController.abrirJugar((Stage) boton.getScene().getWindow(), generarPalabra());
                break;
            case "botonArchivo":
                CambiarFicheroController.abrirCambioDeFichero();
                obtenerArchivoPalabras();
                break;
        }
    }

    private boolean comprobarPalabraValida(String palabra){
        if(palabra.matches(".*[^ñáéíóúüÁÉÍÓÚÜa-zA-Z].*") || palabra.length() < 3 || palabra.length() > 20){
            return false;
        }else{
            return true;
        }
    }

    private String reemplazarTildes(String palabra){
        String[] tildes = {"á", "é", "í", "ó", "ú"};
        String[] sinTildes = {"a", "e", "i", "o", "u"};

        for(int i = 0; i < tildes.length; i++){
            if(palabra.contains(tildes[i])){
                palabra = palabra.replace(tildes[i], sinTildes[i]);
            }
        }

        if(palabra.contains("ü")){
            palabra = palabra.replace("ü", "u");
        }

        return palabra;
    }

    private void obtenerArchivoPalabras(){
        try {
            Scanner lector = new Scanner(new File(OpcionesPartidaController.class.getResource("opciones.txt").getPath()));
            String archivo = "castellano.txt";
            String linea;

            while (lector.hasNext()) {
                linea = lector.nextLine();
                if (linea.matches("archivoPalabras.*")) {
                    archivo = linea.split("=")[1];
                }
            }

            lector.close();

            arhivoPalabra = new File(Objects.requireNonNull(OpcionesPartidaController.class.getResource("archivosDePalabras/" + archivo)).getPath());
        }catch (FileNotFoundException ex){
            System.out.println("Archivo opciones no encontrado");
        }catch (NullPointerException ex){
            System.out.println("Archivo opciones no econtrado");
        }
    }

    private String generarPalabra(){
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

            if(!comprobarPalabraValida(palabra)){
                return generarPalabra();
            }else{
                return reemplazarTildes(palabra);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void abrirOpcionesPartida(Stage stage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MenuPrincipalController.class.getResource("fxml/opcionesPartida.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 350, 250);

            stage.setTitle("Opciones de la partida");

            stage.setScene(scene);
            stage.sizeToScene();

            //Recalcula el tamaño del escenario
            stage.hide();
            stage.show();
        }catch (IOException ex){
            System.out.println("Tengo que poner buenos catch");
        }
    }

    private void prepararAnimacion(){
        animacionBarra.setCycleCount(1);

        KeyValue kv = new KeyValue(fieldPalabra.rotateProperty(), 2, Interpolator.LINEAR);
        KeyValue kv2 = new KeyValue(fieldPalabra.rotateProperty(), 0, Interpolator.LINEAR);
        KeyValue kv3 = new KeyValue(fieldPalabra.rotateProperty(), -2, Interpolator.LINEAR);

        KeyFrame kf = new KeyFrame(Duration.millis(0), kv);
        KeyFrame kf2 = new KeyFrame(Duration.millis(100), kv2);
        KeyFrame kf3 = new KeyFrame(Duration.millis(200), kv3);
        KeyFrame kf4 = new KeyFrame(Duration.millis(300), kv2);

        animacionBarra.getKeyFrames().add(0, kf);
        animacionBarra.getKeyFrames().add(1, kf2);
        animacionBarra.getKeyFrames().add(2, kf3);
        animacionBarra.getKeyFrames().add(3, kf4);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        obtenerArchivoPalabras();
        prepararAnimacion();

        pistaField.setShowDelay(Duration.millis(100));
        pistaField.setHideDelay(Duration.millis(1000));
    }
}
