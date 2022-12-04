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
import android.widget.Toast;

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
    private int sinRespuesta;
    private ArrayList<Integer> respuestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        layout = (ViewGroup) findViewById(R.id.preguntas);
        String pregunta = "1. Pregunta prueba";
        respuestas = new ArrayList<Integer>(18);
        for(int i = 0; i<18;i++){
            respuestas.add(null);
        }
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

            LinearLayout op1 = (LinearLayout) constraint.findViewById(R.id.op1);
            op1.setTag(i+1);

            TextView j1 = (TextView) constraint.findViewById(R.id.j1);
            TextView j2 = (TextView) constraint.findViewById(R.id.j2);
            TextView j3 = (TextView) constraint.findViewById(R.id.j3);
            j1.setText(partida.getEquipo1().elegirJugador(2).getNombre());
            j2.setText(partida.getEquipo1().elegirJugador(3).getNombre());
            j3.setText(partida.getEquipo1().elegirJugador(4).getNombre());

            layout.addView(constraint);
        }
    }

    public void clickFinalizarTest(View v){
        sinRespuesta = 0;
        for(int i = 0;i < 18;i++){
            Log.e("comprobar", String.valueOf(i));
            if (respuestas.get(i) == null) {
                sinRespuesta++;
                Log.e("null", "Null encontrado en"+i);
            }
        }
        if (sinRespuesta == 0) {
            Bundle bundle = this.getIntent().getExtras();

            Intent intent = new Intent();

            partida.getEquipo1().elegirJugador(1).setRespuestas(respuestas);
            DatabaseReference myRef = database.getReference(partida.getCodigo());
            myRef.setValue(partida);
            intent = new Intent(Test.this, Resultados.class);
            intent.putExtra("codigo", codigo);

            startActivity(intent);
        } else{
            CharSequence fail = "Selecciona un jugador en todas las preguntas";
            Toast toast = Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void clickOpcion1(View v){
        LinearLayout op1 = (LinearLayout) v.findViewById(R.id.op1);
        //respuestas.add(numero-1,partida.getEquipo1().elegirJugador(0));

        op1.setBackground(ContextCompat.getDrawable(this, R.drawable.borde_jugador));
        op1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.negroTranslucido)));

        ConstraintLayout padre = (ConstraintLayout) (ViewGroup) v.getParent();
        LinearLayout op2 = (LinearLayout) padre.findViewById(R.id.op2);
        LinearLayout op3 = (LinearLayout) padre.findViewById(R.id.op3);
        op2.setBackgroundColor(0x00FFFFFF);
        op3.setBackgroundColor(0x00FFFFFF);

        TextView nPregunta = (TextView) padre.findViewById(R.id.nPregunta);
        int numero = Integer.parseInt(nPregunta.getText().toString());
        Log.e("numero", String.valueOf(numero));
        respuestas.add(numero-1,1);
    }
    public void clickOpcion2(View v){
        LinearLayout op2 = (LinearLayout) v.findViewById(R.id.op2);
        //respuestas.add(numero-1,partida.getEquipo1().elegirJugador(1));

        op2.setBackground(ContextCompat.getDrawable(this, R.drawable.borde_jugador));
        op2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.negroTranslucido)));

        ConstraintLayout padre = (ConstraintLayout) (ViewGroup) v.getParent();
        LinearLayout op1 = (LinearLayout) padre.findViewById(R.id.op1);
        LinearLayout op3 = (LinearLayout) padre.findViewById(R.id.op3);
        op1.setBackgroundColor(0x00FFFFFF);
        op3.setBackgroundColor(0x00FFFFFF);

        TextView nPregunta = (TextView) padre.findViewById(R.id.nPregunta);
        int numero = Integer.parseInt(nPregunta.getText().toString());
        Log.e("numero", String.valueOf(numero));
        respuestas.add(numero-1,2);
    }
    public void clickOpcion3(View v){
        LinearLayout op3 = (LinearLayout) v.findViewById(R.id.op3);
        //

        op3.setBackground(ContextCompat.getDrawable(this, R.drawable.borde_jugador));
        op3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.negroTranslucido)));

        ConstraintLayout padre = (ConstraintLayout) (ViewGroup) v.getParent();
        LinearLayout op1 = (LinearLayout) padre.findViewById(R.id.op1);
        LinearLayout op2= (LinearLayout) padre.findViewById(R.id.op2);
        op1.setBackgroundColor(0x00FFFFFF);
        op2.setBackgroundColor(0x00FFFFFF);

        TextView nPregunta = (TextView) padre.findViewById(R.id.nPregunta);
        int numero = Integer.parseInt(nPregunta.getText().toString());
        Log.e("numero", String.valueOf(numero));
        respuestas.add(numero-1,3);
    }

    public void clickExit(View v){



    }

}