package es.uva.inf.smov.catchthehit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class Test extends AppCompatActivity {

    private ViewGroup layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        layout = (ViewGroup) findViewById(R.id.preguntas);
        String pregunta = "1. Pregunta prueba";
        IntroducePregunta(pregunta);
    }

    @SuppressLint("InlinedApi")
    private void IntroducePregunta(String tirada) {

        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.pregunta;

        ConstraintLayout relativeLayout = (ConstraintLayout) inflater.inflate(id, null, false);

        TextView textView = (TextView) relativeLayout.findViewById(R.id.textView);
        textView.setText(String.valueOf(tirada));

        layout.addView(relativeLayout);
    }
}