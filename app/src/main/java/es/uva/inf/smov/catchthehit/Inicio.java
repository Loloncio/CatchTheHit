package es.uva.inf.smov.catchthehit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.uva.inf.smov.catchthehit.datos.Partida;


public class Inicio extends AppCompatActivity {

    private EditText txtEdad;
    private FirebaseAuth mAuth;
    private Partida partida;
    private FirebaseUser currentUser;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        txtEdad = (EditText) findViewById(R.id.txtEdad);

    }

    public void clickBotonComoJugar(View v) {
        //Creamos el intent
        Intent intent = new Intent(Inicio.this, ComoJugar.class);
        startActivity(intent);
    }

    public void clickBotonEmpezarJuego(View v) {
        Bundle b = new Bundle();
        //logueamos al usuario como anónimo
        login();
        //Conexión a la base de datos, habrá que crear un código si no se ha introducido ninguno.
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        database = FirebaseDatabase.getInstance();

        //Obtenemos el codigo de la sala
        String sala = String.valueOf(txtEdad.getText());

        if (sala.equals("")) {
            //Si no se ha introducido sala vamos a crear una
            Intent intent;
            intent = new Intent(Inicio.this, CrearGrupos.class);
            startActivity(intent);

        } else {
            //Si se ha introducido, obtenemos una referencia de la base de datos con ese código
            myRef = database.getReference(sala);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    partida = dataSnapshot.getValue(Partida.class);
                    if (partida == null) {
                        CharSequence fail = "Codigo de sala no valido, I puede ser una i mayuscula o una L minuscula ";
                        Toast toast = Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }
                    /*Guardamos el Uid del usuario en el primero vacío y ponemos Ready a true.*/
                    for (int i = 1; i < 4; i++) {
                        if (!partida.getEquipo1().elegirJugador(i).isReady()) {
                            partida.getEquipo1().elegirJugador(i).setReady(true);
                            partida.getEquipo1().elegirJugador(i).setUsuario(currentUser.getUid());
                            break;
                        }
                    }
                    myRef.removeEventListener(this);
                    myRef.setValue(partida);
                    //Vamos a la sala de espera.
                    Intent intent;
                    intent = new Intent(Inicio.this, SalaEspera.class);
                    intent.putExtra("codigo", sala);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Mostramos un mensaje si no se encuentra la sala.

                }
            });

        }

    }

    public void clickCodigoSala(View v) {
        txtEdad.setText("");
    }

    /*
    Logueamos al usuario como usuario anónimo de la base de datos.
     */
    public void login() {
        mAuth = FirebaseAuth.getInstance();
        //Habrá que añadir este usuario en algún sitio, Jugador sería lo mejor creo
        currentUser = mAuth.getCurrentUser();
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser user = mAuth.getCurrentUser();
            }
        });
        currentUser = mAuth.getCurrentUser();
    }

}