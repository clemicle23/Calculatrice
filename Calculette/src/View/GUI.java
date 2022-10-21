package View;

import Controler.Controleur;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public class GUI {
    Controleur controleur = new Controleur();
    public GUI(Stage stage){
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add("View/calcStyle.css");
        //Déclaration des boutons
        Label result = new Label("0");
        Button c = new Button("C");
        Button del = new Button("Del");
        Button exe = new Button("Exe");
        Button zero = new Button("0");
        Button un = new Button("1");
        Button deux = new Button("2");
        Button trois = new Button("3");
        Button quatre = new Button("4");
        Button cinq = new Button("5");
        Button six = new Button("6");
        Button sept = new Button("7");
        Button huit = new Button("8");
        Button neuf = new Button("9");
        Button plus = new Button("+");
        Button moins = new Button("-");
        Button mult = new Button("x");
        Button div = new Button("/");

        //Ajout des boutons au GridPane
        gridPane.add(result,0,1,3,1);
        gridPane.add(c,0,2);
        gridPane.add(del,1,2);
        gridPane.add(exe,2,2);

        gridPane.add(sept,0,3);
        gridPane.add(huit,1,3);
        gridPane.add(neuf,2,3);

        gridPane.add(quatre,0,4);
        gridPane.add(cinq,1,4);
        gridPane.add(six,2,4);

        gridPane.add(un,0,5);
        gridPane.add(deux,1,5);
        gridPane.add(trois,2,5);

        gridPane.add(plus,4,2);
        gridPane.add(moins,4,3);
        gridPane.add(mult,4,4);
        gridPane.add(div,4,5);


        //Ajout d'un Listener
        un.addEventHandler(ActionEvent.ACTION, controleur);

        stage.setScene(scene);
        stage.show();


    }

}
