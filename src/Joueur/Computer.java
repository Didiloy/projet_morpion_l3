package Joueur;

import Grille.Case;
import Grille.Grille;
import Grille.Case.State;

public class Computer extends Player {

    public Computer(Grille grid, State sign) {
        super(grid, sign, "Computer");
    }

    public Computer(Grille grid, State sign, String name) {
        super(grid, sign, name);
    }

    public void play() {
        int[] maxValue = this.getGrid().getMaxValue();
        super.getGrid().playCase(super.getSign(), maxValue[0], maxValue[1]);
    }

}
