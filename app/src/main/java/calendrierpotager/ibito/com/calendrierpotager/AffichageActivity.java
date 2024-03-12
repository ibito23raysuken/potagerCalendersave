package calendrierpotager.ibito.com.calendrierpotager;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibito-PC on 28/05/2017.
 */
public class AffichageActivity extends AppCompatActivity {
    public static final String POTAGER_TABLE_NAME = "potager";
    List<Monpotager> liste;
    ArrayList<String> liste_jardin;
    Model basededonner;
    //declaration de bouton
    private Button oui = null;
    private Button annuler = null;
    //Monpotager nouveau;
    Cursor c;
    //String tag="Test-recuperation";
    private ListView liste_affichage = null;
    ArrayAdapter<String> adapter;
    //session
    public static final String MyPREFERENCES = "Mon_reference" ;
    public static final String Name = "nameKey";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);
        basededonner = new Model(getApplicationContext(), POTAGER_TABLE_NAME, null, 1);
        c = basededonner.getAllpotagerAscursor();
        liste = basededonner.getAllpotager();
        //Log.i(tag,"cursor dans affichage "+c.getColumnCount()+" sur "+c.getCount());
        //Log.i(tag,"essaie"+liste);
        if(liste==null){
            Toast.makeText(getApplicationContext(),"base de donnee vide", Toast.LENGTH_SHORT).show();
        }
        else{
            //creation de liste
            liste_jardin = new ArrayList<>(liste.size());
            for (int i = 0; i < liste.size(); ++i) {
                liste_jardin.add(liste.get(i).getNom());
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, liste_jardin);
            liste_affichage = (ListView) findViewById(R.id.listView3);
            liste_affichage.setAdapter(adapter);
            // liste_affichage.setOnClickListener(this);

            liste_affichage.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //session
                    SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Name, liste_jardin.get(position).toString());
                    editor.commit();

                    //diriger ver calendrier
                    Intent intent=new Intent(AffichageActivity.this,CalendrierActivity.class);
                    startActivity(intent);
                }

            });

            liste_affichage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
            {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   // Log.i("long clic","-----------------");
                    openDialog(position);
                    return true;
                }

            });

        }

    }
    public void openDialog(final int p) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Supprimer ce jardin?");
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        basededonner.effacerPotager(liste.get(p));
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("annuler",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
