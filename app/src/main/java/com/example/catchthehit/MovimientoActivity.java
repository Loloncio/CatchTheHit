package com.example.pruebaa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MovimientoActivity extends AppCompatActivity {

    private Button btnAvanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        btnAvanzar = (Button)findViewById(R.id.avanzar2bases);
    }

    public void clickBotonAvanzar2bases(View v) {
        //Creamos el intent
        Intent intent = new Intent(MovimientoActivity.this, DefensaActivity.class);

        //Iniciamos la nueva actividad
        startActivity(intent);
    }
}