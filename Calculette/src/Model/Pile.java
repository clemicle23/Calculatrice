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
     *Supprime le dernier élément de la pile
     */
    public void drop() {
        this.pop();
    }

    /**
     * Supprime tous les éléments de la pile
     */
    public void clear(){
        while (!this.isEmpty()){
            this.drop();
        }
    }

}
