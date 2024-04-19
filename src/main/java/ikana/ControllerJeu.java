package ikana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

// https://opengameart.org/content/sea-warfare-set-ships-and-more
// NOTA
// porte avion         decalage de 57
// croiseur            decalage de 43
// contre-torpilleur   decalage de 29
// sous-marin          decalage de 29
// torpilleur          decalage de 15

public class ControllerJeu {

    private Bataille bataille;
    public void setBataille(Bataille _bataille){
        bataille = _bataille;
    }

    private Main referenceMain;
    public void setReferenceMain(Main _referenceMain){
        referenceMain = _referenceMain;
    }

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


    @FXML
    private GridPane grilleEnnemie;
    @FXML
    private GridPane grilleJoueur;
    @FXML
    void actionGrilleEnnemi(ActionEvent event)
    {
        Object obj = event.getSource();
        if ((Button) obj == null)
            return;
        Button bouton = (Button) obj;

        System.out.println("Ennemi : " + GridPane.getRowIndex(bouton) + " | " + GridPane.getColumnIndex(bouton));
    }

    @FXML
    void actionGrilleJoueur(ActionEvent event)
    {
        Object obj = event.getSource();
        if ((Button) obj == null)
            return;
        Button bouton = (Button) obj;

        System.out.println( "Joueur : "+ GridPane.getRowIndex(bouton) + " | " + GridPane.getColumnIndex(bouton));
    }

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

    private void placerBateau(ImageView bateauEnDeplacement, int ligne, int colonne, int direction)
    {

        if (direction == 0)
            bateauEnDeplacement.setRotate(90);

        if (! grilleJoueur.getChildren().contains(bateauEnDeplacement))
        {
            grilleJoueur.getChildren().add(bateauEnDeplacement);
        }
        GridPane.setRowIndex(bateauEnDeplacement, ligne);
        GridPane.setColumnIndex(bateauEnDeplacement, colonne);
        appliquerDecalageBateau(bateauEnDeplacement);
    }

    ImageView getBateauId(int id)
    {
        switch (id)
        {
            case 1:
                return porteAvion;
            case 2:
                return croiseur;
            case 3:
                return contreTorpilleur;
            case 4:
                return sousMarin;
            case 5:
                return torpilleur;
            default:
                return porteAvion;
        }
    }

    public void placerTousBateau()
    {
        int[][] position = bataille.getPositionsJoueur();
        for (int index = 0; index < 5; index++)
        {
            int[] info = position[index];
            placerBateau(getBateauId(index+1), info[0], info[1], info[2]);
        }
    }
}