package ikana;

/**
 *   <H1>Bateau<H1/>
 *      @author <h2>Louis Saffré<h2/>
 *      @Description
 *          Classe Bateau permettant de regrouper les informations des bateaux (type, taille, chiffreIdentifiant)
 */
public class Bateau {
    private final String type;
    private final int taille;
    private final int chiffreIdentifiant;

    /**
     *  <h2>Bateau<h2/>
     *  <p>
     *      Constructeur de la classe Bateau
     *  <p/>
     * @param typeBateau type de bateau ("porte-avion" // "torpilleur") : String
     * @param tailleBateau taille du bateau entre 2 et 5 : int
     * @param chiffreIdentifiantBateau chiffre permettant d'identifier le bateau sur la grille : int
     */
    public Bateau(String typeBateau, int tailleBateau, int chiffreIdentifiantBateau)
    {
        type = typeBateau;
        taille = tailleBateau;
        chiffreIdentifiant = chiffreIdentifiantBateau;
    }

    /**
     *  <h2>obtenirType<h2/>
     *  <p>
     *      Permet d'obtenir le type du bateau.
     *  </p>
     * @return type du bateau ("porte-avion" // "torpilleur") : String
     */
    public String obtenirType()
    {
        return type;
    }

    /**
     *  <h2>obtenirTaille<h2/>
     *  <p>
     *      Permet d'obtenir la taille du bateau.
     *  </p>
     * @return taille du bateau entre 1 et 5: int
     */
    public int obtenirTaille()
    {
        return taille;
    }

    /**
     *  <h2>obtenirIdentifiant<h2/>
     *  <p>
     *      Permet d'obtenir l"identifiant du bateau.
     *  </p>
     * @return chiffre permettant d'identifier le bateau sur la grille : int
     */
    public  int obtenirIdentifiant()
    {
        return  chiffreIdentifiant;
    }
}
