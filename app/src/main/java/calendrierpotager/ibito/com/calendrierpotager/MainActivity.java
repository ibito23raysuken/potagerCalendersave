package calendrierpotager.ibito.com.calendrierpotager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,View.OnClickListener {
    private Button calendrier = null;
    private Button phase = null;
    private Button afficher = null;
    private Button quitter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendrier = (Button) findViewById(R.id.button);
        calendrier.setOnTouchListener(this);
        calendrier.setOnClickListener(this);
        phase = (Button) findViewById(R.id.button2);
        phase.setOnTouchListener(this);
        phase.setOnClickListener(this);
        quitter = (Button) findViewById(R.id.button3);
        quitter.setOnTouchListener(this);
        quitter.setOnClickListener(this);
        afficher = (Button) findViewById(R.id.button7);
        afficher.setOnTouchListener(this);
        afficher.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            // Si l'identifiant de la vue est celui du premier bouton
            case R.id.button:
                /*ici essaie
                Intent intent=new Intent(this,PhaseActivity.class);
                this.startActivity(intent);
                */
                Intent intent=new Intent(this,CalendrierActivity.class);
                this.startActivity(intent);
                break;
            // Si l'identifiant de la vue est celui du deuxième bouton
            case R.id.button2:
                Intent intent1=new Intent(this,PhaseActivity.class);
                this.startActivity(intent1);
                break;
            case R.id.button7:
                Intent intent3=new Intent(this,GererActivity.class);
                this.startActivity(intent3);
                break;
            case R.id.button3:
                System.exit(0);
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
