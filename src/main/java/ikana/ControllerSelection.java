package ikana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

// https://opengameart.org/content/sea-warfare-set-ships-and-more
// NOTA

public class ControllerSelection {

    private Bataille bataille;
    public void setBataille(Bataille _bataille){
        bataille = _bataille;
    }

    private Main referenceMain;
    public void setReferenceMain(Main _referenceMain){
        referenceMain = _referenceMain;
    }

    @FXML
    private HBox baseBox;

    @FXML
    private Button boutonDemarrer;

    @FXML
    private ImageView contreTorpilleur;

    @FXML
    private ImageView croiseur;

    @FXML
    private ImageView porteAvion;

    @FXML
    private ImageView sousMarin;

    @FXML
    private ImageView torpilleur;

    private ImageView bateauEnDeplacement;

    @FXML
    private GridPane grilleJoueur;

    // porte avion         decalage de 57
    // croiseur            decalage de 43
    // contre-torpilleur   decalage de 29
    // sous-marin          decalage de 29
    // torpilleur          decalage de 15
    void appliquerDecalageBateau(ImageView bateau)
    {
        double decalage = 0;

        if (bateau == porteAvion)
            decalage = 57;
        if (bateau == croiseur)
            decalage = 43;
        if (bateau == contreTorpilleur || bateau == sousMarin)
            decalage = 29;
        if (bateau == torpilleur)
            decalage = 15;

        if (bateau.getRotate() != 0)
        {
            bateau.setTranslateY(0);
            //y
            bateau.setTranslateX(decalage);
        }
        else
        {
            bateau.setTranslateX(0);
            //x
            bateau.setTranslateY(decalage);
        }
    }

    int getIdBateau(ImageView bateau)
    {
        if (bateau == porteAvion)
            return 1;
        if (bateau == croiseur)
            return 2;
        if (bateau == contreTorpilleur)
            return 3;
        if (bateau == sousMarin)
            return 4;
        if (bateau == torpilleur)
            return 5;
        return 0;
    }

    @FXML
    void actionGrilleJoueur(ActionEvent event)
    {
        Object obj = event.getSource();
        if ((Button) obj == null)
            return;
        Button bouton = (Button) obj;

        System.out.println( "Selection Joueur : "+ GridPane.getRowIndex(bouton) + " | " + GridPane.getColumnIndex(bouton));

        int ligne = GridPane.getRowIndex(bouton);
        int colonne = GridPane.getColumnIndex(bouton);

        if (bateauEnDeplacement != null)
        {
            int idBateau = getIdBateau(bateauEnDeplacement);
            if (idBateau == 0)
                return;

            int direction = 1;
            if (bateauEnDeplacement.getRotate() == 90)
                direction = 0;
            if (!bataille.initGrilleJoueur(idBateau, ligne, colonne, direction))
                return;
            if (! grilleJoueur.getChildren().contains(bateauEnDeplacement))
            {
                grilleJoueur.getChildren().add(bateauEnDeplacement);
            }
            verifierFinDeSelection();
            GridPane.setRowIndex(bateauEnDeplacement, ligne);
            GridPane.setColumnIndex(bateauEnDeplacement, colonne);
            appliquerDecalageBateau(bateauEnDeplacement);
        }
    }

    @FXML
    void cliqueContreTorpilleur(MouseEvent event) {
        bateauEnDeplacement = contreTorpilleur;
    }

    @FXML
    void cliqueCroiseur(MouseEvent event) {
        bateauEnDeplacement = croiseur;
    }

    @FXML
    void cliquePorteAvion(MouseEvent event) {
        bateauEnDeplacement = porteAvion;
    }

    @FXML
    void cliqueSousMarin(MouseEvent event) {
        bateauEnDeplacement = sousMarin;
    }

    @FXML
    void cliqueTorpilleur(MouseEvent event) {
        bateauEnDeplacement = torpilleur;
    }

    @FXML
    void demarrerPartie(ActionEvent event) {

    }

    @FXML
    void tournerBateau(ActionEvent event) {
        if (bateauEnDeplacement == null)
            return;
        if (bateauEnDeplacement.getRotate() == 0)
            bateauEnDeplacement.setRotate(90);
        else
            bateauEnDeplacement.setRotate(0);

        boutonDemarrer.setDisable(true);
        if (!baseBox.getChildren().contains(bateauEnDeplacement))
            baseBox.getChildren().add(bateauEnDeplacement);
        appliquerDecalageBateau(bateauEnDeplacement);
    }

    void verifierFinDeSelection()
    {
        javafx.collections.ObservableList<javafx.scene.Node> listTest = grilleJoueur.getChildren();
        if ( !
                ( listTest.contains(porteAvion)
                && listTest.contains(torpilleur)
                && listTest.contains(contreTorpilleur)
                && listTest.contains(sousMarin )
                && listTest.contains(croiseur)
            )
        ){
            boutonDemarrer.setDisable(true);
            return;
        }

        boutonDemarrer.setDisable(false);
    }
}