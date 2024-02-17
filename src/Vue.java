/**
 *      <h1>Vue</h1>
 *      @author <h2>Louis Saffré</h2>
 *      @Description
 *          Classe représentant une vue tel que dans le MVC <a href='https://www.wikiwand.com/fr/Mod%C3%A8le-vue-contr%C3%B4leur'>(Model Vue Controleur)</a>
 */

public class Vue {

    public static final String ANSI_Zero = "\u001B[0m";
    public static final String ANSI_Noir = "\u001B[30m";
    public static final String ANSI_Rouge = "\u001B[31m";
    public static final String ANSI_Vert = "\u001B[32m";
    public static final String ANSI_Jaune = "\u001B[33m";
    public static final String ANSI_Bleu = "\u001B[34m";
    public static final String ANSI_Violet = "\u001B[35m";
    public static final String ANSI_Cyan = "\u001B[36m";
    public static final String ANSI_Blanc = "\u001B[37m";


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
                        System.out.print(ANSI_Bleu);
                        break;
                    case 6:
                        System.out.print(ANSI_Rouge);
                        break;
                    default:
                        System.out.print(ANSI_Vert);
                }

                System.out.print("  " + collone);
                System.out.print(ANSI_Zero);
            }
        }
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
}
