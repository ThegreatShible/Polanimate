package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
Stage windows=new Stage();        static String choice;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root1 = FXMLLoader.load(getClass().getResource("Splash.fxml"));
        windows.setTitle("Polanimate");
        Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
        windows.getIcons().add(applicationIcon);
        windows.setResizable(false);
        primaryStage.getIcons().add(applicationIcon);

        windows.setScene(new Scene(root1,590,180));
        // root1.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished( event -> windows.close() );
        delay.play();
        windows.showAndWait();


        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        primaryStage.setTitle("Se connecter");
        primaryStage.setScene(new Scene(root));

        primaryStage.getIcons().add(applicationIcon);

        primaryStage.setResizable(false);
        root.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
        primaryStage.show();

        /*choice= box.box();
        System.out.print(choice);
        String s1="Normal Mode";
        String s2="Chemin mode";



        if (choice.equals(s1))
        { Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root));
            root.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
            primaryStage.show();}
        else if(choice.equals(s2)) { Parent root = FXMLLoader.load(getClass().getResource("Chemin.fxml"));
            primaryStage.setTitle("Mode Chemin");
            primaryStage.setScene(new Scene(root));
            root.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
            primaryStage.show();}
        else {
            AlertBox.display("Hello","you have not chosen any mode retray again");
            choice= box.box();
        }
*/

    }



    public static void main(String[] args) {
        launch(args);
    }
}
