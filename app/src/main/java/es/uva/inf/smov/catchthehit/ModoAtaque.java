package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
        //Creación de la referencia a la base de datos y recolección de datos del usuario.
        codigo = getIntent().getExtras().getString("codigo");
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(codigo);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //Cojemos los datos de la partida.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                partida = dataSnapshot.getValue(Partida.class);
                //Comprobamos cuantos jugadores estan eliminados.
                int eliminados = 0;
                for(int i = 0; i < 4; i++){
                    if(!partida.getEquipo1().elegirJugador(i).isEnjuego())
                        eliminados++;
                }

                /*
                Si se han eliminado 3 jugadores o se llega a 3 puntos, vamos al modo defensa y
                reiniciamos los puntos conseguidos en la ronda y jugadaAct.
                 */
                if (partida.getPuntosRonda() == 3 || eliminados >=3) {
                    myRef.removeEventListener(this);
                    partida.setPuntosRonda(0);
                    partida.setJugadaAct(0);
                    myRef.setValue(partida);
                    if(partida.getEquipo1().elegirJugador(0).getUsuario().equals(user.getUid())) {
                        Intent intent = new Intent(ModoAtaque.this, ModoDefensa.class);
                        intent.putExtra("codigo", partida.getCodigo());
                        Bundle b = new Bundle();
                        b.putString("modo_ataque", "modo_ataque");
                        intent.putExtras(b);
                        startActivity(intent);
                    } else{
                        Intent intent = new Intent(ModoAtaque.this, espera_defensa.class);
                        intent.putExtra("codigo", partida.getCodigo());
                        startActivity(intent);
                    }
                    //Si no se han eliminado 3 jugadores o se llega a 3 puntos, sigue el juego.
                } else juego();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.");
            }
        });
    }

    /*
    Metodos para ver las estadísticas de los jugadores.
     */
    public void click1(View v) {
        ImageView bat = v.findViewById(R.id.bateador);
        //Dato almacenado en el xml para saber a que jugador de la base 1 se ha pulsado.
        int user = Integer.parseInt(bat.getTag().toString());
        aJugador(partida.getEquipo1().elegirJugador(user));

    }

    public void click2(View v) {
        //Aqui se usa el tag para comprobar que hay algún jugador en esa base.
        if (v.getTag() != null) {
            aJugador(partida.getEquipo1().elegirJugador((Integer) v.getTag()));
        }
    }

    public void click3(View v) {
        if (v.getTag() != null) aJugador(partida.getEquipo1().elegirJugador((Integer) v.getTag()));
    }

    public void click4(View v) {
        if (v.getTag() != null) aJugador(partida.getEquipo1().elegirJugador((Integer) v.getTag()));
    }

    /*
    Se mandan los datos del jugador seleccionado a la vista de Jugador y se muestra.
     */
    private void aJugador(Jugador jugador) {
        Intent intent = new Intent(ModoAtaque.this, JugadorActivity.class);
        intent.putExtra("nombre", jugador.getNombre());
        intent.putExtra("fuerza", jugador.getFuerza());
        intent.putExtra("velocidad", jugador.getVelocidad());
        intent.putExtra("resistencia", jugador.getResistencia());
        intent.putExtra("codigo", codigo);
        startActivity(intent);
    }

    /*
    Coloca el jugador dado en el tablero. Si va en la primera base usamos inflate, si no, ponemos
    la base correcta como visible y lo colocamos. En todos los casos ponemos en el tag del xml el
    id correspondiente.
     */
    private void colocaJugador(int i) {
        int base;
        base = partida.getEquipo1().elegirJugador(i).getPosicionAtaque();
        ImageView bat;

        switch (base) {
            case 1:
                layout = findViewById(R.id.Base1);
                LayoutInflater inflater = LayoutInflater.from(this);
                int id = R.layout.bateador;
                ConstraintLayout mov = (ConstraintLayout) inflater.inflate(id, null, false);
                bat = mov.findViewById(R.id.bateador);
                bat.setImageResource(buscaImagen(i));
                bat.setTag(String.valueOf(partida.getEquipo1().elegirJugador(i).getId()));
                layout.addView(mov);
                break;
            case 2:
                bat = findViewById(R.id.Base2);
                bat.setTag(i);
                bat.setImageResource(buscaImagen(i));
                bat.setVisibility(View.VISIBLE);
                break;
            case 3:
                bat = findViewById(R.id.Base3);
                bat.setTag(i);
                bat.setImageResource(buscaImagen(i));
                bat.setVisibility(View.VISIBLE);
                break;
            default:
                bat = findViewById(R.id.Base4);
                bat.setTag(i);
                bat.setImageResource(buscaImagen(i));
                bat.setVisibility(View.VISIBLE);
        }
    }

    /*
    Colocamos las opciones de avance del jugador en función de la puntuación obtenida en el minijuego.
    Para esto usamos inflate. Los valores de perdida/ganancia de estadísticas de cada jugador son
    estáticos (en todas las tiradas es igual).
     */
    private void addTirada(int avance) {
        layout = findViewById(R.id.movimientos);
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.movimiento;
        ConstraintLayout mov = (ConstraintLayout) inflater.inflate(id, null, false);
        TextView movimiento = mov.findViewById(R.id.opcion);
        movimiento.setText("Avanza " + avance);
        TextView fuerza = mov.findViewById(R.id.statFuerza);
        TextView velocidad = mov.findViewById(R.id.statVelocidad);

        TextView energia = mov.findViewById(R.id.statEnergia);


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
    /*
    Cuando un jugador selecciona una opción tenemos que identificar que opción es, restar las estadísticas,
    comprobar si los defensores cogen la bola, comprobar que la base donde acaba está vacío y apntar
    el progreso. El proceso es el siguiente:
    1. Diferenciamos avance o descanso (Para simplificar el switch) y guardamos la posición original
    (Por si pasamos de la 4 a la 2).
    2. Restamos las estadísticas y generamos el resultado de la defensa.
    3. Si nos da 1 es que el jugador esta eliminado, 2, es un home run, 0 es que se sigue normal.
    4. Comprobamos si la base objetivo está ocupada o no.
    5. Comprobamos si se ha dado una vuelta y pasamos el turno.
     */
    public void clickOpcion(View v) {
    
        TextView avance = v.findViewById(R.id.opcion);
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(codigo);
        //*****Paso 1*****//
        if (!avance.getText().toString().equals("Descansa")) {
            int posOr = partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getPosicionAtaque();
            int pos;
            int defensa;

            switch (avance.getText().toString()) {
                case "Avanza 1":
                    //*****Paso 2*****//
                    pos = partida.getEquipo1().elegirJugador(partida.getJugadaAct()).avanza(1);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosFue(2);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosRes(5);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosVel(2);
                    defensa = defensa(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getFuerza(), 1);
                    //*****Paso 3*****//
                    if (defensa == 1) {
                        partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setEnjuego(false);
                        Dialog mensaje = new Dialog(this);
                        mensaje.setContentView(R.layout.pop_mensaje);
                        TextView txt = (TextView) mensaje.findViewById((R.id.txtMensaje));
                        txt.setText("Los defensas han atrapado la bola, estas out!!");
                        mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mensaje.show();
                        myRef.setValue(partida);
                    } else if (defensa == 2) {
                        partida.getEquipo1().incrementaPuntos();
                        partida.otroPunto();
                        partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setPosicionAtaque(1);
                        if(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getId()!=3) {
                            partida.siguienteJugada();
                        } else partida.setJugadaAct(0);
                        Dialog mensaje = new Dialog(this);
                        mensaje.setContentView(R.layout.pop_mensaje);
                        TextView txt = (TextView) mensaje.findViewById(R.id.txtMensaje);
                        txt.setText("HOME RUN!!!");
                        mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mensaje.show();
                        myRef.setValue(partida);
                    }
                    //*****Paso 4*****//
                    for (int i = 0; i < 4; i++) {
                        if (i == partida.getJugadaAct()) {
                            continue;
                        } else if (pos == partida.getEquipo1().elegirJugador(i).getPosicionAtaque()) {
                            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setEnjuego(false);
                            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setPosicionAtaque(0);
                            Dialog mensaje = new Dialog(this);
                            mensaje.setContentView(R.layout.pop_mensaje);
                            TextView txt = (TextView) mensaje.findViewById(R.id.txtMensaje);
                            txt.setText("Un jugador ocupaba esa base, has sido eliminado!");
                            mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            mensaje.show();
                            myRef.setValue(partida);
                            break;
                        }
                    }
                    //*****Paso 5*****//
                    if (pos < posOr) {
                        partida.getEquipo1().incrementaPuntos();
                        partida.otroPunto();
                    }
                    if(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getId()!=3) {
                        partida.siguienteJugada();
                    } else partida.setJugadaAct(0);
                    myRef.setValue(partida);

                    break;
                case "Avanza 2":
                    pos = partida.getEquipo1().elegirJugador(partida.getJugadaAct()).avanza(2);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosFue(4);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosRes(10);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosVel(4);
                    defensa = defensa(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getFuerza(), 2);

                    if (defensa == 1) {
                        partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setEnjuego(false);
                        Dialog mensaje = new Dialog(this);
                        mensaje.setContentView(R.layout.pop_mensaje);
                        TextView txt = (TextView) mensaje.findViewById((R.id.txtMensaje));
                        txt.setText("Los defensas han atrapado la bola, estas out!!");
                        mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mensaje.show();
                        myRef.setValue(partida);
                    } else if (defensa == 2) {
                        partida.getEquipo1().incrementaPuntos();
                        partida.otroPunto();
                        partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setPosicionAtaque(1);
                        if(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getId()!=3) {
                            partida.siguienteJugada();
                        } else partida.setJugadaAct(0);
                        Dialog mensaje = new Dialog(this);
                        mensaje.setContentView(R.layout.pop_mensaje);
                        TextView txt = (TextView) mensaje.findViewById(R.id.txtMensaje);
                        txt.setText("HOME RUN!!!");
                        mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mensaje.show();
                        myRef.setValue(partida);
                    }
                    for (int i = 0; i < 4; i++) {
                        if (i == partida.getJugadaAct()) {
                            continue;
                        } else if (pos == partida.getEquipo1().elegirJugador(i).getPosicionAtaque()) {
                            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setEnjuego(false);
                            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setPosicionAtaque(0);
                            Dialog mensaje = new Dialog(this);
                            mensaje.setContentView(R.layout.pop_mensaje);
                            TextView txt = (TextView) mensaje.findViewById(R.id.txtMensaje);
                            txt.setText("Un jugador ocupaba esa base, has sido eliminado!");
                            mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            mensaje.show();
                            myRef.setValue(partida);
                        }
                    }
                    if (pos < posOr){
                        partida.getEquipo1().incrementaPuntos();
                        partida.otroPunto();
                    }
                    if(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getId()!=3) {
                        partida.siguienteJugada();
                    } else partida.setJugadaAct(0);
                    myRef.setValue(partida);

                    break;
                case "Avanza 3":
                    pos = partida.getEquipo1().elegirJugador(partida.getJugadaAct()).avanza(3);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosFue(6);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosRes(15);
                    partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosVel(6);
                    defensa = defensa(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getFuerza(), 3);

                    if (defensa == 1) {
                        partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setEnjuego(false);
                        Dialog mensaje = new Dialog(this);
                        mensaje.setContentView(R.layout.pop_mensaje);
                        TextView txt = (TextView) mensaje.findViewById((R.id.txtMensaje));
                        txt.setText("Los defensas han atrapado la bola, estas out!!");
                        mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mensaje.show();
                        myRef.setValue(partida);
                    } else if (defensa == 2) {
                        partida.getEquipo1().incrementaPuntos();
                        partida.otroPunto();
                        partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setPosicionAtaque(1);
                        if(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getId()!=3) {
                            partida.siguienteJugada();
                        } else partida.setJugadaAct(0);
                        Dialog mensaje = new Dialog(this);
                        mensaje.setContentView(R.layout.pop_mensaje);
                        TextView txt = (TextView) mensaje.findViewById(R.id.txtMensaje);
                        txt.setText("HOME RUN!!!");
                        mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mensaje.show();
                        myRef.setValue(partida);
                    }
                    for (int i = 0; i < 4; i++) {
                        if (i == partida.getJugadaAct()) {
                            continue;
                        } else if (pos == partida.getEquipo1().elegirJugador(i).getPosicionAtaque()) {
                            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setEnjuego(false);
                            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).setPosicionAtaque(0);
                            Dialog mensaje = new Dialog(this);
                            mensaje.setContentView(R.layout.pop_mensaje);
                            TextView txt = (TextView) mensaje.findViewById(R.id.txtMensaje);
                            txt.setText("Un jugador ocupaba esa base, has sido eliminado!");
                            mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            mensaje.show();
                            myRef.setValue(partida);
                        }
                    }
                    if (pos < posOr){
                        partida.getEquipo1().incrementaPuntos();
                        partida.otroPunto();
                    }
                    if(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getId()!=3) {
                        partida.siguienteJugada();
                    } else partida.setJugadaAct(0);
                    myRef.setValue(partida);

                    break;
                default:

            }
        } else {
            /*
            Si se ha seleccionado descansar solo hay que sumar 5 a la resistencia del jugador,
            siempre que la energía actual no supere 95 (si no tendría más de 100).
             */
            if (partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getResistencia() <= 95) ;
            partida.getEquipo1().elegirJugador(partida.getJugadaAct()).menosRes(-5);
            if(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getId()!=3) {
                partida.siguienteJugada();
            } else partida.setJugadaAct(0);
            myRef.setValue(partida);
        }
    }
    /*
    Obtenemos el identificador numérico de la imagen del jugador deseado.
     */
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
    /*
    Inicia la partida cada ronda.
    1. Pone invisibles o elimina las views actuales de los jugadores.
    2. Coloca los jugadores en juego.
    3. Crea las opciones de avance es función de la puntuación obtenida en el minijuego para el
    jugador que le toque avanzar.
     */
    private void juego() {
        //*****Paso 1*****//
        ImageView bat;
        bat = findViewById(R.id.Base2);
        bat.setVisibility(View.INVISIBLE);
        bat.setTag(null);
        bat = findViewById(R.id.Base3);
        bat.setVisibility(View.INVISIBLE);
        bat.setTag(null);
        bat = findViewById(R.id.Base4);
        bat.setVisibility(View.INVISIBLE);
        bat.setTag(null);
        layout = findViewById(R.id.Base1);
        layout.removeAllViews();
        layout = findViewById(R.id.movimientos);
        layout.removeAllViews();

        for (int i = 0; i < 4; i++) {
            //*****Paso 2*****//
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
    /*
    Simulación de la defensa. Elige la zona de caida de la bola de forma aleatoria, despues, calcula
    la media de las estadísticas de los jugadores de esa zona. Finalmente, en función de cuanto
    quiera avanzar el defensor y la media calculada se da un valor de defensa.
    Si la media es superior a 90, se elimina al jugador.
    Media entre 75 y 90, solo se puede avanzar una base.
    Media entre 50 y 75, se puede avanzar hasta 2.
    Media entre 25 y 50, Se puede avanzar 3.
    Media por debajo de 25, home run.
    También se resta alguna estadística a los defensores.
     */
    private int defensa(int fuerza, int avance) {
        int eliminado = 0;
        Random random = new Random();
        int zona = random.nextInt(3)+1;
        int sumaRes = 0;
        int sumaVel = 0;
        int sumaRef = 0;
        int media = 0;
        switch (zona) {
            case 1:
                for (int i = 0; i < 2; i++) {
                        sumaRes += partida.getEquipo2().elegirJugador(i).getResistencia();
                        sumaRef += partida.getEquipo2().elegirJugador(i).getReflejos();
                        sumaVel += partida.getEquipo2().elegirJugador(i).getVelocidad();
                        partida.getEquipo2().elegirJugador(i).menosRes(5);
                        partida.getEquipo2().elegirJugador(i).menosVel(2);
                        partida.getEquipo2().elegirJugador(i).menosRef(2);
                    }

                sumaRes /= 2;
                sumaVel /= 2;
                sumaRef /= 2;
                media = (sumaRes + sumaRef + sumaVel - (fuerza/2)) / 3;
                break;

            case 2:
                for (int i = 2; i < 4; i++) {
                        sumaRes += partida.getEquipo2().elegirJugador(i).getResistencia();
                        sumaRef += partida.getEquipo2().elegirJugador(i).getReflejos();
                        sumaVel += partida.getEquipo2().elegirJugador(i).getVelocidad();
                        partida.getEquipo2().elegirJugador(i).menosRes(5);
                        partida.getEquipo2().elegirJugador(i).menosVel(2);
                        partida.getEquipo2().elegirJugador(i).menosRef(2);
                    }

                sumaRes /= 2;
                sumaVel /= 2;
                sumaRef /= 2;
                media = (sumaRes + sumaRef + sumaVel - (fuerza/2)) / 3;
                break;
            case 3:
                for (int i = 4; i < 7; i++) {
                        sumaRes += partida.getEquipo2().elegirJugador(i).getResistencia();
                        sumaRef += partida.getEquipo2().elegirJugador(i).getReflejos();
                        sumaVel += partida.getEquipo2().elegirJugador(i).getVelocidad();
                        partida.getEquipo2().elegirJugador(i).menosRes(5);
                        partida.getEquipo2().elegirJugador(i).menosVel(2);
                        partida.getEquipo2().elegirJugador(i).menosRef(2);
                    }

                sumaRes /= 3;
                sumaVel /= 3;
                sumaRef /= 3;
                media = (sumaRes + sumaRef + sumaVel - (fuerza/2)) / 3;
                break;
            default:
                break;

        }
        Log.e("DefensaRef", String.valueOf(sumaRef));
        Log.e("DefensaVel", String.valueOf(sumaVel));
        Log.e("DefensaRes", String.valueOf(sumaRes));
        Log.e("DefensaFuer", String.valueOf(fuerza));
        Log.e("DefensaMed", String.valueOf(media));
        Log.e("DefensaZona", String.valueOf(zona));
        Log.e("DefensaJugador",String.valueOf(partida.getEquipo1().elegirJugador(partida.getJugadaAct()).getId()));
        if (media > 90) {
            eliminado = 1;
        } else if (media > 75 && avance > 1) {
            eliminado = 1;
        } else if (media > 50 && avance > 2) {
            eliminado = 1;
        } else if (media < 25) {
            eliminado = 2;
        }
        return eliminado;
    }



}