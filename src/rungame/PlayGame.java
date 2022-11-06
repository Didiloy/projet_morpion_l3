package rungame;


import Joueur.Player;
import common.enums.StateEnum;
import grille.Grille;

import java.util.Random;

public class PlayGame {
    private Player computer, realPlayer;
    private Grille GRID;
    private Grille CASE;
    private StateEnum state;


    /**
     * player choose the signe
     **/

    public void inputPlayerSigne(String signe) {
        signe = this.state.toString();
        if ((!signe.equals(state.ROND.toString())) || (!signe.equals(state.CROIX.toString())))
            System.out.println("Do you want to be X or O");
        signe = getState().toString();
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

    public void whoGoesFirst() {
        int randomNumber = generateRandomNumber(0, 1);

    }

    /**
     * check win
     **/

    public void checkWin(){

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
