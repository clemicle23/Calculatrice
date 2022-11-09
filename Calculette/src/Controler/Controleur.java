package Controler;

import Model.Accumulateur;
import View.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class Controleur implements EventHandler<ActionEvent>, PropertyChangeListener {
    private GUI view;
    private Accumulateur accumulateur;
    private int nbOfLastValues;

    public Controleur(Stage primaryStage, String pathCSS){
        view = new GUI(primaryStage, pathCSS);
        accumulateur = new Accumulateur();
        nbOfLastValues = 4;

        List<Button> buttonList = view.getButtonList();
        for (int i = 0; i < buttonList.size();i++) {
            view.createEventHandler(buttonList.get(i),this);
        }
        accumulateur.addPropertyChangeListener("changePile",event ->{
            ArrayList<Float> values = (ArrayList<Float>) event.getNewValue();
            List<String> lastValues = new ArrayList<>();
            for (int i =0; i<values.size(); i++){
                lastValues.add(Float.toString(values.get(i)));
            }
            if (values.size()<nbOfLastValues){
                for (int i=values.size(); i<nbOfLastValues; i++){
                    lastValues.add("");
                }
            }
            view.change(lastValues);



        });
    }

    public void handle(ActionEvent event) {
        String source = (event.getSource()).toString();
        int length = source.length();
        char buttonText = source.charAt(length - 2);
        switch (buttonText){
            case 'E' -> {
                String currentString = view.getCurrentString();
                if (currentString != "") {
                    try{
                        accumulateur.push(currentString);
                        view.change("");
                    }
                    catch(Exception e){
                        throwInvalidFormat();                        
                    }

                }
            }
            case '+' -> {
                String currentString = view.getCurrentString();
                if (currentString != "") {
                    if (accumulateur.isEmpty()){
                        throwInvalidOperation();
                    }
                    else{
                        try{
                            accumulateur.push(currentString);
                            view.change("");
                        }
                        catch(Exception e){
                            throwInvalidFormat();
                        }
                    }
                }
                accumulateur.add();
            }
            case '-' -> {
                String currentString = view.getCurrentString();
                if (currentString != "") {
                    if (accumulateur.isEmpty()){
                        throwInvalidOperation();
                    }
                    else{
                        try{
                            accumulateur.push(currentString);
                            view.change("");
                        }
                        catch(Exception e){
                            throwInvalidFormat();
                        }
                    }
                }
                accumulateur.sub();
            }
            case 'x' -> {
                String currentString = view.getCurrentString();
                if (currentString != "") {
                    if (accumulateur.isEmpty()){
                        throwInvalidOperation();
                    }
                    else{
                        try{
                            accumulateur.push(currentString);
                            view.change("");
                        }
                        catch(Exception e){
                            throwInvalidFormat();
                        }
                    }
                }
                accumulateur.mult();
            }
            case '/' -> {
                String currentString = view.getCurrentString();
                if (currentString != "") {
                    if (accumulateur.isEmpty()){
                        throwInvalidOperation();
                    }
                    else{
                        try{
                            accumulateur.push(currentString);
                            view.change("");
                        }
                        catch(Exception e){
                            throwInvalidFormat();
                        }
                    }
                }
                accumulateur.div();
            }
            case 'l' -> {
                String currentString = view.getCurrentString();
                if (currentString != "") {
                    currentString = currentString.substring(0,currentString.length()-1);
                    view.change(currentString);
                }

            }
            case 'C' -> {
                accumulateur.clear();
                view.change("");
            }
            case 'g' -> {
                String currentString = view.getCurrentString();
                if (currentString!=""){
                    accumulateur.push(currentString);
                }
                accumulateur.neg();
                view.change("");
            }
            case 'p' -> {
                String currentString = view.getCurrentString();
                if (currentString!=""){
                    accumulateur.push(currentString);
                }
                accumulateur.swap();
                view.change("");
            }
        }
    }

    private void throwInvalidOperation() {
    }

    private void throwInvalidFormat() {
    }

    public Accumulateur getAccumulateur() {
            return accumulateur;
        }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //Envoie les changements à l'interface

    }


}
