package ikana;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    Bataille bataille;
    boolean avecTriche = false;
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
    ControllerMenu controllerMenu;
    @Override
    public void start(Stage stage) throws IOException {
        planche = stage;
        bataille = new Bataille();

        FXMLLoader chargementMenu = new FXMLLoader(Main.class.getResource("menu.fxml"));
        sceneMenu = new Scene(chargementMenu.load(), Width, Height);
        controllerMenu= chargementMenu.getController();
        controllerMenu.setReferenceMain(this);

        stage.setTitle("Bataille Naval !");

        stage.setMinHeight(Height);
        stage.setMaxHeight(MaxHeight);

        stage.setMinWidth(Width);
        stage.setMaxWidth(MaxWidth);

        stage.setScene(sceneMenu);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }

    public void selectionVersJeu()
    {
        try {
            FXMLLoader chargementJeu = new FXMLLoader(Main.class.getResource("game.fxml"));
            sceneJeu = new Scene(chargementJeu.load(), Width, Height);
            controllerJeu=  chargementJeu.getController();
            controllerJeu.setBataille(bataille);
            controllerJeu.setReferenceMain(this);
            controllerJeu.appliquerFond();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        planche.setScene(sceneJeu);
        controllerJeu.placerTousBateau();
        controllerJeu.setTriche(avecTriche);
    }
    public void versSelection()
    {
        try {
            FXMLLoader chargementSelection = new FXMLLoader(Main.class.getResource("selection_bateau.fxml"));
            sceneSelection = new Scene(chargementSelection.load(), Width, Height);
            controllerSelection= chargementSelection.getController();
            controllerSelection.setBataille(bataille);
            controllerSelection.setReferenceMain(this);
            controllerSelection.appliquerFond();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        planche.setScene(sceneSelection);
    }

    public void recommencer()
    {
        bataille = new Bataille();
        versSelection();
    }

    public void terminerApplication()
    {
        planche.close();
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