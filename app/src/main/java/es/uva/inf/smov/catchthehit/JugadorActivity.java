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

public class JugadorActivity extends AppCompatActivity {

    ProgressBar fuerza;
    ProgressBar velocidad;
    ProgressBar resistencia;
    TextView txtNombre;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugador);
        fuerza = (ProgressBar) findViewById(R.id.fuerza);
        velocidad = (ProgressBar) findViewById(R.id.velocidad);
        resistencia = (ProgressBar) findViewById(R.id.resistencia);
        txtNombre = (TextView) findViewById(R.id.nombre);
        imagen = (ImageView) findViewById(R.id.iconoJugador);
        setDatos();
    }

    public void clickField(View v) {
        //Creamos el intent
        Intent intent = new Intent(JugadorActivity.this, ModoAtaque.class);

        startActivity(intent);
    }

    public void clickTeam(View v) {
        //Creamos el intent
        Intent intent = new Intent(JugadorActivity.this, EquipoAtaque.class);

        startActivity(intent);
    }

    private void setDatos(){
        int f = getIntent().getExtras().getInt("fuerza");
        fuerza.setProgress(f);
        velocidad.setProgress(getIntent().getExtras().getInt("velocidad"));
        resistencia.setProgress(getIntent().getExtras().getInt("resistencia"));
        String nombre = getIntent().getExtras().getString("nombre");
        Log.e("nombre", String.valueOf(f));
        txtNombre.setText(nombre);
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
}