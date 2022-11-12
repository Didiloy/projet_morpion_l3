import common.enums.StateEnum;
import grille.Grille;
import Joueur.Computer;
import Joueur.RealPlayer;
import grille.Case;

public class Main {
    public static void main(String[] args) {
        Grille g = new Grille(5, 5);
        g.playCase(StateEnum.ROND, 5, 5);
        g.print();
        // g.printAll();
        // g.printQuint();
        g.printCasesValues();
        g.updateValue(StateEnum.ROND);
        g.printCasesValues();

        RealPlayer p1 = new RealPlayer(g, StateEnum.ROND);
        Computer c1 = new Computer(g, StateEnum.CROIX);
        System.out.println(p1.toString());
        System.out.println(c1.toString());
    }
}
