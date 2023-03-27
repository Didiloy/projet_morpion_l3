package Joueur;
import common.enums.StateEnum;
import grille.Coordinates;
import grille.Grille;
import tournoi.GomokolClient.interfaces.GameOwner;
import tournoi.GomokolClient.interfaces.Validation;


public class Owner implements GameOwner {

    private static final int EMPTY = -1;
    private int playerSign = -5;

    private int width, height;
    private Grille board;

    public Owner(int width, int height) {

        //Initialisation du plateau
        this.board = new Grille(width, height);
        //Définition des attributs
        this.width = width;
        this.height = height;
    }
    @Override
    public Validation getValidation(int player_id, int[] stroke) {
        //On récupère les coordonnées
        int x = stroke[0];
        int y = stroke[1];
        if (x >= 0 && y >= 0 && x < width && y < height) {
            StateEnum symboleGagnant = this.board.getStateQuintupletComplet();
            if (symboleGagnant != null) {
                if (symboleGagnant == StateEnum.VIDE) {
                    return Validation.DRAW;
                }
                else {
                    return Validation.ENDGAME;
                }
            }
            if(this.board.isPlayable(x, y)) return Validation.CAVOK;
        }

        //Cas de Triche ou de jeu incorrect
        return Validation.CHEATING;
    }

    @Override
    public void addStrokeToBoard(int player_id, int[] stroke) {
        this.board.playCase(getSign(player_id), stroke[0], stroke[1]);
        this.board.addMove(new Coordinates(stroke[0], stroke[1], getSign(player_id)));
    }

    private StateEnum getSign(int player_id){
        if(this.playerSign == -5 ){
            this.playerSign = player_id;
        }
        if(this.playerSign == player_id) return StateEnum.CROIX;
        return StateEnum.ROND;
    }

}
