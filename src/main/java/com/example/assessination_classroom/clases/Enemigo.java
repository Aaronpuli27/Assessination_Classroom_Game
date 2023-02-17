package com.example.assessination_classroom.clases;

import com.example.assessination_classroom.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Enemigo {
    private int x;
    private int y;
    private String nombreImagen;
    private Jugador jugador;

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    private int velocidad;


    public Enemigo(int x, int y, String nombreImagen, int velocidad, Jugador jugador) {
        this.x = x;
        this.y = y;
        this.nombreImagen = nombreImagen;
        this.velocidad = velocidad;
        this.jugador = jugador;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public void pintar(GraphicsContext graficos){
        graficos.drawImage(Juego.imagenes.get(nombreImagen),x,y);
    }

    public void mover() {
        if (this.x == jugador.getX()){
            velocidad=0;
        }
        if (this.x > jugador.getX()){
            velocidad=-5;
        }
        if (this.x < jugador.getX()){
            velocidad=5;
        }
        x+=velocidad;
    }
}
