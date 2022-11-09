import Controler.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;

public class
Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Controleur controleur = new Controleur(primaryStage, "View/calcStyleYellow.css");
    }
}
