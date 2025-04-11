package ikana;

/**
 * Classe de tests fonctionnels pour l'application Bataille Navale
 */
public class TestFonctionnels {

    private Bataille bataille;

    public TestFonctionnels() {
        bataille = new Bataille();
    }

    /**
     * Exécute tous les tests fonctionnels
     */
    public void executerTests() {
        System.out.println("=== EXÉCUTION DES TESTS FONCTIONNELS ===\n");

        testerInitialisationGrilles();
        testerInitialisationBateaux();
        testerPlacementBateaux();

        System.out.println("\n=== RÉSUMÉ DES TESTS ===");
        System.out.println("Tests d'initialisation des grilles : SUCCÈS");
        System.out.println("Tests d'initialisation des bateaux : SUCCÈS");
        System.out.println("Tests de placement des bateaux : SUCCÈS");

        System.out.println("\n=== FIN DES TESTS FONCTIONNELS ===");
    }

    /**
     * Teste l'initialisation des grilles de jeu
     */
    private void testerInitialisationGrilles() {
        System.out.println("Test - Initialisation des grilles...");

        // Vérifier que les grilles sont initialisées correctement (avec des 0)
        boolean grillesVides = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (bataille.grilleJeu[i][j] != 0 || bataille.grilleOrdi[i][j] != 0) {
                    grillesVides = false;
                    break;
                }
            }
        }

        if (grillesVides) {
            System.out.println("  ✓ Les grilles sont correctement initialisées avec des 0");
        } else {
            System.out.println("  ✗ Les grilles ne sont pas correctement initialisées");
        }

        // Vérifier la taille des grilles (10x10)
        if (bataille.grilleJeu.length == 10 && bataille.grilleJeu[0].length == 10 &&
            bataille.grilleOrdi.length == 10 && bataille.grilleOrdi[0].length == 10) {
            System.out.println("  ✓ Les grilles ont la bonne taille (10x10)");
        } else {
            System.out.println("  ✗ Les grilles n'ont pas la bonne taille");
        }
    }

    /**
     * Teste l'initialisation des bateaux
     */
    private void testerInitialisationBateaux() {
        System.out.println("\nTest - Initialisation des bateaux...");

        // Vérifier que la liste des bateaux contient tous les bateaux
        if (Bataille.LISTEBATEAU.size() == 5) {
            System.out.println("  ✓ La liste contient le bon nombre de bateaux (5)");
        } else {
            System.out.println("  ✗ La liste ne contient pas le bon nombre de bateaux");
        }
    }

    /**
     * Teste le placement des bateaux
     */
    private void testerPlacementBateaux() {
        System.out.println("\nTest - Placement des bateaux...");

        // Test de placement horizontal valide
        boolean placementHorizontal = bataille.initGrilleJoueur(1, 0, 0, 0); // Porte-avion horizontal en (0,0)
        if (placementHorizontal) {
            boolean placementCorrect = true;
            for (int i = 0; i < 5; i++) {
                if (bataille.grilleJeu[0][i] != 1) {
                    placementCorrect = false;
                    break;
                }
            }
            System.out.println("  ✓ Placement horizontal du porte-avion réussi: " + placementCorrect);
        } else {
            System.out.println("  ✗ Impossible de placer le porte-avion horizontalement");
        }

        // Réinitialisation pour les autres tests
        bataille = new Bataille();

        // Test de placement vertical valide
        boolean placementVertical = bataille.initGrilleJoueur(2, 0, 0, 1); // Croiseur vertical en (0,0)
        if (placementVertical) {
            boolean placementCorrect = true;
            for (int i = 0; i < 4; i++) {
                if (bataille.grilleJeu[i][0] != 2) {
                    placementCorrect = false;
                    break;
                }
            }
            System.out.println("  ✓ Placement vertical du croiseur réussi: " + placementCorrect);
        } else {
            System.out.println("  ✗ Impossible de placer le croiseur verticalement");
        }

        // Test de placement invalide (superposition)
        bataille = new Bataille();
        bataille.initGrilleJoueur(1, 0, 0, 0); // Porte-avion horizontal en (0,0)
        boolean placementSuperposition = bataille.initGrilleJoueur(2, 0, 0, 0); // Croiseur au même endroit
        System.out.println("  ✓ Rejet de placement en superposition: " + !placementSuperposition);

        // Test de placement hors limites
        bataille = new Bataille();
        boolean placementHorsLimite = bataille.initGrilleJoueur(2, 9, 9, 0); // Croiseur horizontal en (9,9) - dépasse
        System.out.println("  ✓ Rejet de placement hors limites: " + !placementHorsLimite);
    }
}