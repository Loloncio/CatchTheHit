package es.uva.inf.smov.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import es.uva.inf.smov.catchthehit.datos.Partida;

public class JugadorDefensaActivity extends AppCompatActivity {

    ProgressBar reflejos;
    ProgressBar velocidad;
    ProgressBar resistencia;
    TextView txtNombre;
    ImageView imagen;
    TextView txtReflejos;
    TextView txtVelocidad;
    TextView txtResistencia;
    String codigo;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    Partida partida;
    private Bundle bundle;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugador_defensa);
        reflejos = (ProgressBar) findViewById(R.id.reflejos);
        velocidad = (ProgressBar) findViewById(R.id.velocidad);
        resistencia = (ProgressBar) findViewById(R.id.resistencia);
        txtNombre = (TextView) findViewById(R.id.nombre);
        imagen = (ImageView) findViewById(R.id.iconoJugador);
        codigo = getIntent().getExtras().getString("codigo");
        txtReflejos = (TextView) findViewById(R.id.txtReflejos);
        txtVelocidad = (TextView) findViewById(R.id.txtVelocidad);
        txtResistencia = (TextView) findViewById(R.id.txtResistencia);

        bundle = this.getIntent().getExtras();
        b = new Bundle();

        //Para que se guarden las imagenes
        if(bundle.containsKey("primera_base")) {
            b.putInt("primera_base", bundle.getInt("primera_base"));
        }
        if(bundle.containsKey("segunda_base")) {
            b.putInt("segunda_base", bundle.getInt("segunda_base"));
        }
        if(bundle.containsKey("tercera_base")) {
            b.putInt("tercera_base", bundle.getInt("tercera_base"));
        }
        if(bundle.containsKey("short_stop")) {
            b.putInt("short_stop", bundle.getInt("short_stop"));
        }
        if(bundle.containsKey("jardinero_derecho")) {
            b.putInt("jardinero_derecho", bundle.getInt("jardinero_derecho"));
        }
        if(bundle.containsKey("jardinero_centro")) {
            b.putInt("jardinero_centro", bundle.getInt("jardinero_centro"));
        }
        if(bundle.containsKey("jardinero_izquierdo")) {
            b.putInt("jardinero_izquierdo", bundle.getInt("jardinero_izquierdo"));
        }

        setDatos();
    }

    public void clickOk(View v) {
        //Creamos el intent
        Intent intent = new Intent(JugadorDefensaActivity.this, ModoDefensa.class);
        intent.putExtra("codigo", codigo);
        b.putInt(bundle.getString("casilla"),getIntent().getExtras().getInt("idJugador"));
        intent.putExtras(b);
        startActivity(intent);
    }

    public void clickTeam(View v) {
        //Creamos el intent
        Intent intent = new Intent(JugadorDefensaActivity.this, EquipoDefensa.class);
        intent.putExtra("codigo", codigo);
        b.putString("casilla",bundle.getString("casilla"));
        intent.putExtras(b);
        startActivity(intent);
    }

    private void setDatos() {
        int reflejosI = getIntent().getExtras().getInt("reflejos");
        int velocidadI = getIntent().getExtras().getInt("velocidad");
        int resistenciaI = getIntent().getExtras().getInt("resistencia");
        reflejos.setProgress(reflejosI);
        velocidad.setProgress(velocidadI);
        resistencia.setProgress(resistenciaI);
        txtReflejos.setText(String.valueOf(reflejosI) + "%");
        txtVelocidad.setText(String.valueOf(velocidadI) + "%");
        txtResistencia.setText(String.valueOf(resistenciaI) + "%");

        String nombre = getIntent().getExtras().getString("nombre");
        txtNombre.setText(nombre);
        int id;
        switch (nombre) {
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
            case "Stan":
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/purple_player", null, null);
                imagen.setImageResource(id);
                break;
            case "Lucas":
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/gray_player", null, null);
                imagen.setImageResource(id);
                break;
            case "John":
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/orange_player", null, null);
                imagen.setImageResource(id);
                break;
            case "Jackson":
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/pink_player", null, null);
                imagen.setImageResource(id);
                break;
            case "Charlie":
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/cyan_player", null, null);
                imagen.setImageResource(id);
                break;
            case "Andrey":
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/yellow_player", null, null);
                imagen.setImageResource(id);
                break;



        }

    }
}