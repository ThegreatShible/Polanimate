package sample;

import javafx.scene.control.Alert;

/**
 * Created by sol on 16/02/2016.
 */
public class NoShapeException extends Exception {
    public void show(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'enregistrement");
        alert.setContentText("il n'y a aucune forme a animer");
        alert.show();
    }
}
