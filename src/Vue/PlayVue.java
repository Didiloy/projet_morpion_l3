package Vue;

import Controleur.ControleurRetour;
import rungame.PlayGame;
import rungame.SaveAndLoad;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayVue {

    private MainVue main;
    private PlayGame game;
    private Board board;
    private TextField tfretour;

    public PlayVue(MainVue main, PlayGame game){
        this.main = main;
        this.game = game;
    }

    public Scene creerVue(int x, int y){
        BorderPane main = new BorderPane();

        BackgroundImage myBI= new BackgroundImage(new Image("./Image/background.png",1000,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        main.setBackground(new Background(myBI));

        VBox botons = new VBox();
        Label lretour = new Label("Nb de retour : ");
        tfretour = new TextField();
        tfretour.setPrefWidth(50);
        HBox hretour = new HBox(lretour, tfretour);
        botons.getChildren().add(hretour);
        Button retour = new Mybutton("Retour en arrière");
        botons.getChildren().add(retour);
        Button echange = new Mybutton("Echanger avec l'IA");
        botons.getChildren().add(echange);
        Label lsauvgarde = new Label("Nom : ");
        TextField tsauvegarde = new TextField();
        tsauvegarde.setPrefWidth(80);
        HBox hsauvegarde = new HBox(lsauvgarde, tsauvegarde);
        botons.getChildren().add(hsauvegarde);
        Button sauvegarde = new Mybutton("Sauvegarder");
        botons.getChildren().add(sauvegarde);


        retour.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControleurRetour(this, game));

        echange.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            game.getRealPlayer().changeSign();
            game.getComputer().changeSign();
        });

        sauvegarde.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (!tsauvegarde.toString().equals("")){
                try {
                    SaveAndLoad.save(game, tsauvegarde.getText());
                    this.getMain().getPrimaryStage().setScene(new MenuVue(this.getMain()).creerVue());
                }catch (IOException e){
                    Alert al = new Alerte("La sauvegarde n'a pas marché.\n" + e);
                    al.showAndWait();
                }
            }else {
                Alert al = new Alerte("Le camps de la sauvegarde est vide.");
                al.showAndWait();
            }

        });

        botons.setAlignment(Pos.CENTER);
        retour.setAlignment(Pos.CENTER);
        echange.setAlignment(Pos.CENTER);
        sauvegarde.setAlignment(Pos.CENTER);
        botons.setSpacing(20);
        botons.setPadding(new Insets(10));
        main.setRight(botons);

        HBox htitle = new HBox();
        Region rtitle = new Region();
        rtitle.setPrefWidth(450);
        Label title = new Label("Morpion");
        title.setStyle("-fx-font-size: 24px; -fx-text-color: blue;");
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(20));
        htitle.getChildren().addAll(rtitle, title);
        main.setTop(htitle);

        this.board = new Board(x,y, game, this);
        this.board.setAlignment(Pos.CENTER);
        GridPane.setHgrow(this.board, Priority.ALWAYS);
        GridPane.setVgrow(this.board, Priority.ALWAYS);
        Region rleft = new Region();
        rleft.setPrefWidth(100);
        Region rright = new Region();
        rright.setPrefWidth(100);
        Region rtop = new Region();
        rtop.setPrefHeight(100);
        Region rbottom = new Region();
        rbottom.setPrefHeight(100);
        BorderPane gp = new BorderPane(this.board, rtop, rright, rbottom, rleft);

        main.setCenter(gp);

        return new Scene(main, 1000, 800);
    }

    public MainVue getMain() {
        return main;
    }

    public void showDialog(String message) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Label messageL = new Label(message);
        Button menu = new Button("Retour au menu");
        messageL.setAlignment(Pos.CENTER);
        menu.setAlignment(Pos.CENTER);

        menu.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.close();
            this.getMain().getPrimaryStage().setScene(new MenuVue(this.getMain()).creerVue());
        });

        VBox root = new VBox();
        root.getChildren().addAll(messageL, menu);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Scene scene = new Scene(root, 200, 100);
        stage.setScene(scene);
        stage.show();
    }

    public Board getBoard() {
        return board;
    }

    public TextField getTfretour() {
        return tfretour;
    }
}
