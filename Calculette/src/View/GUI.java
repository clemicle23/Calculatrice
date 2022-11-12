package View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modélisant l'interface graphique en javafx
 */
public class GUI implements IView{
    //Label stockant les nombres écrits avec les boutons mais non validés, (saisie temporaire ne modifiant pas la pile)
    private Label currentLabel;

    //Labels contenant les quatre dernières valeurs de la pile
    private Label value1;
    private Label value2;
    private Label value3;
    private Label value4;

    //List des boutons ils seront accessibles par le Controleur (via un getteur) pour leur ajouter les listeners
    private List<Button> buttonList;

    //Label indiquant les erreurs
    private Label errorLabel;

    //Scene associée au stage
    private Scene scene;

    //BorderPane associé à la scene
    private BorderPane borderPane;

    /**
     * Constructeur de GUI
     * @param stage
     */
    public GUI(Stage stage){
        //Change le titre de la fenêtre
        stage.setTitle("Calculatrice");

        //Instanciation les attributs
        borderPane = new BorderPane();
        scene = new Scene(borderPane);
        buttonList= new ArrayList<>();

        //Changement de la couleur de fond
        borderPane.setBackground(new Background(new BackgroundFill(Color.rgb(239, 235, 235), new CornerRadii(5.0), new Insets(-5.0))));

        //Affichage des différents éléments
        affiche();

        //Associe à la fenêtre une feuille CSS avec un style personnalisé des différents contrôleurs
        scene.getStylesheets().add("View/calcStyleBlue.css");

        //Empêche de redimensionner la fenêtre
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Getteur de ButtonList
     * @return la liste des boutons de GUI
     */
    public List<Button> getButtonList() {
        return buttonList;
    }

    /**
     * Ajoute le texte du bouton au currentLabel
     * @param button
     */
    public void clickedNumber(Button button){
        button.addEventHandler(ActionEvent.ACTION, (e) ->{
            String currentString= currentLabel.getText();
            currentString += button.getText();
            currentLabel.setText(currentString);
            errorLabel.setText("");
        });
    }

    /**
     * Ajoute un listener au bouton
     * @param button
     * @param listener
     */
    public void createEventHandler(Button button, EventHandler listener){
        button.addEventHandler(ActionEvent.ACTION,listener);
    }

    /**
     * Apelle les fonctions pour afficher les différents éléments graphiques
     */
    @Override
    public void affiche() {
        //Création des top, center et bottom du borderPane
        borderPane.setTop(createTop());
        borderPane.setCenter(createCenter());
        borderPane.setBottom(createBottom());
    }

    public void setErrorLabel(String errorText) {
        errorLabel.setText(errorText);
    }

    /**
     * Crétation du top du broderPane
     * @return un menu pour changer la couleur de la calculatrice
     */
    private Node createTop() {
        //Instanciation de errorLabel
        errorLabel = new Label("");
        errorLabel.getStyleClass().add("label2");

        //Création du menu
        MenuBar menuBar= new MenuBar();
        Menu menuCouleur = new Menu(" Couleur");
        menuBar.getMenus().add(menuCouleur);

        //Création des différents item correspondants au couleur que l'on peut donner à la calculatrice
        RadioMenuItem blue = new RadioMenuItem("Blue");
        RadioMenuItem green = new RadioMenuItem("Green");
        RadioMenuItem orange = new RadioMenuItem("Orange");
        RadioMenuItem pink = new RadioMenuItem("Pink");
        RadioMenuItem purple = new RadioMenuItem("Purple");
        RadioMenuItem red = new RadioMenuItem("Red");
        RadioMenuItem yellow = new RadioMenuItem("Yellow");


        menuCouleur.getItems().addAll(blue, green, orange, pink, purple, red, yellow);
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(blue, green, orange, pink, purple, red, yellow);

        blue.setSelected(true);

        //Méthode appelée lorsque l'item est séléctionné
        setColor(blue);
        setColor(green);
        setColor(orange);
        setColor(pink);
        setColor(purple);
        setColor(red);
        setColor(yellow);

        //Création du GridPane
        GridPane topPane = new GridPane();
        topPane.add(menuBar,0,0);
        topPane.add(errorLabel,1,0);
        return topPane;
    }

    /**
     * Change la feuille CSS associée à la scene en fonction de l'item séléctionné
     * @param color
     */
    private void setColor(RadioMenuItem color) {
        String colorName = color.getText();
        color.setOnAction((e)->{
            //Enlève la feuille CSS associée
            scene.getStylesheets().clear();
            //Ajoute la feuille correspondant à la couleur
            scene.getStylesheets().add("View/calcStyle"+ colorName +".css");
        });

    }

    /**
     * Création du center du borderPane
     * @return Un GridPane contenant les labels des dernières valeurs de la pile
     */
    private Node createCenter() {
        GridPane centerPane = new GridPane();
        centerPane.setVgap(1);
        centerPane.setPadding(new Insets(2));

        //Déclaration des labels des dernières valeurs de la pile
        value1 = new Label("");
        value2 = new Label("");
        value3 = new Label("");
        value4 = new Label("");

        //Ajout des labels des dernières valeurs de la pile
        centerPane.add(value1,0,0);
        centerPane.add(value2,0,1);
        centerPane.add(value3,0,2);
        centerPane.add(value4,0,3);

        return centerPane;
    }

    /**
     * Création du bottom de broderPane
     * @return Un GridPane contenant le currentLabel et tous les boutons de la calculatrice
     */
    private Node createBottom() {
        //Création du GridPane
        GridPane bottomPane = new GridPane();
        bottomPane.setHgap(2);
        bottomPane.setVgap(2);
        bottomPane.setPadding(new Insets(2));

        //Déclaration et ajout du currentLabel
        currentLabel = new Label("");
        bottomPane.add(currentLabel,0,0,4,1);

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
        bottomPane.add(c,0,1);
        bottomPane.add(del,1,1);
        bottomPane.add(enter,2,1);

        bottomPane.add(sept,0,2);
        bottomPane.add(huit,1,2);
        bottomPane.add(neuf,2,2);

        bottomPane.add(quatre,0,3);
        bottomPane.add(cinq,1,3);
        bottomPane.add(six,2,3);

        bottomPane.add(un,0,4);
        bottomPane.add(deux,1,4);
        bottomPane.add(trois,2,4);

        bottomPane.add(zero,0,5);
        bottomPane.add(neg,1,5);
        bottomPane.add(coma,2,5);

        bottomPane.add(add,3,1);
        bottomPane.add(sub,3,2);
        bottomPane.add(mult,3,3);
        bottomPane.add(div,3,4);
        bottomPane.add(swap, 3,5);

        //Ajout du listener sur les nombres et la virgule
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

        return bottomPane;
    }

    /**
     * Rempalace les valeurs des labels correspondant aux dernières valeurs de la pile par les valeurs du paramètre data
     * @param data
     */
    @Override
    public void change(List<String> data) {
        value1.setText(data.get(0));
        value2.setText(data.get(1));
        value3.setText(data.get(2));
        value4.setText(data.get(3));
    }

    /**
     * Remplace la valeur du currentLabel par le valeur de accu
     * @param accu
     */
    @Override
    public void change(String accu) {
        currentLabel.setText(accu);
    }

    /**
     * Getteur de currentLabel
     * @return Un String contenant le texte de currentLabel
     */
    public String getCurrentString() {
        return currentLabel.getText();
    }

}
