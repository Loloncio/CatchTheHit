package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import es.uva.inf.smov.catchthehit.R;
import es.uva.inf.smov.catchthehit.datos.Partida;

public class ModoDefensa extends AppCompatActivity {

    private Bundle b;
    private String codigo;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defensa);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        b = new Bundle();

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

        HashMap<Integer,Integer> jugadores = new HashMap<>();
        jugadores.put(0,R.drawable.red_player);
        jugadores.put(1,R.drawable.green_player);
        jugadores.put(2,R.drawable.blue_player);
        jugadores.put(3,R.drawable.purple_player);
        jugadores.put(4,R.drawable.gray_player);
        jugadores.put(5,R.drawable.orange_player);
        jugadores.put(6,R.drawable.pink_player);
        jugadores.put(8,R.drawable.yellow_player);
        jugadores.put(7,R.drawable.cyan_player);

        //para guardar las imagenes
        if(bundle != null) {
            //ImageButton btn = (ImageButton) findViewBy(bundle.getInt("casilla"));
            if(bundle.containsKey("primera_base")) {
                ImageButton primera_base = findViewById(R.id.primera_base);
                primera_base.setBackgroundResource(jugadores.get(bundle.getInt("primera_base")));
                b.putInt("primera_base", bundle.getInt("primera_base"));
            }
            if(bundle.containsKey("segunda_base")) {
                ImageButton segunda_base = findViewById(R.id.segunda_base);
                segunda_base.setBackgroundResource(jugadores.get(bundle.getInt("segunda_base")));
                b.putInt("segunda_base", bundle.getInt("segunda_base"));
            }
            if(bundle.containsKey("tercera_base")) {
                ImageButton tercera_base = findViewById(R.id.tercera_base);
                tercera_base.setBackgroundResource(jugadores.get(bundle.getInt("tercera_base")));
                b.putInt("tercera_base", bundle.getInt("tercera_base"));
            }
            if(bundle.containsKey("short_stop")) {
                ImageButton short_stop = findViewById(R.id.short_stop);
                short_stop.setBackgroundResource(jugadores.get(bundle.getInt("short_stop")));
                b.putInt("short_stop", bundle.getInt("short_stop"));
            }
            if(bundle.containsKey("jardinero_derecho")) {
                ImageButton jardinero_derecho = findViewById(R.id.jardinero_derecho);
                jardinero_derecho.setBackgroundResource(jugadores.get(bundle.getInt("jardinero_derecho")));
                b.putInt("jardinero_derecho", bundle.getInt("jardinero_derecho"));
            }
            if(bundle.containsKey("jardinero_centro")) {
                ImageButton jardinero_centro = findViewById(R.id.jardinero_centro);
                jardinero_centro.setBackgroundResource(jugadores.get(bundle.getInt("jardinero_centro")));
                b.putInt("jardinero_centro", bundle.getInt("jardinero_centro"));
            }
            if(bundle.containsKey("jardinero_izquierdo")) {
                ImageButton jardinero_izquierdo = findViewById(R.id.jardinero_izquierdo);
                jardinero_izquierdo.setBackgroundResource(jugadores.get(bundle.getInt("jardinero_izquierdo")));
                b.putInt("jardinero_izquierdo", bundle.getInt("jardinero_izquierdo"));
            }
        }
    }

    public void clickPrimeraBase(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","primera_base");

        intent.putExtra("codigo",partida.getCodigo());

        intent.putExtras(b);

        startActivity(intent);
    }

    public void clickSegundaBase(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","segunda_base");

        intent.putExtras(b);

        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickTerceraBase(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","tercera_base");

        intent.putExtras(b);

        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickShortStop(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","short_stop");

        intent.putExtras(b);

        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickJardineroDerecho(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","jardinero_derecho");

        intent.putExtras(b);

        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickJardineroCentro(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","jardinero_centro");

        intent.putExtras(b);

        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickJardineroIzquierdo(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","jardinero_izquierdo");

        intent.putExtras(b);

        intent.putExtra("codigo",partida.getCodigo());

        startActivity(intent);
    }

    public void clickFinalizarSeleccion(View v){
        if(b.size()!=7){
            CharSequence fail = "Seleccionad un jugador para todas las posiciones";
            Toast toast = Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_LONG);
            toast.show();
        }else {
            //Calculos de la puntuacion del modo defensa
            int puntosRival = 0;
            int eliminadosRival = 0;
            int fuerzaZona;
            int velocidadZona;
            int zonaBola;
            int i=0;
            double probabilidadEliminar;
            int casillasAvanzaRival = 0;

            while(puntosRival<3 && eliminadosRival<3) {
                zonaBola = (int)Math.floor(Math.random()*3+1);
                if(zonaBola==1) {
                    fuerzaZona = (partida.getEquipo1().elegirJugador(b.getInt("primera_base")).getFuerza() + partida.getEquipo1().elegirJugador(b.getInt("segunda_base")).getFuerza() - partida.getEquipo2().elegirJugador(i).getFuerza()) / 3;
                    velocidadZona = (partida.getEquipo1().elegirJugador(b.getInt("primera_base")).getVelocidad() + partida.getEquipo1().elegirJugador(b.getInt("segunda_base")).getVelocidad() - partida.getEquipo2().elegirJugador(i).getVelocidad()) / 3;
                    probabilidadEliminar = (fuerzaZona+velocidadZona)/2;
                    if((Math.random()*100)<probabilidadEliminar){
                        eliminadosRival++;
                    }else{
                        casillasAvanzaRival = casillasAvanzaRival + (int)Math.floor(Math.random()*3+1);
                    }
                }
                if(zonaBola==2) {
                    fuerzaZona = (partida.getEquipo1().elegirJugador(b.getInt("tercera_base")).getFuerza() + partida.getEquipo1().elegirJugador(b.getInt("short_stop")).getFuerza() - partida.getEquipo2().elegirJugador(i).getFuerza()) / 3;
                    velocidadZona = (partida.getEquipo1().elegirJugador(b.getInt("tercera_base")).getVelocidad() + partida.getEquipo1().elegirJugador(b.getInt("short_stop")).getVelocidad() - partida.getEquipo2().elegirJugador(i).getVelocidad()) / 3;
                    probabilidadEliminar = (fuerzaZona+velocidadZona)/2;
                    if((Math.random()*100)<probabilidadEliminar){
                        eliminadosRival++;
                    }else{
                        casillasAvanzaRival = casillasAvanzaRival + (int)Math.floor(Math.random()*3+1);
                    }
                }if(zonaBola==3) {
                    fuerzaZona = (partida.getEquipo1().elegirJugador(b.getInt("jardinero_derecho")).getFuerza() + partida.getEquipo1().elegirJugador(b.getInt("jardinero_centro")).getFuerza() + partida.getEquipo1().elegirJugador(b.getInt("jardinero_izquierdo")).getFuerza() - partida.getEquipo2().elegirJugador(i).getFuerza()) / 4;
                    velocidadZona = (partida.getEquipo1().elegirJugador(b.getInt("jardinero_derecho")).getVelocidad() + partida.getEquipo1().elegirJugador(b.getInt("jardinero_centro")).getVelocidad() + partida.getEquipo1().elegirJugador(b.getInt("jardinero_izquierdo")).getVelocidad() - partida.getEquipo2().elegirJugador(i).getVelocidad()) / 4;
                    probabilidadEliminar = (fuerzaZona+velocidadZona)/2;
                    if((Math.random()*100)<probabilidadEliminar){
                        eliminadosRival++;
                    }else{
                        casillasAvanzaRival = casillasAvanzaRival + (int)Math.floor(Math.random()*3+1);
                    }
                }
                puntosRival = (int)Math.floor(casillasAvanzaRival / 4);
                if(i<3) {
                    i++;
                }else{
                    i=0;
                }
            }

            //Creamos el intent
            Intent intent = new Intent(ModoDefensa.this, Test.class);

            database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
            DatabaseReference myRef = database.getReference(partida.getCodigo());
            partida.getEquipo1().elegirJugador(0).setReady(true);
            partida.getEquipo2().setPuntos(partida.getEquipo2().getPuntos()+puntosRival);
            myRef.setValue(partida);
            intent.putExtra("codigo", partida.getCodigo());

            b.putString("modo_defensa", "modo_defensa");

            intent.putExtras(b);

            startActivity(intent);
        }
    }
}
