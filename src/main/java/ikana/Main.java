package ikana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static final int MaxHeight = 700;
    public static final int Height = 600;


    public static final int MaxWidth = 1200;
    public static final int Width = 800;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Width, Height);
        stage.setTitle("Bataille Naval !");

        stage.setMinHeight(Height);
        stage.setMaxHeight(MaxHeight);

        stage.setMinWidth(Width);
        stage.setMaxWidth(MaxWidth);

        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
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
//}
