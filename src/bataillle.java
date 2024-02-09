import java.util.Random;


/**
 *
 *      @author Louis Saffré
 *      @Sujet Jeu de Bataille Navale (Touché Coulé / BattleShip) !
 *      @version 0.1
 *      @Description
 *          Création d'un jeu de bataille navale tel que le jeux de société <a href="https://www.wikiwand.com/fr/Bataille_navale_(jeu)">Bataille Navale</a> !
*/
public class bataillle {
    public static int [ ][ ] grilleOrdi = new int [10] [10];
    public static int [ ][ ] grilleJeu = new int [10] [10];

    /**
     *  <H2>Position Valide<H2/>
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
}
