package com.example.assessination_classroom;

import com.example.assessination_classroom.clases.Enemigo;
import com.example.assessination_classroom.clases.Jugador;
import com.example.assessination_classroom.clases.Proyectil;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.HashMap;


//Con la ayuda del canal de YouTube @ErickVladimirReyesMarin he aprendido ha realizar este videojuego.


public class Juego extends Application {
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas lienzo;
    private int x = 0;
    private Jugador jugador;
    public static boolean arriba;
    public static boolean abajo;
    public static boolean izquierda;
    public static boolean derecha;
    public static HashMap<String, Image> imagenes;
    private Enemigo  enemigo1;
    private Proyectil proyectil1;
    public static boolean defeat, victoria;
    private int tiempo = 1;
    AudioClip music;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ventana) throws Exception{
        inicializarComponentes();
        gestionEventos();
        ventana.setScene(escena);
        ventana.setTitle("Assessination_Classroom_Game");
        ventana.show();
        cicloJuego();

    }

    public void cicloJuego(){
        AnimationTimer animationTimer = new AnimationTimer() {
            //Este metodo se ejecuta unas 60 veces por segundo
            @Override
            public void handle(long tiempoActual) {
                tiempo++;

                if(!defeat && !victoria){
                    actualizarEstado();
                    pintar();
                } else {
                    graficos.drawImage(imagenes.get("game-over"),0,0);
                    music.stop();
                }
                System.out.println(tiempo);
                if (tiempo>1854 && !defeat) {
                    victoria = true;
                    System.out.println("Has ganado!");
                    graficos.drawImage(imagenes.get("victoria"),0,0);
                }


            }
        };
        animationTimer.start();//Empieza el ciclo de juego

    }

    public void actualizarEstado(){
        jugador.mover();
        enemigo1.mover();
        proyectil1.mover();
        jugador.comprobarColision(graficos);
    }

    public void inicializarComponentes(){
        imagenes = new HashMap<String, Image>();
        cargarImagenes();
        jugador = new Jugador(600,510,1,"koro",5);
        root = new Group();
        escena = new Scene(root, 1500, 750);
        lienzo = new Canvas(1500, 750);
        root.getChildren().add(lienzo);
        graficos = lienzo.getGraphicsContext2D();
        enemigo1 = new Enemigo(455,250,"nagisa",5, jugador);
        proyectil1 = new Proyectil(480,290,"cuchillo",770, enemigo1,jugador);
        music = new AudioClip(getClass().getResource("tension_music.mp3").toExternalForm());
        music.play();


    }

    public void cargarImagenes(){
        imagenes.put("koro",new Image(Juego.class.getResource("koro.png").toExternalForm()));
        imagenes.put("escenario",new Image(Juego.class.getResource("escenario.png").toExternalForm()));
        imagenes.put("koro_power_up", new Image(Juego.class.getResource("koro_power_up.png").toExternalForm()));
        imagenes.put("nagisa", new Image(Juego.class.getResource("nagisa.png").toExternalForm()));
        imagenes.put("bala",new Image(Juego.class.getResource("bala.png").toExternalForm()));
        imagenes.put("cuchillo", new Image(Juego.class.getResource("cuchillo.png").toExternalForm()));
        imagenes.put("game-over",new Image(Juego.class.getResource("game-over.jpg").toExternalForm()));
        imagenes.put("victoria",new Image(Juego.class.getResource("victoria.jpg").toExternalForm()));
    }

    public void pintar(){
        graficos.drawImage(imagenes.get("escenario"),0,0);
        jugador.pintar(graficos);
        enemigo1.pintar(graficos);
        proyectil1.pintar(graficos);

    }
    public void gestionEventos(){
        escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
            //El metodo handle se ejecuta cada vez que se presiona una tecla
            @Override
            public void handle(KeyEvent evento) {

                switch (evento.getCode().toString()){
                    case "RIGHT":
                        derecha = true;
                        break;
                    case "LEFT":
                        izquierda = true;
                        break;
                    case "UP":
                        arriba = true;
                        break;
                    case "DOWN":
                        abajo = true;
                        break;
                    case "SPACE":
                        jugador.setVelocidad(15);
                        jugador.setNombreImagen("koro_power_up");
                        break;
                }

            }
        });
        escena.setOnKeyReleased(new EventHandler<KeyEvent>() {
            //Este evento se ejecuta cuando se suelta una tecla
            @Override
            public void handle(KeyEvent evento) {
                switch (evento.getCode().toString()){
                    case "RIGHT":
                        derecha = false;
                        break;
                    case "LEFT":
                        izquierda = false;
                        break;
                    case "UP":
                        arriba = false;
                        break;
                    case "DOWN":
                        abajo = false;
                        break;
                    case "SPACE":
                        jugador.setVelocidad(8);
                        jugador.setNombreImagen("koro");
                        break;
                }
            }
        });

    }

}