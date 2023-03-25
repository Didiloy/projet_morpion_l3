package Vue;

import rungame.PlayGame;
import Controleur.CaseControleur;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class CaseVue extends Label {
    private int coordX;
    private int coordY;
    private Label txt;
    private boolean vide;

    public CaseVue(int x, int y, PlayGame playGame, PlayVue playVue){
        super();
        this.coordX = x;
        this.coordY = y;
        this.txt = new Label("");
        this.vide = true;

        this.getChildren().add(this.txt);
        this.setMinSize(20,20);
        this.setStyle("-fx-background-color: white; -fx-text-fill: blue;");
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new CaseControleur(playGame, playVue));
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setX(){

        Image img = new Image("./Image/close.png", 20, 20, true, false);
        ImageView imageView = new ImageView(img);

        this.getChildren().add(imageView);
        this.vide = false;
    }

    public void setO(){

        Image img = new Image("./Image/circle.png", 20, 20, true, false);
        ImageView imageView = new ImageView(img);

        this.getChildren().add(imageView);
        this.vide = false;
    }

    public void reset(){
        this.txt.setText("zzzz");
        System.out.println("reset simple : " + this.getChildren().size());
        this.getChildren().remove(0);
        System.out.println("reset simple bis: " + this.getChildren().size());

        this.vide = true;
    }

    public boolean isVide() {
        return vide;
    }
}