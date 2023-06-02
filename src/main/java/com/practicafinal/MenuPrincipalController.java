package com.practicafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
                abrirJugar(boton);
                break;
            default:
                break;
        }
    }

    private void abrirJugar(Button boton){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MenuPrincipalController.class.getResource("fxml/juegoAhorcado.fxml"));

            Stage stage = (Stage) boton.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), boton.getScene().getWindow().getWidth(), boton.getScene().getWindow().getHeight());
            stage.setScene(scene);
        }catch (IOException ex){
            System.out.println("Tengo que poner buenos catch");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stageCambiarArchivo = new Stage();
        stageCambiarArchivo.initModality(Modality.APPLICATION_MODAL);

        botonCambiarArchivo.setOnAction(event -> {
            try {
                if(!stageCambiarArchivo.isShowing()) {
                    FXMLLoader fxmlLoader = new FXMLLoader(AhorcadoApplication.class.getResource("fxml/cambiarFichero.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 400, 200);
                    stageCambiarArchivo.setScene(scene);

                    stageCambiarArchivo.setTitle("Cambiar archivo");
                    stageCambiarArchivo.setResizable(false);
                    stageCambiarArchivo.showAndWait();
                }else{
                    stageCambiarArchivo.toFront();
                }
            }catch(IOException ex){
                System.out.println("Falló");
            }
        });
    }
}