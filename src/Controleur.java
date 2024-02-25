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
    private static final int[ ] OPTION_MENU_PRINCIPALE = {1,2};
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
        boolean quitter = false;
        while (!quitter)
        {
            int option = -1;
            // Penser a rajouter l'option dans la liste OPTION_MENU_PRINCIPALE
            while (!entierDansListe(OPTION_MENU_PRINCIPALE, option)) {
                vue.afficherMenuPrincipale();
                option = lectureEntier();
            }
            switch (option) {
                case 1:
                    //démarer jeux
                    demarrerJeu();
                    break;
                case 2:
                    quitter = true;
            }
        }
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
                vue.afficherGrille(bataille.grilleJeu);
                vue.tourJoueur();
                // Resultat entre 0 touche, 1 coule, 2 a l'eau
                resultat =
                        bataille.mouvement(
                                bataille.grilleOrdi,
                                demandeLigne(1),
                                demandeColonne(1));
            }
            else
            {
                // ligne du dessous pour voir la grille de l'ordi (debug)
                //vue.afficherGrille(bataille.grilleOrdi);
                vue.tourOrdi();
                int[] tirOrdi = bataille.tirOrdinateur();
                // Resultat entre 0 touche, 1 coule, 2 a l'eau
                resultat =
                        bataille.mouvement(
                                bataille.grilleJeu,
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
        int[] res = {demandeLigne(0, bateau),demandeColonne(0, bateau),demandeOrientation(bateau)};

        return res;
    }


    /**
     * <h2>demandeColonne</h2>
     * <p>
     *     Demande la colonne au joueur soit a l'étape 0 pour placer un bateau soit a l'étape 1 pour tirer.
     * </p>
     * @param etape 0 ou 1, étape a l'aquelle se trouve le jeux (preparation/combat) : int
     * @param bateau dans le cas du palcement d'un bateau : bateau
     * @return indice de la colonne choisie : int
     */
    public int demandeColonne(int etape, Bateau bateau)
    {
        if (etape < 1)
            vue.demanderColonne(bateau);
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

    /**
     * <h2>demandeColonne</h2>
     * <p>
     *     Surcharge de demandeColonne sans bateau, inutile dans la cas de l'étape 1
     * </p>
     * @param etape 0 ou 1, étape a l'aquelle se trouve le jeux (preparation/combat) : int
     * @return indice de la colonne choisie : int
     */
    public int demandeColonne(int etape)
    {
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

    /**
     * <h2>demandeLigne</h2>
     * <p>
     *     Demande la ligne au joueur soit a l'étape 0 pour placer un bateau soit a l'étape 1 pour tirer.
     * </p>
     * @param etape 0 ou 1, étape a l'aquelle se trouve le jeux (preparation/combat) : int
     * @param bateau dans le cas du palcement d'un bateau : bateau
     * @return indice de la ligne choisie : int
     */
    public int demandeLigne(int etape, Bateau bateau)
    {
        if (etape < 1)
            vue.demanderLigne(bateau);
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

    /**
     * <h2>demandeLigne</h2>
     * <p>
     *     Surcharge de demandeLigne sans bateau, inutile dans la cas de l'étape 1
     * </p>
     * @param etape 0 ou 1, étape a l'aquelle se trouve le jeux (preparation/combat) : int
     * @return indice de la ligne choisie : int
     */
    public int demandeLigne(int etape)
    {
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

    /**
     * <h2>demandeOrientation</h2>
     * <p>
     *     Demande l'orientation du bateau que l'on souhaite placer au joueur
     * </p>
     * @param bateau : Bateau que l'on souhaite placer : bateau
     * @return 1 ou 2, 1 pour horizontale 2 pour vertical
     */
    public int demandeOrientation(Bateau bateau)
    {
        vue.demandeOrientation(bateau);
        while (true)
        {
            int orientation = lectureEntier();
            if (orientation>0)
            {
                return orientation+1;
            }

        }
    }

    /**
     * <h2>verifierVictoire</h2>
     * <p>
     *     Permet de savoir si l'ordinateur ou le joueur ont gagné.
     * </p>
     * @return Si l'un des deux a gagné : boolean
     */
    public boolean verifierVictoire()
    {
        if (bataille.vainqueur(bataille.grilleJeu))
        {
            vue.victoireOrdi();
            return true;
        }
        if (bataille.vainqueur(bataille.grilleOrdi))
        {
            vue.victoireJoueur();
            return true;
        }

        return false;
    }
}
