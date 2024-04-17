package ikana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// https://opengameart.org/content/sea-warfare-set-ships-and-more
// NOTA
// porte avion         decalage de 57
// croiseur            decalage de 43
// contre-torpilleur   decalage de 29
// sous-marin          decalage de 29
// torpilleur          decalage de 15

public class JavaFXController {

    @FXML
    private GridPane grilleEnnemie;
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

}