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
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_test);

        //Conectamos a la base de datos y obtenemos la referencia de la partida.
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        String codigo = getIntent().getExtras().getString("codigo");
        myRef = database.getReference(codigo);

        //Referencias a los campos a rellenar.
        TextView listosTxt = (TextView) findViewById(R.id.espera);

        //listener para obtener los datos de la partida cuando haya cambios.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                partida = dataSnapshot.getValue(Partida.class);
                /*
                 * Comprobamos cuantos jugadores hay listos.
                 */
                for (int i = 0; i < 4; i++) {
                    if (partida.getEquipo1().elegirJugador(i).isReady()) {
                        listos = i + 1;
                    }
                    //Cuando todos esten listos vamos aresultados.
                    if (listos == 4) {
                        myRef.removeEventListener(this);
                        Intent intent = new Intent(espera_test.this, Resultados.class);
                        intent.putExtra("codigo", partida.getCodigo());
                        startActivity(intent);
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

}