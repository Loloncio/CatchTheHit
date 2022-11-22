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
