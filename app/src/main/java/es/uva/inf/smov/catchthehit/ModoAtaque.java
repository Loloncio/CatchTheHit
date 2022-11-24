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

import es.uva.inf.smov.catchthehit.datos.Jugador;
import es.uva.inf.smov.catchthehit.datos.Partida;

public class ModoAtaque extends AppCompatActivity {

    private ViewGroup layout;
    private String codigo;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_ataque);
        layout = (ViewGroup) findViewById(R.id.movimientos);
        addTirada(1);
        addTirada(2);
        addTirada(3);
        addTirada(0);
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

    public void click1(View v) {
        aJugador(partida.getEquipo1().elegirJugador(0));
    }
    public void click2(View v){
        aJugador(partida.getEquipo1().elegirJugador(1));
    }
    public void click3(View v){
        aJugador(partida.getEquipo1().elegirJugador(2));
    }
    public void click4(View v){
        aJugador(partida.getEquipo1().elegirJugador(3));
    }

    public void aJugador(Jugador jugador){
        Intent intent = new Intent(ModoAtaque.this, JugadorActivity.class);
        intent.putExtra("nombre",jugador.getNombre());
        intent.putExtra("fuerza",jugador.getFuerza());
        intent.putExtra("velocidad",jugador.getVelocidad());
        intent.putExtra("resistencia",jugador.getResistencia());
        intent.putExtra("codigo", codigo);
        startActivity(intent);
    }


    @SuppressLint("InlinedApi")
    private void addTirada(int avance) {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.movimiento;
        ConstraintLayout relativeLayout = (ConstraintLayout) inflater.inflate(id, null, false);

        TextView movimiento = (TextView) relativeLayout.findViewById(R.id.textView);
        movimiento.setText("Avanza " + String.valueOf(avance));
        TextView fuerza = (TextView) relativeLayout.findViewById(R.id.fuerzaMov);
        TextView velocidad = (TextView) relativeLayout.findViewById(R.id.velocidadMov);;
        TextView energia = (TextView) relativeLayout.findViewById(R.id.energiaMov);;

        switch (avance){
            case 1:
                fuerza.setText("- "+"2");
                velocidad.setText("- "+"2");
                energia.setText("- "+"5");
                break;

            case 2:
                fuerza.setText("- "+"4");
                velocidad.setText("- "+"4");
                energia.setText("- "+"10");
                break;

            case 3:
                fuerza.setText("- "+"6");
                velocidad.setText("- "+"6");
                energia.setText("- "+"15");
                break;

            default:
                movimiento.setText("Descansa");
                fuerza.setText("- "+"0");
                velocidad.setText("- "+"0");
                energia.setText("+ "+"5");

        }

        layout.addView(relativeLayout);
    }
}