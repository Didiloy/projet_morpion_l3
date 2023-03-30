package Vue;

import common.enums.StateEnum;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import rungame.PlayGame;
import rungame.SaveAndLoad;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class SaveVue {

    private MainVue main;
    private PlayGame game;

    public SaveVue(MainVue main){
        this.main = main;
    }

    public Scene creerVue(String[] save_game){

        BorderPane main = new BorderPane();
        main.setPadding(new Insets(50));

        BackgroundImage myBI= new BackgroundImage(new Image("./Image/background.png",1000,1000,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        main.setBackground(new Background(myBI));
        Button retour = new Button("Menu principal");

        retour.setAlignment(Pos.TOP_RIGHT);
        main.setRight(retour);

        retour.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            this.getMain().getPrimaryStage().setScene(new MenuVue(this.getMain()).creerVue());
        });

        VBox listVbox = new VBox();
        listVbox.setPadding(new Insets(30));

        int nb_save = save_game.length;
        int nb = save_game.length;

        if (save_game.length < 6){
            nb_save = 1;
        }else {
            nb_save = nb_save / 6 +1;
        }

        for (int i = 0 ; i < nb_save; i++) {
            HBox ligne = new HBox();
            ligne.setSpacing(20);

            int cpt;
            if (nb > 6) {
                cpt = 6;
                nb -= 6;
            }else{
                cpt = nb;
            }
            for (int j = 0; j < cpt ; j++) {
                Image img = new Image("./Image/save.png", 100, 100, true, false);
                ImageView imageView = new ImageView(img);

                Label nom_save = new Label(save_game[j+i*6]);
                VBox save = new VBox(imageView, nom_save);
                save.setAlignment(Pos.CENTER);
                ligne.getChildren().add(save);
                ligne.setSpacing(20);

                save.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    try {
                        this.game = SaveAndLoad.load((nom_save.getText()));
                        PlayVue playVue =new PlayVue(this.main, game);
                        Scene scene = playVue.creerVue(game.getX(), game.getY());

                        this.main.getPrimaryStage().setScene(scene);
                        playVue.getBoard().reset();
                        for (int x = 0; x < game.getGrid().getTabCases().length; x++) {
                            for (int y = 0; y < game.getGrid().getTabCases()[x].length; y++) {
                                StateEnum sign = game.getGrid().getTabCases()[x][y].getState();
                                if (sign != StateEnum.VIDE) {
                                    playVue.getBoard().majCase(y - 5, x - 5, sign);
                                }
                            }
                        }
                    }catch (FileNotFoundException e){
                        Alert al = new Alerte(e.getMessage());
                        al.showAndWait();
                    }
                });
            }
            listVbox.getChildren().add(ligne);
        }
        listVbox.setSpacing(20);
        main.setCenter(listVbox);

        return new Scene(main, 1000, 800);
    }

    public MainVue getMain() {
        return main;
    }
}
