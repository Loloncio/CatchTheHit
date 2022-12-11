package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import es.uva.inf.smov.catchthehit.datos.Jugador;
import es.uva.inf.smov.catchthehit.datos.Partida;

public class espera_defensa extends AppCompatActivity {

    Partida partida;
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
    private ImageView primera_base;
    private ImageView segunda_base;
    private ImageView tercera_base;
    private ImageView short_stop;
    private ImageView jardinero_derecho;
    private ImageView jardinero_centro;
    private ImageView jardinero_izquierdo;
    private HashMap<Integer, Integer> jugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_defensa);

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

        jugadores = new HashMap<>();
        jugadores.put(1, R.id.imageView1);
        jugadores.put(9, R.id.imageView2);
        jugadores.put(2, R.id.imageView3);
        jugadores.put(3, R.id.imageView4);
        jugadores.put(6, R.id.imageView5);
        jugadores.put(4, R.id.imageView6);
        jugadores.put(5, R.id.imageView7);
        jugadores.put(8, R.id.imageView9);
        jugadores.put(7, R.id.imageView8);

        //Para que se guarden las imagenes
        if (bundle.containsKey("primera_base")) {
            b.putInt("primera_base", bundle.getInt("primera_base"));
            primera_base = (ImageView) findViewById(jugadores.get(b.getInt("primera_base")));
            ColorFilter filter = new LightingColorFilter(0xFF7F7F7F, 0x00000000);
            primera_base.setColorFilter(filter);
            jugadores.put(b.getInt("primera_base") * 10, 0);
        }
        if (bundle.containsKey("segunda_base")) {
            b.putInt("segunda_base", bundle.getInt("segunda_base"));
            segunda_base = (ImageView) findViewById(jugadores.get(b.getInt("segunda_base")));
            ColorFilter filter = new LightingColorFilter(0xFF7F7F7F, 0x00000000);
            segunda_base.setColorFilter(filter);
            jugadores.put(b.getInt("segunda_base") * 10, 0);
        }
        if (bundle.containsKey("tercera_base")) {
            b.putInt("tercera_base", bundle.getInt("tercera_base"));
            tercera_base = (ImageView) findViewById(jugadores.get(b.getInt("tercera_base")));
            ColorFilter filter = new LightingColorFilter(0xFF7F7F7F, 0x00000000);
            tercera_base.setColorFilter(filter);
            jugadores.put(b.getInt("tercera_base") * 10, 0);
        }
        if (bundle.containsKey("short_stop")) {
            b.putInt("short_stop", bundle.getInt("short_stop"));
            short_stop = (ImageView) findViewById(jugadores.get(b.getInt("short_stop")));
            ColorFilter filter = new LightingColorFilter(0xFF7F7F7F, 0x00000000);
            short_stop.setColorFilter(filter);
            jugadores.put(b.getInt("short_stop") * 10, 0);
        }
        if (bundle.containsKey("jardinero_derecho")) {
            b.putInt("jardinero_derecho", bundle.getInt("jardinero_derecho"));
            jardinero_derecho = (ImageView) findViewById(jugadores.get(b.getInt("jardinero_derecho")));
            ColorFilter filter = new LightingColorFilter(0xFF7F7F7F, 0x00000000);
            jardinero_derecho.setColorFilter(filter);
            jugadores.put(b.getInt("jardinero_derecho") * 10, 0);
        }
        if (bundle.containsKey("jardinero_centro")) {
            b.putInt("jardinero_centro", bundle.getInt("jardinero_centro"));
            jardinero_centro = (ImageView) findViewById(jugadores.get(b.getInt("jardinero_centro")));
            ColorFilter filter = new LightingColorFilter(0xFF7F7F7F, 0x00000000);
            jardinero_centro.setColorFilter(filter);
            jugadores.put(b.getInt("jardinero_centro") * 10, 0);
        }
        if (bundle.containsKey("jardinero_izquierdo")) {
            b.putInt("jardinero_izquierdo", bundle.getInt("jardinero_izquierdo"));
            jardinero_izquierdo = (ImageView) findViewById(jugadores.get(b.getInt("jardinero_izquierdo")));
            ColorFilter filter = new LightingColorFilter(0xFF7F7F7F, 0x00000000);
            jardinero_izquierdo.setColorFilter(filter);
            jugadores.put(b.getInt("jardinero_izquierdo") * 10, 0);
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

                if (partida.getEquipo1().elegirJugador(0).isReady()) {
                    myRef.removeEventListener(this);
                    Intent intent = new Intent(espera_defensa.this, Test.class);
                    intent.putExtra("codigo", partida.getCodigo());
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.");
            }
        });
    }

    public void clickRedPlayer(View v) {

        aJugador(partida.getEquipo1().elegirJugador(0));

    }

    public void clickGreenPlayer(View v) {

        aJugador(partida.getEquipo1().elegirJugador(1));

    }

    public void clickBluePlayer(View v) {

        aJugador(partida.getEquipo1().elegirJugador(2));
    }

    public void clickPurplePlayer(View v) {

        aJugador(partida.getEquipo1().elegirJugador(3));

    }

    public void clickGrayPlayer(View v) {

        aJugador(partida.getEquipo1().elegirJugador(4));

    }

    public void clickOrangePlayer(View v) {

        aJugador(partida.getEquipo1().elegirJugador(5));

    }

    public void clickPinkPlayer(View v) {

        aJugador(partida.getEquipo1().elegirJugador(6));

    }

    public void clickCyanPlayer(View v) {

        aJugador(partida.getEquipo1().elegirJugador(7));

    }

    public void clickYellowPlayer(View v) {

        aJugador(partida.getEquipo1().elegirJugador(8));

    }

    private void aJugador(Jugador jugador) {
        Dialog player = new Dialog(this);
        player.setContentView(R.layout.pop_jugador);
        TextView txtReflejos = (TextView) player.findViewById((R.id.txtFuerza));
        TextView txtVelocidad = (TextView) player.findViewById((R.id.txtVelocidad));
        TextView txtResistencia = (TextView) player.findViewById((R.id.txtResistencia));
        TextView txtNombre = (TextView) player.findViewById((R.id.nombre));
        txtReflejos.setText(String.valueOf(jugador.getReflejos())+"%");
        txtResistencia.setText(String.valueOf(jugador.getResistencia())+"%");
        txtVelocidad.setText(String.valueOf(jugador.getVelocidad())+"%");
        txtNombre.setText(jugador.getNombre());

        ProgressBar progReflejos = (ProgressBar) player.findViewById(R.id.fuerza);
        ProgressBar progVelocidad = (ProgressBar) player.findViewById(R.id.velocidad);
        ProgressBar progResistencia = (ProgressBar) player.findViewById(R.id.resistencia);
        progReflejos.setProgress(jugador.getReflejos());
        progVelocidad.setProgress(jugador.getVelocidad());
        progResistencia.setProgress(jugador.getResistencia());

        ImageView icono = (ImageView) player.findViewById(R.id.iconoJugador);
        icono.setImageResource(buscaImagen(jugador.getId()));

        player.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        player.show();

    }

    private int buscaImagen(int i) {
        switch (i) {
            default:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/yellow_player", null, null);
            case 0:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/red_player", null, null);
            case 1:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/green_player", null, null);
            case 2:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/blue_player", null, null);
            case 3:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/purple_player", null, null);
            case 4:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/gray_player", null, null);
            case 5:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/orange_player", null, null);
            case 6:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/pink_player", null, null);
            case 7:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/cyan_player", null, null);
        }
    }
}