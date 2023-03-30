package rungame;


import Joueur.Computer;
import Joueur.Player;
import Joueur.RealPlayer;
import Vue.Board;
import common.enums.ANSIColor;
import common.enums.StateEnum;
import grille.Coordinates;
import grille.Grille;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class PlayGame {

    private final Player computer;
    private final Player realPlayer;
    private final Grille GRID;
    private int round;

    public int winner; //0 for draw - 1 for computer - 2 for player
    private final boolean computerVsComputer;
    private final int firstPlayer;

    private boolean skipSleep = false;

    private boolean save = false;
    private Board board;

    public static Scanner input = new Scanner(System.in);

    // Initialise la partie
    public PlayGame() {
        System.out.println("Do you want to simulate a game with two computers ? (y or n)");
        String answer = input.nextLine();

        // Demande au joueur une réponse
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("You can must answer by y or n");
            answer = input.nextLine();
        }
        this.computerVsComputer = answer.equals("y");
        if (this.computerVsComputer) {
            System.out.println("Do you want to skip the unnecessary time between plays ? (y or n)");
            answer = input.nextLine();

            // Demande au joueur une réponse
            while (!answer.equals("y") && !answer.equals("n")) {
                System.out.println("You can must answer by y or n");
                answer = input.nextLine();
            }
            this.skipSleep = answer.equals("y");
        }


        // Choisis la taille de la grille
        int x = get_size_grid("width");
        int y = get_size_grid("height");

        StateEnum signPlayer;
        StateEnum signComputer;

        if (this.computerVsComputer) {
            signPlayer = StateEnum.CROIX;
            signComputer = StateEnum.ROND;
            // initialisation de la grille, des joueurs, des tours et du premier joueur
            this.GRID = new Grille(x, y);
            this.realPlayer = new Computer(this.GRID, signPlayer);
            this.computer = new Computer(this.GRID, signComputer);
            this.round = 0;
            this.firstPlayer = this.whoGoesFirst();

        } else {
            // Choisis le signe du joueur
            System.out.println("Choose a sign (x or o) :");
            String sign = input.nextLine();
            // Demande au joueur un signe valide
            while (!sign.equals("x") && !sign.equals("o")) {
                System.out.println("You can only choose x and o as signs ");
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

    }

    public PlayGame(int lengthX, int lengthY, int firstPlayer, int signPlayer, int skipTime, int pcVsPc) {
        this.GRID = new Grille(lengthX, lengthY);
        this.firstPlayer = firstPlayer;
        this.computerVsComputer = pcVsPc == 1;
        this.skipSleep = skipTime == 1;
        StateEnum signComputer = signPlayer == 1 ? StateEnum.ROND : StateEnum.CROIX;
        if (this.computerVsComputer){
            this.realPlayer = new Computer(this.GRID, signPlayer == 1 ? StateEnum.CROIX : StateEnum.ROND);
        }else {
            this.realPlayer = new RealPlayer(this.GRID, signPlayer == 1 ? StateEnum.CROIX : StateEnum.ROND);
        }

        this.computer = new Computer(this.GRID, signComputer);
        this.round = 0;

        System.out.println("Partie chargé avec succés.");
    }

    //Constructuer pour la partie graphique
    public PlayGame(int lengthX, int lengthY, int signPlayer) {
        this.GRID = new Grille(lengthX, lengthY);
        this.firstPlayer = this.whoGoesFirst();
        this.computerVsComputer = false;
        this.skipSleep = false;
        StateEnum signComputer = signPlayer == 1 ? StateEnum.ROND : StateEnum.CROIX;

        this.realPlayer = new RealPlayer(this.GRID, signPlayer == 1 ? StateEnum.CROIX : StateEnum.ROND);
        this.computer = new Computer(this.GRID, signComputer);

        this.round = 0;

        System.out.println("Partie chargé avec succés.");
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

    private int parseOrderAnswer() {
        int res = (this.round * 2) + 1;
        while (res > (this.round * 2) - 1) {
            System.out.println("you must only enter an integer smaller than the number of played cell");
            res = input.nextInt();
        }
        return res;
    }

    public void goBack(int order) {
        int taille = this.GRID.movesList.size();
        for (int i = taille; i > taille - order; i--) {
            Coordinates c = this.GRID.movesList.get(i - 1);
            this.GRID._tabCases[c.getX()][c.getY()].setState(StateEnum.VIDE);
            this.GRID.movesList.remove(i - 1);
        }
        this.round = this.round - (order / 2);
    }

    public void goBackGraphique(int order) {
        int taille = this.GRID.movesList.size();
        for (int i = taille; i > taille - order; i--) {
            Coordinates c = this.GRID.movesList.get(i - 1);
            this.GRID._tabCases[c.getX()][c.getY()].setState(StateEnum.VIDE);
            this.GRID.movesList.remove(i - 1);
            board.resetCase(c.getY()-5, c.getX()-5);
        }
        this.round = this.round - (order / 2);
    }

    public void nextRound() {
        this.GRID.print();
        this.round++;
        System.out.println("Round " + this.round);
        if (this.round > 1) {
            if (!this.computerVsComputer) {
                System.out.println("Do you want to go back in the moves list ?");
                if (this.parseSignAnswer()) {
                    System.out.println("how much time do you want to go back to");
                    int order = this.parseOrderAnswer();
                    this.goBack(order);
                    this.GRID.print();
                }
            }
        }

        if (this.firstPlayer > 0) {
            if (!this.computerVsComputer) {
                System.out.println("Do you want to change sign with the computer ?");
                if (this.parseSignAnswer()) {
                    this.realPlayer.changeSign();
                    this.computer.changeSign();
                }
            }

            System.out.println("It's your turn to play\n");
            if (this.computerVsComputer && !this.skipSleep) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ignored) {
                    }
            }
            this.realPlayer.play();
            this.GRID.print();
            StateEnum symboleGagnant = this.GRID.getStateQuintupletComplet();
            if (symboleGagnant != null) return;
//            this.GRID.printCasesValues();
//            this.GRID.printQuint();
            System.out.println("The computer play.");
            if (!this.computerVsComputer || !this.skipSleep) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
            this.computer.play();
//            this.GRID.printQuint();
//            this.GRID.printCasesValues();

            if (!this.computerVsComputer){
                System.out.println("Do you want to save the party ?");
                if (this.parseSignAnswer()){
                    System.out.println("Saisissez le nom de la partie.");
                    String namePath = input.nextLine();
                    try{
                        SaveAndLoad.save(this, namePath);
                        this.save = true;
                    }catch (FileNotFoundException e){
                        System.out.println(e);
                    }
                }
            }
        } else {
            System.out.println("The computer play.");
//            this.GRID.printQuint();
//            this.GRID.printCasesValues();
            if (!this.computerVsComputer || !this.skipSleep) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
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
            if (!this.computerVsComputer) {

                System.out.println("Do you want to save the party ?");
                if (this.parseSignAnswer()){
                    System.out.println("Saisissez le nom de la partie.");
                    String namePath = input.nextLine();
                    try{
                        SaveAndLoad.save(this, namePath);
                        this.save = true;
                    }catch (FileNotFoundException e){
                        System.out.println(e);
                    }
                }

                System.out.println("Do you want to change sign with the computer ?");
                if (this.parseSignAnswer()) {
                    this.realPlayer.changeSign();
                    this.computer.changeSign();
                }
            }
            System.out.println("It's your turn to play");
            if (this.computerVsComputer && !this.skipSleep) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
            this.realPlayer.play();
        }
    }
    // Pour la partie graphique
    public void nextRound(int x, int y){
        this.round++;

        ((RealPlayer)this.realPlayer).play(x+1, y+1);
        this.board.majCase(x, y, this.realPlayer.getSign());

        StateEnum symboleGagnant = this.GRID.getStateQuintupletComplet();
        if (symboleGagnant != null) return;


        int[] coord = ((Computer) this.computer).played();
        this.board.majCase(coord[1], coord[0], this.computer.getSign());
        this.GRID.print();

    }


    /**
     * check win
     **/
    public boolean checkWin() {
        StateEnum symboleGagnant = this.GRID.getStateQuintupletComplet();
        if (symboleGagnant != null) {
            if (symboleGagnant == StateEnum.VIDE) {
                System.out.println(ANSIColor.ANSI_PURPLE + "It's a draw !" + ANSIColor.ANSI_RESET);
                this.winner = 0;
            } else if (symboleGagnant.equals(this.computer.getSign())) {
                System.out.println(ANSIColor.ANSI_YELLOW + "The computer win !" + ANSIColor.ANSI_RESET);
                this.winner  = 1;
            } else {
                System.out.println(ANSIColor.ANSI_GREEN + "You win !" + ANSIColor.ANSI_RESET);
                this.winner = 2;
            }
            input.close();
            return true;
        }
        return false;

    }

    public  boolean getComputerVsComputer(){
        return this.computerVsComputer;
    }

    public boolean getSkipSleep(){
        return this.skipSleep;
    }

    public Grille getGrid(){
        return this.GRID;
    }

    public int getWhoFirst(){
        return this.firstPlayer;
    }

    public Player getRealPlayer(){
        return this.realPlayer;
    }

    public boolean isSave() {
        return save;
    }

    public int getWinner() {
        return winner;
    }

    public StateEnum getSignPlayer(){
        return this.realPlayer.getSign();
    }

    public StateEnum getSignComputer(){
        return this.computer.getSign();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getFirstPlayer() {
        return firstPlayer;
    }

    public Player getComputer() {
        return computer;
    }

    public int getX(){
        return this.GRID.getX();
    }

    public int getY(){
        return this.GRID.getY();
    }
}
