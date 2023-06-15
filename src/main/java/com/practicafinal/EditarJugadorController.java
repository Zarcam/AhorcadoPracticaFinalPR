package com.practicafinal;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditarJugadorController {
    @FXML
    private TextField nombreField;
    @FXML
    private TextField imagenField;
    @FXML
    private Button botonImagen;
    @FXML
    private Button botonConfirmar;
    @FXML
    private ImageView imagenJugador;

    private ObservableList<Jugador> listaJugadores;
    private Jugador jugador;

    @FXML
    private void pulsadoBoton(ActionEvent event){
        Button boton = (Button) event.getSource();

        switch(boton.getId()){
            case "botonImagen":
                try {
                    if(imagenField.getText().contains(";")){
                        throw new IllegalArgumentException("El URL contiene un ;");
                    }

                    Image imagen = new Image(imagenField.getText());
                    imagenJugador.setImage(imagen);
                }catch (NullPointerException | IllegalArgumentException ex){
                    System.out.println("URL de imagen no valido");
                }
                break;
            case "botonConfirmar":
                if(!nombreField.getText().contains(";") && !nombreField.getText().isBlank() && !comprobarRepetido()){
                    AdministrarJugadores.editarJugador(jugador, nombreField.getText(), imagenField.getText(), imagenJugador.getImage().getUrl());
                    Stage stage = (Stage) boton.getScene().getWindow();
                    stage.close();
                }else if(comprobarRepetido()){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText("Ese jugador ya existe");
                    alerta.setContentText("");
                    alerta.showAndWait();
                }
                break;
        }
    }

    private boolean comprobarRepetido(){
        for(Jugador i : listaJugadores){
            if(i.NombreProperty().getValue().equals(nombreField.getText())){
                return true;
            }
        }
        return false;
    }

    public static void abrirEditarJugador(Jugador jugador, ObservableList<Jugador> listaJugadores){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(EditarJugadorController.class.getResource("fxml/anadirYEditarJugador.fxml"));
            fxmlLoader.setController(new EditarJugadorController());

            Scene scene = new Scene(fxmlLoader.load(), 376, 230);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar jugador");
            stage.setResizable(false);

            EditarJugadorController controller = fxmlLoader.getController();
            controller.initJugadores(jugador, listaJugadores);

            stage.show();
        }catch (IOException ex){
            System.out.println("Fallo al cargar escena");
            System.out.println(ex.getCause().toString());
        }
    }

    public void initJugadores(Jugador jugador, ObservableList<Jugador> listaJugadores){
        this.jugador = jugador;
        this.listaJugadores = listaJugadores;
        nombreField.setText(jugador.NombreProperty().getValue());
        imagenJugador.setImage(jugador.ImagenProperty().getValue());
    }
}
