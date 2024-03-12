package calendrierpotager.ibito.com.calendrierpotager;
import android.content.res.Resources;
/**
 * Created by ibito-PC on 27/04/2017.
 */
public class Mois {
    ;
    private String  nomMois;
    private int nbrdeJour;
    private int position;
        public Mois(String mNom,int nbrJour,int i) {
       // Resources res = Resources.getSystem();
//        String[] nom = res.getStringArray(R.array.noms);
  //      int[] nJour = res.getIntArray(R.array.nJour);
        nomMois = mNom;
        nbrdeJour =nbrJour;
        position=i;
    }

    public int getNbrJour() {

        return nbrdeJour;
    }
    public int getPosition() {

        return position;
    }
    public String getmNom() {

        return nomMois;
    }
}
