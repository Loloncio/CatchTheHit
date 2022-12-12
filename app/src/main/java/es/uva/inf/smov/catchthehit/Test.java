package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import es.uva.inf.smov.catchthehit.datos.Partida;

public class Test extends AppCompatActivity {

    private ViewGroup layout;
    private String codigo;
    private FirebaseDatabase database;
    private Partida partida;
    private ArrayList<Integer> respuestas;
    private int jugador;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        codigo = getIntent().getExtras().getString("codigo");
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(codigo);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        layout = findViewById(R.id.preguntas);
        respuestas = new ArrayList<Integer>(18);
        for(int i = 0; i < 18; i++){
            respuestas.add(i, null);
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                partida = dataSnapshot.getValue(Partida.class);

                for (int i = 0; i < 4; i++) {
                    partida.getEquipo1().elegirJugador(i).setReady(false);
                    if (partida.getEquipo1().elegirJugador(i).getUsuario().equals(user.getUid())) {
                        jugador = i;
                    }
                }
                myRef.removeEventListener(this);
                IntroducePreguntas();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.");
            }
        });
    }

    private void IntroducePreguntas() {
        int[] jugadores;
        if (jugador == 0) jugadores = new int[]{1, 2, 3};
        else if (jugador == 1) jugadores = new int[]{0, 2, 3};
        else if (jugador == 2) jugadores = new int[]{0, 1, 3};
        else jugadores = new int[]{0, 1, 2};
        ArrayList<Integer> preguntas = preguntas();
        for (int i : preguntas) {
            LayoutInflater inflater = LayoutInflater.from(this);
            int id = R.layout.pregunta;

            ConstraintLayout constraint = (ConstraintLayout) inflater.inflate(id, null, false);
            TextView pregunta = constraint.findViewById(R.id.pregunta);
            TextView nPregunta = constraint.findViewById(R.id.nPregunta);
            nPregunta.setText(String.valueOf(i + 1));
            pregunta.setText(toUTF8(partida.getPregunta(i)));
            TextView j1 = constraint.findViewById(R.id.j1);
            TextView j2 = constraint.findViewById(R.id.j2);
            TextView j3 = constraint.findViewById(R.id.j3);
            j1.setText(partida.getEquipo1().elegirJugador(jugadores[0]).getNombre());
            j2.setText(partida.getEquipo1().elegirJugador(jugadores[1]).getNombre());
            j3.setText(partida.getEquipo1().elegirJugador(jugadores[2]).getNombre());

            ImageView i1 = constraint.findViewById(R.id.J1);
            ImageView i2 = constraint.findViewById(R.id.J2);
            ImageView i3 = constraint.findViewById(R.id.J3);
            i1.setImageResource(getImagen(jugadores[0]));
            i2.setImageResource(getImagen(jugadores[1]));
            i3.setImageResource(getImagen(jugadores[2]));
            i1.setTag(jugadores[0]);
            i2.setTag(jugadores[1]);
            i3.setTag(jugadores[2]);

            layout.addView(constraint);
        }
    }

    public void clickFinalizarTest(View v) {
        int sinRespuesta = 0;
        for (int i = 0; i < 18; i++) {
            if (respuestas.get(i) == null) {
                sinRespuesta++;
            }
        }
        if (sinRespuesta == 0) {
            partida.getEquipo1().elegirJugador(jugador).setRespuestas(respuestas);
            partida.getEquipo1().elegirJugador(jugador).setReady(true);
            database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
            database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(codigo);

            if(partida.getRondaAct()== partida.getRondaAct()) {
                myRef.setValue(partida);
                Intent intent = new Intent(Test.this, espera_test.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
            }else{
                for(int i = 0; i < 4; i++){
                    partida.getEquipo1().elegirJugador(i).setEnjuego(true);
                    if( partida.getEquipo1().elegirJugador(i).getResistencia()<80)
                        partida.getEquipo1().elegirJugador(i).menosRes(-20);
                    partida.siguienteRonda();
                }
                myRef.setValue(partida);
                Intent intent = new Intent(Test.this, ModoAtaque.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
            }


        }

    }

    public void clickOpcion1(View v) {
        LinearLayout op1 = v.findViewById(R.id.op1);

        op1.setBackground(ContextCompat.getDrawable(this, R.drawable.border_test));
        op1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.negroTranslucido)));

        ConstraintLayout padre = (ConstraintLayout) v.getParent();

        ImageView imagen = (ImageView) v.findViewById(R.id.J1);
        TextView nPregunta = padre.findViewById(R.id.nPregunta);
        int numero = Integer.parseInt(nPregunta.getText().toString());
        respuestas.add(numero - 1, Integer.parseInt(imagen.getTag().toString()));


        LinearLayout op2 = padre.findViewById(R.id.op2);
        LinearLayout op3 = padre.findViewById(R.id.op3);
        op2.setBackgroundColor(0x00FFFFFF);
        op3.setBackgroundColor(0x00FFFFFF);
    }


    public void clickOpcion2(View v) {
        LinearLayout op2 = v.findViewById(R.id.op2);

        op2.setBackground(ContextCompat.getDrawable(this, R.drawable.border_test));
        op2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.negroTranslucido)));

        ConstraintLayout padre = (ConstraintLayout) v.getParent();
        ImageView imagen = (ImageView) v.findViewById(R.id.J2);
        TextView nPregunta = padre.findViewById(R.id.nPregunta);
        int numero = Integer.parseInt(nPregunta.getText().toString());
        respuestas.add(numero - 1, Integer.parseInt(imagen.getTag().toString()));

        LinearLayout op1 = padre.findViewById(R.id.op1);
        LinearLayout op3 = padre.findViewById(R.id.op3);
        op1.setBackgroundColor(0x00FFFFFF);
        op3.setBackgroundColor(0x00FFFFFF);

    }

    public void clickOpcion3(View v) {
        LinearLayout op3 = v.findViewById(R.id.op3);

        op3.setBackground(ContextCompat.getDrawable(this, R.drawable.border_test));
        op3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.negroTranslucido)));

        ConstraintLayout padre = (ConstraintLayout) v.getParent();

        ImageView imagen = (ImageView) v.findViewById(R.id.J3);
        TextView nPregunta = padre.findViewById(R.id.nPregunta);
        int numero = Integer.parseInt(nPregunta.getText().toString());
        respuestas.add(numero - 1, Integer.parseInt(imagen.getTag().toString()));

        LinearLayout op1 = padre.findViewById(R.id.op1);
        LinearLayout op2 = padre.findViewById(R.id.op2);
        op1.setBackgroundColor(0x00FFFFFF);
        op2.setBackgroundColor(0x00FFFFFF);

    }

    private int getImagen(int id) {
        switch (id) {
            case 0:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/red_player", null, null);

            case 1:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/green_player", null, null);

            case 2:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/blue_player", null, null);

            default:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/purple_player", null, null);
        }
    }

    private ArrayList<Integer> preguntas() {
        ArrayList<Integer> preguntas = new ArrayList<Integer>();
        int resto = 18 % partida.getRondas();
        int inicio = 0;
        int size = 18 / partida.getRondas();

        if (resto != 0) {
            for (int i = 0; i < resto; i++)
                if (partida.getRondaAct() == i) size++;
        }

        if (size % partida.getRondas() != 0) for (int i = 0; i < partida.getRondas(); i++)
            if (partida.getRondaAct() < resto) inicio = size * (partida.getRondaAct() - 1);
            else inicio = size * (partida.getRondaAct() - 1) + resto;
        else inicio = size * (partida.getRondaAct() - 1);

        for (int i = 0; i < size; i++) {
            preguntas.add(i + inicio);
        }

        return preguntas;
    }

    private String toUTF8(String s) {

        if (s != null) {

            String ss;

            ss = new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            return ss;

        } else {

            return "";

        }

    }

}