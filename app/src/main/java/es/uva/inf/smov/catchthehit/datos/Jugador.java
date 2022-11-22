package es.uva.inf.smov.catchthehit.datos;

import java.util.ArrayList;
import java.util.Random;

public class Jugador {
    private int [] probabilidad = new int[100];
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

    public Jugador(String nombbre){
        String nombre;
        usuario = "";
        probabilidad = llenaProbabilidad();
        fuerza = valorRandom();
        reflejos = valorRandom();
        velocidad = valorRandom();
        resistencia = 100;
        ready = false;
        enjuego = true;
        test = new Test();
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
    /*
        Obtenemos un valor entre 50 y 100 con la probabilidad indicada en llenaProbabilidad.
         */
    private int valorRandom(){
        Random random = new Random();
        int valor = probabilidad[random.nextInt(probabilidad.length)];
        return valor;
    }
    /*
    Rellenamos el array probabilidad de forma que tengamos
    20% de valores entre 50 y 60
    45% entre 60 y 75
    20% entre 75 y 85
    15% entre 85 y 100
     */
    private int [] llenaProbabilidad(){
        int cont = 0;
        int [] valores = new int[100];
        for(int i = 50; i<=100; i++){
            if (i<=60)
                for(int j=0;j<2;j++){
                    valores[cont]=i;
                    cont++;
                }
            else if (i<=75 && i>60)
                for(int j=0;j<3;j++){
                    valores[cont]=i;
                    cont++;
                }
            else if (i>75 && i<=85)
                for(int j=0;j<2;j++){
                    valores[cont]=i;
                    cont++;
                }
            else
                for(int j=0;j<1;j++){
                    valores[cont]=i;
                    cont++;
                }
        }
        return valores;
    }
}
