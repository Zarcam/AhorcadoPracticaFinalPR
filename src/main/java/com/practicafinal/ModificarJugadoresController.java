package com.practicafinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ModificarJugadoresController implements Initializable {
    @FXML
    private TableView<Jugador> tablaJugadores;
    @FXML
    private TableColumn<Jugador, Image> columnaImagen;
    @FXML
    private TableColumn<Jugador, String> columnaNombre;

    private ObservableList<Jugador> jugadores;

    @FXML
    private void pulsadoBoton(ActionEvent event){
        Button boton = (Button) event.getSource();


    }

    public static void abrirModificar(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ModificarJugadoresController.class.getResource("fxml/modificarJugadores.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();

            stage.setTitle("Modificar");
            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();
        }catch (IOException ex){
            System.out.println("Coso raro");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();

        String path = ModificarJugadoresController.class.getResource("images/ayuda.png").toString();
        Image imagen = new Image(path, 40, 40, true, true);

        Jugador jugador1 = new Jugador("Pepe", imagen, 0, 0, 0);
        listaJugadores.add(jugador1);

        jugadores = FXCollections.observableArrayList(listaJugadores);
        tablaJugadores.setItems(jugadores);

        columnaNombre.setCellValueFactory(celldata -> celldata.getValue().NombreProperty());
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
                      view.setImage(imagen);
                      setGraphic(view);
                  }
              }
            };
        });
    }
}
