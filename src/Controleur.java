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

    /**
     * <h2>menuPrincipale</h2>
     * <p>
     *     Fonction pour gérer le menu principale
     * </p>
     */
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

        menuPrincipale();
    }

    /**
     * <h2>demarrerJeu</h2>
     * <p>
     *     Fonction pour démarrer le jeu
     * </p>
     */
    public void demarrerJeu()
    {
        bataille.controleur = this;
        bataille.initGrilleOrdi();
        bataille.initGrilleJoueur();

        boolean tourJoueur = true;
        boolean finDePartie = false;

        while (!finDePartie)
        {
            int resultat = 2;
            if (tourJoueur)
            {
                vue.tourJoueur();
                resultat =
                        bataille.mouvement(
                                bataille.grilleJeu,
                                demandeLigne(1),
                                demandeColonne(1));
            }
            else
            {
                vue.tourOrdi();
                int[] tirOrdi = bataille.tirOrdinateur();
                resultat =
                        bataille.mouvement(
                                bataille.grilleOrdi,
                                tirOrdi[0],
                                tirOrdi[1]
                                );
            }

            switch (resultat)
            {
                case 0 :
                    vue.touche();
                    break;
                case 1:
                    vue.coule();
                    finDePartie = verifierVictoire();
                    break;
                case 2:
                    vue.aLEau();
            }
            tourJoueur = !tourJoueur;
        }
    }

    /**
     * <h2>entierDansListe</h2>
     * <p>
     *     Permet de savoir si un entier est présent dans un liste.
     * </p>
     *
     * @param liste La liste que l'on va regarder : int[]
     * @param entier L'entier qui devrait apparaitre dans la liste : int
     * @return Si l'entier apparait dans la liste : boolean
     */
    private boolean entierDansListe(int[] liste, int entier)
    {
        for (int elem : liste)
        {
            if (elem == entier)
                return true;
        }
        return false;
    }

    /**
     * <h2>lectureChaine</h2>
     * <p>
     *     Permet de récupérer une chaine de charactère dans l'entrée console.
     * </p>
     * @return La chaine récupérée : String
     */
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

    /**
     * <h2>estEntier</h2>
     * <p>
     *     Fontion qui vérifie si une chaine peut être transformé en entier.
     * </p>
     * @param chaine La chaine qui doit être vérifié : String
     * @return Si oui ou non elle est un entier : boolean
     */
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

    /**
     * <h2>lectureEntier</h2>
     * <p>
     *     Permet de lire un entier dans l'entrée console.
     * </p>
     * @return L'entier qui a été lu : int
     */
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

    /**
     * <h2>demandePositionBateau</h2>
     * <p>
     *     Permet de demander a l'utilisateur la positon d'un Bateau.
     * </p>
     * @param bateau Le bateau dont on demande la position : Bateau
     *
     * @return Une liste composé de la ligne, la colonne et l'orientation.
     */
    public int[] demandePositionBateau(Bateau bateau)
    {
        vue.demanderBateau(bateau);
        vue.afficherGrille(bataille.grilleJeu);
        int[] res = {demandeLigne(0),demandeColonne(0),demandeOrientation()};

        return res;
    }


    public int demandeColonne(int etape)
    {
        if (etape < 1)
            vue.demanderColonne();
        else
            vue.demandeTireColonne();
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

    public int demandeLigne(int etape)
    {
        if (etape < 1)
            vue.demanderLigne();
        else
            vue.demandeTireLigne();
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

    public boolean verifierVictoire()
    {
        if (bataille.vainqueur(bataille.grilleJeu))
        {
            vue.victoireJoueur();
            return true;
        }
        if (bataille.vainqueur(bataille.grilleOrdi))
        {
            vue.victoireOrdi();
            return true;
        }

        return false;
    }
}
