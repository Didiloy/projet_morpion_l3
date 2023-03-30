package Vue;

import common.enums.StateEnum;
import rungame.PlayGame;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class Board extends GridPane {

    private PlayGame playGame;

    public Board (int x, int y, PlayGame playGame, PlayVue playVue){
        super();
        this.playGame = playGame;
        this.playGame.setBoard(this);
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                this.add(new CaseVue(i, j, playGame, playVue), i, j);
            }
        }

        this.setStyle("-fx-background-color: blue; -fx-border-color:blue;");
        this.setPadding(new Insets(20));
        this.setVgap(5);
        this.setHgap(5);
        this.setPrefSize((x*20)+5, (y*20)+5);
    }

    public void majCase(int x, int y, StateEnum signe){
        for(int i=0; i < this.getChildren().size(); i++){
            if(((CaseVue) (this.getChildren().get(i))).getCoordX() == x && ((CaseVue) (this.getChildren().get(i))).getCoordY() == y){
                if (signe == StateEnum.CROIX){
                    ((CaseVue) (this.getChildren().get(i))).setX();
                }else {
                    ((CaseVue) (this.getChildren().get(i))).setO();
                }

            }
        }
    }

    public void resetCase(int x, int y){
        for(int i=0; i < this.getChildren().size(); i++){
            if(((CaseVue) (this.getChildren().get(i))).getCoordX() == x && ((CaseVue) (this.getChildren().get(i))).getCoordY() == y){
                ((CaseVue) (this.getChildren().get(i))).reset();
            }
        }
    }

    public void reset(){
        for (int i=0; i < this.getChildren().size(); i++) {
            ((CaseVue) this.getChildren().get(i)).reset();
        }
    }
}
