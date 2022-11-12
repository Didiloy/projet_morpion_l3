package Joueur;

<<<<<<< HEAD
import common.enums.StateEnum;
import grille.Grille;

import java.util.Scanner;
=======
import java.util.Scanner;

import Grille.Grille;
import Grille.Case.State;
>>>>>>> 25a96053ac762b4ef408b447e0b6dfc403f9cf54

public abstract class Player {

    private Grille GRID;
    private boolean superUser = false;
    private final StateEnum SIGN;
    private String name;

    public Player(Grille grid, StateEnum sign, String _name) {
        this.GRID = grid;
        this.SIGN = sign;
        if (_name == "Human") {
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
