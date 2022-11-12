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
    private Grille CASE;
    private StateEnum state;
    private int round;
    private int firstPlayer;

    public static Scanner input = new Scanner(System.in);

    public PlayGame(){
        // Choisis la taille de la grille
        System.out.println("Choose width of the grid: ");
        int x = input.nextInt();
        System.out.println("Choose height of the grid: ");
        int y = input.nextInt();

        // Choisis le signe du joueur
        System.out.println("Choose a sign (x or o) :");
        input.nextLine();
        String sign = input.nextLine();

        StateEnum signPlayer;
        StateEnum signComputer;

        // Demande au joueur un signe valide
        while ( !sign.equals("x") && !sign.equals("o")){
            System.out.println("Please choose a sign x or o, not other thing :");
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
    }

    /**
     * player choose the signe
     **/

    public void inputPlayerSigne(String signe) {
        signe = this.state.toString();
        if ((!signe.equals(StateEnum.ROND.toString())) || (!signe.equals(StateEnum.CROIX.toString())))
            System.out.println("Do you want to be X or O");
        signe = getState().toString();
    }

    /**
     * Start the game and stop it if there is a win
     * @param g the grid
     * @param c the computer
     * @param p the real player
     */
    public static void start(Grille g, Computer c, RealPlayer p){

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
        this.GRID.print();
        //this.GRID.printQuint();
        if(this.firstPlayer == 1) {
            System.out.println("It's your turn to play");
            this.realPlayer.play();
            System.out.println("The computer play.");
            this.computer.play();
        }else{
            System.out.println("The computer play.");
            this.computer.play();
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



    public Player getComputer() {
        return computer;
    }

    public void setComputer(Player computer) {
        this.computer = computer;
    }

    public Player getRealPlayer() {
        return realPlayer;
    }

    public void setRealPlayer(Player realPlayer) {
        this.realPlayer = realPlayer;
    }

    public Grille getGRID() {
        return GRID;
    }

    public void setGRID(Grille GRID) {
        this.GRID = GRID;
    }

    public Grille getCASE() {
        return CASE;
    }

    public void setCASE(Grille CASE) {
        this.CASE = CASE;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }


    public static void main(String[] args) {
        System.out.println(new PlayGame().generateRandomNumber(0, 2));
    }
}
