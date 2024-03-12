package calendrierpotager.ibito.com.calendrierpotager;

import java.util.Date;

/**
 * Created by ibito-PC on 28/05/2017.
 */
public class Monpotager {
        // Notez que l'identifiant est un long
        private long id;
        private String nom;
        private String jourJ;
        private String plante;
        public  Monpotager(){}
        public Monpotager( String nom, String plante,String jourJ) {
            super();
            this.nom = nom;
            this.plante = plante;
            this.jourJ=jourJ;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getJourJ() {
            return jourJ;
        }

        public void setJourJ(String jourJ) {
            this.jourJ = jourJ;
        }
        public String getPlante() {
        return plante;
        }

         public void setPlante(String plante) {
        this.plante = plante;
    }

    @Override
    public String toString() {
        return "Monpotager{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", plante='" + plante + '\'' +
                ",jour='"+jourJ+'}';
    }
}

