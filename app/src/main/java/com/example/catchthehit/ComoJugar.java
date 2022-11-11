package com.example.catchthehit;


import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.IOException;

public class ComoJugar extends AppCompatActivity implements View.OnClickListener{
    private Button btnPlay;
    private Button btnPause;
    private Button btnStop;
    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_como_jugar);

        //Inicializamos la clase VideoView asociandole el fichero de Video
        video=(VideoView) findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.prueba;
        video.setVideoURI(Uri.parse(path));

        //Obtenemos los tres botones de la interfaz
        btnPlay= (Button)findViewById(R.id.botonPlay);
        btnStop= (Button)findViewById(R.id.botonStop);
        btnPause= (Button)findViewById(R.id.botonPause);

        //Se les asigna el controlador de eventos
        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //Comprobamos el identificador del boton que ha llamado al evento para ver
        //cual de los botones es y ejecutar la acción correspondiente
        switch(v.getId()){
            case R.id.botonPlay:
                //Iniciamos el video
                video.start();
                break;
            case R.id.botonPause:
                //Pausamos el video
                video.pause();
                break;
            case R.id.botonStop:
                //Paramos el video y volvemos a inicializar
                video.stopPlayback();
                video.seekTo(0);
                break;

        }
    }
}