package com.example.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Inicio extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtEdad;
    private Button btnCrearGrupo;
    private Button btnComoJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        //Obtenemos una referencia a los controles de la interfaz
        btnCrearGrupo = (Button)findViewById(R.id.BtnCrearGrupo);
        btnComoJugar = (Button)findViewById(R.id.BtnComoJugar);
        txtEdad = (EditText)findViewById(R.id.TxtEdad);
    }


    public void  clickBotonComoJugar(View v) {
        //Creamos el intent
        Intent intent = new Intent(Inicio.this, ComoJugar.class);
        startActivity(intent);
    }

    public void  clickBotonEmpezarJuego(View v) {
        //Creamos el intent
        Intent intent = new Intent(Inicio.this, CrearGrupos.class);
        startActivity(intent);
    }
// SOLO YO PROBANDO COSAS, DO NOT WORRY XD
}