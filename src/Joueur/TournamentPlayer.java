package Joueur;

import common.enums.StateEnum;
import grille.Coordinates;
import grille.Grille;

public class TournamentPlayer extends Player implements tournoi.GomokolClient.interfaces.Player {

    private int id;
    private StateEnum opponentSign;
    public TournamentPlayer(Grille grid, StateEnum sign, String _name) {
        super(grid, sign, _name);
        this.opponentSign = sign == StateEnum.CROIX ? StateEnum.ROND : StateEnum.CROIX;
    }

    @Override
    public void play() {
        this.getStroke();
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int[] getStroke() {
        super.getGrid().updateValue(this.SIGN);
        int[] maxValue = this.getGrid().getMaxValue();
        super.getGrid().playCase(super.getSign(), maxValue[0], maxValue[1]);
        super.getGrid().addMove(new Coordinates(maxValue[0], maxValue[1], this.getSign()));
        super.getGrid().updateValue(this.SIGN);
        return maxValue;
    }

    @Override
    public void receiveNewStroke(int player_id, int[] stroke) {
        super.getGrid().playCase(super.getSign(), stroke[0], stroke[1]);
        super.getGrid().addMove(new Coordinates(stroke[0], stroke[1], this.opponentSign));
    }
}
