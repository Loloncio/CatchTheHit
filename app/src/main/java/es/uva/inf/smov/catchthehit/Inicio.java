package es.uva.inf.smov.catchthehit;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.provider.Settings.Secure;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.*;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import es.uva.inf.smov.catchthehit.R;
import es.uva.inf.smov.catchthehit.datos.Partida;


public class Inicio extends AppCompatActivity {

    private EditText txtEdad;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private Bundle b;
    private Partida partida;
    private FirebaseUser currentUser;
    private ValueEventListener listener;
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
        b = new Bundle();
        //logueamos al usuario como anónimo
        login();
        //Conexión a la base de datos, habrá que crear un código si no se ha introducido ninguno.
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
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
            listener = myRef.addValueEventListener(new ValueEventListener() {
                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    partida = dataSnapshot.getValue(Partida.class);
                    if(partida == null){
                        DatabaseError DatabaseError = null;
                        onCancelled(DatabaseError);
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
                    Intent intent = new Intent(Inicio.this, SalaEspera.class);
                    intent.putExtra("codigo", sala);
                    startActivity(intent);

                }
                @Override
                public void onCancelled(DatabaseError error) {
                    // Mostramos un mensaje si no se encuentra la sala.
                    CharSequence fail = "Codigo de sala no valido";
                    Toast toast = Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_LONG);
                    toast.show();
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