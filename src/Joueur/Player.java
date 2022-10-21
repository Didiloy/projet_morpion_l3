package Joueur;

import grille.Grille;
import grille.Case.State;

public abstract class Player {

    private Grille GRID;
    private boolean superUser = false;
    private final State SIGN;
    private String name;

    public Player(Grille grid, State sign) {
        this.GRID = grid;
        this.SIGN = sign;
    }

    public Player(Grille grid, State sign, String _name) {
        this.GRID = grid;
        this.SIGN = sign;
        this.name = _name;
    }

    public abstract void play();

    @Override
    public String toString() {
        return "[ Name : " + this.name + " | Sign : " + this.SIGN + " | SuperUser ? : " + this.superUser + " ]";
    }

    public Grille getGrid() {
        return this.GRID;
    }

    public boolean isSuperUser() {
        return this.superUser;
    }

    public State getSign() {
        return this.SIGN;
    }

    public String getName() {
        return this.name;
    }

}
