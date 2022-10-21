package Joueur;

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
        // TODO

    }

}
