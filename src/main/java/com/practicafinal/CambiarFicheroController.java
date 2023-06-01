package com.practicafinal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
public class CambiarFicheroController implements Initializable {
    @FXML
    Button botonAyuda;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botonAyuda.setOnAction(event -> {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);

            mensaje.setTitle("Añadir archivo");
            mensaje.setHeaderText("");
            mensaje.setContentText("Para añadir un archivo con palabras personalizadas" +
                    " inserta un archivo con extension .txt en la carpeta src/main/resources/archivosDePalabras dentro del proyecto.\n\n" +
                    "Las palabras con espacios en blanco o caracteres especiales serán omitidas, las vocales con tilde serán reemplazadas por su respectiva vocal sin tilde, cada palabra debe ir en una linea diferente.");

            mensaje.showAndWait();
        });
    }
}
