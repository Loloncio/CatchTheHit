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
        this.preguntas.add("¿Qué jugador consideras que ha sido el más comunicativo? quién ha consultado más sus decisiones con el resto del grupo");
        this.preguntas.add("¿Quién ha sido el más positivo?");
        this.preguntas.add("¿Quién consideras que ha tenido mayor atención a la hora de explicar su decisión final?");
        this.preguntas.add("¿Crees que tú como jugador has tomado una decisión correcta?");
        this.preguntas.add("¿Estás satisfecho con tu equipo?¿ te has sentido cómodo con ellos?");
        this.preguntas.add("¿Quién crees que ha tenido más iniciativa?");
        this.preguntas.add("¿Qué jugador ha motivado más al resto con la decisión tomada?");
        this.preguntas.add("¿Con quién te ha resultado más fácil tomar la decisión?");
        this.preguntas.add("¿Cuál consideras que ha motivado más a tu equipo?");
        this.preguntas.add("¿Quién crees que ha tomado la mejor solución en la  mayoría de las  decisiones?");
        this.preguntas.add("¿Qué jugador ha sido el que más se ha expresado?");
        this.preguntas.add("¿Qué jugador consideras que ha destacado con respecto al resto?");
        this.preguntas.add("¿Quién se ha preocupado más por conseguir los objetivos individuales?");
        this.preguntas.add("¿Quién se ha preocupado más por conseguir los objetivos comunes?");
        this.preguntas.add("¿Mejoraríais muchos aspectos con respecto a la toma de decisiones que habéis llevado a cabo?");
        this.preguntas.add("¿Te has sentido responsable a lo largo del juego?");
        this.preguntas.add("¿Quién ha sido el que más ha tratado de negociar sobre las posibles soluciones?");
        this.preguntas.add("¿Qué jugador consideras que ha dirigido más al grupo ?");
        this.preguntas.add("¿Qué jugador consideras que ha tenido más confianza en sí mismo?");
        this.preguntas.add("¿Quién ha sido más firme con sus decisiones?");
        this.preguntas.add("¿Quién ha sido el más persuasivo racionalmente?");
        this.preguntas.add("¿Quién ha sido el jugador que más ha escuchado y tenido en cuenta las decisiones del resto?");
        this.preguntas.add("¿Qué jugador crees que ha tenido más impacto sobre el resto?");
        this.preguntas.add("¿Quién ha sido el más inspirador?");
        this.preguntas.add("¿Qué jugador crees que ha sido el que más ha arriesgado?");
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
