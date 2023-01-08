package rungame;


import Joueur.Computer;
import Joueur.Player;
import Joueur.RealPlayer;
import common.enums.ANSIColor;
import common.enums.StateEnum;
import grille.Coordinates;
import grille.Grille;

import java.util.Random;
import java.util.Scanner;

public class PlayGame {

    private final Player computer;
    private final Player realPlayer;
    private final Grille GRID;
    private int round;
    private int firstPlayer;

    public static Scanner input = new Scanner(System.in);

    // Initialise la partie
    public PlayGame() {
        // Choisis la taille de la grille
        int x = get_size_grid("width");
        int y = get_size_grid("height");

        // Choisis le signe du joueur
        System.out.println("Choose a sign (x or o) :");
        String sign = input.nextLine();

        StateEnum signPlayer;
        StateEnum signComputer;

        // Demande au joueur un signe valide
        while (!sign.equals("x") && !sign.equals("o")) {
            System.out.println("You can only choose x and o as signs: ");
            sign = input.nextLine();
        }

        if ((sign.compareToIgnoreCase("x")) == 0) {
            signPlayer = StateEnum.CROIX;
            signComputer = StateEnum.ROND;
        } else {
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

    private int get_size_grid(String toget) {
        int value = -1;
        while (value == -1) {
            System.out.println("Choose " + toget + " of the grid: ");
            String line = input.nextLine();
            try {
                value = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                value = -1;
                System.out.println("The " + toget + " has to be an integer greater than 5");
                continue;
            }
            if (value < 5) {
                value = -1;
                System.out.println("The " + toget + " can't be less than 5");
            } else if (value > 100) {
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
        return generateRandomNumber(0, 3);

    }

    private boolean parseSignAnswer() {
        String res = input.nextLine();
        while (!res.equalsIgnoreCase("YES") && !res.equalsIgnoreCase("NO")) {
            System.out.println("you must only answer with yes or no");
            res = input.nextLine();
        }
        return res.equalsIgnoreCase("YES");
    }

    private int parseOrderAnswer(){
        int res = this.round + 1;
        while (res > this.round) {
            System.out.println("you must only enter an integer smaller than the number of round");
            res = input.nextInt();
        }
        return res;
    }

    private void goBack(int order) {
        int taille = this.GRID.movesList.size();
        for (int i = taille; i > taille - order; i--) {
            Coordinates c = this.GRID.movesList.get(i - 1);
            this.GRID._tabCases[c.getX()][c.getY()].setState(StateEnum.VIDE);
            this.GRID.movesList.remove(i - 1);
            this.round--;
        }
    }

    public void nextRound() {
        this.GRID.print();
        this.round++;
        System.out.println("Round " + this.round);
        if (this.round > 1) {
            System.out.println("Do you want to go back in the moves list ?");
            if (this.parseSignAnswer()) {
                System.out.println("how much time do you want to go back to");
                int order = this.parseOrderAnswer();
                this.goBack(order);
                this.GRID.print();
            }
        }

        if (this.firstPlayer > 0) {
            System.out.println("Do you want to change sign with the computer ?");
            if (this.parseSignAnswer()) {
                this.realPlayer.changeSign();
                this.computer.changeSign();
            }

            System.out.println("It's your turn to play\n");
            this.realPlayer.play();
            this.GRID.print();
            StateEnum symboleGagnant = this.GRID.getStateQuintupletComplet();
            if (symboleGagnant != null) return;
//            this.GRID.printCasesValues();
//            this.GRID.printQuint();
            System.out.println("The computer play.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            this.computer.play();
//            this.GRID.printQuint();
//            this.GRID.printCasesValues();
        } else {
            System.out.println("The computer play.");
//            this.GRID.printQuint();
//            this.GRID.printCasesValues();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            this.computer.play();
//            this.GRID.printQuint();
//            this.GRID.printCasesValues();
            this.GRID.print();
            StateEnum symboleGagnant = this.GRID.getStateQuintupletComplet();
            if (symboleGagnant != null) return;

//            this.GRID.print();
//            this.GRID.printAll();
//            this.GRID.printCasesJouable();
            System.out.println("Do you want to change sign with the computer ?");
            if (this.parseSignAnswer()) {
                this.realPlayer.changeSign();
                this.computer.changeSign();
            }
            System.out.println("It's your turn to play");
            this.realPlayer.play();
        }
    }

    /**
     * check win
     **/
    public boolean checkWin() {
        StateEnum symboleGagnant = this.GRID.getStateQuintupletComplet();
        if (symboleGagnant != null) {
            if (symboleGagnant == StateEnum.VIDE) {
                System.out.println(ANSIColor.ANSI_PURPLE + "It's a draw !" + ANSIColor.ANSI_RESET);
            } else if (symboleGagnant.equals(this.computer.getSign())) {
                System.out.println(ANSIColor.ANSI_YELLOW + "The computer win !" + ANSIColor.ANSI_RESET);
            } else {
                System.out.println(ANSIColor.ANSI_GREEN + "You win !" + ANSIColor.ANSI_RESET);
            }
            input.close();
            return true;
        }
        return false;

    }
}
