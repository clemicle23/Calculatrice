public class Main {
    public static void main(String[] args){
        // TODO Auto-generated method stub

        Accumulateur calc = new Accumulateur();

        calc.setMemoire("2");
        calc.setMemoire("3");
        calc.setMemoire("4");
        calc.add();
        calc.mult();
        String a = calc.getMemoire().pop();

        System.out.println(a);
    }
}
