package com.practicafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private File arhivoPalabra;

    @FXML
    private void pulsadoBoton(ActionEvent event){
        Button boton = (Button) event.getSource();
        switch(boton.getId()){
            case "botonOk":
                break;
            case "botonAleatoria":
                abrirJugar(boton, generarPalabra());
                break;
            case "botonArchivo":
                CambiarFicheroController.abrirCambioDeFichero();
                obtenerArchivoPalabras();
                break;
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

    private void abrirJugar(Node nodo, String palabra){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MenuPrincipalController.class.getResource("fxml/juegoAhorcado.fxml"));

            Stage stage = (Stage) nodo.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            JuegoAhorcadoController controller = fxmlLoader.getController();
            controller.initPalabra(palabra);
            stage.setScene(scene);
        }catch (IOException ex){
            ex.initCause(ex.getCause());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        obtenerArchivoPalabras();
    }
}
