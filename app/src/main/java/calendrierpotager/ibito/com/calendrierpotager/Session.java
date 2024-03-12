package calendrierpotager.ibito.com.calendrierpotager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by ibito-PC on 31/05/2017.
 */
public class Session {
    public static final String MyPREFERENCES = "Mon_reference" ;
    public static final String Name = "nameKey";
    Context context;
    SharedPreferences sharedPref;
    public SharedPreferences sharedpreferences;

    public void login(String log){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, log);
        editor.commit();
    }

    public void Logout(){

    }

}
