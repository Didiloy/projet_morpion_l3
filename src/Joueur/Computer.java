package Joueur;

import grille.Case;
import grille.Grille;
import grille.Case.State;

public class Computer extends Player {

    public Computer(Grille grid, State sign) {
        super(grid, sign, "Computer");
    }

    public Computer(Grille grid, State sign, String name) {
        super(grid, sign, name);
    }

    public void play() {
        int xMax = 0;
        int yMax = 0;
        Case[][] tabCases = super.getGrid().getTabCases();
        int maxValue = 0;
        for (int x = 0; x < tabCases.length; x++) {
            for (int y = 0; y < tabCases[x].length; y++) {
                if (tabCases[x][y].getValeur() >= maxValue && tabCases[x][y].getJouable()) {
                    xMax = x;
                    yMax = y;
                    maxValue = tabCases[x][y].getValeur();
                }
            }
        }
        super.getGrid().playCase(super.getSign(), xMax, yMax);
    }

}
