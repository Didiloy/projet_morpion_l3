import Grille.Grille;
import Joueur.Computer;
import Joueur.RealPlayer;
import Grille.Case;
import Grille.Case.State;

public class Main {
    public static void main(String[] args) {
        Grille g = new Grille(5, 5);
        g.playCase(Case.State.ROND, 5, 5);
        g.print();
        // g.printAll();
        // g.printQuint();
        g.printCasesValues();
        g.updateValue(Case.State.ROND);
        g.printCasesValues();

        RealPlayer p1 = new RealPlayer(g, State.ROND);
        Computer c1 = new Computer(g, State.CROIX);
        System.out.println(p1.toString());
        System.out.println(c1.toString());
    }
}
