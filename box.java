package sample; /**
 * Created by Chemss on 02/02/2016.
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class box {

    public static String box(){
        final String[] choice = {""};

        Stage windows =new Stage();
        windows.initModality(Modality.APPLICATION_MODAL);
        windows.setTitle("Hello on our app");
        Button button=new Button("Ok");
        ChoiceBox<String> choicebox=new ChoiceBox<>();
        choicebox.getItems().add("Normal Mode");
        choicebox.getItems().add("Chemin mode");

        button.setOnAction(e-> {
            choice[0] = choicebox.getValue();
            System.out.print(choice[0]);windows.close();
        });
        VBox lyout =new VBox(10);
        lyout.getChildren().addAll(choicebox,button);
        Scene sc=new Scene(lyout,300,300);
        windows.setScene(sc);
        windows.showAndWait();
        return choice[0];

    }
}
