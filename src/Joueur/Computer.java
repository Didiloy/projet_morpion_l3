package Joueur;

import common.enums.StateEnum;
import grille.Coordinates;
import grille.Grille;

public class Computer extends Player {

    public Computer(Grille grid, StateEnum sign) {
        super(grid, sign, "Computer");
    }

    public Computer(Grille grid, StateEnum sign, String name) {
        super(grid, sign, name);
    }

    public void play() {
        super.getGrid().updateValue(this.SIGN);
//        super.getGrid().printCasesValues();
        int[] maxValue = this.getGrid().getMaxValue();
        super.getGrid().playCase(super.getSign(), maxValue[0], maxValue[1]);
        super.getGrid().addMove(new Coordinates(maxValue[0], maxValue[1], this.getSign()));
        super.getGrid().updateValue(this.SIGN); //utilisé pour les test
//        super.getGrid().printCasesValues();

    }

}
