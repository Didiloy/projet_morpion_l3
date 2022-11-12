package Joueur;

import common.enums.StateEnum;
import grille.Grille;
import rungame.PlayGame;

import java.util.Objects;
import java.util.Scanner;

public abstract class Player {

    private Grille GRID;
    private boolean superUser = false;
    protected final StateEnum SIGN;
    private String name;

    public Player(Grille grid, StateEnum sign, String _name) {
        this.GRID = grid;
        this.SIGN = sign;
        if (Objects.equals(_name, "Human")) {
            System.out.print("Would you change your name ? (Answer by yes or no) : ");
            String answer = PlayGame.input.nextLine();
            System.out.println(); // ATTENTION TEST
            if ((answer.compareToIgnoreCase("yes")) == 0) {
                System.out.println("Write your new name :");
                answer = PlayGame.input.nextLine();
                this.name = answer;
            } else {
                this.name = _name;
            }
        } else {
            this.name = _name;
        }
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

    public String getName() {
        return this.name;
    }

}
