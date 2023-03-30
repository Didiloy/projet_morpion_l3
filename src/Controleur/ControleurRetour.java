package Controleur;

import Vue.Alerte;
import Vue.PlayVue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import rungame.PlayGame;

public class ControleurRetour implements EventHandler {

    private PlayVue playVue;
    private PlayGame playGame;

    public ControleurRetour(PlayVue playVue, PlayGame playGame){
        this.playVue = playVue;
        this.playGame = playGame;
    }

    @Override
    public void handle(Event event) {
        if (playVue.getTfretour().getCharacters().toString().matches("[0-9]+")){
            int nb = Integer.parseInt(playVue.getTfretour().getCharacters().toString());
            playGame.goBackGraphique(nb);
        }else{
            Alert al = new Alerte("Il faut mettre des nombres pour le retour en arrière.");
            al.showAndWait();
        }

    }
}
