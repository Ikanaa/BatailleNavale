import java.util.Map;
import java.util.function.Consumer;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 *      <h1>Controleur</h1>
 *      @author <h2>Louis Saffré</h2>
 *      @Description
 *          Classe représentant un controleur tel que dans le MVC <a href='https://www.wikiwand.com/fr/Mod%C3%A8le-vue-contr%C3%B4leur'>(Model Vue Controleur)</a>
 */

public class Controleur {

    Bataille bataille = new Bataille();
    Vue vue = new Vue();
    private static final int[ ] OPTION_MENU_PRINCIPALE = {1};
    public static BufferedReader lisseurTampon = new BufferedReader (new InputStreamReader(System.in));

    public Controleur() {
        //Rien ?
    }

    public void menuPrincipale()
    {
        int option = -1;
        while (!entierDansListe(OPTION_MENU_PRINCIPALE, option))
        {
            vue.afficherMenuPrincipale();
            option = lectureEntier();
        }
        switch (option)
        {
            case 1:
                //démarer jeux
                demarrerJeu();
                break;
        }

    }

    public void demarrerJeu()
    {
        bataille.controleur = this;
        bataille.initGrilleOrdi();
        bataille.initGrilleJoueur();


        //do things
    }

    private boolean entierDansListe(int[] liste, int entier)
    {
        for (int elem : liste)
        {
            if (elem == entier)
                return true;
        }
        return false;
    }

    public String lectureChaine()
    {
        String resultat = "";
        try
        {
            resultat= lisseurTampon.readLine();
        }
        catch(Exception e)
        {
            System.out.print("y a un bogue avec la lecture");
        }
        return resultat;
    }

    public static boolean estEntier( String chaine)
    {
        try {
            Integer.parseInt( chaine );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    public int lectureEntier ()
    {
        while (true)
        {
            String chaine = lectureChaine();
            if (estEntier(chaine))
            {
                return Integer.parseInt(chaine);

            }
        }
    }

    public int[] demandePositionBateau()
    {
        vue.afficherGrille(bataille.grilleJeu);
        int[] res = {demandeLigne(),demandeColonne(),demandeOrientation()};

        return res;
    }

    public int demandeColonne()
    {
        vue.demanderColonne();
        while (true)
        {
            String chaine = lectureChaine();
            char lettre = chaine.charAt(0);
            if (lettre>64 && lettre<75)
            {
                return lettre - 65;
            }
            if (lettre>96 && lettre<123)
            {
                return lettre - 97;
            }
        }
    }

    public int demandeLigne()
    {
        vue.demanderLigne();
        while (true)
        {
            int colonne = lectureEntier();
            if (colonne>0 && colonne<11)
            {
                return colonne - 1;
            }

        }
    }

    public int demandeOrientation()
    {
        vue.demandeOrientation();
        while (true)
        {
            int orientation = lectureEntier();
            if (orientation>0)
            {
                return orientation+1;
            }

        }
    }
}
