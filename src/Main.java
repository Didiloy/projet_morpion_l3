import common.enums.StateEnum;
import grille.Grille;
import Joueur.Computer;
import Joueur.RealPlayer;
import grille.Case;
import rungame.PlayGame;

public class Main {
    public static void main(String[] args) {
        Grille g = new Grille(5, 5);
        Computer c1 = new Computer(g, StateEnum.CROIX);
        RealPlayer p1 = new RealPlayer(g, StateEnum.ROND);
        PlayGame.start(g, c1, p1);
//        g.playCase(StateEnum.ROND, 5, 5);
//        g.print();
        // g.printAll();
        // g.printQuint();
//        g.printCasesValues();
//        g.updateValue(StateEnum.ROND);
//        g.printCasesValues();

    }
}
