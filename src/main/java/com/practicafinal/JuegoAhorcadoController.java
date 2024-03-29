package com.practicafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


public class JuegoAhorcadoController {
    @FXML
    private Label textoPalabra;
    @FXML
    private ImageView imagenAhorcado;

    private String palabraOculta;
    private char[] solucion;
    private char[] letrasDescubiertas;

    private int fallos = 0;

    private Jugador jugador;

    @FXML
    private void pulsadoBoton(ActionEvent event){
        Button boton = (Button) event.getSource();

        char letra = boton.getText().charAt(0);

        if(Utilidades.arrayContiene(letra, solucion)){
            for(int i : Utilidades.encontrarTodosLosIndicesDe(letra, solucion)){
                letrasDescubiertas[i] = letra;
            }

            mostrarDescubierto();
            boton.setStyle("-fx-background-color: green");
        }else{
            System.out.println("Fallo");
            fallos++;

            if(fallos < 7) {
                imagenAhorcado.setImage(new Image(JuegoAhorcadoController.class.getResource("images/ahorcado/ahorcado" + fallos + ".png").toString()));
            }
            boton.setStyle("-fx-background-color: red");
        }

        boton.setDisable(true);
        comprobarResultado();
    }

    private void comprobarResultado(){
        if(fallos >= 6){
            MensajeFinalController.abrirMensajeFinal("HAS PERDIDO","Solucion: " + String.valueOf(solucion), ":(", "-5 puntos");
            MenuPrincipalController.abrirMenu((Stage) textoPalabra.getScene().getWindow());
            jugador.setPuntos(jugador.PuntosProperty().getValue()-5);
            jugador.setPartidasPerdidas(jugador.PartidasPerdidasProperty().getValue()+1);
        }
        if(Arrays.compare(solucion, letrasDescubiertas) == 0){
            MensajeFinalController.abrirMensajeFinal("HAS GANADO","Solucion: " + String.valueOf(solucion), "B)", "+10 puntos");
            MenuPrincipalController.abrirMenu((Stage) textoPalabra.getScene().getWindow());
            jugador.setPuntos(jugador.PuntosProperty().getValue()+10);
            jugador.setPartidasGanadas(jugador.PartidasGanadasProperty().getValue()+1);
        }
        AdministrarJugadores.actualizarPuntos(jugador);
    }

    private void mostrarDescubierto(){
        String textoAMostrar = "";

        for(int i : letrasDescubiertas){
            textoAMostrar = textoAMostrar.concat(Character.toString(i));
            textoAMostrar = textoAMostrar.concat(" ");
        }

        textoPalabra.setText(textoAMostrar);
    }

    public void initPalabra(String palabra){
        palabraOculta = palabra.toUpperCase();
        System.out.println(palabraOculta);

        solucion = palabraOculta.toCharArray();
        letrasDescubiertas = new char[solucion.length];
        Arrays.fill(letrasDescubiertas, '_');
        mostrarDescubierto();
    }

    public void initJugador(Jugador jugador){
        this.jugador = jugador;
        System.out.println(this.jugador.NombreProperty().getValue());
    }

    public static void abrirJugar(Stage stage, String palabra, Jugador jugador){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MenuPrincipalController.class.getResource("fxml/juegoAhorcado.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 350);

            stage.setTitle("El Ahorcado");

            JuegoAhorcadoController controller = fxmlLoader.getController();
            controller.initPalabra(palabra);
            controller.initJugador(jugador);

            stage.setScene(scene);
            stage.sizeToScene();

            //Recalcula el tamaño del escenario
            stage.hide();
            stage.show();
        }catch (IOException ex){
            ex.initCause(ex.getCause());
        }
    }
}
