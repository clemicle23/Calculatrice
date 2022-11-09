package Model;

import java.util.Stack;

/**
 * Classe héritant de Stack. Permet de représenter une Pile d'éléments String
 */
public class Pile extends Stack<String>{
    public Pile(){
        super();
    }

    /**
     * Méthode supprimant le dernier élément de la pile
     */
    public void drop() {
        this.pop();
    }

    /**
     * Méthode supprimant tous les éléments de la pile
     */
    public void clear(){
        while (!this.isEmpty()){
            this.drop();
        }
    }

}
