package Controleur;

import rungame.PlayGame;
import Vue.Alerte;
import Vue.CaseVue;
import Vue.PlayVue;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CaseControleur implements EventHandler {
    private PlayGame playGame;
    private PlayVue playVue;

    public CaseControleur(PlayGame playGame, PlayVue playVue){
        this.playGame = playGame;
        this.playVue = playVue;
    }

    @Override
    public void handle(Event event) {
        if (((CaseVue) event.getSource()).isVide()){
            playGame.nextRound(((CaseVue) event.getSource()).getCoordX(), ((CaseVue) event.getSource()).getCoordY());

            if (playGame.checkWin()){
                if (playGame.getWinner() == 0){ // égalité
                    playVue.showDialog("Égalité");
                }else if (playGame.getWinner() == 1){ // pc win
                    playVue.showDialog("Vous avez perdu");
                }else if (playGame.getWinner() == 2){ // joueur win
                    playVue.showDialog("Vous avez gagné !");
                }
            }
        }else {
            Alerte al = new Alerte("Vous ne pouvez pas joué sur cette case.");
            al.showAndWait();
        }
    }
}
