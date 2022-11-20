package es.uva.inf.smov.catchthehit;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.provider.Settings.Secure;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.*;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import es.uva.inf.smov.catchthehit.R;


public class Inicio extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtEdad;
    private Button btnCrearGrupo;
    private Button btnComoJugar;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        //Obtenemos una referencia a los controles de la interfaz
        btnCrearGrupo = (Button) findViewById(R.id.BtnCrearGrupo);
        btnComoJugar = (Button) findViewById(R.id.BtnComoJugar);
        txtEdad = (EditText) findViewById(R.id.txtEdad);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    public void clickBotonComoJugar(View v) {
        //Creamos el intent
        Intent intent = new Intent(Inicio.this, ComoJugar.class);
        startActivity(intent);
    }

    public void clickBotonEmpezarJuego(View v) {
        //logueamos al usuario como anónimo
        login();
        //Conexión a la base de datos, habrá que crear un código si no se ha introducido ninguno.
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        database = FirebaseDatabase.getInstance();
        /*
        *****Ejemplo de introducción de datos en firebase
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
        *****Ejemplo de actualización de datos, cada vez que hay un cambio se ejecuta onDataChange
        *****onCancelled es lo que ocurre si hay un fallo.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtEdad.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                txtEdad.setText("Error");
            }
        });
        */

        //Creamos el intent, si no hay sala se va a selección de rondas, si la hay habrá que conectar.
        Intent intent;
        String sala = String.valueOf(txtEdad.getText());
        if(sala.equals("")){
            intent  = new Intent(Inicio.this, CrearGrupos.class);
            startActivity(intent);
        } else{
            //Conectar a la sala
        }

    }
    public void clickCodigoSala(View v){
        txtEdad.setText("");
    }
    /*
    Logueamos al usuario como usuario anónimo de la base de datos.
     */
    public void login() {
        mAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //Habrá que añadir este usuario en algún sitio, Jugador sería lo mejor creo
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = mAuth.getCurrentUser();
                    }
                });
       currentUser = mAuth.getCurrentUser();
    }


}