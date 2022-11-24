package es.uva.inf.smov.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import es.uva.inf.smov.catchthehit.R;

public class ModoDefensa extends AppCompatActivity {

    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defensa);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        b = new Bundle();

        //para guardar las imagenes
        if(bundle != null) {
            //ImageButton btn = (ImageButton) findViewById(bundle.getInt("casilla"));
            if(bundle.containsKey("primera_base")) {
                ImageButton primera_base = findViewById(R.id.primera_base);
                primera_base.setBackgroundResource(bundle.getInt("primera_base"));
                b.putInt("primera_base", bundle.getInt("primera_base"));
            }
            if(bundle.containsKey("segunda_base")) {
                ImageButton segunda_base = findViewById(R.id.segunda_base);
                segunda_base.setBackgroundResource(bundle.getInt("segunda_base"));
                b.putInt("segunda_base", bundle.getInt("segunda_base"));
            }
            if(bundle.containsKey("tercera_base")) {
                ImageButton tercera_base = findViewById(R.id.tercera_base);
                tercera_base.setBackgroundResource(bundle.getInt("tercera_base"));
                b.putInt("tercera_base", bundle.getInt("tercera_base"));
            }
            if(bundle.containsKey("short_stop")) {
                ImageButton short_stop = findViewById(R.id.short_stop);
                short_stop.setBackgroundResource(bundle.getInt("short_stop"));
                b.putInt("short_stop", bundle.getInt("short_stop"));
            }
            if(bundle.containsKey("jardinero_derecho")) {
                ImageButton jardinero_derecho = findViewById(R.id.jardinero_derecho);
                jardinero_derecho.setBackgroundResource(bundle.getInt("jardinero_derecho"));
                b.putInt("jardinero_derecho", bundle.getInt("jardinero_derecho"));
            }
            if(bundle.containsKey("jardinero_centro")) {
                ImageButton jardinero_centro = findViewById(R.id.jardinero_centro);
                jardinero_centro.setBackgroundResource(bundle.getInt("jardinero_centro"));
                b.putInt("jardinero_centro", bundle.getInt("jardinero_centro"));
            }
            if(bundle.containsKey("jardinero_izquierdo")) {
                ImageButton jardinero_izquierdo = findViewById(R.id.jardinero_izquierdo);
                jardinero_izquierdo.setBackgroundResource(bundle.getInt("jardinero_izquierdo"));
                b.putInt("jardinero_izquierdo", bundle.getInt("jardinero_izquierdo"));
            }
        }
    }

    public void clickPrimeraBase(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","primera_base");

        intent.putExtras(b);

        startActivity(intent);
    }

    public void clickSegundaBase(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","segunda_base");

        intent.putExtras(b);

        startActivity(intent);
    }

    public void clickTerceraBase(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","tercera_base");

        intent.putExtras(b);

        startActivity(intent);
    }

    public void clickShortStop(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","short_stop");

        intent.putExtras(b);

        startActivity(intent);
    }

    public void clickJardineroDerecho(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","jardinero_derecho");

        intent.putExtras(b);

        startActivity(intent);
    }

    public void clickJardineroCentro(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","jardinero_centro");

        intent.putExtras(b);

        startActivity(intent);
    }

    public void clickJardineroIzquierdo(View v) {
        //Creamos el intent
        Intent intent = new Intent(ModoDefensa.this, EquipoDefensa.class);

        b.putString("casilla","jardinero_izquierdo");

        intent.putExtras(b);

        startActivity(intent);
    }
}
