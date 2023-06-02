package com.practicafinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class CambiarFicheroController implements Initializable {
    @FXML
    private Button botonAyuda;
    @FXML
    private Button botonSeleccion;
    @FXML
    private ListView<String> listaFicheros;

    private ObservableList<String> datos;
    private File directorioArchivos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> lista = new ArrayList<>();
        datos = FXCollections.observableList(lista);
        listaFicheros.setItems(datos);

        try {
            directorioArchivos = new File(CambiarFicheroController.class.getResource("archivosDePalabras").getPath());

            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (name.endsWith(".txt")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };

            for (String i : directorioArchivos.list(filter)) {
                datos.add(i);
            }

            listaFicheros.refresh();
        }catch (NullPointerException ex){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("");
            error.setContentText("El directorio de archivos esta vacío.\n\nPulse el boton de informacion en la esquina inferior derecha.");
            error.showAndWait();
        }

        botonAyuda.setOnAction(event -> {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);

            mensaje.setTitle("Añadir archivo");
            mensaje.setHeaderText("");
            mensaje.setContentText("Para añadir un archivo con palabras personalizadas" +
                    " inserta un archivo con extension .txt en la carpeta src/main/resources/archivosDePalabras dentro del proyecto.\n\n" +
                    "Las palabras con espacios en blanco o caracteres especiales serán omitidas, las vocales con tilde serán reemplazadas por su respectiva vocal sin tilde, cada palabra debe ir en una linea diferente.");

            mensaje.showAndWait();
        });

        botonSeleccion.setOnAction(event -> {
            try {
                File opciones = new File(CambiarFicheroController.class.getResource("opciones.txt").getPath());
                String seleccion = datos.get(listaFicheros.getSelectionModel().getSelectedIndex());

                Utilidades.reemplazarLinea(opciones, "archivoPalabras=", "archivoPalabras="+seleccion);

                Button boton = (Button) event.getSource();
                Stage stage = (Stage) boton.getScene().getWindow();
                stage.close();
            }catch (NullPointerException ex){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("");
                error.setContentText("No se ha encontrado el archivo de opciones");
                error.showAndWait();
            }
        });

        listaFicheros.getSelectionModel().selectedIndexProperty().addListener(((observableValue, number, t1) -> {
            if(listaFicheros.getSelectionModel().getSelectedIndex() < 0){
                botonSeleccion.setDisable(true);
            }else{
                botonSeleccion.setDisable(false);
            }
        }));
    }
}
