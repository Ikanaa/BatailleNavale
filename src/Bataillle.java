import java.util.Map;
import java.util.Random;


/**
 *
 *      @author Louis Saffré
 *      @Sujet Jeu de Bataille Navale (Touché Coulé / BattleShip) !
 *      @version 0.1
 *      @Description
 *          Création d'un jeu de bataille navale tel que le jeux de société <a href="https://www.wikiwand.com/fr/Bataille_navale_(jeu)">Bataille Navale</a> !
*/
public class Bataillle {
    public static Random rand = new Random();
    public static int [ ][ ] grilleOrdi = new int [10] [10];
    public static int [ ][ ] grilleJeu = new int [10] [10];

    private final Map<Integer, Bateau> LISTEBATEAU = Map.ofEntries(
        Map.entry(1,  new Bateau("porte-avion", 5, 1)),
        Map.entry(2,  new Bateau("croiseur", 4, 2)),
        Map.entry(3,  new Bateau("contre-torpilleur", 3, 3)),
        Map.entry(4,  new Bateau("sous-marin", 3, 4)),
        Map.entry(5,  new Bateau("torpilleur", 2, 5))
    );

    /**
     *  <H2>positionValide<H2/>
     * <p>
     *     Methode pour savoir dans une grille a une position x, y avec un bateau de taille t et de direction 1 ou 2 si le bateau peut être placé.
     *     <p/>
     * @param grille Grille sur laquelle on travail : int [ ] [ ]
     * @param ligne Ligne pour placer le bateau : int
     * @param colonne Colonne pour placer le bateau : int
     * @param direction Direction dans laquelle doit être le bateau  nombre impaires (1) pour horizontal et paire (2) pour vertical  : int
     * @param t Taille du bateau entre 2 et 5 : int
     * @return  Si la position est valide ou pas : Boolean
     */
    public static boolean positionValide (int [ ] [ ] grille, int ligne, int colonne, int direction, int t)
    {
        if (t < 2) t = 2;
        if (t > 5) t = 5;
        direction = direction%2;


        for (int caseRestante = t; caseRestante > 0 ; caseRestante--) {
            if (ligne > 9 || colonne > 9 || ligne < 0 || colonne < 0)
                return false;

            if (grille[ligne][colonne] != 0)
                return false;

            //direction paire -> Vertical
            if (direction == 0)
                colonne++;
            //direction impaire -> Horizontal
            else
                ligne++;
        }
        return true;
    }

    /**
     *  <H2>aleatoireEntre<H2/>
     *
     *  <p>
     *      Retourne un nombre pseudo aléatoire entre debut et fin
     *      <p/>
     *
     * @param debut Debut de la borne pour le nombre aléatoire
     * @param fin Fin de la borne pour le nombre aléatoire
     * @return Nombre selectionné : int
     */
    public static int aleatoireEntre(int debut, int fin)
    {
        return rand.nextInt(fin-debut)+ debut;
    }

    /**
     *  <H2>ajoutBat<H2/>
     * <p>
     *     Methode pour ajouter dans une grille a une position x, y un bateau de taille t et de direction 1 (horizontal) ou 2 (vertical).
     *     <p/>
     * @param grille Grille sur laquelle on travail : int [ ] [ ]
     * @param ligne Ligne pour placer le bateau : int
     * @param colonne Colonne pour placer le bateau : int
     * @param direction Direction dans laquelle doit être le bateau  nombre impaires (1) pour horizontal et paire (2) pour vertical  : int
     * @param t Taille du bateau entre 2 et 5 : int
     * @return  Retourne le tableau resultat contenant le bateau s'il a été possible de l'ajouter int [ ] [ ]
     */
    public static int [ ] [ ] ajouteBat(int [ ] [ ] grille, int ligne, int colonne, int direction, int t) throws Exception {
        if (positionValide(grille, ligne, colonne, direction, t))
            throw new Exception("Impossible d'ajouter le bateau ! As tu vérifié que y'avait la place ?");

        if (t < 2) t = 2;
        if (t > 5) t = 5;
        direction = direction%2;


        int indentificationBateau;
        /*
        *  1 indique la présence d’un porte-avions
        * 2 indique la présence d’un croiseur
        * 3 indique la présence d’un contre-torpilleur
        * 4 indique la présence d’un sous-marin
        * 5 indique la présence d’un torpilleur
        * */

        switch (t) {
            case 6 : indentificationBateau = 1; break;
            case 5 : indentificationBateau = 2; break;
            case 4 : indentificationBateau = 3; break;
            case 3 : indentificationBateau = 4; break;
            case 2 : default : indentificationBateau = 5;
        }

        for (int caseRestante = t; caseRestante > 0 ; caseRestante--) {

            grille[ligne][colonne] = indentificationBateau;

            //direction paire -> Vertical
            if (direction == 0)
                colonne++;
                //direction impaire -> Horizontal
            else
                ligne++;
        }
        return grille;
    }

    public static void initGrilleOrdi() {
        // Mise a zero de la Grille
        for ( int [ ] ligne : grilleOrdi)
            for (int caase : ligne)
                caase = 0;



    }
}
