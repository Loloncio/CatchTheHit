package es.uva.inf.smov.catchthehit.datos;

import java.util.ArrayList;
import java.util.Arrays;

public class Equipo {

    private int puntos;
    private ArrayList<Jugador> jugadores;

    public Equipo(){
        puntos = 0;
        jugadores = creaJugadores();
    }

    public ArrayList creaJugadores(){
        ArrayList<Jugador> equipo = new ArrayList<Jugador>(9);
        ArrayList<String> nombres = new ArrayList<String>();
        nombres.addAll(Arrays.asList("Babe","Ted","Willie","Stan","John","Jackson","Lucas","Charlie","Andrey"));
        for(int i=0; i<9 ; i++){
            equipo.add(new Jugador(nombres.get(i)));
        }
        return equipo;
    }

}
