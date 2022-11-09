package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Gère la pile des nombres en atttente et effectue les opérations.
 */
public class Accumulateur {
    //La mémoire est une Pile contenant les valeurs en attentes
    private Pile memoire = new Pile();
    private PropertyChangeSupport emetteur;


    /**
     * Constructeur de Accumulateur
     * Initialise l'émetteur de type PropertyChangeSupport, qui va envoyer des informations au Controleur lorsque la mémoire (Pile) est modifiée
     */
    public Accumulateur() {
        emetteur = new PropertyChangeSupport(this);
    }

    /**
     *Ajoute d'un listener à l'émetteur.
     * @param propertyName Nom indiquant quel type de changement est opéré. Ici, il n'y en a qu'un seul : "changePile".
     * @param listener Celui qui reçoit les informations lorsqu'un changement est détecté.
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener){
        emetteur.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Lorsque la méthode est appelée, l'émetteur envoie les anciennes et nouvelles valeurs de la pile.
     * Le nom du type de changement est "changePile" par défaut.
     * @param oldValues Anciennes denrières valeurs de la pile.
     * @param newValues Nouvelles dernières valeurs de la pile.
     */
    public void changeAccu(ArrayList<Float> oldValues, ArrayList<Float> newValues){
        emetteur.firePropertyChange("changePile",oldValues, newValues);
    }

    /**
     *
     * @return La dernière valeur (String) de mémoire sans la supprimer ni la modifier si elle existe. Sinon renvoie un String vide.
     */
    public String getLast(){
        if (!memoire.isEmpty()){
            String text = memoire.pop();
            memoire.push(text);
            return(text);
        }
        else{
            return("");
        }
    }

    /**
     * @param n Nombre de valeurs que l'on veut
     * @return Une ArrayList<Float></> contenant les n dernières valeurs de la mémoire sans les supprimer ni les modifier si elles existent. Sinon, renvoie une ArrayList vide
     */
    public ArrayList<Float> nLastValues(int n) {
        ArrayList<Float> lastValues = new ArrayList<>();

        //On parcourt la
        for (int i=0;i<n;i++){
            String lastValue = getLast();
            //Si
            if (lastValue == "")
                break;
            else

                lastValues.add(Float.parseFloat(memoire.pop()));
        }
        for (int i=lastValues.size()-1; i>=0; i--){
            memoire.push(Float.toString(lastValues.get(i)));
        }

        return lastValues;
    }

    public void push(String elem) {
        ArrayList<Float> oldValues = nLastValues(4);
        memoire.push(elem);
        ArrayList<Float> newValues = nLastValues(4);
        changeAccu(oldValues,newValues);
    }

    public void drop(){
        memoire.drop();

    }

    public void clear(){
        ArrayList<Float> oldValues = nLastValues(4);
        memoire.clear();
        ArrayList<Float> newValues = nLastValues(4);
        changeAccu(oldValues,newValues);
    }

    public void swap() {
        if (hasTwo()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            float x2= oldValues.get(1);
            drop();
            drop();
            memoire.push(Float.toString(x1));
            memoire.push(Float.toString(x2));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }


    public void add() {
        if (hasTwo()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            float x2= oldValues.get(1);
            drop();
            drop();
            memoire.push(Float.toString(x1+x2));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }

    public void sub() {
        if(hasTwo()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            float x2= oldValues.get(1);
            drop();
            drop();
            memoire.push(Float.toString(x2-x1));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }

    public void mult() {
        if (hasTwo()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            float x2= oldValues.get(1);
            drop();
            drop();
            memoire.push(Float.toString(x1*x2));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }

    public void div() {
        if (hasTwo()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            float x2= oldValues.get(1);
            drop();
            drop();
            memoire.push(Float.toString(x2/x1));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }

    public void neg() {
        if (!memoire.isEmpty()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            drop();
            memoire.push(Float.toString(-x1));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }

    public boolean hasTwo(){
        if  (!memoire.isEmpty()){
            String x1 = memoire.pop();
            if  (!memoire.isEmpty()){
                memoire.push(x1);
                return true;
            }
            memoire.push(x1);
            return false;
        }
        return false;
    }

    public boolean isEmpty(){
        return memoire.isEmpty();
    }



}
