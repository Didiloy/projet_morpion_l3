import Grille.Case;
import Grille.Grille;

public class Main {
    public static void main(String[]args){
        Grille g = new Grille(5, 5);
        g.playCase(Case.State.ROND, 5, 5);
        g.print();
//        g.printAll();
//        g.printQuint();
        g.printCasesValues();
        g.updateValue(Case.State.ROND);
        g.printCasesValues();
    }
}
