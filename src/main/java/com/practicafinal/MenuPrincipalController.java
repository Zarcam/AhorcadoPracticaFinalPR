package com.practicafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {
    @FXML
    private MenuItem botonCambiarArchivo;

    @FXML
    private void pulsadoBoton(ActionEvent event){
        Button boton = (Button) event.getSource();

        switch(boton.getText().toLowerCase()){
            case "jugar":
                abrirOpcionesPartida(boton);
                break;
            default:
                break;
        }
    }

    private void abrirOpcionesPartida(Node nodo){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MenuPrincipalController.class.getResource("fxml/opcionesPartida.fxml"));

            Stage stage = (Stage) nodo.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 300, 200);

            stage.setTitle("Opciones de la partida");
            stage.setResizable(true);
            stage.setHeight(250);
            stage.setWidth(350);
            stage.setScene(scene);
        }catch (IOException ex){
            System.out.println("Tengo que poner buenos catch");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botonCambiarArchivo.setOnAction(event -> {
            CambiarFicheroController.abrirCambioDeFichero();
        });
    }
}