package sample; /**
 * Created by Chemss on 02/02/2016.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static int display(String title){

        Stage window =new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        JFXTextField txt =new JFXTextField();
        txt.setPromptText("Inserer un nombre");
        txt.setLabelFloat(true);
        JFXButton button1=new JFXButton("Valider");
        button1.setOnAction(e->window.close());

        VBox la=new VBox(20);
        HBox hb=new HBox(100);

        hb.getChildren().addAll(txt);
        txt.setPadding(new Insets(10,0,0,0));
        la.getChildren().addAll(hb,button1);
        la.setAlignment(Pos.CENTER);
        hb.setAlignment(Pos.CENTER);

        Scene sc=new Scene(la,200,200);
        window.setScene(sc);
        window.showAndWait();
        return Integer.parseInt(txt.getText());

    }
}
