package es.uva.inf.smov.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EventosSpeciales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_speciales);

        //numero del evento : Mala decision 1 o 2
        int evento = 1, bases = 0;
        String eventotxt = "";
        TextView eventoTextView = findViewById(R.id.evento_special);


        if(evento == 1){
            eventotxt = "Intentas avanzar " + bases + " bases, pero cuando ibas a llegar, un defensor más rápido llega a ti con la bola eliminandote";

        }
        else if(evento==2){
            eventotxt = "Decides descansar en la base actual. Ted decidio avanzar, pero como tu base estaba ocupada fue eliminado";
        }
        else{
            eventotxt = "Avanzas " + bases + " bases";
        }

        eventoTextView.setText(eventotxt);

    }
}