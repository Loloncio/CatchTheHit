package es.uva.inf.smov.catchthehit.datos;

import java.util.ArrayList;

public class Test {

    private ArrayList<String> preguntas;
    private ArrayList<Jugador> respuestas;

    public Test(ArrayList<String> preg, ArrayList<Jugador> jugadores) {
        preguntas = new ArrayList<String>();
        setPreguntas();
        respuestas = new ArrayList<Jugador>();
    }

    private void setPreguntas() {
        this.preguntas.add("�Qu� jugador consideras que ha sido el m�s comunicativo? qui�n ha consultado m�s sus decisiones con el resto del grupo");
        this.preguntas.add("�Qui�n ha sido el m�s positivo?");
        this.preguntas.add("�Qui�n consideras que ha tenido mayor atenci�n a la hora de explicar su decisi�n final?");
        this.preguntas.add("�Crees que t� como jugador has tomado una decisi�n correcta?");
        this.preguntas.add("�Est�s satisfecho con tu equipo?� te has sentido c�modo con ellos?");
        this.preguntas.add("�Qui�n crees que ha tenido m�s iniciativa?");
        this.preguntas.add("�Qu� jugador ha motivado m�s al resto con la decisi�n tomada?");
        this.preguntas.add("�Con qui�n te ha resultado m�s f�cil tomar la decisi�n?");
        this.preguntas.add("�Cu�l consideras que ha motivado m�s a tu equipo?");
        this.preguntas.add("�Qui�n crees que ha tomado la mejor soluci�n en la  mayor�a de las  decisiones?");
        this.preguntas.add("�Qu� jugador ha sido el que m�s se ha expresado?");
        this.preguntas.add("�Qu� jugador consideras que ha destacado con respecto al resto?");
        this.preguntas.add("�Qui�n se ha preocupado m�s por conseguir los objetivos individuales?");
        this.preguntas.add("�Qui�n se ha preocupado m�s por conseguir los objetivos comunes?");
        this.preguntas.add("�Mejorar�ais muchos aspectos con respecto a la toma de decisiones que hab�is llevado a cabo?");
        this.preguntas.add("�Te has sentido responsable a lo largo del juego?");
        this.preguntas.add("�Qui�n ha sido el que m�s ha tratado de negociar sobre las posibles soluciones?");
        this.preguntas.add("�Qu� jugador consideras que ha dirigido m�s al grupo ?");
        this.preguntas.add("�Qu� jugador consideras que ha tenido m�s confianza en s� mismo?");
        this.preguntas.add("�Qui�n ha sido m�s firme con sus decisiones?");
        this.preguntas.add("�Qui�n ha sido el m�s persuasivo racionalmente?");
        this.preguntas.add("�Qui�n ha sido el jugador que m�s ha escuchado y tenido en cuenta las decisiones del resto?");
        this.preguntas.add("�Qu� jugador crees que ha tenido m�s impacto sobre el resto?");
        this.preguntas.add("�Qui�n ha sido el m�s inspirador?");
        this.preguntas.add("�Qu� jugador crees que ha sido el que m�s ha arriesgado?");
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
