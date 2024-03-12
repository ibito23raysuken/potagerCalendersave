package calendrierpotager.ibito.com.calendrierpotager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by ibito-PC on 05/06/2017.
 */
public class GererActivity extends AppCompatActivity implements View.OnClickListener{
    private Button Nouveau = null;
    private Button mespotage = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Log.i("ici","test numero 1");
        setContentView(R.layout.activity_gerer);
        Nouveau = (Button) findViewById(R.id.button9);
        Nouveau.setOnClickListener(this);
        mespotage = (Button) findViewById(R.id.button10);
        mespotage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            // Si l'identifiant de la vue est celui du premier bouton
            case R.id.button9:
                /*ici essaie
                Intent intent=new Intent(this,PhaseActivity.class);
                this.startActivity(intent);
                */
                Intent intent=new Intent(this,New_potagerActivity.class);
                this.startActivity(intent);
                break;
            // Si l'identifiant de la vue est celui du deuxième bouton
            case R.id.button10:
                Intent intent1=new Intent(this,AffichageActivity.class);
                this.startActivity(intent1);
                break;
        }
    }
}
