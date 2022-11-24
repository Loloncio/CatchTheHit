package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.uva.inf.smov.catchthehit.datos.Jugador;
import es.uva.inf.smov.catchthehit.datos.Partida;

public class EquipoAtaque extends AppCompatActivity {

    private TextView player_green;
    private TextView player_red;
    private TextView player_blue;
    private TextView player_purple;
    private ImageView field;
    private ImageView player;
    private String codigo;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_ataque);

        player_green = (TextView) findViewById(R.id.name_green);
        player_red = (TextView) findViewById(R.id.name_red);
        player_blue = (TextView) findViewById(R.id.name_blue);
        player_purple = (TextView) findViewById(R.id.name_purple);
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
                player_red.setText(partida.getEquipo1().elegirJugador(0).getNombre());
                player_green.setText(partida.getEquipo1().elegirJugador(1).getNombre());
                player_blue.setText(partida.getEquipo1().elegirJugador(2).getNombre());
                player_purple.setText(partida.getEquipo1().elegirJugador(3).getNombre());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read value.");
            }
        });

        field = (ImageView) findViewById(R.id.field);
        player = (ImageView) findViewById(R.id.player);
    }

    public void clickField(View v) {
        //Creamos el intent
        Intent intent = new Intent(EquipoAtaque.this, ModoAtaque.class);
        intent.putExtra("codigo", codigo);
        startActivity(intent);
    }

    public void clickPlayer(View v) {
        //Creamos el intent
        Intent intent = new Intent(EquipoAtaque.this, JugadorActivity.class);
        Jugador jugador = partida.getEquipo1().elegirJugador(1);
        intent.putExtra("nombre",jugador.getNombre());
        intent.putExtra("fuerza",jugador.getFuerza());
        intent.putExtra("velocidad",jugador.getVelocidad());
        intent.putExtra("resistencia",jugador.getResistencia());
        intent.putExtra("codigo", codigo);
        startActivity(intent);
    }

    public void click1(View v){
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
        Intent intent = new Intent(EquipoAtaque.this, JugadorActivity.class);
        intent.putExtra("nombre",jugador.getNombre());
        intent.putExtra("fuerza",jugador.getFuerza());
        intent.putExtra("velocidad",jugador.getVelocidad());
        intent.putExtra("resistencia",jugador.getResistencia());
        intent.putExtra("codigo", codigo);
        startActivity(intent);
    }



}
