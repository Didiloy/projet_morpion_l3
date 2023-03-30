package Vue;

import javafx.scene.control.Button;

public class Mybutton extends Button {

    public Mybutton(String name){
        super(name);
        this.setStyle("-fx-background: blue;");
        this.setMinSize(20, 10);
    }
}
