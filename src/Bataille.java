import java.util.Map;
import java.util.Random;


/**
 *
 *      @author <h2>Louis Saffré</h2>
 *      @Sujet Jeu de Bataille Navale (Touché Coulé / BattleShip) !
 *      @version 0.1
 *      @Description
 *          Création d'un jeu de bataille navale tel que le jeux de société <a href="https://www.wikiwand.com/fr/Bataille_navale_(jeu)">Bataille Navale</a> !
*/
public class Bataille {
    public Random rand;
    public int [ ][ ] grilleOrdi;
    public int [ ][ ] grilleJeu;

    public Controleur controleur;

    public Bataille() {
        //Rien ?
        rand = new Random();
        grilleOrdi = new int [10] [10];
        grilleJeu = new int [10] [10];
    }

    // Permet de définir les bateaux utilisé dans la partie !
    public static final Map<Integer, Bateau> LISTEBATEAU = Map.ofEntries(
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
    public boolean positionValide (int [ ] [ ] grille, int ligne, int colonne, int direction, int t)
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
    public int aleatoireEntre(int debut, int fin)
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
     * @param bat Objet contenant les informations relative au bateau : Bateau
     * @return  Retourne le tableau resultat contenant le bateau s'il a été possible de l'ajouter int [ ] [ ]
     */
    public int [ ] [ ] ajouteBat(int [ ] [ ] grille, int ligne, int colonne, int direction, Bateau bat) throws Exception {
        if (!positionValide(grille, ligne, colonne, direction, bat.obtenirTaille()))
            throw new Exception("Impossible d'ajouter le bateau ! As tu vérifié que y'avait la place ?");

        direction = direction%2;



        for (int caseRestante = bat.obtenirTaille(); caseRestante > 0 ; caseRestante--) {

            grille[ligne][colonne] = bat.obtenirIdentifiant();

            //direction paire -> Vertical
            if (direction == 0)
                colonne++;
                //direction impaire -> Horizontal
            else
                ligne++;
        }
        return grille;
    }


    /**
     *  <H2>initGrilleOrdi<H2/>
     * <p>
     *     Methode pour initialiser la grille de jeu de l'ordinateur.
     *     <p/>
     */
    public void initGrilleOrdi() {

        for (Map.Entry<Integer, Bateau> entree :  LISTEBATEAU.entrySet())
        {
            Bateau bateauActuel = entree.getValue();

            boolean posOk = false;
            while (!posOk)
            {
                int ligne = aleatoireEntre(0,10);
                int colonne = aleatoireEntre(0,10);
                int direction = aleatoireEntre(1, 3);

                if (positionValide(grilleOrdi, ligne, colonne, direction, bateauActuel.obtenirTaille()))
                {
                    posOk = true;

                    //Ajout du bateau quand tout est ok !
                    try {
                        grilleOrdi = ajouteBat(grilleOrdi, ligne, colonne, direction, bateauActuel);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }

    /**
     * <h2>initGrilleJoueur</h2>
     * <p>
     *     Initialise la grille du joueur en lui demandant la position des bateaux
     * </p>
     */
    public void initGrilleJoueur()
    {
        for (Map.Entry<Integer, Bateau> entree :  LISTEBATEAU.entrySet())
        {
            Bateau bateauActuel = entree.getValue();

            boolean posOk = false;
            while (!posOk)
            {
                int[] info = controleur.demandePositionBateau(bateauActuel);
                int ligne = info[0];
                int colonne = info[1];
                int direction = info[2];

                if (positionValide(grilleJeu, ligne, colonne, direction, bateauActuel.obtenirTaille()))
                {
                    posOk = true;

                    //Ajout du bateau quand tout est ok !
                    try {
                        grilleJeu = ajouteBat(grilleJeu, ligne, colonne, direction, bateauActuel);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * <h2>couler</h2>
     * <p>
     *     Permet de vérifier si un bateau a été coulé dans une grille
     * </p>
     * @param grille grille sur laquelle on regarde : int[][]
     * @param bateau entier qui représente le bateau dans la grille : int
     * @return Si le bateau a été coulé : boolean
     */
    public boolean couler (int[][] grille, int bateau)
    {
        for (int[] ligne : grille)
            for (int colonne : ligne)
                if (colonne == bateau)
                    return false;
        return true;
    }

    /**
     * <h2>mouvement</h2>
     * <p>
     *     Represente un tire sur une case.
     * </p>
     * @param grille la grille sur laquelle on tire : int[][]
     * @param ligne la ligne du tire : int
     * @param colonne la colonne du tire : int
     * @return Un entier qui représente le resultat du tire : 0 = touche | 1 = coule | 2 = a l'eau : int
     */
    public int mouvement (int[][] grille, int ligne, int colonne)
    {
        switch (grille[ligne][colonne])
        {
            case 0 : case 6 :
                //a l'eau
            return 2;
            default :
                int tempBateau = grille[ligne][colonne];
                grille[ligne][colonne] = 6;
                if (couler(grille, tempBateau))
                    return 1;
                else
                    return 0;
        }
    }

    /**
     * <h2>tirOrdinateur</h2>
     * <p>
     *     Donne un tableau de 2 entier aleatoire qui représente le tire de l'ordinateur
     * </p>
     * @return Tableau de 2 entier aleatoire : int[]
     */
    public int[] tirOrdinateur()
    {
        int[] res = {
                aleatoireEntre(0,10),
                aleatoireEntre(0,10)
        };
        return res;
    }

    /**
     * <h2>vainqueur</h2>
     * <p>
     *     Vérifie dans une grille si tous les bateaux sont coulés
     * </p>
     * @param grille la grille que l'on regarde : int[][]
     * @return Si tous les bateaux sont coulés : boolean
     */
    public boolean vainqueur(int[][] grille)
    {
        for (int[] ligne : grille)
            for (int colonne : ligne)
                if (colonne != 6 && colonne != 0)
                    return false;
        return true;
    }
}
