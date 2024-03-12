package calendrierpotager.ibito.com.calendrierpotager;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ibito-PC on 25/05/2017.
 */
public class Liste_potagerActivity extends Activity {
    //liste drawable
    int x;
    //liste
    ListView list;
    ArrayAdapter adapter;
    ArrayList<Potager> liste_potager;
    private ListView liste = null;
    Resources resources_potager;
    final String EXTRA_POTAGE="potage";
    final String DATE = "date";
    //transfer
    final String TYPE_PLANTE="type_plante";
    //session
    String myString,typepotager,J;
    Intent intent;
    public static final String MyPREFERENCES = "Mon_reference" ;
    public static final String Name = "nameKey";
    public Liste_potagerActivity(){
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //transfert
        intent = getIntent();
        if (intent != null) {
            //information date
            typepotager = intent.getStringExtra(TYPE_PLANTE);
            J=intent.getStringExtra(DATE);
        }
        setContentView(R.layout.activity_affichage);
        //session
        SharedPreferences preferences =  getSharedPreferences(MyPREFERENCES, 0);
        myString = preferences.getString(Name,null);
        openDialog();
        affichelistview();

    }
    //creation de boite de dialogue
    public void openDialog() {
        //AlertDialog.Builder alertDialog = new AlertDialog.Builder(Liste_potagerActivity.this);
        //LayoutInflater inflater = getLayoutInflater();
        //View convertView = (View) inflater.inflate(R.layout.custom, null);
        //alertDialog.setView(convertView);
        //alertDialog.setTitle("Liste de potage");
        //lecture de resource
        resources_potager =getResources();
        final String[] nom_potager = resources_potager.getStringArray(R.array.potageliste);
        final int[] idicon = resources_potager.getIntArray(R.array.idicon);
        final String[] nom_description = resources_potager.getStringArray(R.array.description);
        // recopie dans le ArrayList
        liste_potager = new ArrayList<Potager>();
        //Integer.parseInt
        /*
        for (int   i=0; i<nom_potager.length; ++i) {
            liste_potager.add(new Potager(nom_potager[i], image.get(0),nom_description[0]));
        }
        */
        //transfert de type

        //metter les image petit a pertiti

        if (typepotager.equals("feuille")){
            liste_potager.clear();
            liste_potager.add(new Potager(nom_potager[5], R.drawable.choux_de_chine,nom_description[5]));
            liste_potager.add(new Potager(nom_potager[6], R.drawable.choux_de_brocoli,nom_description[6]));
            liste_potager.add(new Potager(nom_potager[13], R.drawable.haricot_sec,nom_description[13]));
            liste_potager.add(new Potager(nom_potager[14], R.drawable.haricot_vert,nom_description[14]));
            liste_potager.add(new Potager(nom_potager[20], R.drawable.persil,nom_description[20]));
            liste_potager.add(new Potager(nom_potager[19], R.drawable.petit_poid,nom_description[19]));
            liste_potager.add(new Potager(nom_potager[23], R.drawable.poid_de_cap,nom_description[23]));
            x=7;
        }
        else if(typepotager.equals("fleur")){
            liste_potager.clear();
            liste_potager.add(new Potager(nom_potager[7], R.drawable.choux_fleur,nom_description[7]));
            liste_potager.add(new Potager(nom_potager[21], R.drawable.poireau,nom_description[21]));
            liste_potager.add(new Potager(nom_potager[27], R.drawable.soja,nom_description[27]));
            liste_potager.add(new Potager(nom_potager[15], R.drawable.laitue,nom_description[15]));
            liste_potager.add(new Potager(nom_potager[24], R.drawable.poivron,nom_description[24]));
            liste_potager.add(new Potager(nom_potager[22], R.drawable.piment,nom_description[22]));
            x=6;
        }
        else if (typepotager.equals("fruit")){
            liste_potager.clear();
            liste_potager.add(new Potager(nom_potager[2], R.drawable.aubergine,nom_description[2]));
            liste_potager.add(new Potager(nom_potager[3], R.drawable.betterave,nom_description[3]));
            liste_potager.add(new Potager(nom_potager[8], R.drawable.choux_blanc,nom_description[8]));
            liste_potager.add(new Potager(nom_potager[9], R.drawable.cocombre,nom_description[9]));
            liste_potager.add(new Potager(nom_potager[10], R.drawable.courge,nom_description[10]));
            liste_potager.add(new Potager(nom_potager[11], R.drawable.courgette,nom_description[11]));
            liste_potager.add(new Potager(nom_potager[16], R.drawable.melon,nom_description[16]));
            liste_potager.add(new Potager(nom_potager[28], R.drawable.tomate,nom_description[28]));
            x=8;
        }
        else{
            liste_potager.clear();
            liste_potager.add(new Potager(nom_potager[26], R.drawable.radis,nom_description[26]));
            liste_potager.add(new Potager(nom_potager[4], R.drawable.carotte,nom_description[4]));
            liste_potager.add(new Potager(nom_potager[0],R.drawable.arachide,nom_description[0]));
            liste_potager.add(new Potager(nom_potager[1], R.drawable.ail,nom_description[1]));
            liste_potager.add(new Potager(nom_potager[12],R.drawable.gigembre,nom_description[12]));
            liste_potager.add(new Potager(nom_potager[17], R.drawable.navets,nom_description[17]));
            liste_potager.add(new Potager(nom_potager[18], R.drawable.oignon,nom_description[18]));
            liste_potager.add(new Potager(nom_potager[25], R.drawable.betterave,nom_description[25]));
            x=8;
        }
        //pomme de terre
        //Log.i("potage","----------------------------"+idicon);
        //adapteur
        //alertDialog.show();
    }
    private void affichelistview(){
        //ArrayAdapter adapter = new ArrayAdapter<Potager>(this,android.R.layout.simple_list_item_1, liste_potager);
        adapter=new PotagerAdapter();
        //Log.i("adapter", String.valueOf(adapter));

        //LayoutInflater inflater = getLayoutInflater();
        //View convertView = (View) inflater.inflate(R.layout.custom, null);
        //ListView lv = (ListView) convertView.findViewById(R.id.listView1);
        list= (ListView) findViewById(R.id.listView3);
        list.setAdapter(adapter);
        if (myString!=null){
            list.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    // String content = (String)parent.getItemAtPosition(position);

                    Intent intent=new Intent(Liste_potagerActivity.this,Choix_potageActicity.class);
                    intent.putExtra(TYPE_PLANTE,typepotager);
                    Log.i("ici_envoi_date","----------------------------");
                    Log.i("ici_date2",""+J);
                    intent.putExtra(DATE,J);
                    intent.putExtra(EXTRA_POTAGE,liste_potager.get(position).getNompotage());
                    startActivity(intent);
                }
            });
        }
    }
    private class PotagerAdapter extends ArrayAdapter<Potager>{
        public PotagerAdapter(){
            super(Liste_potagerActivity.this,R.layout.potageview, liste_potager);
        }

        @Override
        public int getCount() {
            return x;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           //Potage_View = null;
            //Potager potagercurrent=null;
            View Potage_View = convertView;
            //LayoutInflater inflater =LayoutInflater.from(parent.getContext());
            if (Potage_View == null){
               //Potage_View = (View) inflater.inflate(R.layout.potageview, null);
                Potage_View =getLayoutInflater().inflate(R.layout.potageview, parent, false);

            }
            //chargement de donnee
            //charger les vue
                Potager potagercurrent = liste_potager.get(position);
                TextView IvNom = (TextView) Potage_View.findViewById(R.id.item_titleTextView);
                IvNom.setText(potagercurrent.getNompotage());
                TextView IvDescription = (TextView) Potage_View.findViewById(R.id.item_descriptionTextView);
                IvDescription.setText(potagercurrent.getdescription());
                ImageView IvImage = (ImageView) Potage_View.findViewById(R.id.item_imageView);
                IvImage.setImageResource(potagercurrent.getIconID());



            return Potage_View;
            //return super.getView(position, convertView, parent);
        }
    }
// gérer un clic sur l'item identifié par id...
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}