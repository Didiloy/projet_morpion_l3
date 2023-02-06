package Joueur;

import common.enums.StateEnum;
import grille.Case;
import grille.Coordinates;
import grille.Grille;
import rungame.PlayGame;


public class RealPlayer extends Player {

    public RealPlayer(Grille grid, StateEnum sign) {
        super(grid, sign, "Human");
    }

    public RealPlayer(Grille grid, StateEnum sign, String name) {
        super(grid, sign, name);
    }

    /**
     * Get the player input to play on the case he chooses
     */
    public void play(){
        //Call the private play methode
        play("");
    }
    private void play(String msg) {
        if(msg.equals("Implayable case. Choose another case")) System.out.println(msg);
        Case[][] tabCases = super.getGrid().getTabCases();
        int x = get_value_play("X", tabCases.length - 10);
        int y = get_value_play("Y", tabCases[0].length - 10);

        if (tabCases[y+4][x+4].getJouable()) {
            super.getGrid().playCase(this.getSign(), y + 4, x + 4);
            super.getGrid().addMove(new Coordinates(y+4, x+4, this.getSign()));
//            super.getGrid().updateValue(this.SIGN);
        } else {
            this.play("Implayable case. Choose another case");
        }
    }

    private int get_value_play(String axe, int max)
    {
        int value = -1;
        while (value == -1)
        {
            System.out.println("Choose an " + axe + " position : ");
            String line = PlayGame.input.nextLine();
            try {
                value = Integer.parseInt(line);
            }
            catch (NumberFormatException e)
            {
                System.out.println("The " + axe + " should be an integer");
                value = -1;
                continue;
            }
            if (value < 1 || value > max)
            {
                System.out.println("The " + axe + " should be between " + 1 + " and " + max);
                value = -1;
            }
        }
        return value;
    }
}
