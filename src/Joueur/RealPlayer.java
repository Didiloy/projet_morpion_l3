package Joueur;

import common.enums.StateEnum;
import grille.Case;
import grille.Grille;
import rungame.PlayGame;

import java.util.Scanner;


public class RealPlayer extends Player {

    public RealPlayer(Grille grid, StateEnum sign) {
        super(grid, sign, "Human");
    }

    public RealPlayer(Grille grid, StateEnum sign, String name) {
        super(grid, sign, name);
    }

    /**
     * Get the player input to play on the case he choose
     */
    public void play(){
        //Call the private play methode
        play("");
    }
    private void play(String msg) {
        if(msg.equals("Implayable case. Choose another case")) System.out.println(msg);
        Case[][] tabCases = super.getGrid().getTabCases();
        System.out.println("Choose an X position : ");
        int x = PlayGame.input.nextInt();
        System.out.println("Choose an Y position : ");
        int y = PlayGame.input.nextInt();

        if (tabCases[x+4][y+4].getJouable()) {
            super.getGrid().playCase(this.getSign(), x + 4, y + 4);
        } else {
            this.play("Implayable case. Choose another case");
        }

    }

}
