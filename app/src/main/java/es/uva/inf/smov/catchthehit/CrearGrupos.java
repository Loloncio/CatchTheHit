package es.uva.inf.smov.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import es.uva.inf.smov.catchthehit.R;
import es.uva.inf.smov.catchthehit.datos.Partida;

public class CrearGrupos extends AppCompatActivity {

    private Partida partida;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupos);
    }


    public void clickBoton1(View v) {
        crearPartida(1, v);
    }

    public void clickBoton2(View v) {
        crearPartida(2, v);
    }

    public void clickBoton3(View v) {
        crearPartida(3, v);
    }

    public void clickBoton4(View v) {
        crearPartida(4, v);
    }

    public void clickBoton5(View v) {
        crearPartida(5, v);
    }

    public void crearPartida(int rondas, View v){
        partida = new Partida(rondas);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference(partida.getCodigo());
        myRef.setValue(partida);
        Intent intent = new Intent(CrearGrupos.this, ModoAtaque.class);
        intent.putExtra("codigo",partida.getCodigo());
        startActivity(intent);
    }
}