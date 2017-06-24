package sample;

import javafx.scene.control.Alert;

/**
 * Created by sol on 14/04/2016.
 */
public class CompteDejaExistantException extends Exception {
    public void show(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("erreur d'inscription");
        alert.setContentText("compte deja existant");
        alert.show();
    }
}
