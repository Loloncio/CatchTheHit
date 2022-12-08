package es.uva.inf.smov.catchthehit.datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Equipo implements Serializable {

    private int puntos;
    private ArrayList<Jugador> jugadores;

    public Equipo(){
        puntos = 0;
        jugadores = creaJugadores();
    }

    public ArrayList creaJugadores(){
        ArrayList<Jugador> equipo = new ArrayList<Jugador>(9);
        ArrayList<String> nombres = new ArrayList<String>(9);
        nombres.addAll(Arrays.asList("Tom","Ted","Willie","Stan","John","Jackson","Lucas","Charlie","Andrey"));
        for(int i=0; i<9 ; i++){
            equipo.add(new Jugador(nombres.get(i), i, ""));
        }
        return equipo;
    }

    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int pt){
        puntos=pt;
    }
    public Jugador elegirJugador(int index){
        return jugadores.get(index);
    }
    public void setJugadores(ArrayList<Jugador> players){
        jugadores = players;
    }
    public List<Jugador> getJugadores() {
        return jugadores;
    }
    public void incrementaPuntos(){
        puntos++;
    }
}
