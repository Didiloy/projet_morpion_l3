package Joueur;

import common.enums.StateEnum;
import grille.Grille;

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
