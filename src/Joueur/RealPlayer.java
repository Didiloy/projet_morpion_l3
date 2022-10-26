package Joueur;

import java.util.Scanner;
import Grille.Case;
import Grille.Grille;
import Grille.Case.State;

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
            super.getGrid().playCase(this.getSign(), x + 4, y + 4);
            input.close();
        } else {
            this.play();
        }

    }

}
