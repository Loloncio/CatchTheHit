package es.uva.inf.smov.catchthehit.datos;

import java.security.SecureRandom;
import java.util.ArrayList;


public class Partida {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    private String codigo;
    private int rondas;
    private int rondaAct;
    private Equipo Equipo1;
    private Equipo Equipo2;

    public Partida(){
        codigo = randomString(5);
        rondas = 0;
        rondaAct=1;
        Equipo1 = new Equipo();
        Equipo2 = new Equipo();
    }
    /*
    Generamos una cadena alfanumérica aleatoria de la longitud indicada
     */
    String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public String getCodigo() {
        return codigo;
    }

    public int getRondaAct() {
        return rondaAct;
    }

    public int getRondas() {
        return rondas;
    }

    public Equipo getEquipo1() {
        return Equipo1;
    }

    public Equipo getEquipo2() {
        return Equipo2;
    }
}

