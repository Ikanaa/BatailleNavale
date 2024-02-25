/**
 *      <h1>Vue</h1>
 *      @author <h2>Louis Saffré</h2>
 *      @Description
 *          Classe représentant une vue tel que dans le MVC <a href='https://www.wikiwand.com/fr/Mod%C3%A8le-vue-contr%C3%B4leur'>(Model Vue Controleur)</a>
 */

public class Vue {


    /*
        Tous les textes sont dans des variables pour plus de facilité a les changer.
     */

    private static final String ANSI_ZERO = "\u001B[0m";
    private static final String ANSI_NOIR = "\u001B[30m";
    private static final String ANSI_ROUGE = "\u001B[31m";
    private static final String ANSI_VERT = "\u001B[32m";
    private static final String ANSI_JAUNE = "\u001B[33m";
    private static final String ANSI_BLEU = "\u001B[34m";
    private static final String ANSI_VIOLET = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BLANC = "\u001B[37m";

    private static final String MENU_PRINCIPALE
            = "\n"
            + "Bienvenue dans le Menu de la Bataille Navale ! \n"
            + "   Option du menu : \n"
            + "     1 - Démarrer le jeux\n"
            + "     2 - Quitter le programme"
            + "\n\n\n";
    private static final String DEMANDE_LIGNE_P1
            = "\nVeuillez entrez la ligne sur laquelle placer le ";
    private static final String DEMANDE_LIGNE_P2
            = " (1,2,3...10)\n";
    private static final String DEMANDE_COLONNE_P1
            = "\nVeuillez entrez la colonne sur laquelle placer le ";
    private static final String DEMANDE_COLONNE_P2
            = " (A,B,C...J)\n";
    private static final String DEMANDE_ORIENTATION_P1
            = "\nVeuillez entrez l'orientation du ";
    private static final String DEMANDE_ORIENTATION_P2
            = " (1 horizontal, 2 vertical)\n";

    private static final String TOUR_JOUEUR
            = "\nTour du joueur : \n";
    private static final String TOUR_ORDI
            = "\nTour de l'ordinateur : \n";
    private static final String DEMANDE_LIGNE_TIRE
            = "\nVeuillez entrez la ligne sur laquelle tirer (1,2,3...10)\n";
    private static final String DEMANDE_COLONNE_TIRE
            = "\nVeuillez entrez la colonne sur laquelle tirer (A,B,C...J)\n";
    private static final String A_L_EAU
            = "\nÀ l'eau !\n";
    private static final String TOUCHE
            = "\nTouché !\n";
    private static final String COULE
            = "\nCoulé !\n";

    private static final String VICTOIRE_JOUEUR
            = "\nBravo ! Victoire du Joueur !\n";
    private static final String VICTOIRE_ORDI
            = "\nDommage ! Victoire de l'ordinateur !\n";

    public Vue() {
        //rien ?
    }

    /**
     * <h2>afficherGrille</h2>
     * <p>
     *     Permet d'afficher une grille en sortie console, l'eau est bleu, les bateaux vert et les bateaux coulés rouge
     * </p>
     * @param Grille Grille que l'on souhaite afficher : int[][]
     */
    public void afficherGrille(int[][] Grille)
    {
        // Entête constante
        System.out.print("      A  B  C  D  E  F  G  H  I  J\n      -  -  -  -  -  -  -  -  -  -");

        int ite = 1;
        for (int[] ligne : Grille)
        {
            System.out.print("\n" + formaterString("" + ite, 2) + " |");
            ite++;
            for (int collone : ligne) {

                switch (collone)
                {
                    case 0:
                        System.out.print(ANSI_BLEU);
                        break;
                    case 6:
                        System.out.print(ANSI_ROUGE);
                        break;
                    default:
                        System.out.print(ANSI_VERT);
                }

                System.out.print("  " + collone);
                System.out.print(ANSI_ZERO);
            }
        }
    }

    /**
     * <H2>afficherMenuPrincipale</H2>
     * <p>
     *     Permet d'afficher le menu principale du jeu.
     * </p>
     */
    public void afficherMenuPrincipale()
    {
        System.out.print(MENU_PRINCIPALE);
    }


    /**
     * <h2>formaterString</h2>
     * <p>
     *     Permet de formater un string pour qu'il fasse une certaine taille. Pour ce faire on rajoute des espaces a la fin du string.
     * </p>
     * @param str La chaine a modifier : String
     * @param taille La taille que doit atteindre la chaine : int
     * @return La chaine modifié : String
     */
    public String formaterString(String str, int taille)
    {
        while (str.length() < taille)
        {
            str += " ";
        }

        return str;
    }


    public void demanderBateau(Bateau bateau)
    {
        System.out.print(
                "Veuillez placer le : " + bateau.obtenirType() + " (" + bateau.obtenirTaille() + ") " + " .\n"
        );
    }

    public void demanderLigne(Bateau bateau) {
        System.out.print(
                DEMANDE_LIGNE_P1 + bateau.obtenirType() + " (" + bateau.obtenirTaille() + ")" + DEMANDE_LIGNE_P2);
    }
    public void demanderColonne(Bateau bateau) {
        System.out.print(
                DEMANDE_COLONNE_P1 + bateau.obtenirType() + " (" + bateau.obtenirTaille() + ")" + DEMANDE_COLONNE_P2);
    }
    public void demandeOrientation(Bateau bateau) {
        System.out.print(
                DEMANDE_ORIENTATION_P1 + bateau.obtenirType() + " (" + bateau.obtenirTaille() + ")" + DEMANDE_ORIENTATION_P2);
    }
    public void tourJoueur() {System.out.print(TOUR_JOUEUR);}
    public void tourOrdi() {System.out.print(TOUR_ORDI);}
    public void demandeTireLigne(){System.out.print(DEMANDE_LIGNE_TIRE);}
    public void demandeTireColonne(){System.out.print(DEMANDE_COLONNE_TIRE);}
    public void aLEau() {System.out.print(A_L_EAU);}
    public void touche() {System.out.print(TOUCHE);}
    public void coule() {System.out.print(COULE);}
    public void victoireJoueur() {System.out.print(VICTOIRE_JOUEUR);}
    public void victoireOrdi() {System.out.print(VICTOIRE_ORDI);}
}
