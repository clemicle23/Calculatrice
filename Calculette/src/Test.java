import Model.Accumulateur;

public class Test {
    public static void main(String[] args){
        // TODO Auto-generated method stub

        Accumulateur calc = new Accumulateur();

        calc.setMemoire("16");
        calc.setMemoire("3");
        calc.setMemoire("5");

        calc.add();
        calc.swap();
        calc.sub();
        calc.neg();
        String a = calc.getMemoire().pop();

        System.out.println(a);
    }
}
