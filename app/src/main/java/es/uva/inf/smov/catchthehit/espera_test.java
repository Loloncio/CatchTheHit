package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.uva.inf.smov.catchthehit.datos.Partida;

public class espera_test extends AppCompatActivity {

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
        setContentView(R.layout.activity_espera_test);

        //Conectamos a la base de datos y obtenemos la referencia de la partida.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        codigo = getIntent().getExtras().getString("codigo");
        myRef = database.getReference(codigo);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //Referencias a los campos a rellenar, excepto la imagen.
        TextView listosTxt = (TextView) findViewById(R.id.espera);
        Button boton = (Button) findViewById(R.id.verResultados);

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
                for (int i = 0; i < 4; i++) {
                    if (partida.getEquipo1().elegirJugador(i).isReady()) {
                        listos = i + 1;
                    }
                    //Cuando todos esten listos vamos aresultados.
                    if (listos == 4) {
                        myRef.removeEventListener(this);
                        boton.setVisibility(View.VISIBLE);
                        boton.setClickable(true);
                    }
                    else{
                        listosTxt.setText("Espera que tus compañeros finalicen el test ("+listos+"/4)");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
            });
    }
    public void clickBoton(View v){
        Intent intent = new Intent(espera_test.this, Resultados.class);
        intent.putExtra("codigo", partida.getCodigo());
        startActivity(intent);
    }
}