package Joueur;

import common.enums.StateEnum;
import grille.Grille;

import java.util.Objects;
import java.util.Scanner;

public abstract class Player {

    private Grille GRID;
    private boolean superUser = false;
    private final StateEnum SIGN;
    private String name;

    public Player(Grille grid, StateEnum sign, String _name) {
        this.GRID = grid;
        this.SIGN = sign;
        if (Objects.equals(_name, "Human")) {
            Scanner input = new Scanner(System.in);
            System.out.print("Would you change your name ? (Answer by yes or no) : ");
            String answer = input.nextLine();
            System.out.println(); // ATTENTION TEST
            if ((answer.compareToIgnoreCase("yes")) == 0) {
                System.out.println("Write your new name :");
                answer = input.nextLine();
                this.name = answer;
                input.close();
            } else {
                this.name = _name;
                input.close();
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
