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

/**
 * Classe faisant l'intermédiare entre le modèle (Pile, Accupmulateur) et la vue (GUI)
 */
public class Controleur implements EventHandler<ActionEvent>, PropertyChangeListener {
    //Insatnce de la vue
    private GUI view;
    //Insatnce de l'accumalateur
    private Accumulateur accumulateur;

    public Controleur(Stage primaryStage) {
        view = new GUI(primaryStage);
        accumulateur = new Accumulateur();

        //Ajoute à tous les boutons un listener qui est le Controleur lui-même
        List<Button> buttonList = view.getButtonList();
        for (int i = 0; i < buttonList.size(); i++) {
            view.createEventHandler(buttonList.get(i), this);
        }

        //Ajoute à l'emetteur d'accumulateur un listener lié au changement "changePile"
        accumulateur.addPropertyChangeListener("changePile", this);
    }

    /**
     * Ajoute à la pile la valeur contenue dans le label stockant les nombres écrits avec les boutons mais non validés
     * Différentie le ca où on veut effectuer une poération avec deux opérandes et les autres cas
     * @param operation2operandes
     * @return false s'il n'y a pas d'erreur, true s'il y en a au moins une
     */
    public boolean addValue(boolean operation2operandes, boolean notOperandeOperation){
        String currentString = view.getCurrentString();
        boolean error = false;
        //Si la pile n'est pas vide, on tente d'ajouter la valeur à la pile
        if (currentString != "") {
            //Repère si on tente de faire une opération à 2 opérandes alors qu'il n'y a pas suffisamment de valeurs
            if (operation2operandes&&accumulateur.isEmpty()){
                error = true;
                throwInvalidOperation();
            }
            else{
                try{
                    accumulateur.push(currentString);
                    //Envoie le nouveau label à afficher (label vide car la valeur a été ajoutée à la pile
                    view.change("");
                }
                catch(Exception e){
                    error = true;
                    throwInvalidFormat();
                }
            }
        }
        //Si le currentLabel est vide et que l'opération ne nécessite pas d'opérandes, une erreur est levée
        else if (notOperandeOperation){
            error = true;
            throwInvalidOperation();
        }
        //Repère si on tente de faire une opération à 2 opérandes alors qu'il n'y a pas suffisamment de valeurs
        if (operation2operandes&&!accumulateur.hasTwo()){
            error = true;
            throwInvalidOperation();
        }
        return error;
    }

    /**
     * Méthode activé lorsque un bouton est actionné
     * @param event
     */
    public void handle(ActionEvent event) {
        //Réinitialise l'erreur
        view.setErrorLabel("");
        //Identification du bouton activé en réccupérant le dernière lettre du text associé au bouton
        String source = (event.getSource()).toString();
        int length = source.length();
        char buttonText = source.charAt(length - 2);
        //Distinction des actions en fonction du bouton
        switch (buttonText){
            //Bouton Entrée
            //Ajoute à la pile la valeur contenue dans le label stockant les nombres écrits avec les boutons mais non validés
            case 'E' -> {
                addValue(false,true);
            }
            //Bouton addition
            //Ajoute à la pile la valeur dans le label s'il y en a une
            //Puis, additionne les deux dernières valeurs de la pile
            case '+' -> {
                boolean error = addValue(true, false);
                if (!error){
                    accumulateur.add();
                }
            }
            //Bouton soustratction
            //Ajoute à la pile la valeur dans le label s'il y en a une
            //Puis, soustrait les deux dernières valeurs de la pile (ancienne - récente)
            case '-' -> {
                boolean error = addValue(true, false);
                if (!error){
                    accumulateur.sub();
                }
            }
            //Bouton multiplication
            //Ajoute à la pile la valeur dans le label s'il y en a une
            //Puis, multiplie les deux dernières valeurs de la pile
            case 'x' -> {
                boolean error = addValue(true, false);
                if (!error){
                    accumulateur.mult();
                }
            }
            //Bouton division
            //Ajoute à la pile la valeur dans le label s'il y en a une
            //Puis, divise les deux dernières valeurs de la pile (ancienne/récente)
            case '/' -> {
                boolean error = addValue(true, false);
                if (!error){
                    accumulateur.div();
                }
            }
            //Bouton delete
            //Supprime le dernier caratère du label s'il y en a un
            case 'l' -> {
                String currentString = view.getCurrentString();
                if (currentString != "") {
                    currentString = currentString.substring(0,currentString.length()-1);
                    //Envoie le nouveau label avec un caractère en moins
                    view.change(currentString);
                }
                else {
                    throwInvalidOperation();
                }

            }
            //Bouton clear
            //Réinitailise toutes les données
            case 'C' -> {
                //Supprime toutes les valeurs de l'accumulateur
                accumulateur.clear();
                //Envoie le label vide à la vue
                view.change("");
            }
            //Bouton negatif
            //Ajoute à la pile la valeur dans le label s'il y en a une
            //Puis, modifie la dernière valeur de la pile en son négatif
            case 'g' -> {
                boolean error = addValue(false, false);
                if (!error){
                    accumulateur.neg();
                }
            }
            //Bouton swap
            //Ajoute à la pile la valeur dans le label s'il y en a une
            //Puis, intervertit les deux dernières valeurs de la pile
            case 'p' -> {
                boolean error = addValue(true, false);
                if (!error){
                    accumulateur.swap();
                }
            }
        }
    }

    /**
     * Est appelée lorsque une opération impossible est effectuée
     */
    private void throwInvalidOperation() {
        view.setErrorLabel("Opération impossible");
    }

    /**
     * Est appelée lorsque on tente d'ajouter à la pile une valeur possédant un format incomapatible avec float
     */
    private void throwInvalidFormat() {
        view.setErrorLabel("Format numérique non valide");
    }

    /**
     * Méthode appelée lorsque un changement de propriété de la pile est oppéré
     * @param event
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        ArrayList<Float> values = (ArrayList<Float>) event.getNewValue();
        List<String> lastValues = new ArrayList<>();
        //Crée une ArrayList avec les 4 dernières de la pile (ou moins si elle en possède moins)
        for (int i =0; i<values.size(); i++){
            lastValues.add(Float.toString(values.get(i)));
        }
        //Si la pile a moins de 4 valeurs, pour les valeurs manquante on ajoute un String vide à l'ArrayList
        if (values.size()<4){
            for (int i=values.size(); i<4; i++){
                lastValues.add("");
            }
        }
        //On envoie un ArrayList de String contanant les 4 nouvelles dernières de la pile à la vue
        view.change(lastValues);
    }
}
