package ikana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    Bataille bataille;

    Stage planche;

    private Scene sceneJeu;
    private Scene sceneMenu;
    private Scene sceneSelection;

    public static final int MaxHeight = 700;
    public static final int Height = 600;


    public static final int MaxWidth = 1200;
    public static final int Width = 800;

    ControllerJeu controllerJeu;
    ControllerSelection controllerSelection;
    @Override
    public void start(Stage stage) throws IOException {
        planche = stage;
        bataille = new Bataille();

        FXMLLoader chargementJeu = new FXMLLoader(Main.class.getResource("game.fxml"));
        sceneJeu = new Scene(chargementJeu.load(), Width, Height);
        controllerJeu=  chargementJeu.getController();
        controllerJeu.setBataille(bataille);
        controllerJeu.setReferenceMain(this);

        FXMLLoader chargementSelection = new FXMLLoader(Main.class.getResource("selection_bateau.fxml"));
        sceneSelection = new Scene(chargementSelection.load(), Width, Height);
        controllerSelection= chargementSelection.getController();
        controllerSelection.setBataille(bataille);
        controllerSelection.setReferenceMain(this);

        stage.setTitle("Bataille Naval !");

        stage.setMinHeight(Height);
        stage.setMaxHeight(MaxHeight);

        stage.setMinWidth(Width);
        stage.setMaxWidth(MaxWidth);

        stage.setScene(sceneSelection);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }

    public void selectionVersJeu()
    {
        planche.setScene(sceneJeu);
        controllerJeu.placerTousBateau();
    }
}



//public class Main {
//    public static void main(String[ ] args) {
//        //test
//
//        Controleur controleur = new Controleur();
//
//        controleur.menuPrincipale();
//    }
//