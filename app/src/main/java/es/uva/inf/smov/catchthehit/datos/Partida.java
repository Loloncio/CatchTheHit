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
    Generamos una cadena alfanum�rica aleatoria de la longitud indicada
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
        this.preguntas.add("�Qu� jugador consideras que ha sido el m�s comunicativo? �Qui�n ha consultado m�s sus decisiones con el resto del grupo?");
        this.preguntas.add("�Qui�n ha sido el m�s positivo?");
        this.preguntas.add("�Qu� jugador ha sido el que m�s te ha apoyado con tus decisiones?");
        this.preguntas.add("�Qui�n crees que ha tenido m�s iniciativa?� Qu� jugador consideras que ha tenido m�s confianza en s� mismo?");
        this.preguntas.add("�Qu� jugador ha motivado m�s al resto con la decisi�n tomada?");
        this.preguntas.add("�Con qui�n te ha resultado m�s f�cil tomar la decisi�n?");
        this.preguntas.add("� Qui�n consideras que ha motivado m�s a tu equipo?");
        this.preguntas.add("� Qu� jugador ha sido el que m�s se ha expresado?");
        this.preguntas.add("�Qui�n se ha preocupado m�s por conseguir los objetivos individuales?");
        this.preguntas.add("� Qui�n se ha preocupado m�s por conseguir los objetivos comunes?");
        this.preguntas.add("� Qui�n ha sido el que m�s ha tratado de negociar sobre las posibles soluciones?");
        this.preguntas.add("�Qui�n ha sido el m�s persuasivo racionalmente?");
        this.preguntas.add("� Qui�n ha sido el jugador que m�s ha escuchado y tenido en cuenta las decisiones del resto?");
        this.preguntas.add("�Qui�n ha sido el m�s inspirador?");
        this.preguntas.add("� Qu� jugador crees que ha sido el que m�s ha arriesgado? � Qui�n ha sido m�s firme con sus decisiones?");
        this.preguntas.add("� Qui�n tiene cualidades diferenciales sobre el resto del equipo? � Qu� jugador consideras que ha destacado con respecto al resto?");
        this.preguntas.add("�Qui�n consideras que presenta m�s conocimientos sobre las decisiones que toma?");
        this.preguntas.add("� Qui�n est� haciendo la mejor labor por el funcionamiento del equipo? �Qu� jugador consideras que ha dirigido m�s al grupo ?");
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

