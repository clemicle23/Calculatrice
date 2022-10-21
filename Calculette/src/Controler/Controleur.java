package Controler;

import View.IView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class Controleur implements EventHandler<ActionEvent>, IView, java.beans.PropertyChangeListener {
    public void handle(ActionEvent event) {
        event.getTarget();
        System.out.println("Hello world !");
    }

    @Override
    public void affiche() {

    }

    @Override
    public void change(List<String> data) {

    }

    @Override
    public void change(String accu) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
