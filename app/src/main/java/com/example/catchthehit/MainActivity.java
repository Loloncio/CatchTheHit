package com.example.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnTAttack;
    private Button btnTDefense;
    private Button btnMAttack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get references to interface controls
        btnTAttack = (Button)findViewById(R.id.EquipoAtaque);
        btnTDefense = (Button)findViewById(R.id.EquipoDefensa);
        btnMAttack = (Button) findViewById(R.id.ModoAtaque);
    }

    //Implementamos el evento click del botón
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

    public void clickModoAtaque(View v) {
        //Creamos el intent
        Intent intent = new Intent(MainActivity.this, ModoAtaque.class);

        startActivity(intent);
    }
}