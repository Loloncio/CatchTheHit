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

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.zip.Inflater;

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

    private void aJugador(Jugador jugador){
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
        ConstraintLayout mov = (ConstraintLayout) inflater.inflate(id, null, false);
        TextView movimiento = (TextView) mov.findViewById(R.id.textView);
        movimiento.setText("Avanza " + String.valueOf(avance));
        TextView fuerza = (TextView) mov.findViewById(R.id.fuerzaMov);
        TextView velocidad = (TextView) mov.findViewById(R.id.velocidadMov);;
        TextView energia = (TextView) mov.findViewById(R.id.energiaMov);;

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

        layout.addView(mov);
    }
    public void clickOpcion(View v){
        TextView avance = (TextView) v.findViewById(R.id.textView);
        /*
        Si se ha seleccionado descansar solo hay que sumar 5 a la resistencia del jugador.
         */
        if(!avance.getText().toString().equals("Descansa")) {
            /*
            Si se ha seleccionado otra cosa tendremos que restar estadisticas y cambiar posición con
            el switch
             */
            TextView energia = (TextView) v.findViewById(R.id.energiaMov);
            TextView fuerza = (TextView) v.findViewById(R.id.fuerzaMov);
            TextView velocidad = (TextView) v.findViewById(R.id.velocidadMov);
            switch (avance.getText().toString()) {
                case "Avanza 1":

                    break;
                case "Avanza 2":

                    break;
                case "Avanza 3":

                    break;
                default:

            }
        } else{

        }
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        Intent intent = new Intent(ModoAtaque.this, Test.class);
        intent.putExtra("codigo",partida.getCodigo());

        Bundle b = new Bundle();

        b.putString("modo_ataque","modo_ataque");
        intent.putExtras(b);

        startActivity(intent);
    }

}