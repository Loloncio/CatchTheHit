package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.uva.inf.smov.catchthehit.datos.Partida;

public class Test extends AppCompatActivity {

    private ViewGroup layout;
    private String codigo;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        layout = (ViewGroup) findViewById(R.id.preguntas);
        String pregunta = "1. Pregunta prueba";
        IntroducePregunta(pregunta);
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
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.");
            }
        });
    }

    @SuppressLint("InlinedApi")
    private void IntroducePregunta(String tirada) {

        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.pregunta;

        ConstraintLayout relativeLayout = (ConstraintLayout) inflater.inflate(id, null, false);

        TextView textView = (TextView) relativeLayout.findViewById(R.id.textView);
        textView.setText(String.valueOf(tirada));

        layout.addView(relativeLayout);
    }

    public void clickFinalizarTest(View v){
        Bundle bundle = this.getIntent().getExtras();

        Intent intent = new Intent();

        if(bundle.containsKey("modo_ataque")) {
            intent = new Intent(Test.this, ModoDefensa.class);
        }
        if(bundle.containsKey("modo_defensa")) {
            partida.setRondaAct(partida.getRondaAct()+1);
            if(partida.getRondaAct()>partida.getRondas()){
                intent = new Intent(Test.this, Resultados.class);
            }else{
                intent = new Intent(Test.this,ResumenRonda.class);

            }

        }
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }
}