package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.uva.inf.smov.catchthehit.datos.Jugador;
import es.uva.inf.smov.catchthehit.datos.Partida;

public class Test extends AppCompatActivity {

    private ViewGroup layout;
    private String codigo;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    Partida partida;
    private ArrayList<Jugador> respuestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        layout = (ViewGroup) findViewById(R.id.preguntas);
        String pregunta = "1. Pregunta prueba";
        respuestas = new ArrayList<Jugador>();
        codigo = getIntent().getExtras().getString("codigo");
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(codigo);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                partida = dataSnapshot.getValue(Partida.class);
                IntroducePreguntas();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.");
            }
        });
    }

    @SuppressLint("InlinedApi")
    private void IntroducePreguntas() {
        for(int i = 0; i<18;i++) {
            LayoutInflater inflater = LayoutInflater.from(this);
            int id = R.layout.pregunta;

            ConstraintLayout constraint = (ConstraintLayout) inflater.inflate(id, null, false);
            TextView pregunta = (TextView) constraint.findViewById(R.id.pregunta);
            TextView nPregunta = (TextView) constraint.findViewById(R.id.nPregunta);
            nPregunta.setText(String.valueOf(i+1));
            pregunta.setText(partida.getPregunta(i));

            layout.addView(constraint);
        }
    }

    public void clickFinalizarTest(View v){
        Bundle bundle = this.getIntent().getExtras();

        Intent intent = new Intent();

        partida.getEquipo1().elegirJugador(1).setRespuestas(respuestas);
        intent = new Intent(Test.this, Resultados.class);
        intent.putExtra("codigo",codigo);

        startActivity(intent);
    }

    public void clickOpcion1(View v){
        TextView nPregunta = (TextView) v.findViewById(R.id.nPregunta);
        String numero = nPregunta.getText().toString();
        respuestas.add(Integer.parseInt(numero)-1,partida.getEquipo1().elegirJugador(0));
        LinearLayout op = (LinearLayout) v.findViewById(R.id.op1);

        op.setBackground(ContextCompat.getDrawable(this, R.drawable.borde_jugador));
        op.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.negroTranslucido)));
    }
    public void clickOpcion2(View v){
        TextView nPregunta = (TextView) v.findViewById(R.id.nPregunta);
        String numero = nPregunta.getText().toString();
        respuestas.add(Integer.parseInt(numero)-1,partida.getEquipo1().elegirJugador(1));
        LinearLayout op = (LinearLayout) v.findViewById(R.id.op2);
        op.setBackground(ContextCompat.getDrawable(this, R.drawable.borde_jugador));
        op.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.negroTranslucido)));
    }
    public void clickOpcion3(View v){
        TextView nPregunta = (TextView) v.findViewById(R.id.nPregunta);
        String numero = nPregunta.getText().toString();
        Log.e("numero", numero);
        respuestas.add(Integer.parseInt(numero)-1,partida.getEquipo1().elegirJugador(2));
        LinearLayout op = (LinearLayout) v.findViewById(R.id.op3);
        op.setBackground(ContextCompat.getDrawable(this, R.drawable.borde_jugador));
        op.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.negroTranslucido)));
    }
    public void clickOpcion4(View v){
        TextView nPregunta = (TextView) v.findViewById(R.id.nPregunta);
        String numero = nPregunta.getText().toString();
        respuestas.add(Integer.parseInt(numero)-1,partida.getEquipo1().elegirJugador(3));
        LinearLayout op = (LinearLayout) v.findViewById(R.id.op4);
        op.setBackground(ContextCompat.getDrawable(this, R.drawable.borde_jugador));
        op.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.negroTranslucido)));

    }

    public void clickExit(View v){



    }

}