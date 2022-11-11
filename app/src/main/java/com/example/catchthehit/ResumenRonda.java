package com.example.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResumenRonda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_ronda);


        int puntosequipo1, puntosequipo2;
        puntosequipo1 = 15; //get points from database instead
        puntosequipo2 = 9; //get points from database instead

        String puntos1, puntos2;
        puntos1 = "Tu equipo : " + puntosequipo1 + " puntos";
        puntos2 = "Equipo rival : " + puntosequipo2 + " puntos";


        TextView puntos1TextView = (TextView)findViewById(R.id.resumen_ronda_tu_equipo_puntos);
        puntos1TextView.setText(puntos1);

        TextView puntos2TextView = (TextView)findViewById(R.id.resumen_ronda_equipo_rival_puntos);
        puntos2TextView.setText(puntos2);

        }

    }
