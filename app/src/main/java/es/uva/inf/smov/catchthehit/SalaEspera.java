package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.uva.inf.smov.catchthehit.datos.Partida;

public class SalaEspera extends AppCompatActivity {

    private int listos;
    private Partida partida;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private String codigo;
    private DatabaseReference myRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala_espera);
        //Conectamos a la base de datos y obtenemos la referencia de la partida.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        codigo = getIntent().getExtras().getString("codigo");
        myRef = database.getReference(codigo);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        carga();
    }

    @Override
    protected void onStart() {
        super.onStart();
        carga();
    }

    private void carga(){

        //Referencias a los campos a rellenar, excepto la imagen.
        TextView listosTxt = (TextView) findViewById(R.id.espera);
        TextView codigoTxt = (TextView) findViewById(R.id.codigo);
        TextView miJugador = (TextView) findViewById(R.id.miJugador);

        //listener para obtener los datos de la partida cuando haya cambios.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                partida = dataSnapshot.getValue(Partida.class);
                /*
                 * Comprobamos cuantos jugadores hay listos.
                 * Ademas si uno de esos listos somos nosotros, actualizamos los campos con nuestra
                 * info.
                 */
                for(int i = 0; i < 4; i++){
                    if(partida.getEquipo1().elegirJugador(i).isReady()) {
                        listos = i + 1;
                        if(partida.getEquipo1().elegirJugador(i).getUsuario().equals(user.getUid())) {
                            miJugador.setText("Tu jugador es: "+partida.getEquipo1().elegirJugador(i).getNombre());
                            setImagen(partida.getEquipo1().elegirJugador(i).getNombre());
                        }
                    }else
                        break;
                }
                //Cuando todos esten listos vamos al modo ataque
                if(listos == 4){
                    myRef.removeEventListener(this);
                    Button boton = (Button) findViewById(R.id.button);
                    boton.setText("Empezar");
                }
                //Si no lo estan, ponemos los datos de codigo de partida y jugadores listos.
                codigoTxt.setText("Código de sala: "+codigo);
                listosTxt.setText("Esperando jugadores ("+(listos)+"/4)");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.");
            }
        });
    }

    /*
     * Ponemos la imagen del color que corresponde a cada jugador.
     */
    private void setImagen(String nombre){
        ImageView imagen = (ImageView) findViewById(R.id.miJugadorIcon);
        int id;
        switch (nombre){
            case "Tom":
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/red_player", null, null);
                imagen.setImageResource(id);
                break;
            case "Ted":
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/green_player", null, null);
                imagen.setImageResource(id);
                break;
            case "Willie":
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/blue_player", null, null);
                imagen.setImageResource(id);
                break;
            default:
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/purple_player", null, null);
                imagen.setImageResource(id);

        }
    }
    /*
     * Salir de la sala dejando el hueco libre para otros jugadores.
     */
    public void clickBoton(View v){
        if(listos < 4) {
            /*
             * Esto es para que cuando un jugador salga de la sala se elimine su Uid de la lista de
             * jugadores y ready de ese jugador se ponga en false.
             */

        for(int i = 0; i < 4; i++){
            if(partida.getEquipo1().elegirJugador(i).getUsuario().equals(user.getUid())) {
                partida.getEquipo1().elegirJugador(i).setUsuario("");
                partida.getEquipo1().elegirJugador(i).setReady(false);
                myRef.setValue(partida);
                break;
            }
        }
            Intent intent = new Intent(SalaEspera.this, Inicio.class);
            startActivity(intent);
        } else{
            if(partida.getEquipo1().elegirJugador(0).getUsuario().equals(user.getUid())){
                for(int i = 0; i < 4; i++){
                    partida.getEquipo1().elegirJugador(i).setReady(false);
                }
            }
            Intent intent = new Intent(SalaEspera.this, ModoAtaque.class);
            intent.putExtra("codigo",partida.getCodigo());
            startActivity(intent);
        }
    }
}