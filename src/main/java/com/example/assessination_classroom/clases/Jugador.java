package com.example.assessination_classroom.clases;

import com.example.assessination_classroom.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;

public class Jugador{
    private int x;
    private int y;
    private int vida;
    private String nombreImagen;
    private int velocidad;


    public Jugador(int x, int y, int vida, String nombreImagen, int velocidad) {
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.nombreImagen = nombreImagen;
        this.velocidad = velocidad;
    }


    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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

    //Se ejecuta por cada iteracion por ciclo de juego
    public void mover(){

        if (Juego.derecha && x<1410)//Mover a la derecha
            x+=velocidad;

        if (Juego.izquierda && x>-0)//Mover a la izquierda
            x-=velocidad;

        if (Juego.arriba && y>=370)//Mover a la arriba
            y-=velocidad;

        if (Juego.abajo && y<=525)//Mover a la abajo
            y+=velocidad;

    }

    public Rectangle obtenerRectangle(GraphicsContext graficos){
        return new Rectangle(x,y,Juego.imagenes.get("koro").getWidth()-10,Juego.imagenes.get("koro").getHeight());

    }

    public void comprobarColision(GraphicsContext graficos){
        if (this.obtenerRectangle(graficos).getBoundsInLocal().intersects(Proyectil.obtenerRectangle(graficos).getBoundsInLocal())){
            vida--;
            System.out.println("Colision");
            if (vida<=0){
                Juego.defeat = true;
            }

        }
    }

}
