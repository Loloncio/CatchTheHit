package es.uva.inf.smov.catchthehit.datos;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


public class Partida implements Serializable {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    private String codigo;
    private int rondas;
    private int rondaAct;
    private Equipo Equipo1;
    private Equipo Equipo2;
    private ArrayList<String> preguntas;

    public Partida(){

    }
    public Partida(int noRondas){
        codigo = randomString(5);
        rondas = noRondas;
        rondaAct=1;
        Equipo1 = new Equipo();
        Equipo2 = new Equipo();
        preguntas = new ArrayList<String>();
        setPreguntas();
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

    private void setPreguntas() {
        this.preguntas.add("¿Qué jugador consideras que ha sido el más comunicativo? ¿Quién ha consultado más sus decisiones con el resto del grupo?");
        this.preguntas.add("¿Quién ha sido el más positivo?");
        this.preguntas.add("¿Qué jugador ha sido el que más te ha apoyado con tus decisiones?");
        this.preguntas.add("¿Quién crees que ha tenido más iniciativa?¿ Qué jugador consideras que ha tenido más confianza en sí mismo?");
        this.preguntas.add("¿Qué jugador ha motivado más al resto con la decisión tomada?");
        this.preguntas.add("¿Con quién te ha resultado más fácil tomar la decisión?");
        this.preguntas.add("¿ Quién consideras que ha motivado más a tu equipo?");
        this.preguntas.add("¿ Qué jugador ha sido el que más se ha expresado?");
        this.preguntas.add("¿Quién se ha preocupado más por conseguir los objetivos individuales?");
        this.preguntas.add("¿ Quién se ha preocupado más por conseguir los objetivos comunes?");
        this.preguntas.add("¿ Quién ha sido el que más ha tratado de negociar sobre las posibles soluciones?");
        this.preguntas.add("¿Quién ha sido el más persuasivo racionalmente?");
        this.preguntas.add("¿ Quién ha sido el jugador que más ha escuchado y tenido en cuenta las decisiones del resto?");
        this.preguntas.add("¿Quién ha sido el más inspirador?");
        this.preguntas.add("¿ Qué jugador crees que ha sido el que más ha arriesgado? ¿ Quién ha sido más firme con sus decisiones?");
        this.preguntas.add("¿ Quién tiene cualidades diferenciales sobre el resto del equipo? ¿ Qué jugador consideras que ha destacado con respecto al resto?");
        this.preguntas.add("¿Quién consideras que presenta más conocimientos sobre las decisiones que toma?");
        this.preguntas.add("¿ Quién está haciendo la mejor labor por el funcionamiento del equipo? ¿Qué jugador consideras que ha dirigido más al grupo ?");
    }

    public String getPregunta(int index){
        return preguntas.get(index);
    }

    public ArrayList<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<String> preguntas) {
        this.preguntas = preguntas;
    }
}

