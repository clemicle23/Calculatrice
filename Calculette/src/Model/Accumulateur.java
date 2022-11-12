package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Classe gèrant la pile(memoire) des nombres en attente et effectuant les opérations.
 */
public class Accumulateur {
    //La memoire est une Pile contenant les valeurs en attentes
    private Pile memoire = new Pile();
    //emetteur va permettre d'envoyer les changements les informations changeantes dan memoire au Controleur
    private PropertyChangeSupport emetteur;


    /**
     * Constructeur de Accumulateur
     * Initialise l'émetteur de type PropertyChangeSupport, qui va envoyer des informations au Controleur lorsque la memoire (Pile) est modifiée
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
     * Lorsque la méthode est appelée, l'émetteur envoie les anciennes et nouvelles valeurs de la memoire.
     * Le nom du type de changement est "changePile" par défaut.
     * @param oldValues Anciennes denrières valeurs de la pile.
     * @param newValues Nouvelles dernières valeurs de la pile.
     */
    public void changeAccu(ArrayList<Float> oldValues, ArrayList<Float> newValues){
        emetteur.firePropertyChange("changePile",oldValues, newValues);
    }

    /**
     * @return La dernière valeur (String) de la mémoire sans la supprimer ni la modifier si elle existe. Sinon renvoie un String vide.
     */
    public String getLast(){
        //Si la memoire n'est pas vide, on récupère la dernière valeur de la memoire, on la réinjecte au dessus de la pile et on la retourne
        if (!memoire.isEmpty()){
            String text = memoire.pop();
            memoire.push(text);
            return(text);
        }
        //Si la memoire est vide, on renvoie un String vide
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

        //On parcourt la pile(memoire) en partant du dessus. On récupère chacune des n dernières valeurs si elles existent.
        // Dès que la memoire est vide, on interrompt la boucle
        for (int i=0;i<n;i++){
            String lastValue = getLast();
            if (lastValue == "")
                break;
            else

                lastValues.add(Float.parseFloat(memoire.pop()));
        }
        //On réinjecte les valeurs récupérée en les remettant dasn l'ordre initial.
        for (int i=lastValues.size()-1; i>=0; i--){
            memoire.push(Float.toString(lastValues.get(i)));
        }

        return lastValues;
    }

    /**
     *Permet de savoir s'il y a au moins une valeur dans la pile(memoire)
     * @return true si la pile(memoire) est vide, false sinon
     */
    public boolean isEmpty(){
        return memoire.isEmpty();
    }

    /**
     *Permet de savoir s'il y a au moins deux valeurs dans la pile(memoire)
     * @return true s'il y a au moins deux valeurs dans la pile(memoire), false sinon
     */
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

    /**
     * Ajoute le paramètre elem au-dessus de la pile(memoire)
     * Envoie une information de changement de propriété pour memoire
     * @param elem
     */
    public void push(String elem) {
        ArrayList<Float> oldValues = nLastValues(4);
        memoire.push(elem);
        ArrayList<Float> newValues = nLastValues(4);
        changeAccu(oldValues,newValues);
    }

    /**
     * Supprime le dernier élément de la pile(memoire)
     * Envoie une information de changement de propriété pour memoire
     */
    public void drop(){
        ArrayList<Float> oldValues = nLastValues(4);
        memoire.drop();
        ArrayList<Float> newValues = nLastValues(4);
        changeAccu(oldValues,newValues);
    }

    /**
     * Supprime tous les valeurs de la pile(memoire)
     * Envoie une information de changement de propriété pour memoire
     */
    public void clear(){
        ArrayList<Float> oldValues = nLastValues(4);
        memoire.clear();
        ArrayList<Float> newValues = nLastValues(4);
        changeAccu(oldValues,newValues);
    }

    /**
     * Intervertit les deux dernières valeurs de la pile(mémoire)
     * Envoie une information de changement de propriété pour memoire
     */
    public void swap() {
        //On vérifie qu'il y a au moins deux valeurs dans la pile(memoire)
        if (hasTwo()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            float x2= oldValues.get(1);
            //On supprime les 2 dernières valeurs qui viennement d'être récupérées
            drop();
            drop();
            memoire.push(Float.toString(x1));
            memoire.push(Float.toString(x2));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }

    /**
     * Récupère les deux dernières valeurs de la pile(memoire) et réinjecte leur somme au-dessus de la pile(memoire)
     * Envoie une information de changement de propriété pour memoire
     */
    public void add() {
        //On vérifie qu'il y a au moins deux valeurs dans la pile(memoire)
        if (hasTwo()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            float x2= oldValues.get(1);
            //On supprime les 2 dernières valeurs qui viennement d'être récupérées
            drop();
            drop();
            memoire.push(Float.toString(x1+x2));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }

    /**
     * Récupère les deux dernières valeurs de la pile(memoire) et réinjecte leur différence(plus vieille - plus récente) au-dessus de la pile(memoire)
     * Envoie une information de changement de propriété pour memoire
     * */
    public void sub() {
        //On vérifie qu'il y a au moins deux valeurs dans la pile(memoire)
        if(hasTwo()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            float x2= oldValues.get(1);
            //On supprime les 2 dernières valeurs qui viennement d'être récupérées
            drop();
            drop();
            memoire.push(Float.toString(x2-x1));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }

    /**
     * Récupère les deux dernières valeurs de la pile(memoire) et réinjecte leur produit au-dessus de la pile(memoire)
     * Envoie une information de changement de propriété pour memoire
     */
    public void mult() {
        //On vérifie qu'il y a au moins deux valeurs dans la pile(memoire)
        if (hasTwo()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            float x2= oldValues.get(1);
            //On supprime les 2 dernières valeurs qui viennement d'être récupérées
            drop();
            drop();
            memoire.push(Float.toString(x1*x2));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }

    /**
     * Récupère les deux dernières valeurs de la pile(memoire) et réinjecte leur division (plus vieille/plus récente) au-dessus de la pile(memoire)
     * Envoie une information de changement de propriété pour memoire
     */
    public void div() {
        //On vérifie qu'il y a au moins deux valeurs dans la pile(memoire)
        if (hasTwo()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            float x2= oldValues.get(1);
            //On supprime les 2 dernières valeurs qui viennement d'être récupérées
            drop();
            drop();
            memoire.push(Float.toString(x2/x1));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }

    /**
     * Récupère la dernière valeur de la pile(memoire) et réinjecte son opposé au-dessus de la pile(memoire)
     * Envoie une information de changement de propriété pour memoire
     */
    public void neg() {
        //On vérifie qu'il y a au moins une valeur dans la pile(memoire)
        if (!memoire.isEmpty()){
            ArrayList<Float> oldValues = nLastValues(4);
            float x1= oldValues.get(0);
            //On supprime la dernière valeur qui viennt d'être récupérée
            drop();
            memoire.push(Float.toString(-x1));
            ArrayList<Float> newValues = nLastValues(4);
            changeAccu(oldValues,newValues);
        }
    }
}
