package Joueur;

<<<<<<< HEAD
import common.enums.StateEnum;
import grille.Case;
import grille.Grille;

import java.util.Scanner;
=======
import java.util.Scanner;
import Grille.Case;
import Grille.Grille;
import Grille.Case.State;
>>>>>>> 25a96053ac762b4ef408b447e0b6dfc403f9cf54

public class RealPlayer extends Player {

    public RealPlayer(Grille grid, StateEnum sign) {
        super(grid, sign, "Human");
    }

    public RealPlayer(Grille grid, StateEnum sign, String name) {
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
