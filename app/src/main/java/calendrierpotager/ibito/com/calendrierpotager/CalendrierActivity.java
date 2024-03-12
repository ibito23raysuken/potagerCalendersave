package calendrierpotager.ibito.com.calendrierpotager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ibito-PC on 19/04/2017.
 */

public class CalendrierActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {
    final String EXTRA_JOUR="jour";
    final String EXTRA_MOIS="mois";
    final String EXTRA_ANNEE="annee";
    final String TYPE_PLANTE="type_plante";
    //sesion
    String myString,typepotage;
    Calcul cal_calend;
    int jour, mois, annee, NL, PL, DQ, PQ;
    public static final String MyPREFERENCES = "Mon_reference" ;
    public static final String Name = "nameKey";

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);
        //session
        SharedPreferences preferences =  getSharedPreferences(MyPREFERENCES, 0);
        myString = preferences.getString(Name,null);
        //Log.i("information","--------------------------------------------------------------------------------"+myString);
        //afichage textView5
        TextView t1=(TextView) findViewById(R.id.textView7);
        TextView t2 = (TextView) findViewById(R.id.textView5);
        if (myString==null){
            t1.setText("");
            t2.setText("");
        }
        else{
            t2.setText(myString);
        }
        CalendarView calendrier =(CalendarView) findViewById(R.id.calendarView);
        calendrier.setOnDateChangeListener(this);
    }

    @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            if (myString==null){

                cal_calend = new Calcul(month, year, getApplicationContext());
                NL = (int) cal_calend.getNL();
                PL = (int) cal_calend.getPL();
                PQ = (int) cal_calend.getDQ();
                DQ = (int) cal_calend.getPQ();
                if(dayOfMonth<PQ)
                    typepotage="feuille";
                else if(dayOfMonth>PQ && dayOfMonth<NL)
                    typepotage="fleur";
                else if(dayOfMonth>PQ && dayOfMonth<NL)
                    typepotage="fruit";
                 else
                    typepotage="racine";

           // Liste_potagerActivity l=new Liste_potagerActivity();
            Intent intent=new Intent(CalendrierActivity.this,Liste_potagerActivity.class);
            intent.putExtra(TYPE_PLANTE,typepotage);
            this.startActivity(intent);
        }
        else{
            Intent intent=new Intent(CalendrierActivity.this,Choix_potageActicity.class);
            intent.putExtra(EXTRA_JOUR,""+dayOfMonth);
            intent.putExtra(EXTRA_MOIS,""+month);
            intent.putExtra(EXTRA_ANNEE,""+year);
            Toast.makeText(CalendrierActivity.this,"jour"+dayOfMonth,Toast.LENGTH_SHORT).show();
            this.startActivity(intent);
        }

    }
    public void onBackPressed() {
        SharedPreferences sharedpreferences = getSharedPreferences(CalendrierActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
        finish();
    }
}
