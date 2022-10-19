package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI {
    public GUI(Stage stage){
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);

        borderPane.setCenter(createDrawPane());//Crée le centre du borderPane

        stage.setScene(scene);
        stage.show();


    }
    private Pane createDrawPane() {
        Button button = new Button("Hello");
        Pane pane = new Pane();
        pane.getChildren().addAll(button);//Ajout du shooter dans le pane

        return pane;
    }
}
