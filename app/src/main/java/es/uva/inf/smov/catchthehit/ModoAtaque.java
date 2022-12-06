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
import android.widget.ImageView;
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
                colocaJugadores();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.");
            }
        });
    }

    public void click1(View v) {
        ImageView bat = (ImageView) v.findViewById(R.id.Base4);
        String user = (String) bat.getTag();
        for (int i = 0; i < 4; i++) {
            if (partida.getEquipo1().elegirJugador(i).getUsuario().equals(user))
                aJugador(partida.getEquipo1().elegirJugador(i));
        }
    }

    public void click2(View v) {
        if((Integer)v.getTag()!=null)
        aJugador(partida.getEquipo1().elegirJugador((Integer)v.getTag()));
    }

    public void click3(View v) {
        if((Integer)v.getTag()!=null)
        aJugador(partida.getEquipo1().elegirJugador((Integer)v.getTag()));
    }

    public void click4(View v) {
        if((Integer)v.getTag()!=null)
        aJugador(partida.getEquipo1().elegirJugador((Integer)v.getTag()));
    }

    private void aJugador(Jugador jugador) {
        Intent intent = new Intent(ModoAtaque.this, JugadorActivity.class);
        intent.putExtra("nombre", jugador.getNombre());
        intent.putExtra("fuerza", jugador.getFuerza());
        intent.putExtra("velocidad", jugador.getVelocidad());
        intent.putExtra("resistencia", jugador.getResistencia());
        intent.putExtra("codigo", codigo);
        startActivity(intent);
    }

    private void colocaJugadores() {
        int base;
        for (int i = 0; i < 4; i++) {
            if (!partida.getEquipo1().elegirJugador(i).isEnjuego())
                continue;
            base = partida.getEquipo1().elegirJugador(i).getPosicion();
            ImageView bat;
            switch (base) {
                case 1:
                    layout = (ViewGroup) findViewById(R.id.Base1);
                    LayoutInflater inflater = LayoutInflater.from(this);
                    int id = R.layout.bateador;
                    ConstraintLayout mov = (ConstraintLayout) inflater.inflate(id, null, false);
                    bat = (ImageView) mov.findViewById(R.id.bateador);
                    bat.setTag(partida.getEquipo1().elegirJugador(i).getNombre());
                    layout.addView(mov);
                    break;
                case 2:
                    bat = (ImageView) findViewById(R.id.Base2);
                    bat.setTag(i);
                    bat.setImageResource(buscaImagen(i));
                    bat.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    bat = (ImageView) findViewById(R.id.Base3);
                    bat.setTag(i);
                    bat.setImageResource(buscaImagen(i));
                    bat.setVisibility(View.VISIBLE);
                    break;
                default:
                    bat = (ImageView) findViewById(R.id.Base4);
                    bat.setTag(i);
                    bat.setImageResource(buscaImagen(i));
                    bat.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @SuppressLint("InlinedApi")
    private void addTirada(int avance) {
        layout = (ViewGroup) findViewById(R.id.movimientos);
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.movimiento;
        ConstraintLayout mov = (ConstraintLayout) inflater.inflate(id, null, false);
        TextView movimiento = (TextView) mov.findViewById(R.id.opcion);
        movimiento.setText("Avanza " + String.valueOf(avance));
        TextView fuerza = (TextView) mov.findViewById(R.id.statFuerza);
        TextView velocidad = (TextView) mov.findViewById(R.id.statVelocidad);
        ;
        TextView energia = (TextView) mov.findViewById(R.id.statEnergia);
        ;

        switch (avance) {
            case 1:
                fuerza.setText("- " + "2");
                velocidad.setText("- " + "2");
                energia.setText("- " + "5");
                break;

            case 2:
                fuerza.setText("- " + "4");
                velocidad.setText("- " + "4");
                energia.setText("- " + "10");
                break;

            case 3:
                fuerza.setText("- " + "6");
                velocidad.setText("- " + "6");
                energia.setText("- " + "15");
                break;

            default:
                movimiento.setText("Descansa");
                fuerza.setText("- " + "0");
                velocidad.setText("- " + "0");
                energia.setText("+ " + "5");

        }

        layout.addView(mov);
    }

    public void clickOpcion(View v) {
        TextView avance = (TextView) v.findViewById(R.id.opcion);
        /*
        Si se ha seleccionado descansar solo hay que sumar 5 a la resistencia del jugador.
         */
        if (!avance.getText().toString().equals("Descansa")) {
            /*
            Si se ha seleccionado otra cosa tendremos que restar estadisticas y cambiar posición con
            el switch
             */
            TextView energia = (TextView) v.findViewById(R.id.statEnergia);
            TextView fuerza = (TextView) v.findViewById(R.id.statFuerza);
            TextView velocidad = (TextView) v.findViewById(R.id.statVelocidad);
            switch (avance.getText().toString()) {
                case "Avanza 1":

                    break;
                case "Avanza 2":

                    break;
                case "Avanza 3":

                    break;
                default:

            }
        } else {

        }
        if (partida.getJugadaAct() == 4) {
            database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
            DatabaseReference myRef = database.getReference(partida.getCodigo());
            myRef.setValue(partida);
            Intent intent = new Intent(ModoAtaque.this, ModoDefensa.class);
            intent.putExtra("codigo", partida.getCodigo());

            Bundle b = new Bundle();

            b.putString("modo_ataque", "modo_ataque");
            intent.putExtras(b);

            startActivity(intent);
        }
    }

    public int buscaImagen(int i) {
        switch (i) {
            default:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/purple_player", null, null);
            case 0:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/red_player", null, null);
            case 1:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/green_player", null, null);
            case 2:
                return getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/blue_player", null, null);

        }
    }

}