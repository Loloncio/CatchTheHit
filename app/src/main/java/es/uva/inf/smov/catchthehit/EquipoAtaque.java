package es.uva.inf.smov.catchthehit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import es.uva.inf.smov.catchthehit.R;
import com.google.android.material.navigation.NavigationView;

public class EquipoAtaque extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener  {

    private DrawerLayout drawerLayout;
    private TextView player_green;
    private TextView player_red;
    private TextView player_blue;
    private TextView player_purple;
    private ImageView field;
    private ImageView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_ataque);

        player_green = (TextView) findViewById(R.id.name_green);
        player_red = (TextView) findViewById(R.id.name_red);
        player_blue = (TextView) findViewById(R.id.name_blue);
        player_purple = (TextView) findViewById(R.id.name_purple);
        player_green.setText("Babe");
        player_red.setText("Ted");
        player_blue.setText("Willie");
        player_purple.setText("Stan");
        field = (ImageView) findViewById(R.id.field);
        player = (ImageView) findViewById(R.id.player);
    }

    public void clickField(View v) {
        //Creamos el intent
        Intent intent = new Intent(EquipoAtaque.this, ModoAtaque.class);

        startActivity(intent);
    }

    public void clickPlayer(View v) {
        //Creamos el intent
        Intent intent = new Intent(EquipoAtaque.this, Jugador.class);

        startActivity(intent);
    }


    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
