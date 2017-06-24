package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.Icon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.xml.validation.Validator;

public class TextFieldDemo extends Application {

    private VBox pane;

    @Override
    public void start(Stage stage) throws Exception {

        pane = new VBox();
        pane.setSpacing(30);
        pane.setStyle("-fx-background-color:WHITE;-fx-padding:40;");



        JFXTextField validationField = new JFXTextField();

        validationField.setPromptText("With Validation..");
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Welighdwa");
        validator.setIcon(new Icon(AwesomeIcon.WARNING,"1em",";","error"));
        validationField.getValidators().add(validator);
        validationField.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) validationField.validate();
        });
        pane.getChildren().add(validationField);


        JFXPasswordField passwordField = new JFXPasswordField();
        passwordField.setStyle("-fx-label-float:true;");
        passwordField.setPromptText("Password");
        validator = new RequiredFieldValidator();
        validator.setMessage("Password Can't be empty");
        validator.setIcon(new Icon(AwesomeIcon.WARNING,"1em",";","error"));

        passwordField.getValidators().add(validator);
        passwordField.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) passwordField.validate();
        });
        pane.getChildren().add(passwordField);

        final Scene scene = new Scene(pane, 600, 400, Color.WHITE);
        //scene.getStylesheets().add(TextFieldDemo.class.getResource("Ui.css").toExternalForm());
        stage.setTitle("JFX TextField Demo ");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }
    public static void main(String[] args) { launch(args); }





}
