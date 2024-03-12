package calendrierpotager.ibito.com.calendrierpotager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by ibito-PC on 07/05/2017.
 */
public class Choix_potageActicity extends AppCompatActivity implements View.OnTouchListener,View.OnClickListener {
    final String EXTRA_JOUR = "jour";
    final String EXTRA_MOIS = "mois";
    final String EXTRA_ANNEE = "annee";
    final String TYPE_PLANTE="type_plante";
    int jour, mois, annee, NL, PL, DQ, PQ;
    String jourStr, moisStr, anneeStr;
    String potage;
    Calcul cal_calend;
    // EditText edit=null;
    private Button ajout = null;
    private Button sauver = null;
    //base de donner
    String tag = "Test-recuperation";
    public static final String POTAGER_TABLE_NAME = "potager";
    List<Monpotager> liste;
    ArrayList<String> Listeenregistrer;
    String J,JR;
    Model basededonner;
    Monpotager nouveau;
    Cursor c;
    //variable liste
    public String typepotage;
    public static ArrayList liste_monpotager = new ArrayList<Potager>();
    private ListView maliste = null;
    ArrayAdapter<String> monadapter;
    final String EXTRA_POTAGE = "potage";
    final String DATE = "date";
    //session
    String myString;
    public static final String MyPREFERENCES = "Mon_reference";
    public static final String Name = "nameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choixpotager);
        Intent intent = getIntent();

        //edit= (EditText) findViewById(R.id.editText);
        ajout = (Button) findViewById(R.id.button5);
        sauver = (Button) findViewById(R.id.button8);
        ajout.setOnClickListener(this);
        sauver.setOnClickListener(this);
        //recuperation date
        DateFormat df = new SimpleDateFormat("d-MMM-yyy");
        String date = df.format(Calendar.getInstance().getTime());
        //session
        SharedPreferences preferences = getSharedPreferences(MyPREFERENCES, 0);
        myString = preferences.getString(Name, null);
        // Log.i("date","------------"+date);
        //afichage textView5
        TextView t1 = (TextView) findViewById(R.id.textView8);
        t1.setText(myString);
        //lecture de base
        basededonner = new Model(getApplicationContext(), POTAGER_TABLE_NAME, null, 1);
        c = basededonner.getAllpotagerAscursor();
        liste = basededonner.getAllpotager();

//        Log.i("test",liste.toString());
        // associer la liste affichée et l'adaptateur

        if (intent != null) {
            //information date
            jourStr = intent.getStringExtra(EXTRA_JOUR);
            moisStr = intent.getStringExtra(EXTRA_MOIS);
            anneeStr = intent.getStringExtra(EXTRA_ANNEE);
            potage=intent.getStringExtra(EXTRA_POTAGE);
            JR=intent.getStringExtra(DATE);
            J=jourStr+'-'+moisStr+'-'+anneeStr;
            if(jourStr==null){
                J=JR;
                Log.i("date",J);
            }
            //introduction de date

            if (jourStr != null && moisStr != null && anneeStr != null) {
                jour = Integer.parseInt(jourStr);
                mois = Integer.parseInt(moisStr);
                annee = Integer.parseInt(anneeStr);

                cal_calend = new Calcul(mois, annee, getApplicationContext());
                NL = (int) cal_calend.getNL();
                PL = (int) cal_calend.getPL();
                PQ = (int) cal_calend.getDQ();
                DQ = (int) cal_calend.getPQ();
                if(jour<PQ){
                    typepotage="feuille";
                }
                else if(jour>PQ && jour<NL)
                    typepotage="fleur";
                else if(jour>PQ && jour<NL)
                    typepotage="fruit";
                else
                    typepotage="racine";
                //Log.i("type_potage",typepotage+"");
            }
            else{
                //information date
                typepotage = intent.getStringExtra(TYPE_PLANTE);
                Log.i("ici_type",typepotage);
            }
            if(liste!=null) {
                Listeenregistrer = new ArrayList<>(liste.size());
                for (int i = 0; i < liste.size(); ++i) {
                    if (liste.get(i).getPlante() != null) {
                        if (liste.get(i).getJourJ() != null) {
                            if (liste.get(i).getJourJ().equals(J)) {
                                Listeenregistrer.add(liste.get(i).getPlante());
                                Log.i("afiichetest", "" + liste.get(i).getPlante());
                            }
                        }

                    }
                    else{
                        //Listeenregistrer.add("null");
                    }
                }
                if(potage!=null) {
                    Listeenregistrer.add(potage);
                }
//                Log.i("size",Listeenregistrer[1]);
                //liste_monpotager.add(potage);
                monadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Listeenregistrer);
                maliste = (ListView) findViewById(R.id.listView);
                maliste.setAdapter(monadapter);

            }
        }
    }


        //potager
    public  boolean onTouch(View v, MotionEvent event){
        // edit.setText("NL="+NL+"-PL="+PL+"-PQ="+PQ+"-DQ="+DQ);
        return true;
    }
    public void onBackPressed() {
        finish();
    }

    @Override
        public void onClick(View v) {
        //sauvegarde des donners
        switch(v.getId()) {
            // Si l'identifiant de la vue est celui du premier bouton
            case R.id.button5:
                /*ici essaie
                Intent intent=new Intent(this,PhaseActivity.class);
                this.startActivity(intent);
                */
                Intent intent=new Intent(this,Liste_potagerActivity.class);
                //Intent intent=new Intent(CalendrierActivity.this,Choix_potageActicity.class);
                intent.putExtra(TYPE_PLANTE,typepotage);
                intent.putExtra(DATE,J);
                this.startActivity(intent);
                break;
            // Si l'identifiant de la vue est celui du deuxième bouton
            case R.id.button8:

                nouveau=new Monpotager(myString,potage,J);
                basededonner.Sauvegarde(nouveau);
                Toast.makeText(this,"sauvegarde reussit", LENGTH_SHORT).show();
                //lecture de base
                break;
        }
    }

}

