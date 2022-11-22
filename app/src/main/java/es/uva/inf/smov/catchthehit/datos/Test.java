package es.uva.inf.smov.catchthehit.datos;

import java.util.ArrayList;

public class Test {

    private ArrayList<String> preguntas;
    private ArrayList<Jugador> respuestas;

    public Test() {
        preguntas = new ArrayList<String>();
        setPreguntas();
        respuestas = new ArrayList<Jugador>();
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

    private String getPregunta(int index){
        return preguntas.get(index);
    }

    private void setRespuesta(int index, Jugador jugador){
        respuestas.add(index, jugador);
    }

    private Jugador getRespuesta(int index){
        return respuestas.get(index);
    }
}
