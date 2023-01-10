package es.uva.inf.smov.catchthehit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Game_finish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finish);
    }

    public void clickReturnInit(View v){
        Intent intent = new Intent(Game_finish.this,Inicio.class);
        startActivity(intent);
    }
}