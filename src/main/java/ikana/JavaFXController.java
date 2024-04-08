package ikana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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

        System.out.println(GridPane.getRowIndex(bouton) + " | " + GridPane.getColumnIndex(bouton));
    }

}