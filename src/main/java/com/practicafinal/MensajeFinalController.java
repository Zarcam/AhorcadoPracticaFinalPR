package com.practicafinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MensajeFinalController implements Initializable {
    @FXML
    private Text textResultado;
    @FXML
    private Text textSolucion;
    @FXML
    private Button botonOk;
    @FXML
    private Label labelPuntos;

    public static void abrirMensajeFinal(String resultado, String solucion, String simboloBoton, String puntos){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MenuPrincipalController.class.getResource("fxml/mensajeFinal.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 325, 205);
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Resultado");
            stage.setResizable(false);
            stage.setScene(scene);

            MensajeFinalController controller = fxmlLoader.getController();
            controller.initMensaje(resultado, solucion, simboloBoton, puntos);

            stage.showAndWait();
        }catch (IOException ex){
            System.out.println("Tengo que poner buenos catch");
        }
    }

    public void initMensaje(String resultado, String solucion, String simboloBoton, String puntos){
        textResultado.setText(resultado);
        textSolucion.setText(solucion);

        botonOk.setText(simboloBoton);
        labelPuntos.setText(puntos);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botonOk.setOnAction(event -> {
            Stage stage = (Stage) botonOk.getScene().getWindow();
            stage.close();
        });
    }
}
