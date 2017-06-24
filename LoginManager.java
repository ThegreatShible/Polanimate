package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Manages control flow for logins */
public class LoginManager {
  private Scene scene;

  public LoginManager(Scene scene) {
    this.scene = scene;
  }

  /**
   * Callback method invoked to notify that a user has been authenticated.
   * Will show the main application screen.
   */


  /**
   * Callback method invoked to notify that a user has logged out of the main application.
   * Will show the login application screen.
   */
  public void logout() {
    showLoginScreen();
  }

  public void showLoginScreen() {
    try {
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("Login.fxml")
      );
      scene.setRoot((Parent) loader.load());
      Controller controller = loader.<Controller>getController();
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void showMainView( ) {
    try {
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("sample.fxml")
      );
      scene.getStylesheets().add(String.valueOf(getClass().getResource("Ui.css")));
      scene.setRoot((Parent) loader.load());
      Controller controller =
        loader.<Controller>getController();
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
