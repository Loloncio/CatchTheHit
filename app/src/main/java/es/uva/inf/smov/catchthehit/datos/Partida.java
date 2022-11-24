package es.uva.inf.smov.catchthehit.datos;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.List;


public class Partida implements Serializable {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    private String codigo;
    private int rondas;
    private int rondaAct;
    private Equipo Equipo1;
    private Equipo Equipo2;

    public Partida(){

    }
    public Partida(int noRondas){
        codigo = randomString(5);
        rondas = noRondas;
        rondaAct=1;
        Equipo1 = new Equipo();
        Equipo2 = new Equipo();
    }

    public static SecureRandom getRnd() {
        return rnd;
    }

    public static void setRnd(SecureRandom rnd) {
        Partida.rnd = rnd;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    public void setRondaAct(int rondaAct) {
        this.rondaAct = rondaAct;
    }

    public void setEquipo1(Equipo equipo1) {
        Equipo1 = equipo1;
    }

    public void setEquipo2(Equipo equipo2) {
        Equipo2 = equipo2;
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

