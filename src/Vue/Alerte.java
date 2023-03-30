package Vue;

import javafx.scene.control.Alert;

public class Alerte extends Alert {
    public Alerte(String message){
        super(Alert.AlertType.ERROR);
        this.setTitle("Erreur");
        this.setHeaderText("TypeError");
        this.setContentText(message);
    }
}
