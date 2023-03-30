package Vue;

import Joueur.Computer;
import Vue.Alerte;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import rungame.PlayGame;

import java.io.File;

public class MenuVue {

    private MainVue mainVue;
    public MenuVue(MainVue mainVue){
        this.mainVue = mainVue;
    }

    public Scene creerVue(){
        VBox main = new VBox();

        BackgroundImage myBI= new BackgroundImage(new Image("./Image/background.png",1000,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        main.setBackground(new Background(myBI));

        Label titre = new Label("Morpion");
        titre.setFont(new Font("Arial", 20));

        Label l_taille_x = new Label("Taille x :");
        TextField tf_taille_x = new TextField();

        Label l_taille_y = new Label("Taille y :");
        TextField tf_taille_y = new TextField();

        Label l_signe = new Label("Signe choisi(x ou o) :");
        TextField tf_signe = new TextField();

        tf_taille_y.setPrefWidth(50);
        tf_taille_x.setPrefWidth(50);
        tf_signe.setPrefWidth(50);

        HBox taille = new HBox(l_taille_x, tf_taille_x, l_taille_y, tf_taille_y, l_signe, tf_signe);
        taille.setSpacing(5);
        taille.setAlignment(Pos.CENTER);


        Button jouer = new Button("Jouer");
        Button sauvegarder = new Button("Charger une partie");

        jouer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            int x = 0;
            int y = 0;
            int signe = 0;

            if (tf_taille_x.getCharacters().toString().matches("[0-9]+") && tf_taille_y.getCharacters().toString().matches("[0-9]+")) {
                x = Integer.parseInt(tf_taille_x.getCharacters().toString());
                y = Integer.parseInt(tf_taille_y.getCharacters().toString());
                if ((x >= 5 || x <= 50) && (y >= 5 || y <= 50)) {
                    if (tf_signe.getCharacters().toString().equals("x") || tf_signe.getCharacters().toString().equals("o")) {
                        if (tf_signe.getCharacters().toString().equals("x")){
                            signe = 1;
                        } else {
                            signe = 0;
                        }
                        PlayGame playGame = new PlayGame(x, y, signe);
                        PlayVue playVue =new PlayVue(this.mainVue, playGame);
                        Scene scene = playVue.creerVue(x, y);
                        this.mainVue.getPrimaryStage().setScene(scene);


                        if (playGame.getFirstPlayer() == 0){

                            int[] coord =((Computer) playGame.getComputer()).played();
                            playVue.getBoard().majCase(coord[1], coord[0], playGame.getComputer().getSign());
                        }

                    }else {
                        Alert al = new Alerte("Il faut mettre x ou o.");
                        al.showAndWait();
                    }
                }else {
                    Alert al = new Alerte("Les tailles doivent être comprise entre 5 et 50.");
                    al.showAndWait();
                }
            } else {
                Alert al = new Alerte("Il faut mettre des nombres pour les tailles.");
                al.showAndWait();
            }
        });

        sauvegarder.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            File repertoire = new File("./saved_game");
            String liste[] = repertoire.list();

            if (liste != null) {
                this.mainVue.getPrimaryStage().setScene(new SaveVue(this.mainVue).creerVue(liste));
            } else {
                Alert al = new Alerte("Problème pour charger les sauvegardes.");
                al.showAndWait();
            }
        });

        jouer.setMinWidth(100);
        sauvegarder.setMinWidth(100);

        main.getChildren().addAll(titre, taille, jouer, sauvegarder);
        main.setSpacing(10);
        main.setAlignment(Pos.CENTER);


        return new Scene(main, 1000, 800);
    }
}
