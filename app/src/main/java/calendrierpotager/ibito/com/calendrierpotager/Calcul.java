package calendrierpotager.ibito.com.calendrierpotager;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibito-PC on 07/05/2017.
 */
public class Calcul{
    private double NL,PL,PQ,DQ;
    private int nomMois,nomAnnee;
    private  boolean resultat;
    private ArrayList<Mois> moisListe;
    int m;

    public Calcul(int mois,int annee,Context context) {
        int [] nJour= context.getResources().getIntArray(R.array.nJour);
        String[] noms = context.getResources().getStringArray(R.array.noms);
            nomMois=mois;
            nomAnnee=annee;
            resultat=bissextille(nomAnnee);
            //creation du liste
            moisListe = new ArrayList<Mois>();

            for (int i=0; i<noms.length; ++i) {
                moisListe.add(new Mois(noms[i],nJour[i],i));
            }
            //changement de fevrier
            if(nomMois==2 && resultat==true){
                moisListe.remove(1);
                moisListe.add(1,new Mois("Fevrier",29,1));
            }
            else{
                moisListe.remove(1);
                moisListe.add(1,new Mois("Fevrier",28,1));
            }
        NL=calcul(mois,resultat,nomAnnee,"NL");
        PL=calcul(mois,resultat,nomAnnee,"PL");
        PQ=calcul(mois,resultat,nomAnnee,"PQ");
        DQ=calcul(mois,resultat,nomAnnee,"DQ");
    }
    private double calcul(int m,boolean bis,int annee,String phase_lune){
        float k,nbrjour_annee,somme=0,T,E,M,M_second,F,n,somme_cor,somme_cor2,Z;
        float[] tab_cor=new float[26];
        double JD,JDE,reste;
        int alpha,A,B,C,D,E_final,reponse;

        for(int i=m;i>=0;--i)
            somme=somme+moisListe.get(i).getNbrJour();
        if(bis==true)
            nbrjour_annee=366;
        else
            nbrjour_annee=365;
        somme=somme/nbrjour_annee;
        somme=(float)arrondi(somme,2);
        somme=somme+annee;
        k=calcul_k(somme,phase_lune);
        T=(float)arrondi(k/1236.85,5);
        JDE=(double)(2451550.09765+(29.530588853*k)+(0.0001337*T*T)-(0.00000015*T*T*T)+(0.00000000073*T*T*T*T));
        E=(float)(1-(0.002516*T)-(0.0000074*T*T));
        M=(float)(2.5534 + (29.10535669*k)-(0.0000218*T*T)- (0.00000011*T*T*T));
        M_second=(float)(201.5643 + (385.81693528 *(k)) + (0.0107438*T*T) + (0.00001239*T*T*T) - (0.000000058*T*T*T*T));
        F=(float)(160.7108 + 390.67050274*(k) - 0.0016341*T*T - 0.00000227*T*T*T + 0.000000011*T*T*T*T);
        n= (float)(124.7746 - 1.5637558*(k) + 0.0020691*T*T + 0.00000215 *T*T*T);

        tab_cor[0]=(float)(299.77 + 0.107408*k - 0.009173*T*T);
        tab_cor[1]= (float)(251.88 + 0.016321*k);
        tab_cor[2]=(float)(251.83 + 26.651886*(float)k);
        tab_cor[3]=(float)(349.42 + 36.412478*k);
        tab_cor[4]=(float)( 84.66 + 18.206239*k);
        tab_cor[5]=(float)(141.74 + 53.303771*k);
        tab_cor[6]=(float)(207.14 + 2.453732*k);
        tab_cor[7]=(float)(154.84 + 7.30686*k);
        tab_cor[8]=(float)(34.52 + 27.261239*k);
        tab_cor[9]=(float)(207.19 + 0.121824 *k);
        tab_cor[10]=(float)(291.34 + 1.844379 *k);
        tab_cor[11]=(float)(161.72 + 24.198154*k);
        tab_cor[12]=(float)(239.56 + 25.513099 *k);
        tab_cor[13]=(float)(331.55 + 3.592518*k);

        tab_cor[0]=(float)(0.000325*Math.sin(Math.toRadians(tab_cor[0])));
        tab_cor[1]=(float)(0.000165*Math.sin(Math.toRadians(tab_cor[1])));
        tab_cor[2]=(float)(0.000164*Math.sin(Math.toRadians(tab_cor[2])));
        tab_cor[3]=(float)(0.000126*Math.sin(Math.toRadians(tab_cor[3])));
        tab_cor[4]=(float)(0.00011*Math.sin(Math.toRadians(tab_cor[4])));
        tab_cor[5]=(float)(0.000062*Math.sin(Math.toRadians(tab_cor[5])));
        tab_cor[6]=(float)(0.00006*Math.sin(Math.toRadians(tab_cor[6])));
        tab_cor[7]=(float)(0.000056*Math.sin(Math.toRadians(tab_cor[7])));
        tab_cor[8]=(float)(0.000047*Math.sin(Math.toRadians(tab_cor[8])));
        tab_cor[9]=(float)(0.000042*Math.sin(Math.toRadians(tab_cor[9])));
        tab_cor[10]=(float)(0.00004*Math.sin(Math.toRadians(tab_cor[10])));
        tab_cor[11]=(float)(0.000037*Math.sin(Math.toRadians(tab_cor[11])));
        tab_cor[12]=(float)(0.000035*Math.sin(Math.toRadians(tab_cor[12])));
        tab_cor[13]=(float)(0.000023*Math.sin(Math.toRadians(tab_cor[13])));

        somme_cor=0;
        for(int i=0;i<=13;++i)
            somme_cor=somme_cor+tab_cor[i];

        tab_cor[0]=(float)(-0.4072*Math.sin(Math.toRadians(M_second)));
        tab_cor[1]=(float)(0.17241*E*Math.sin(Math.toRadians(M)));
        tab_cor[2]=(float)(0.01608*Math.sin(Math.toRadians(2*M_second)));
        tab_cor[3]=(float)(0.01039*Math.sin(Math.toRadians(2*F)));
        tab_cor[4]=(float)(0.00739*E*Math.sin(Math.toRadians(M_second-M)));
        tab_cor[5]=(float)(-0.00514*E*Math.sin(Math.toRadians(M_second+M)));
        tab_cor[6]=(float)(0.00208*E*E*Math.sin(Math.toRadians(2*M)));
        tab_cor[7]=(float)(-0.00111*Math.sin(Math.toRadians(M_second-2*F)));
        tab_cor[8]=(float)(-0.00057*Math.sin(Math.toRadians(M_second+2*F)));
        tab_cor[9]=(float)(0.00056 *Math.sin(Math.toRadians(2*M_second+M)));
        tab_cor[10]=(float)(-0.00042*Math.sin(Math.toRadians(3*M_second)));
        tab_cor[11]=(float)(0.00042 *Math.sin(Math.toRadians(M+2*F)));
        tab_cor[12]=(float)(0.00038*E*Math.sin(Math.toRadians(M-2*F)));
        tab_cor[13]=(float)(-0.00024*E*Math.sin(Math.toRadians(2*M_second-M)));
        tab_cor[14]=(float)(-0.00017*Math.sin(Math.toRadians(n)));
        tab_cor[15]=(float)(-0.00007*Math.sin(Math.toRadians(M_second-2*M)));
        tab_cor[16]=(float)(0.00004*Math.sin(Math.toRadians(2*M_second-2*F)));
        tab_cor[17]=(float)(0.00004*Math.sin(Math.toRadians(3*M)));
        tab_cor[18]=(float)(0.00003*Math.sin(Math.toRadians(M_second+M-2*F)));
        tab_cor[19]=(float)(0.00003*Math.sin(Math.toRadians(2*M_second+2*F)));
        tab_cor[20]=(float)(-0.00003*Math.sin(Math.toRadians(M_second+M+2*F)));
        tab_cor[21]=(float)(0.00003*Math.sin(Math.toRadians(M_second-M+2*F)));
        tab_cor[22]=(float)(-0.00002*Math.sin(Math.toRadians(M_second-M-2*F)));
        tab_cor[23]=(float)(-0.00002*Math.sin(Math.toRadians(3*M_second-M)));
        tab_cor[24]=(float)(0.00002*Math.sin(Math.toRadians(4*M_second)));

        somme_cor2=0;
        for(int i=0;i<=24;++i)
            somme_cor2=somme_cor2+tab_cor[i];

        JD=JDE+somme_cor2+somme_cor;
        JD=JD+0.5;
        Z=(int)JD;
        reste=JD-Z;
        reste=arrondi(reste,5);
        alpha=(int)((Z-1867216.25)/36524.25);
        A=(int)(Z+1+alpha-(alpha/4));
        B=A+1524;
        C=(int)((B-122.1)/365.25);
        D=(int)(365.25*C);
        E_final=(int)((B-D)/30.6001);
        reponse=(B-D-(int)(30.6001*E_final)+(int)reste);
        return reponse;
    }

    public static double arrondi(double A, int B) {
        return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
    }
    public static float calcul_k(float somme,String phase_lune){
        float k=0;
        k=(int)((somme-2000)*12.3685);
        if(phase_lune=="NL")
            k=(float)(k+0);
        else if(phase_lune=="PQ"){
            k=(float)(k+0.25);
        }
        else if(phase_lune=="DQ")
            k=(float)(k+0.75);
        else
            k=(float)(k+0.5);

        return k;
    }
    //bisextille
    private boolean bissextille(int annee){
        boolean resultat;
        if((annee%400)==0||((annee%100!=0)&&(annee%4==0)))
            resultat=true;
        else
            resultat=false;
        return  resultat;
    }
    //getter
    public double getNL() {
        return NL;
    }

    public double getPL() {
        return PL;
    }

    public double getPQ() {
        return PQ;
    }

    public double getDQ() {
        return DQ;
    }
    //setter
    public void setMois(int m) {
        this.nomMois = m;
    }
    public void setAnnee(int k) {
        this.nomAnnee = k;
    }
    //gerer resource

}
