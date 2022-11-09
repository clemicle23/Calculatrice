package View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class GUI implements IView{

    private Label currentLabel;
    private Label value1;
    private Label value2;
    private Label value3;
    private Label value4;
    private List<Button> buttonList;
    private BorderPane borderPane;

    public List<Button> getButtonList() {
        return buttonList;
    }

    public GUI(Stage stage, String pathCSS){
        stage.setTitle("Calculatrice");
        borderPane = new BorderPane();
        buttonList= new ArrayList<>();

        borderPane.setBackground(new Background(new BackgroundFill(Color.rgb(239, 235, 235), new CornerRadii(5.0), new Insets(-5.0))));

        affiche();
        Scene scene = new Scene(borderPane);

        scene.getStylesheets().add(pathCSS);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //Ajout des Listeners
    public void clickedNumber(Button button){
        button.addEventHandler(ActionEvent.ACTION, (e) ->{
            String currentString= currentLabel.getText();
            currentString += button.getText();
            currentLabel.setText(currentString);
        });
    }

    public void createEventHandler(Button button, EventHandler listener){
        button.addEventHandler(ActionEvent.ACTION,listener);
    }

    @Override
    public void affiche() {
        borderPane.setTop(createTop());
        borderPane.setCenter(createCenter());
    }

    private Node createTop() {
        GridPane topPane = new GridPane();
        topPane.setVgap(1);
        topPane.setPadding(new Insets(2));

        //Déclaration des labels des dernières valeurs de la pile
        value1 = new Label("");
        value2 = new Label("");
        value3 = new Label("");
        value4 = new Label("");

        //Ajout des labels des dernières valeurs de la pile
        topPane.add(value1,0,0);
        topPane.add(value2,0,1);
        topPane.add(value3,0,2);
        topPane.add(value4,0,3);

        return topPane;
    }

    private Node createCenter() {
        GridPane centerPane = new GridPane();
        centerPane.setHgap(2);
        centerPane.setVgap(2);
        centerPane.setPadding(new Insets(2));

        currentLabel = new Label("");
        centerPane.add(currentLabel,0,0,4,1);

        //Déclaration des boutons
        Button c = new Button("C");
        Button del = new Button("Del");
        Button enter = new Button("E");
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
        Button add = new Button("+");
        Button sub = new Button("-");
        Button mult = new Button("x");
        Button div = new Button("/");
        Button neg = new Button("Neg");
        Button coma = new Button(".");
        Button swap = new Button("Swap");

        //Ajout des boutons à la liste des boutons
        buttonList.add(c);
        buttonList.add(del);
        buttonList.add(enter);
        buttonList.add(zero);
        buttonList.add(un);
        buttonList.add(deux);
        buttonList.add(trois);
        buttonList.add(quatre);
        buttonList.add(cinq);
        buttonList.add(six);
        buttonList.add(sept);
        buttonList.add(huit);
        buttonList.add(neuf);
        buttonList.add(add);
        buttonList.add(sub);
        buttonList.add(mult);
        buttonList.add(div);
        buttonList.add(neg);
        buttonList.add(coma);
        buttonList.add(swap);

        //Ajout des boutons au GridPane
        centerPane.add(c,0,1);
        centerPane.add(del,1,1);
        centerPane.add(enter,2,1);

        centerPane.add(sept,0,2);
        centerPane.add(huit,1,2);
        centerPane.add(neuf,2,2);

        centerPane.add(quatre,0,3);
        centerPane.add(cinq,1,3);
        centerPane.add(six,2,3);

        centerPane.add(un,0,4);
        centerPane.add(deux,1,4);
        centerPane.add(trois,2,4);

        centerPane.add(zero,0,5);
        centerPane.add(neg,1,5);
        centerPane.add(coma,2,5);

        centerPane.add(add,3,1);
        centerPane.add(sub,3,2);
        centerPane.add(mult,3,3);
        centerPane.add(div,3,4);
        centerPane.add(swap, 3,5);

        clickedNumber(zero);
        clickedNumber(un);
        clickedNumber(deux);
        clickedNumber(trois);
        clickedNumber(quatre);
        clickedNumber(cinq);
        clickedNumber(six);
        clickedNumber(sept);
        clickedNumber(huit);
        clickedNumber(neuf);
        clickedNumber(coma);

        return centerPane;
    }

    @Override
    public void change(List<String> data) {
        value1.setText(data.get(0));
        value2.setText(data.get(1));
        value3.setText(data.get(2));
        value4.setText(data.get(3));
    }

    @Override
    public void change(String accu) {
        currentLabel.setText(accu);
    }

    public String getCurrentString() {
        return currentLabel.getText();
    }

}
