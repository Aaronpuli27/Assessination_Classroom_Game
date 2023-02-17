package com.example.assessination_classroom.clases;

import com.example.assessination_classroom.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Proyectil {
    public static int x;
    public static int y;
    private String nombreImagen;
    private int limite;
    private Enemigo enemigo;
    private Jugador jugador;

    public Proyectil(int x, int y, String nombreImagen, int limite, Enemigo enemigo, Jugador jugador) {
        this.x = x;
        this.y = y;
        this.nombreImagen = nombreImagen;
        this.limite = limite;
        this.enemigo = enemigo;
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
        graficos.drawImage(Juego.imagenes.get(nombreImagen),x,this.y);
    }

    public void mover(){
        if (y>limite){
            y=250;

            if (this.enemigo.getVelocidad()>0) {
                x = this.enemigo.getX() + 30;
            } else {
                x= this.enemigo.getX()+10;
            }
        }

        y +=12;
    }

    public static Rectangle obtenerRectangle(GraphicsContext graficos){
        return new Rectangle(x,y,Juego.imagenes.get("cuchillo").getWidth(),Juego.imagenes.get("cuchillo").getHeight());

    }
}
