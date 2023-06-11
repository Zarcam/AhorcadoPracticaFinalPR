package com.practicafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
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
                OpcionesPartidaController.abrirOpcionesPartida((Stage) boton.getScene().getWindow());
                break;
            case "listar/modificar jugadores":
                ModificarJugadoresController.abrirModificarJugadores((Stage) boton.getScene().getWindow());
                break;
            case "ranking":
                RankingController.abrirRanking();
                break;
            default:
                break;
        }
    }

    public static void abrirMenu(Stage stage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AhorcadoApplication.class.getResource("fxml/menuPrincipal.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.sizeToScene();

            //Recalcula tamaño del escenario
            stage.hide();
            stage.show();
        }catch(IOException ex){
            System.out.println("AAAAAAA");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botonCambiarArchivo.setOnAction(event -> {
            CambiarFicheroController.abrirCambioDeFichero();
        });
    }
}