package calendrierpotager.ibito.com.calendrierpotager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.*;

/**
 * Created by ibito-PC on 28/05/2017.
 */
public class Model extends SQLiteOpenHelper {
    public static final String POTAGER_KEY = "id";
    public static final String POTAGER_NOM = "nom";
    public static final String POTAGER_PLANTER = "plante";
    public static final String POTAGER_TABLE_NAME = "potager";
    public static final String POTAGER_TABLE_DATE= "datyj";
    public static final String POTAGER_TABLE_JARDIN = "jardin";

    public static final String POTAGER_TABLE_CREATE = "CREATE TABLE " + POTAGER_TABLE_NAME + "(" + POTAGER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            POTAGER_NOM + " TEXT, " + POTAGER_PLANTER +" TEXT ,"+POTAGER_TABLE_DATE+ " TEXT );";
    public static final String POTAGER_TABLE_DROP = "DROP TABLE IF EXISTS " + POTAGER_TABLE_NAME + ";";
    public static final String POTAGER_SELECT = "SELECT" + "*" + " FROM " + POTAGER_TABLE_NAME + ";";
    public static final String POTAGER_DELETE = "DELETE FROM" + POTAGER_TABLE_NAME+ " WHERE " +POTAGER_NOM+"=";
    String selectQuery = "Select name from sqlite_master where type ='table'";
    public static final String SELECT_DATE = "SELECT FROM" + POTAGER_TABLE_NAME+ " WHERE " +POTAGER_TABLE_DATE+"=";

    public Model(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("base-donner","-------entree--------");
            db.execSQL(POTAGER_TABLE_CREATE);
        Log.i("base-donner","-------sortie--------");
    }


    @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(POTAGER_TABLE_DROP);
        onCreate(db);
    }
    public void Sauvegarde(Monpotager m) {
       // Log.i("base",m.getJourJ());
        ContentValues value = new ContentValues();
        value.put(POTAGER_NOM, m.getNom());
        value.put(POTAGER_PLANTER, m.getPlante());
        value.put(POTAGER_TABLE_DATE, m.getJourJ());
        Long id = m.getId();
        if (id == 0) {
            id = this.getWritableDatabase().insert(POTAGER_TABLE_NAME, null, value);
            m.setId(id);
        }
        else {
            this.getWritableDatabase().update(POTAGER_TABLE_NAME, value, POTAGER_KEY + "= ?", new String[]{String.valueOf(id)});
        }

    }

    public void effacertout() {
        this.getWritableDatabase().delete(POTAGER_TABLE_NAME, null, null);
    }

    public void effacerPotager(Monpotager p) {
        this.getWritableDatabase().delete(POTAGER_TABLE_NAME, POTAGER_KEY + "= ?", new String[]{String.valueOf(p.getId())});
    }

    public Cursor getAllpotagerAscursor() {
        return this.getReadableDatabase().rawQuery(POTAGER_SELECT, null);
    }
    public List<String> allTableName() {
        List<String> result = new ArrayList<String>();
        Cursor c = this.getReadableDatabase().rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                String n = c.getString(c.getColumnIndex("name"));
                result.add(n);
            } while (c.moveToNext());
        }
        return result;
    }
    public List<Monpotager> getAllpotager() {
        List<Monpotager> resulat = new ArrayList<Monpotager>();
        Cursor c = this.getAllpotagerAscursor();
        /*
        if (c.moveToFirst()) {
            do {
                Long id = c.getLong(c.getColumnIndex(POTAGER_KEY));
                p.setId(id);
                p.setNom(c.getString(c.getColumnIndex(POTAGER_NOM)));
                p.setPlante(c.getString(c.getColumnIndex(POTAGER_PLANTER)));
                resulat.add(p);
            } while (c.moveToNext());
        }
       // Log.i("getAllpotager","----------------------------------------"+resulat.toString());
        c.close();
        */
        if (c.getCount() == 0) {
            return null;
        } else {
            // déplaçons le curseur sur le premier enregistrement
            c.moveToFirst();
            // tant que le curseur pourra se déplacer sur les autres éléments, remplir la liste
            while (c.isAfterLast() == false) {
                Monpotager p = new Monpotager();
                Long id = c.getLong(c.getColumnIndex(POTAGER_KEY));
                p.setId(id);
                p.setNom(c.getString(c.getColumnIndex(POTAGER_NOM)));
                p.setPlante(c.getString(c.getColumnIndex(POTAGER_PLANTER)));
                p.setJourJ(c.getString(c.getColumnIndex(POTAGER_TABLE_DATE)));
                //Log.i("test_BD","liste "+c.getString(c.getColumnIndex(POTAGER_TABLE_DATE)));
                resulat.add(p);
                c.moveToNext();
            }
            c.close();
            return resulat;
        }
    }
}

