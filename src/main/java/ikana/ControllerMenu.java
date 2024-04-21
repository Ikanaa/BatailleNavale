package ikana;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControllerMenu {

    private Main referenceMain;
    public void setReferenceMain(Main _referenceMain){
        referenceMain = _referenceMain;
    }

    @FXML
    void jouer(ActionEvent event) {
        referenceMain.versSelection();
    }

    @FXML
    void jouerAvecTriche(ActionEvent event) {
        referenceMain.avecTriche = true;
        referenceMain.versSelection();
    }

    @FXML
    void quitter(ActionEvent event) {
        referenceMain.terminerApplication();
    }

}
