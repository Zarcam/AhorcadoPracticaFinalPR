package com.practicafinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EstadisticasController implements Initializable {
    @FXML
    private ImageView imagenJugador;
    @FXML
    private Label labelGanadas;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelPerdidas;
    @FXML
    private Label labelPuntos;
    @FXML
    private Label labelRatio;
    @FXML
    private ChoiceBox<Jugador> seleccionJugador;

    private ObservableList<Jugador> jugadores;

    public static void abrirEstadisticas(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ModificarJugadoresController.class.getResource("fxml/estadisticas.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();

            stage.setTitle("Estadisticas");
            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();
        }catch (IOException ex){
            System.out.println("Coso raro");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Jugador> listaJugadores = AdministrarJugadores.extraerJugadoresFichero();

        jugadores = FXCollections.observableArrayList(listaJugadores);
        seleccionJugador.setItems(jugadores);

        seleccionJugador.getSelectionModel().selectedItemProperty().addListener((observableValue, jugadorSingleSelectionModel, t1) -> {
            Jugador jugadorSeleccionado = seleccionJugador.getSelectionModel().getSelectedItem();

            imagenJugador.setImage(jugadorSeleccionado.ImagenProperty().getValue());
            labelNombre.setText(jugadorSeleccionado.NombreProperty().getValue());
            labelGanadas.setText(jugadorSeleccionado.PartidasGanadasProperty().getValue().toString());
            labelPerdidas.setText(jugadorSeleccionado.PartidasPerdidasProperty().getValue().toString());
            labelPuntos.setText(jugadorSeleccionado.PuntosProperty().getValue().toString());

            int ratio;
            try {
                ratio = jugadorSeleccionado.PartidasGanadasProperty().getValue() / jugadorSeleccionado.PartidasPerdidasProperty().getValue();
            }catch (ArithmeticException ex){
                ratio = 0;
            }
            labelRatio.setText(Integer.toString(ratio));
        });
    }
}
