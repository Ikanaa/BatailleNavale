package ikana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 900);
        stage.setTitle("Bataille Naval !");
        stage.setMinHeight(900);
        stage.setMaxHeight(900);
        stage.setMinWidth(750);
        stage.setMaxWidth(750);
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
