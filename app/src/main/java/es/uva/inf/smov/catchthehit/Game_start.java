package es.uva.inf.smov.catchthehit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Random;


public class Game_start extends AppCompatActivity {

    Button showerButton; // Falling balls

    //int whiteBallScore; // Aciertos
    //int redBallScore;   // Fallos
    //int totalScore;     // Puntuación final = Aciertos - Fallos

    TextView temporizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        showerButton = findViewById(R.id.showerButton);
        temporizador = findViewById(R.id.tiempo);
        showerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTemp();
                for (int i=1; i<10; i++){
                    whiteBall();
                    redBall();
                }
                showerButton.setEnabled(false); // Una vez se usa, a false
            }
        });
    }

    public void initTemp(){
        long seg = 15000;
        CountDownTimer cuenta = new CountDownTimer(seg, 1000) {
            @Override
            public void onTick(long l) {
                long tiempo = l / 1000;
                String mostrar_tiempo = String.format("%02d", tiempo);
                temporizador.setText("00:" + mostrar_tiempo);
            }
            @Override
            public void onFinish() {
                //Toast.makeText(this, "FIN", Toast.LENGTH_SHORT).show();
                //temporizador.setText("FIN DEL JUEGO");
                stopGame();
            }
        }.start();
    }

    public void whiteBall() {
        // Variables a establecer sobre el View Group
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.bola;
        ConstraintLayout newBola = (ConstraintLayout) inflater.inflate(id, null, false);
        ConstraintLayout container = (ConstraintLayout) findViewById(R.id.zonaBolas);
        int containerW = container.getWidth();
        int containerH = container.getHeight();

        // Coger medidas de la bola
        float ballW = newBola.getWidth();
        float ballH = newBola.getHeight();

        // Creacion de nuevas bolas
        container.addView(newBola);

        // Se veia enorme asi que resize para que se vea mas peque
        ballW *= newBola.getScaleX();
        ballH *= newBola.getScaleY();
        float value = new Random().nextFloat() * 2 - 1;
        newBola.setTranslationX(containerW * value);

        // Movimiento de la bola al caer
        ObjectAnimator mover = ObjectAnimator.ofFloat(newBola, View.TRANSLATION_Y,
                new float[]{-ballH, (float)containerH + ballH});
        mover.setInterpolator((TimeInterpolator)(new AccelerateInterpolator(1.0F)));
        ObjectAnimator rotator = ObjectAnimator.ofFloat(newBola, View.ROTATION,
                new float[]{(float)(Math.random() * (double)1080)});
        rotator.setInterpolator((TimeInterpolator)(new LinearInterpolator()));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(new Animator[]{(Animator)mover, (Animator)rotator});

        //set.setDuration(13000);
        // Duracion para el programa, usar el anterior para que me de tiempo a pulsar
        set.setDuration((long)(Math.random() * (double)4000 + (double)1500));
        set.addListener((Animator.AnimatorListener)(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                container.removeView((View)newBola);
            }
        }));
        set.start();
    }

    public void redBall() {
        // Variables a establecer sobre el View Group
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.bolaroja;
        ConstraintLayout newBola = (ConstraintLayout) inflater.inflate(id, null, false);
        ConstraintLayout container = (ConstraintLayout) findViewById(R.id.zonaBolas);
        int containerW = container.getWidth();
        int containerH = container.getHeight();

        // Coger medidas de la bola
        float ballW = newBola.getWidth();
        float ballH = newBola.getHeight();

        // Creacion de nuevas bolas
        container.addView(newBola);

        // Se veia enorme asi que resize para que se vea mas peque
        ballW *= newBola.getScaleX();
        ballH *= newBola.getScaleY();
        float value = new Random().nextFloat() * 2 - 1;
        newBola.setTranslationX(containerW * value);

        // Movimiento de la bola al caer
        ObjectAnimator mover = ObjectAnimator.ofFloat(newBola,
                View.TRANSLATION_Y,
                -ballH, (float)containerH + ballH);
        mover.setInterpolator((TimeInterpolator)(new AccelerateInterpolator(1.0F)));
        ObjectAnimator rotator = ObjectAnimator.ofFloat(newBola,
                View.ROTATION,
                (float)(Math.random() * (double)1080));
        rotator.setInterpolator((TimeInterpolator)(new LinearInterpolator()));
        AnimatorSet set = new AnimatorSet();
        set.playTogether((Animator)mover, (Animator)rotator);

        //set.setDuration(13000);
        // Duracion para el programa, usar el anterior para que me de tiempo a pulsar
        set.setDuration((long)(Math.random() * (double)2500 + (double)1000));
        set.addListener((Animator.AnimatorListener)(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                container.removeView((View)newBola);
            }
        }));
        set.start();
    }

    // Cada que se acierte, puntuacion acierto +10 --> No implementado, solo funciona el mensaje
    public void setAcierto(){
        Toast.makeText(this, "BOLA BLANCA +10", Toast.LENGTH_SHORT).show();
        //whiteBallScore+=10;
    }

    // Cada que se falle, puntuacion fallo +10 --> No implementado, solo funciona el mensaje
    public void setFallo(){
        Toast.makeText(this, "BOLA ROJA -10", Toast.LENGTH_SHORT).show();
        //redBallScore+=10;
    }

    public void clickBolaBlanca(View v){
        //Log.d("Tocada", "Bola tocada");
        setAcierto();
        whiteBall();
        redBall();
    }

    public void clickBolaRoja(View v){
        //Log.d("Tocada", "Bola tocada");
        setFallo();
        whiteBall();
        redBall();
    }

    public void stopGame(){
        Intent intent = new Intent(Game_start.this,Game_finish.class);
        startActivity(intent);
    }
}