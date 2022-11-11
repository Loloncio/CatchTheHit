package com.example.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MinijuegoActivity extends AppCompatActivity {

    private ImageButton btnBolaBuena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minijuego);

        btnBolaBuena = (ImageButton)findViewById(R.id.bola_buena1);
    }

    public void clickBotonBolaBuena(View v) {
        //Creamos el intent
        Intent intent = new Intent(MinijuegoActivity.this, ModoAtaque.class);

        //Iniciamos la nueva actividad
        startActivity(intent);
    }
}