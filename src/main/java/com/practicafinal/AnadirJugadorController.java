package com.practicafinal;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AnadirJugadorController {
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

    private ObservableList<Jugador> jugadores;

    @FXML
    private void pulsadoBoton(ActionEvent event){
        Button boton = (Button) event.getSource();

        switch (boton.getId()){
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
                    AdministrarJugadores.crearJugador(nombreField.getText(), imagenField.getText(), jugadores, imagenJugador.getImage());
                    Stage stage = (Stage) boton.getScene().getWindow();
                    stage.close();
                }
                break;
            default:
                break;
        }
    }

    private boolean comprobarRepetido(){
        for(Jugador i : jugadores){
            if(i.NombreProperty().getValue().equals(nombreField.getText())){
                return true;
            }
        }
        return false;
    }

    public static void abrirAnadirJugador(ObservableList<Jugador> listaJugadores){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AnadirJugadorController.class.getResource("fxml/anadirJugador.fxml"));
            Parent parent = fxmlLoader.load();

            AnadirJugadorController controller = fxmlLoader.getController();
            controller.initListaJugadores(listaJugadores);

            Scene scene = new Scene(parent, 376, 230);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Añadir jugador");
            stage.setResizable(false);

            stage.show();
        }catch (IOException ex){
            System.out.println("Fallo al cargar escena");
            System.out.println(ex.getCause().toString());
        }

    }

    public void initListaJugadores(ObservableList<Jugador> listaJugadores){
        this.jugadores = listaJugadores;
    }
}
