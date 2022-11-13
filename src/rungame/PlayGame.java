package rungame;


import Joueur.Computer;
import Joueur.Player;
import Joueur.RealPlayer;
import common.enums.StateEnum;
import grille.Grille;

import java.util.Random;
import java.util.Scanner;

public class PlayGame {

    private Player computer, realPlayer;
    private Grille GRID;
    private int round;
    private int firstPlayer;

    public static Scanner input = new Scanner(System.in);

    public PlayGame(){
        // Choisis la taille de la grille
        int x = get_size_grid("width");
        int y = get_size_grid("height");

        // Choisis le signe du joueur
        System.out.println("Choose a sign (x or o) :");
        String sign = input.nextLine();

        StateEnum signPlayer;
        StateEnum signComputer;

        // Demande au joueur un signe valide
        while ( !sign.equals("x") && !sign.equals("o")){
            System.out.println("You can only choose x and o as signs: ");
            sign = input.nextLine();
        }

        if ((sign.compareToIgnoreCase("x")) == 0 ){
            signPlayer = StateEnum.CROIX;
            signComputer = StateEnum.ROND;
        }else{
            signPlayer = StateEnum.ROND;
            signComputer = StateEnum.CROIX;
        }

        // initialisation de la grille, des joueurs, des tours et du premier joueur
        this.GRID = new Grille(x, y);
        this.realPlayer = new RealPlayer(this.GRID, signPlayer);
        this.computer = new Computer(this.GRID, signComputer);
        this.round = 0;
        this.firstPlayer = this.whoGoesFirst();
        System.out.println(this.firstPlayer == 0 ? "The computer start this game." : "You start this game.");
    }

    private int get_size_grid(String toget){
        int value = -1;
        while (value == -1)
        {
            System.out.println("Choose " + toget + " of the grid: ");
            String line = input.nextLine();
            try {
                value = Integer.parseInt(line);
            }
            catch (NumberFormatException e) {
                value = -1;
                System.out.println("The " + toget + " has to be an integer greater than 5");
                continue;
            }
            if (value < 5) {
                value = -1;
                System.out.println("The " + toget + " can't be less than 5");
            }
            else if (value > 100) {
                value = -1;
                System.out.println("The " + toget + " can't be greater than 100");
            }
        }
        return value;
    }

    /**
     * Get random number
     *
     * @param min
     * @param max
     * @return {@link int}
     **/
    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * choose the player who goes first
     **/

    private int whoGoesFirst() {
        int randomNumber = generateRandomNumber(0, 1);
        return randomNumber;

    }

    public void nextRound(){
        this.round ++;
        System.out.println("Round " + this.round);

        if(this.firstPlayer == 1) {
            this.GRID.print();
            System.out.println("It's your turn to play");
            this.realPlayer.play();
//            this.GRID.printAll();
//            this.GRID.printCasesJouable();
//            this.GRID.printCasesValues();
            System.out.println("The computer play.");
            this.computer.play();
        }else{
            System.out.println("The computer play.");
            this.computer.play();
            this.GRID.print();
//            this.GRID.printAll();
//            this.GRID.printCasesJouable();
//            this.GRID.printCasesValues();
            System.out.println("It's your turn to play");
            this.realPlayer.play();
        }
    }

    /**
     * check win
     **/
    public boolean checkWin(){
        StateEnum symboleGagnant = this.GRID.getStateQuintupletComplet();
        if( symboleGagnant != null){
            if (symboleGagnant.equals(this.computer.getSign())){
                System.out.println("The computer win !");
            }else{
                System.out.println("You win !");
            }
            input.close();
            return true;
        }
        return false;

    }
}
