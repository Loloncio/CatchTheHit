package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.uva.inf.smov.catchthehit.datos.Partida;

public class Resultados extends AppCompatActivity {

    private String codigo;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private Partida partida;
    private ViewGroup layout;
    private ArrayList<Integer> respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        layout = (ViewGroup) findViewById(R.id.resultadoTest);
        codigo = getIntent().getExtras().getString("codigo");
        database = FirebaseDatabase.getInstance("https://catch-the-hit-default-rtdb.europe-west1.firebasedatabase.app/");
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(codigo);
        respuesta = new ArrayList<Integer>(18);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                partida = dataSnapshot.getValue(Partida.class);

                TextView ganador = (TextView) findViewById(R.id.ganador);
                TextView puntuacion = (TextView) findViewById(R.id.puntuacion);
                if(partida.getEquipo1().getPuntos()>partida.getEquipo2().getPuntos()){
                    ganador.setText("Sois los ganadores!!!");
                } else if (partida.getEquipo1().getPuntos()<partida.getEquipo2().getPuntos()){
                    ganador.setText("Habeis perdido!!");
                } else{
                    ganador.setText("Empate!!");
                }
                puntuacion.setText("Habeis conseguido: "+partida.getEquipo1().getPuntos()+" puntos\n"+
                        "El equipo contrario ha conseguido: "+partida.getEquipo2().getPuntos()+"puntos");

                addResultado(0);
                addResultado(1);
                addResultado(2);
                addResultado(3);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.");
            }
        });
    }

    public void clickVolverInicio(View v){
        Bundle bundle = this.getIntent().getExtras();

        Intent intent = new Intent(Resultados.this,Inicio.class);

        startActivity(intent);
    }
    /*
     * Creamos los resultados para cada jugador. Lo primero es coger todos los componentes
     * que habr? que modificar. Despues repasamos para cada jugador las respuestas dadas para
     * el test, si la respuesta es el jugador del cual estamos construyendo los resultados, entonces
     * incrementamos la variable corriespondiente al rasgo al cual afecta la pregunta. Luego,
     * dividimos ese recuento entre el total de posibles respuestas para ese rasgo y multiplicamos
     * por 100 para obtener el porcentaje. Finalmente, asignamos los valores correspondientes a
     * cada componente del layout y lo inflamos en su lugar.
     */
    @SuppressLint("InlinedApi")
    private void addResultado(int jugador) {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.test_result;
        LinearLayout res = (LinearLayout) inflater.inflate(id, null, false);
        TextView jugadorTxt = (TextView) res.findViewById(R.id.resultadoJugador);
        TextView liderCTxt = (TextView) res.findViewById(R.id.carismatxt);
        TextView liderTTxt = (TextView) res.findViewById(R.id.tranformacionaltxt);
        TextView comunicacionTxt = (TextView) res.findViewById(R.id.comunicativotxt);
        TextView equipoTxt = (TextView) res.findViewById(R.id.equipotxt);
        ProgressBar liderC = (ProgressBar) res.findViewById(R.id.carisma);
        ProgressBar liderT = (ProgressBar) res.findViewById(R.id.transformacional);
        ProgressBar comunicacion = (ProgressBar) res.findViewById(R.id.comunicativo);
        ProgressBar equipo = (ProgressBar) res.findViewById(R.id.equipo);

        int recuentoLiderC = 0;
        int recuentoLiderT = 0;
        int recuentoComunicacion = 0;
        int recuentoEquipo = 0;

        for(int i = 0; i<4;i++){
            respuesta = partida.getEquipo1().elegirJugador(i).getRespuestas();

            for(int j = 0;j<18;j++){
                if((j==0)||(j==1)||(j==4)||(j==6)||(j==7)||(j==10)) {
                    if (respuesta.get(j) == jugador)
                        recuentoComunicacion++;
                } else if((j==2)||(j==5)||(j==8)||(j==9)||(j==13)) {
                    if (respuesta.get(j) == jugador)
                        recuentoEquipo++;
                } else if ((j==3)||(j==11)||(j==17)){
                    if (respuesta.get(j) == jugador)
                        recuentoLiderT++;
                }
                else{
                    if (respuesta.get(j) == jugador)
                        recuentoLiderC++;
                }
            }
        }
        recuentoComunicacion = (recuentoComunicacion/24)*100;
        recuentoEquipo = (recuentoEquipo/24)*100;
        recuentoLiderT = (recuentoLiderT/12)*100;
        recuentoLiderC = (recuentoLiderC/12)*100;
        liderC.setProgress(recuentoLiderC);
        liderCTxt.setText(recuentoLiderC+"%");
        liderT.setProgress(recuentoLiderT);
        liderTTxt.setText(recuentoLiderT+"%");
        comunicacion.setProgress(recuentoComunicacion);
        comunicacionTxt.setText(recuentoComunicacion+"%");
        equipo.setProgress(recuentoEquipo);
        equipoTxt.setText(recuentoEquipo+"%");

        jugadorTxt.setText(partida.getEquipo1().elegirJugador(jugador).getNombre());

        layout.addView(res);
    }
}