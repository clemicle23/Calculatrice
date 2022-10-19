package Model;

public class Accumulateur {
    Pile memoire = new Pile();

    public Accumulateur() {
    }

    public int[] deuxDerVal() {

        int[] tab = new int[2];

        String X1 = (String) memoire.pop();
        String X2 = (String) memoire.pop();

        int x1 = Integer.parseInt(X1);
        int x2 = Integer.parseInt(X2);

        tab[0] = x1;
        tab[1] = x2;

        return tab;
    }

    public void swap() {
        int[] vals = deuxDerVal();
        int x1= vals[0];
        int x2= vals[1];
        memoire.push(Integer.toString(x1));
        memoire.push(Integer.toString(x2));
    }


    public void add() {
        int[] vals = deuxDerVal();
        int x1= vals[0];
        int x2= vals[1];
        memoire.push(Integer.toString(x1+x2));
    }

    public void sub() {
        int[] vals = deuxDerVal();
        int x1= vals[0];
        int x2= vals[1];
        memoire.push(Integer.toString(x1-x2));
    }

    public void mult() {
        int[] vals = deuxDerVal();
        int x1= vals[0];
        int x2= vals[1];
        memoire.push(Integer.toString(x1*x2));
    }

    public void div() {
        int[] vals = deuxDerVal();
        int x1= vals[0];
        int x2= vals[1];
        memoire.push(Integer.toString(x1/x2));
    }

    public void neg() {
        String X1 = (String) memoire.pop();
        int x1 = Integer.parseInt(X1);
        memoire.push(Integer.toString(-x1));
    }

    public void setMemoire(String elem) {
        memoire.push(elem);
    }

    public Pile getMemoire() {
        return(memoire);
    }

}
