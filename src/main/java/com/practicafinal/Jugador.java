package com.practicafinal;

import javafx.beans.property.*;
import javafx.scene.image.Image;

public class Jugador {
    private StringProperty nombre = new SimpleStringProperty();
    private ObjectProperty<Image> imagen = new SimpleObjectProperty<>();
    private IntegerProperty puntos = new SimpleIntegerProperty();
    private IntegerProperty partidasGanadas = new SimpleIntegerProperty();
    private IntegerProperty partidasPerdidas = new SimpleIntegerProperty();

    public Jugador(String nombre, Image imagen, int puntos, int partidasGanadas, int partidasPerdidas){
        this.nombre.setValue(nombre);
        this.imagen.setValue(imagen);
        this.puntos.setValue(puntos);
        this.partidasGanadas.setValue(partidasGanadas);
        this.partidasPerdidas.setValue(partidasPerdidas);
    }


    public StringProperty NombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.setValue(nombre);
    }

    public ObjectProperty<Image> ImagenProperty() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen.setValue(imagen);
    }

    public IntegerProperty PuntosProperty() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos.setValue(puntos);
    }

    public IntegerProperty PartidasGanadasProperty() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas.setValue(partidasGanadas);
    }

    public IntegerProperty PartidasPerdidasProperty() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas.setValue(partidasPerdidas);
    }
}
