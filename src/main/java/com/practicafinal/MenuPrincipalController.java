package com.practicafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

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
            default:
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botonCambiarArchivo.setOnAction(event -> {
            CambiarFicheroController.abrirCambioDeFichero();
        });
    }
}