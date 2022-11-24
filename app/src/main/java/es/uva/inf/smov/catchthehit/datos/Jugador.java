package es.uva.inf.smov.catchthehit.datos;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Rojo - 1
Verde - 2
Azul - 3
Morado - 4
...
Orden de izquierda a derecha y arriba abajo en la vista equipo_defensa.
 */
public class Jugador implements Serializable {
    private ArrayList<Integer> probabilidad;
    private String nombre;
    private String usuario;
    private int fuerza;
    private int velocidad;
    private int resistencia;
    private int reflejos;
    private int posicion;
    private boolean ready;
    private boolean enjuego;
    private Test test;
    private int id;

    public Jugador(){

    }
    public Jugador(String nombre, int i, String user){
        this.nombre = nombre;
        usuario = user;
        probabilidad = llenaProbabilidad();
        fuerza = valorRandom();
        reflejos = valorRandom();
        velocidad = valorRandom();
        resistencia = 100;
        ready = false;
        enjuego = true;
        test = new Test();
        id = i;
    }

    public String getUsuario(){
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEnjuego() {
        return enjuego;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getReflejos() {
        return reflejos;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public boolean isReady() {
        return ready;
    }

    public Test getTest() {
        return test;
    }

    public void setFuerza(int valor){
        fuerza = valor;
    }

    public void setResistencia(int valor){
        resistencia = valor;
    }

    public void setPosicion(int valor){
        posicion = valor;
    }

    public void setReflejos(int valor){
        reflejos = valor;
    }

    public void setVelocidad(int valor){
        velocidad = valor;
    }

    public void setReady(boolean valor){
        ready = valor;
    }

    public void setEnjuego(boolean valor){
        enjuego = valor;
    }

    public void setUsuario(String user){
        usuario = user;
    }

    public int getId(){ return id; }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    /*
            Obtenemos un valor entre 50 y 100 con la probabilidad indicada en llenaProbabilidad.
             */
    private int valorRandom(){
        Random random = new Random();
        int valor = probabilidad.get(random.nextInt(probabilidad.size()));
        return valor;
    }

    /*
            Rellenamos el array probabilidad de forma que tengamos
            20% de valores entre 50 y 60
            45% entre 60 y 75
            20% entre 75 y 85
            15% entre 85 y 100
             */
    private ArrayList llenaProbabilidad(){
        int cont = 0;
        ArrayList<Integer> valores = new ArrayList<Integer>(100);
        for(int i = 51; i<=100; i++){
            if (i<=60)
                for(int j=0;j<2;j++){
                    valores.add(cont,i);
                    cont++;
                }
            else if (i<=75 && i>60)
                for(int j=0;j<3;j++){
                    valores.add(cont,i);
                    cont++;
                }
            else if (i>75 && i<=85)
                for(int j=0;j<2;j++){
                    valores.add(cont,i);
                    cont++;
                }
            else {
                valores.add(cont,i);
                cont++;
            }
        }

        return valores;
    }
}
