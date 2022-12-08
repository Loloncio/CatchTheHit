package es.uva.inf.smov.catchthehit;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.uva.inf.smov.catchthehit.datos.Partida;

public class JugadorDefensaActivity extends AppCompatActivity {

    ProgressBar fuerza;
    ProgressBar velocidad;
    ProgressBar resistencia;
    TextView txtNombre;
    ImageView imagen;
    TextView txtFuerza;
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
        setContentView(R.layout.activity_jugador);
        fuerza = (ProgressBar) findViewById(R.id.fuerza);
        velocidad = (ProgressBar) findViewById(R.id.velocidad);
        resistencia = (ProgressBar) findViewById(R.id.resistencia);
        txtNombre = (TextView) findViewById(R.id.nombre);
        imagen = (ImageView) findViewById(R.id.iconoJugador);
        codigo = getIntent().getExtras().getString("codigo");
        txtFuerza = (TextView) findViewById(R.id.txtFuerza);
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

    public void clickField(View v) {
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
        int fuerzaI = getIntent().getExtras().getInt("fuerza");
        int velocidadI = getIntent().getExtras().getInt("velocidad");
        int resistenciaI = getIntent().getExtras().getInt("resistencia");
        fuerza.setProgress(fuerzaI);
        velocidad.setProgress(velocidadI);
        resistencia.setProgress(resistenciaI);
        txtFuerza.setText(String.valueOf(fuerzaI) + "%");
        txtVelocidad.setText(String.valueOf(velocidadI) + "%");
        txtResistencia.setText(String.valueOf(resistenciaI) + "%");

        String nombre = getIntent().getExtras().getString("nombre");
        txtNombre.setText(nombre);
        int id;
        switch (nombre) {
            case "Ted":
                id = getResources().getIdentifier("es.uva.inf.smov.catchthehit:drawable/red_player", null, null);
                imagen.setImageResource(id);
                break;
            case "Babe":
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