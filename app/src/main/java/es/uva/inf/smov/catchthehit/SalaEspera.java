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
    private ViewGroup layout;
    private Partida partida;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala_espera);

        //Conectamos a la base de datos y obtenemos la referencia de la partida.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        codigo = getIntent().getExtras().getString("codigo");
        DatabaseReference myRef = database.getReference(codigo);

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
                            miJugador.setText(partida.getEquipo1().elegirJugador(i).getNombre());
                            setImagen(partida.getEquipo1().elegirJugador(i).getNombre());
                        }
                    }else
                        break;
                }
                //Cuando todos esten listos vamos al modo ataque
                if(listos == 4){
                    Intent intent = new Intent(SalaEspera.this, ModoAtaque.class);
                    intent.putExtra("codigo",partida.getCodigo());
                    startActivity(intent);
                }
                //Si no lo estan, ponemos los datos de codigo de partida y jugadores listos.
                codigoTxt.setText("Código de sala: "+codigo);
                listosTxt.setText("Esperando jugadores ("+(listos+1)+"/4)");

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

    public void clickVolver(View v){
        Intent intent = new Intent(SalaEspera.this, Inicio.class);
        startActivity(intent);
    }
}