package calendrierpotager.ibito.com.calendrierpotager;

/**
 * Created by ibito-PC on 25/05/2017.
 */
public class Potager {
    private String Nompotage;
    //private String Urlimagepotage;
    private String description;
    private int iconID;

    Potager(String nom, int iconID, String description) {
        super();
        this.Nompotage = nom; // nom du potager
        this.iconID=iconID;
        //this.Urlimagepotage=Urlimagepotage;
        this.description=description;
    }

    public String toString() {
        return "liste des potages{Nom" + Nompotage +
                ", id icon='" + iconID + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getNompotage() {
        return Nompotage;
    }
    /*
    public String getUrlimagepotage() {
        return Urlimagepotage;
    }
   */
    public String getdescription() {
        return description;
    }

    public int getIconID() {
        return iconID;
    }
}
