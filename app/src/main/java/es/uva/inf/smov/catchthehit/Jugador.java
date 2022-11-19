package es.uva.inf.smov.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import es.uva.inf.smov.catchthehit.R;

public class Jugador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugador);
    }

    public void clickField(View v) {
        //Creamos el intent
        Intent intent = new Intent(Jugador.this, ModoAtaque.class);

        startActivity(intent);
    }

    public void clickTeam(View v) {
        //Creamos el intent
        Intent intent = new Intent(Jugador.this, EquipoAtaque.class);

        startActivity(intent);
    }
}