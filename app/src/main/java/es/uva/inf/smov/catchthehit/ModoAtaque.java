package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import es.uva.inf.smov.catchthehit.datos.Jugador;
import es.uva.inf.smov.catchthehit.datos.Partida;

public class ModoAtaque extends AppCompatActivity {

    private ViewGroup layout;
    private String codigo;
    private FirebaseDatabase database;
    private Partida partida;
    private DatabaseReference myRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_ataque);

        codigo = getIntent().getExtras().getString("codigo");
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(codigo);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                partida = dataSnapshot.getValue(Partida.class);

                if (partida.getJugadaAct() == 4) {
                    myRef.removeEventListener(this);
                    partida.setJugadaAct(0);
                    myRef.setValue(partida);
                    Intent intent = new Intent(ModoAtaque.this, ModoDefensa.class);
                    intent.putExtra("codigo", partida.getCodigo());

                    Bundle b = new Bundle();

                    b.putString("modo_ataque", "modo_ataque");
                    intent.putExtras(b);

                    startActivity(intent);
                } else juego();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.");
            }
        });
    }

    public void click1(View v) {
        ImageView bat = (ImageView) v.findViewById(R.id.bateador);
        int user = Integer.parseInt(bat.getTag().toString());
        aJugador(partida.getEquipo1().elegirJugador(user));

    }

    public void click2(View v) {
        if ((Integer) v.getTag() != null) {
            aJugador(partida.getEquipo1().elegirJugador((Integer) v.getTag()));
        }
    }

    public void click3(View v) {
        if ((Integer) v.getTag() != null)
            aJugador(partida.getEquipo1().elegirJugador((Integer) v.getTag()));
    }

    public void click4(View v) {
        if ((Integer) v.getTag() != null)
            aJugador(partida.getEquipo1().elegirJugador((Integer) v.getTag()));
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

    private void colocaJugador(int i) {
        int base;
        base = partida.getEquipo1().elegirJugador(i).getPosicionAtaque();
        ImageView bat;

        switch (base) {
            case 1:
                layout = (ViewGroup) findViewById(R.id.Base1);
                LayoutInflater inflater = LayoutInflater.from(this);
                int id = R.layout.bateador;
                ConstraintLayout mov = (ConstraintLayout) inflater.inflate(id, null, false);
                bat = (ImageView) mov.findViewById(R.id.bateador);
                bat.setImageResource(buscaImagen(i));
                bat.setTag(String.valueOf(partida.getEquipo1().elegirJugador(i).getId()));
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

        TextView energia = (TextView) mov.findViewById(R.id.statEnergia);


        switch (avance) {
            case 1:
                fuerza.setText("- " + "2");
                velocidad.setText("- " + "2");
                energia.setText("-" + "5");
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
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(codigo);
        if (!avance.getText().toString().equals("Descansa")) {
            /*
            Si se ha seleccionado otra cosa tendremos que restar estadisticas y cambiar posición con
            el switch
             */
            int posOr = partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getPosicionAtaque();
            int pos;

            switch (avance.getText().toString()) {
                case "Avanza 1":
                    pos = partida.getEquipo1().elegirJugador(partida.getJugadaAct()).avanza(1);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosFue(2);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosRes(5);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosVel(2);
                    for (int i = 0; i < 4; i++) {
                        if (i == partida.getJugadaAct()) {
                            continue;
                        } else if (pos == partida.getEquipo1().elegirJugador(i).getPosicionAtaque()) {
                            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setEnjuego(false);
                            Dialog mensaje = new Dialog(this);
                            mensaje.setContentView(R.layout.eliminado);
                            mensaje.show();
                            myRef.setValue(partida);
                            break;
                        }
                    }
                    /*if(defensa(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getFuerza())
                        > 90){
                        partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setEnjuego(false);
                        myRef.setValue(partida);
                        break;
                    }*/

                    if (pos < posOr) partida.getEquipo1().incrementaPuntos();
                    partida.siguienteJugada();
                    myRef.setValue(partida);

                    break;
                case "Avanza 2":
                    pos = partida.getEquipo1().elegirJugador(partida.getJugadaAct()).avanza(2);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosFue(4);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosRes(10);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosVel(4);
                    for (int i = 0; i < 4; i++) {
                        if (i == partida.getJugadaAct()) {
                            continue;
                        } else if (pos == partida.getEquipo1().elegirJugador(i).getPosicionAtaque()) {
                            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setEnjuego(false);
                        }
                    }
                    if (pos < posOr) partida.getEquipo1().incrementaPuntos();
                    partida.siguienteJugada();
                    myRef.setValue(partida);

                    break;
                case "Avanza 3":
                    pos = partida.getEquipo1().elegirJugador(partida.getJugadaAct()).avanza(3);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosFue(6);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosRes(15);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosVel(6);
                    for (int i = 0; i < 4; i++) {
                        if (i == partida.getJugadaAct()) {
                            continue;
                        } else if (pos == partida.getEquipo1().elegirJugador(i).getPosicionAtaque()) {
                            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setEnjuego(false);
                        }
                    }
                    if (pos < posOr) partida.getEquipo1().incrementaPuntos();
                    partida.siguienteJugada();
                    myRef.setValue(partida);

                    break;
                default:

            }
        } else {
            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosRes(-5);
            partida.siguienteJugada();
            myRef.setValue(partida);
        }

    }

    private int buscaImagen(int i) {
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

    private void juego() {
        ImageView bat;
        bat = (ImageView) findViewById(R.id.Base2);
        bat.setVisibility(View.INVISIBLE);
        bat = (ImageView) findViewById(R.id.Base3);
        bat.setVisibility(View.INVISIBLE);
        bat = (ImageView) findViewById(R.id.Base4);
        bat.setVisibility(View.INVISIBLE);
        layout = (ViewGroup) findViewById(R.id.Base1);
        layout.removeAllViews();
        layout = (ViewGroup) findViewById(R.id.movimientos);
        layout.removeAllViews();

        for (int i = 0; i < 4; i++) {
            if (partida.getEquipo1().elegirJugador(i).isEnjuego()) colocaJugador(i);
            if (i == partida.getJugadaAct() && partida.getEquipo1().elegirJugador(i).getUsuario().equals(user.getUid())) {
                if (partida.getPuntuacion() > 85) {
                    addTirada(1);
                    addTirada(2);
                    addTirada(3);
                    addTirada(0);
                } else if (partida.getPuntuacion() > 50) {
                    addTirada(1);
                    addTirada(2);
                    addTirada(0);
                } else if (partida.getPuntuacion() > 25) {
                    addTirada(1);
                    addTirada(0);
                } else {
                    partida.getEquipo1().elegirJugador(i).setEnjuego(false);
                    partida.setJugadaAct(i + 1);
                    myRef.setValue(partida);
                }
            }

        }
    }

    private int defensa(int fuerza){
        Random random = new Random();
        int zona = random.nextInt(4);
        int sumaRes = 0;
        int sumaVel = 0;
        int sumaRef = 0;
        int media = 0;
        switch (zona){
            case 1:
                for(int i = 0; i < 4;i++){
                    if(partida.getEquipo2().elegirJugador(i).getPosicionDefensa()==0 || partida.getEquipo2().elegirJugador(i).getPosicionDefensa()==1) {
                        sumaRes += partida.getEquipo2().elegirJugador(i).getResistencia();
                        sumaRef += partida.getEquipo2().elegirJugador(i).getReflejos();
                        sumaVel += partida.getEquipo2().elegirJugador(i).getVelocidad();
                        partida.getEquipo2().elegirJugador(i).menosRes(5);
                        partida.getEquipo2().elegirJugador(i).menosVel(2);
                        partida.getEquipo2().elegirJugador(i).menosRef(2);
                    }
                }
                sumaRes /= 2;
                sumaVel /= 2;
                sumaRef /= 2;
                media = (sumaRes+sumaRef+sumaVel-fuerza)/3;
                return media;
            case 2:
                for(int i = 0; i < 4;i++){
                    if(partida.getEquipo2().elegirJugador(i).getPosicionDefensa()==2 || partida.getEquipo2().elegirJugador(i).getPosicionDefensa()==3) {
                        sumaRes += partida.getEquipo2().elegirJugador(i).getResistencia();
                        sumaRef += partida.getEquipo2().elegirJugador(i).getReflejos();
                        sumaVel += partida.getEquipo2().elegirJugador(i).getVelocidad();
                        partida.getEquipo2().elegirJugador(i).menosRes(5);
                        partida.getEquipo2().elegirJugador(i).menosVel(2);
                        partida.getEquipo2().elegirJugador(i).menosRef(2);
                    }
                }
                sumaRes /= 2;
                sumaVel /= 2;
                sumaRef /= 2;
                media = (sumaRes+sumaRef+sumaVel-fuerza)/3;
                return media;
            case 3:
                for(int i = 0; i < 4;i++){
                    if(partida.getEquipo2().elegirJugador(i).getPosicionDefensa()==4 ||
                            partida.getEquipo2().elegirJugador(i).getPosicionDefensa()==5 ||
                            partida.getEquipo2().elegirJugador(i).getPosicionDefensa()==6) {
                        sumaRes += partida.getEquipo2().elegirJugador(i).getResistencia();
                        sumaRef += partida.getEquipo2().elegirJugador(i).getReflejos();
                        sumaVel += partida.getEquipo2().elegirJugador(i).getVelocidad();
                        partida.getEquipo2().elegirJugador(i).menosRes(5);
                        partida.getEquipo2().elegirJugador(i).menosVel(2);
                        partida.getEquipo2().elegirJugador(i).menosRef(2);
                    }
                }
                sumaRes /= 3;
                sumaVel /= 3;
                sumaRef /= 3;
                media = (sumaRes+sumaRef+sumaVel-fuerza)/3;
                return media;
            default:
                break;

        }
        return 0;
    }

}