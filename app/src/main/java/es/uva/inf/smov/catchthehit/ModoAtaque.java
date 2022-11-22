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
        addTirada(1);
        addTirada(2);
        addTirada(3);
        addTirada(0);
    }

    @SuppressLint("InlinedApi")
    private void addTirada(int avance) {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.movimiento;
        ConstraintLayout relativeLayout = (ConstraintLayout) inflater.inflate(id, null, false);

        TextView movimiento = (TextView) relativeLayout.findViewById(R.id.textView);
        movimiento.setText("Avanza " + String.valueOf(avance));
        TextView fuerza = (TextView) relativeLayout.findViewById(R.id.fuerzaMov);
        TextView velocidad = (TextView) relativeLayout.findViewById(R.id.velocidadMov);;
        TextView energia = (TextView) relativeLayout.findViewById(R.id.energiaMov);;

        switch (avance){
            case 1:
                fuerza.setText("- "+"2");
                velocidad.setText("- "+"2");
                energia.setText("- "+"5");
                break;

            case 2:
                fuerza.setText("- "+"4");
                velocidad.setText("- "+"4");
                energia.setText("- "+"10");
                break;

            case 3:
                fuerza.setText("- "+"6");
                velocidad.setText("- "+"6");
                energia.setText("- "+"15");
                break;

            default:
                movimiento.setText("Descansa");
                fuerza.setText("- "+"0");
                velocidad.setText("- "+"0");
                energia.setText("+ "+"5");

        }

        layout.addView(relativeLayout);
    }
}