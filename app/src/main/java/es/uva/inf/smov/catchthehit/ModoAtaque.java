package es.uva.inf.smov.catchthehit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import es.uva.inf.smov.catchthehit.R;

public class ModoAtaque extends AppCompatActivity {

    private ViewGroup layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_ataque);
        layout = (ViewGroup) findViewById(R.id.movimientos);
        String tirada = " Avanza 1 base";
        addChild(tirada);
        tirada = "Avanza 2 bases";
        addChild(tirada);
    }

    @SuppressLint("InlinedApi")
    private void addChild(String tirada) {

        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.movimiento;

        ConstraintLayout relativeLayout = (ConstraintLayout) inflater.inflate(id, null, false);

        TextView textView = (TextView) relativeLayout.findViewById(R.id.textView);
        textView.setText(String.valueOf(tirada));

        layout.addView(relativeLayout);
    }
}