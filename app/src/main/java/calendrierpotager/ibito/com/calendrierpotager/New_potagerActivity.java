package calendrierpotager.ibito.com.calendrierpotager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ibito-PC on 27/05/2017.
 */
public class New_potagerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button ajouter = null;
    EditText monjardin = null;
    String a;
    //base de donner
    //String tag="Test-recuperation";
    public static final String POTAGER_TABLE_NAME = "potager";
    List<Monpotager> liste;
    Model basededonner;
    Monpotager nouveau;
    Cursor c;
    //session
    public static final String MyPREFERENCES = "Mon_reference" ;
    public static final String Name = "nameKey";
    //Monpotager nouveu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpotager);
        monjardin = (EditText)findViewById(R.id.editText);
        ajouter = (Button) findViewById(R.id.button6);
        ajouter.setOnClickListener(this);
    }
    public void onBackPressed() {
        SharedPreferences sharedpreferences = getSharedPreferences(CalendrierActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
        finish();
    }

    @Override
        public void onClick(View v) {
        //recuperation date
        DateFormat df=new SimpleDateFormat("d-MMM-yyy");
        String date =df.format(Calendar.getInstance().getTime());
        //enregistrement
        a = monjardin.getText().toString();
        basededonner=new Model(getApplicationContext(),POTAGER_TABLE_NAME,null,1);
        nouveau=new Monpotager(a,null,date);
        //basededonner.Sauvegarde(nouveau);
        //Log.i("test_BD","liste"+basededonner.allTableName());
        c=basededonner.getAllpotagerAscursor();
        //Log.i("test_BD","liste"+basededonner.allTableName());
        //basededonner.ajouter(nouveau);
        //SQLiteOpenHelper nouveau=SQLiteOpenHelper(this.getContext(),"database.db",null,1);
        //Cursor c = basededonner.rawQuery("select " + INTITULE + " from " + TABLE_NAME + " where salaire > ?", new String[]{"1"});
        liste=basededonner.getAllpotager();
        //Log.i(tag,"cursor "+c.getColumnCount()+" su "+c.getCount());
        //Log.i(tag,"ligne 2"+liste);
//        Log.i(tag,"ligne 2"+liste.get(2).getNom());
        //Toast.makeText(New_potagerActivity.this,"Sauvegard reussit",Toast.LENGTH_SHORT).show();
        //basededonner.effacertout();
        //gestion de Sessionn
        //session
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, a);
        editor.commit();
        Intent intent1=new Intent(this,CalendrierActivity.class);
        this.startActivity(intent1);
    }
}
