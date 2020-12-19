package appGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    
    @FXML
    public void login() throws IOException {
        if(authenticate()) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("scenes/citizen_game.fxml"));
            Parent root = loader.load();
            Main.setView(root);
        }
    }
    
    private boolean authenticate() {
        return true;
    }

    @FXML
    public void quitApplication() {
        Platform.exit();
        System.exit(0);
    }
}
