package Joueur;

import java.util.Scanner;
import grille.Case;
import grille.Grille;
import grille.Case.State;

public class RealPlayer extends Player {

    public RealPlayer(Grille grid, State sign) {
        super(grid, sign, "Human");
    }

    public RealPlayer(Grille grid, State sign, String name) {
        super(grid, sign, name);
    }

    public void play() {
        Case[][] tabCases = super.getGrid().getTabCases();
        Scanner input = new Scanner(System.in);
        System.out.println("Choose an X position : ");
        int x = input.nextInt();
        System.out.println("Choose an Y position : ");
        int y = input.nextInt();

        if (tabCases[x][y].getJouable()) {
            super.getGrid().playCase(this.getSign(), x, y);
            input.close();
        } else {
            this.play();
        }

    }

}
