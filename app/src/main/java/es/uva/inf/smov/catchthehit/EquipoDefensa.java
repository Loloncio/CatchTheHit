package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

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

import es.uva.inf.smov.catchthehit.datos.Partida;

public class EquipoDefensa extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private TextView player_green;
    private TextView player_red;
    private TextView player_blue;
    private TextView player_purple;
    private TextView player_orange;
    private TextView player_pink;
    private TextView player_gray;
    private TextView player_cyan;
    private TextView player_yellow;
    private Bundle bundle;
    private Bundle b;
    private String codigo;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_defensa);

        player_green = (TextView) findViewById(R.id.name_green);
        player_red = (TextView) findViewById(R.id.name_red);
        player_blue = (TextView) findViewById(R.id.name_blue);
        player_purple = (TextView) findViewById(R.id.name_purple);
        player_orange = (TextView) findViewById(R.id.name_orange);
        player_pink = (TextView) findViewById(R.id.name_pink);
        player_gray = (TextView) findViewById(R.id.name_gray);
        player_cyan = (TextView) findViewById(R.id.name_cyan);
        player_yellow = (TextView) findViewById(R.id.name_yelow);
        player_green.setText("Babe");
        player_red.setText("Ted");
        player_blue.setText("Willie");
        player_purple.setText("Stan");
        player_orange.setText("John");
        player_pink.setText("Jackson");
        player_gray.setText("Lucas");
        player_cyan.setText("Charlie");
        player_yellow.setText("Andrey");

        bundle = this.getIntent().getExtras();
        b = new Bundle();

        //Para que se guarden las imagenes
        if(bundle.containsKey("primera_base")) {
            b.putInt("primera_base", bundle.getInt("primera_base"));
        }
        if(bundle.containsKey("segunda_base")) {
            b.putInt("segunda_base", bundle.getInt("segunda_base"));
        }
        if(bundle.containsKey("tercera_base")) {
            b.putInt("tercera_base", bundle.getInt("tercera_base"));
        }
        if(bundle.containsKey("short_stop")) {
            b.putInt("short_stop", bundle.getInt("short_stop"));
        }
        if(bundle.containsKey("jardinero_derecho")) {
            b.putInt("jardinero_derecho", bundle.getInt("jardinero_derecho"));
        }
        if(bundle.containsKey("jardinero_centro")) {
            b.putInt("jardinero_centro", bundle.getInt("jardinero_centro"));
        }
        if(bundle.containsKey("jardinero_izquierdo")) {
            b.putInt("jardinero_izquierdo", bundle.getInt("jardinero_izquierdo"));
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
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.");
            }
        });


    }

    public void clickField(View v) {
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, ModoDefensa.class);

        startActivity(intent);
    }

    public void clickPlayer(View v) {
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, JugadorActivity.class);

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickRedPlayer(View v){
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, ModoDefensa.class);

        b.putInt(bundle.getString("casilla"),R.drawable.red_player);
        //b.putInt("casilla",bundle.getInt("casilla"));

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        intent.putExtras(b);

        startActivity(intent);
    }

    public void clickGreenPlayer(View v){
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, ModoDefensa.class);

        b.putInt(bundle.getString("casilla"),R.drawable.green_player);
        //b.putInt("casilla",bundle.getInt("casilla"));

        intent.putExtras(b);

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickBluePlayer(View v){
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, ModoDefensa.class);

        b.putInt(bundle.getString("casilla"),R.drawable.blue_player);
        //b.putInt("casilla",bundle.getInt("casilla"));

        intent.putExtras(b);

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickPurplePlayer(View v){
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, ModoDefensa.class);

        b.putInt(bundle.getString("casilla"),R.drawable.purple_player);
        //b.putInt("casilla",bundle.getInt("casilla"));

        intent.putExtras(b);

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickGrayPlayer(View v){
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, ModoDefensa.class);

        b.putInt(bundle.getString("casilla"),R.drawable.gray_player);
        //b.putInt("casilla",bundle.getInt("casilla"));

        intent.putExtras(b);

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickOrangePlayer(View v){
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, ModoDefensa.class);

        b.putInt(bundle.getString("casilla"),R.drawable.orange_player);
        //b.putInt("casilla",bundle.getInt("casilla"));

        intent.putExtras(b);

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickPinkPlayer(View v){
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, ModoDefensa.class);

        b.putInt(bundle.getString("casilla"),R.drawable.pink_player);
        //b.putInt("casilla",bundle.getInt("casilla"));

        intent.putExtras(b);

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickCyanPlayer(View v){
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, ModoDefensa.class);

        b.putInt(bundle.getString("casilla"),R.drawable.cyan_player);
        //b.putInt("casilla",bundle.getInt("casilla"));

        intent.putExtras(b);

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickYellowPlayer(View v){
        //Creamos el intent
        Intent intent = new Intent(EquipoDefensa.this, ModoDefensa.class);

        b.putInt(bundle.getString("casilla"),R.drawable.yellow_player);
        //b.putInt("casilla",bundle.getInt("casilla"));

        intent.putExtras(b);

        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }
}