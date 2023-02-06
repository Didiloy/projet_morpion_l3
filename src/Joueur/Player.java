package Joueur;

import common.enums.StateEnum;
import grille.Grille;

public abstract class Player {

    private Grille GRID;
    private boolean superUser = false;
    protected StateEnum SIGN;
    private String name;

    public Player(Grille grid, StateEnum sign, String _name) {
        this.GRID = grid;
        this.SIGN = sign;
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

    public StateEnum getSign() {
        return this.SIGN;
    }

    public void changeSign(){
        this.SIGN = this.SIGN == StateEnum.CROIX ? StateEnum.ROND : StateEnum.CROIX;
    }

    public String getName() {
        return this.name;
    }

}
