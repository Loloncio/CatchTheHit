package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.uva.inf.smov.catchthehit.R;
import es.uva.inf.smov.catchthehit.datos.Partida;

public class ResumenRonda extends AppCompatActivity {

    private String codigo;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_ronda);


        int puntosequipo1, puntosequipo2;
        puntosequipo1 = 15; //get points from database instead
        puntosequipo2 = 9; //get points from database instead

        String puntos1, puntos2;
        puntos1 = "Tu equipo : " + puntosequipo1 + " puntos";
        puntos2 = "Equipo rival : " + puntosequipo2 + " puntos";


        TextView puntos1TextView = (TextView)findViewById(R.id.resumen_ronda_tu_equipo_puntos);
        puntos1TextView.setText(puntos1);

        TextView puntos2TextView = (TextView)findViewById(R.id.resumen_ronda_equipo_rival_puntos);
        puntos2TextView.setText(puntos2);

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

    public void clickSiguienteRonda(View v){
        //Creamos el intent
        Intent intent = new Intent(ResumenRonda.this, ModoAtaque.class);

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());


        startActivity(intent);
    }



    }
