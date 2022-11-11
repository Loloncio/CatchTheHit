package com.example.catchthehit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.TextView;

public class EquipoDefensa extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private TextView player_green;
    private TextView player_red;
    private TextView player_blue;
    private TextView player_purple;
    private TextView player_orange;
    private TextView player_pink;
    private TextView player_gray;
    private TextView player_cyan;
    private TextView player_yellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_defensa);

        player_green = (TextView) findViewById(R.id.name_green);
        player_red = (TextView) findViewById(R.id.name_red);
        player_blue = (TextView) findViewById(R.id.name_blue);
        player_purple = (TextView) findViewById(R.id.name_purple);
        player_orange = (TextView) findViewById(R.id.name_orange);
        player_pink = (TextView) findViewById(R.id.name_pink);
        player_gray = (TextView) findViewById(R.id.name_gray);
        player_cyan = (TextView) findViewById(R.id.name_cyan);
        player_yellow = (TextView) findViewById(R.id.name_yelow);
        player_green.setText("Babe");
        player_red.setText("Ted");
        player_blue.setText("Willie");
        player_purple.setText("Stan");
        player_orange.setText("John");
        player_pink.setText("Jackson");
        player_gray.setText("Lucas");
        player_cyan.setText("Charlie");
        player_yellow.setText("Andrey");

    }
}