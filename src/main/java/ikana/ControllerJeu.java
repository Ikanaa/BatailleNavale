package ikana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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

    private boolean triche = false;
    private boolean finDeParti = false;
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
    private ImageView contreTorpilleurE;

    @FXML
    private ImageView croiseurE;

    @FXML
    private ImageView porteAvionE;

    @FXML
    private ImageView sousMarinE;

    @FXML
    private ImageView torpilleurE;

    @FXML
    private TextArea textOut;

    @FXML
    private GridPane grilleEnnemie;
    @FXML
    private GridPane grilleJoueur;
    @FXML
    void actionGrilleEnnemi(ActionEvent event)
    {
        if (finDeParti)
            return;
        Object obj = event.getSource();
        if ((Button) obj == null)
            return;
        Button bouton = (Button) obj;

        System.out.println("Ennemi : " + GridPane.getRowIndex(bouton) + " | " + GridPane.getColumnIndex(bouton));
        if (
                bouton.getStyle().equals("-fx-background-color: rgba(255, 0, 0, 0.5);-fx-border-color: black;-fx-border-width: 1px;")
                || bouton.getStyle().equals("-fx-background-color: rgba(0, 255, 0, 0.5);-fx-border-color: black;-fx-border-width: 1px;")
        )
            return;

        int result = bataille.mouvement(bataille.grilleOrdi, GridPane.getRowIndex(bouton), GridPane.getColumnIndex(bouton));

        switch (result)
        {
            case 0:
                //Touche
                bouton.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);-fx-border-color: black;-fx-border-width: 1px;");
                textOut.setText("Touche en " + GridPane.getRowIndex(bouton) + ":" + GridPane.getColumnIndex(bouton));
                break;
            case 1:
                //Coule
                bouton.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);-fx-border-color: black;-fx-border-width: 1px;");
                textOut.setText("Coule en " + GridPane.getRowIndex(bouton) + ":" + GridPane.getColumnIndex(bouton));
                if (bataille.vainqueur(bataille.grilleOrdi)) {
                    finDeParti = true;
                    textOut.setText("Victoire du Joueur !");
                }
                break;
            case 2:
                //a l'eau
                bouton.setStyle("-fx-background-color: rgba(0, 255, 0, 0.5);-fx-border-color: black;-fx-border-width: 1px;");
                textOut.setText("A l'eau en " + GridPane.getRowIndex(bouton) + ":" + GridPane.getColumnIndex(bouton));
                break;
        }

        Button attaqueOrdi = null;
        int ligne = 0;
        int colonne = 0;

        boolean trouver = false;
        while (!trouver)
        {
            ligne = bataille.aleatoireEntre(0,9);
            colonne = bataille.aleatoireEntre(0,9);
            for (Node node : grilleJoueur.getChildren())
            {
                Button castTest;
                try {
                    castTest = (Button) node;
                }
                catch (Exception e)
                {
                    castTest = null;
                }

                if (castTest != null)
                    if (GridPane.getRowIndex(castTest) == ligne && GridPane.getColumnIndex(castTest) == colonne)
                    {

                        if (!(
                                castTest.getStyle().equals("-fx-background-color: rgba(255, 0, 0, 0.5);-fx-border-color: black;-fx-border-width: 1px;")
                                        || castTest.getStyle().equals("-fx-background-color: rgba(0, 255, 0, 0.5);-fx-border-color: black;-fx-border-width: 1px;")
                        ))
                        {
                            attaqueOrdi = castTest;
                            trouver = true;
                            break;
                        }
                    }
            }
        }

        int resultE = bataille.mouvement(bataille.grilleJeu, ligne, colonne);

        switch (resultE)
        {
            case 0:
                //Touche
                attaqueOrdi.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);-fx-border-color: black;-fx-border-width: 1px;");
                break;
            case 1:
                //Coule
                attaqueOrdi.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);-fx-border-color: black;-fx-border-width: 1px;");
                if (bataille.vainqueur(bataille.grilleJeu)) {
                    finDeParti = true;
                    textOut.setText("Victoire de l'Ordinateur !");
                }
                break;
            case 2:
                //a l'eau
                attaqueOrdi.setStyle("-fx-background-color: rgba(0, 255, 0, 0.5);-fx-border-color: black;-fx-border-width: 1px;");
                break;
        }
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

        if (bateau == porteAvion ||bateau == porteAvionE)
            decalage = 57;
        if (bateau == croiseur || bateau == croiseurE)
            decalage = 43;
        if (bateau == contreTorpilleur || bateau == sousMarin || bateau == contreTorpilleurE || bateau == sousMarinE)
            decalage = 29;
        if (bateau == torpilleur || bateau == torpilleurE)
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

    private void placerBateau(ImageView bateauEnDeplacement, int ligne, int colonne, int direction, GridPane grille)
    {
        if (bateauEnDeplacement == null)
            return;
        if (direction == 0)
            bateauEnDeplacement.setRotate(90);

        if (! grille.getChildren().contains(bateauEnDeplacement))
        {
            grille.getChildren().addFirst(bateauEnDeplacement);
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
                return null;
        }
    }

    ImageView getBateauEnnemiId(int id)
    {
        switch (id)
        {
            case 1:
                return porteAvionE;
            case 2:
                return croiseurE;
            case 3:
                return contreTorpilleurE;
            case 4:
                return sousMarinE;
            case 5:
                return torpilleurE;
            default:
                return null;
        }
    }

    public void setTriche(boolean triche)
    {
        porteAvionE.setVisible(triche);
        croiseurE.setVisible(triche);
        contreTorpilleurE.setVisible(triche);
        sousMarinE.setVisible(triche);
        torpilleurE.setVisible(triche);
    }

    @FXML
    public void toogleTriche(ActionEvent event)
    {
        triche = !triche;
        setTriche(triche);
    }

    @FXML
    public void quitter(ActionEvent event)
    {
        referenceMain.terminerApplication();
    }

    @FXML
    public void recommencer(ActionEvent event)
    {
        referenceMain.recommencer();
    }

    public void placerTousBateau()
    {
        bataille.initGrilleOrdi();
        int[][] position = bataille.getPositionsJoueur();
        for (int index = 0; index < 5; index++)
        {
            int[] info = position[index];
            placerBateau(getBateauId(index+1), info[0], info[1], info[2], grilleJoueur);
        }

        int[][] positionE = bataille.getPositionsEnnemi();
        for (int index = 0; index < 5; index++)
        {
            int[] info = positionE[index];
            placerBateau(getBateauEnnemiId(index+1), info[0], info[1], info[2], grilleEnnemie);
            setTriche(triche);
        }
    }

    public void appliquerFond()
    {
        BackgroundImage mer = new BackgroundImage(new Image("ikana/image/lapis.jpg",512,512,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        grilleJoueur.setBackground(new Background(mer));
        grilleEnnemie.setBackground(new Background(mer));
    }
}