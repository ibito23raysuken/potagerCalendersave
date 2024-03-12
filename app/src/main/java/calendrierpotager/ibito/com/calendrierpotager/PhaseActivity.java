package calendrierpotager.ibito.com.calendrierpotager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibito-PC on 19/04/2017.
 */
public class PhaseActivity extends AppCompatActivity implements View.OnTouchListener,View.OnClickListener{
    Calcul cal_calend;
    private Button calcul = null;
    EditText annee = null;
    TextView NLune=null;
    TextView PLune=null;
    TextView PQLune=null;
    TextView DQLune=null;
    TextView text1=null;
    TextView text2=null;
    TextView text3=null;
    TextView text4=null;
    int m;
    double NL,PL,PQ,DQ;
    Resources res;
    protected ArrayList<Mois> moisListe;
    private  boolean resultat=true;
    private Spinner liste = null;
    //sesion
    String myString;
    public static final String MyPREFERENCES = "Mon_reference" ;
    public static final String Name = "nameKey";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase);
        //ssession
        //session
        SharedPreferences preferences =  getSharedPreferences(MyPREFERENCES, 0);
        myString = preferences.getString(Name,null);
        Log.i("information","--------------------------------------------------------------------------------"+myString);

        liste = (Spinner) findViewById(R.id.spinner1);
        List<String> liste_mois = new ArrayList<String>();
        //gerer le liste
        //creation d'un objet Mois
        res = getResources();
        String[] noms = res.getStringArray(R.array.noms);
        int[] nJour = res.getIntArray(R.array.nJour);
        moisListe = new ArrayList<Mois>();
        for (int i=0; i<noms.length; ++i) {
            moisListe.add(new Mois(noms[i],nJour[i],i));
        }
        for (Mois Lmois:moisListe) {
            liste_mois.add(Lmois.getmNom());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, liste_mois);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liste.setAdapter(adapter);
        text1=(TextView) findViewById(R.id.textView9);
        text2=(TextView) findViewById(R.id.textView11);
        text3=(TextView) findViewById(R.id.textView13);
        text4=(TextView) findViewById(R.id.textView15);
        NLune= (TextView) findViewById(R.id.textView10);
        PLune= (TextView) findViewById(R.id.textView12);
        PQLune= (TextView) findViewById(R.id.textView14);
        DQLune= (TextView) findViewById(R.id.textView16);
        if(NL==0 && PL==0 && PQ==0 && DQ == 0){
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
            NLune.setText("");
            PLune.setText("");
            PQLune.setText("");
            DQLune.setText("");
        }
        //gerer edit text
        annee = (EditText)findViewById(R.id.editText2);
       // mois = (EditText)findViewById(R.id.editText);
        //gerer bouton
        calcul = (Button) findViewById(R.id.button4);
        calcul.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }



    public double getNL() {
        return NL;
    }

    public double getPL() {
        return PL;
    }

    public double getPQ() {
        return PQ;
    }

    public double getDQ() {
        return DQ;
    }
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,PhaseActivity.class);
        String a = annee.getText().toString();
        int annee=Integer.parseInt(a);
        int k= Integer.valueOf(a).intValue();
        m=liste.getSelectedItemPosition();
        Log.i("ici",k+"---------------------"+m);
        //elemente de calcul moisListe.get(m).getNbrJour()
        cal_calend=new Calcul(m,annee+1,getApplicationContext());
        NL=(int)cal_calend.getNL();
        PL=(int)cal_calend.getPL();
        PQ=(int)cal_calend.getDQ();
        DQ=(int)cal_calend.getPQ();
        text1.setText("Nouvelle Lune");
        text2.setText("Pleine lune");
        text3.setText("Premier Quartier");
        text4.setText("Dernier Quartier");
        NLune.setText(NL+"");
        PLune.setText(PL+"");
        PQLune.setText(PQ+"");
        DQLune.setText(DQ+"");
    }
}
