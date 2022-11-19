package es.uva.inf.smov.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.uva.inf.smov.catchthehit.R;

public class MainActivity extends AppCompatActivity {

    private Button btnInicio;
    private Button btnHowto;
    private Button btnPartida;
    private Button btnAtaque;
    private Button btnEquipoDefensa;
    private Button btnEquipoAtaue;
    private Button btnResumen;
    private Button btnMinijuego;
    private Button btnDefensa;
    private Button btnJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get references to interface controls
        btnPartida = (Button)findViewById(R.id.partida);
        btnHowto = (Button)findViewById(R.id.howto);
        btnInicio = (Button) findViewById(R.id.inicio);
        btnAtaque = (Button)findViewById(R.id.ataque);
        btnEquipoAtaue = (Button)findViewById(R.id.equipo_at);
        btnEquipoDefensa = (Button) findViewById(R.id.equipo_def);
        btnMinijuego = (Button)findViewById(R.id.minijuego);
        btnResumen = (Button)findViewById(R.id.resumen);
        btnDefensa = (Button)findViewById(R.id.defensa);
        btnJugador = (Button)findViewById(R.id.jugador);

    }

    //Implementamos el evento click del botón
    public void clickInicio(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, Inicio.class);

        startActivity(intent);
    }

    public void clickHowto(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, ComoJugar.class);

        startActivity(intent);
    }

    public void clickRondas(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, CrearGrupos.class);

        startActivity(intent);
    }

    public void clickAtaque(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, ModoAtaque.class);

        startActivity(intent);
    }
    public void clickEquipoAtaque(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, EquipoAtaque.class);

        startActivity(intent);
    }
    public void clickEquipoDefensa(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, EquipoDefensa.class);

        startActivity(intent);
    }
    public void clickResumen(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, ResumenRonda.class);

        startActivity(intent);
    }
    public void clickMinijuego(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, MinijuegoActivity.class);

        startActivity(intent);
    }
    public void clickDefensa(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, ModoDefensa.class);

        startActivity(intent);
    }
    public void clickJugador(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, Jugador.class);

        startActivity(intent);
    }
}