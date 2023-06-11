package com.practicafinal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RankingController implements Initializable {
    @FXML
    private TableColumn<Jugador, Image> columnaImagen;
    @FXML
    private TableColumn<Jugador, String> columnaNombre;
    @FXML
    private TableColumn<Jugador, Integer> columnaPuesto;
    @FXML
    private TableColumn<Jugador, Integer> columnaPuntos;
    @FXML
    private TableView<Jugador> tablaRanking;

    private ObservableList<Jugador> jugadores;

    public static void abrirRanking(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ModificarJugadoresController.class.getResource("fxml/ranking.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();

            stage.setTitle("Ranking");
            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();
        }catch (IOException ex){
            System.out.println("Coso raro");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Jugador> ranking = AdministrarJugadores.buscarRankingJugadores();

        jugadores = FXCollections.observableArrayList(ranking);
        tablaRanking.setItems(jugadores);

        columnaNombre.setCellValueFactory(celldata -> celldata.getValue().NombreProperty());
        columnaPuntos.setCellValueFactory(celldata -> celldata.getValue().PuntosProperty().asObject());
        columnaPuesto.setCellValueFactory(celldata -> celldata.getValue().PuestoProperty().asObject());

        columnaImagen.setCellValueFactory(celldata -> celldata.getValue().ImagenProperty());
        columnaImagen.setCellFactory(columna -> {
            return new TableCell<>(){
                private ImageView view = new ImageView();
                @Override
                protected void updateItem(Image item, boolean empty){
                    super.updateItem(item, empty);
                    if(item == null || empty){
                        setGraphic(null);
                    }else{
                        view.setImage(item);
                        setGraphic(view);
                    }
                }
            };
        });
    }
}
