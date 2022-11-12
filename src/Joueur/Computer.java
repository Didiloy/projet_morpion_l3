package Joueur;

<<<<<<< HEAD
import common.enums.StateEnum;
import grille.Case;
import grille.Grille;
=======
import Grille.Case;
import Grille.Grille;
import Grille.Case.State;
>>>>>>> 25a96053ac762b4ef408b447e0b6dfc403f9cf54

public class Computer extends Player {

    public Computer(Grille grid, StateEnum sign) {
        super(grid, sign, "Computer");
    }

    public Computer(Grille grid, StateEnum sign, String name) {
        super(grid, sign, name);
    }

    public void play() {
        int[] maxValue = this.getGrid().getMaxValue();
        super.getGrid().playCase(super.getSign(), maxValue[0], maxValue[1]);
    }

}
