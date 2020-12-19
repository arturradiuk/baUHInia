package appGUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @FXML
    public void quitApplication() {
        Platform.exit();
        System.exit(0);
    }
    
    @FXML
    public void login() {
        
    }
}
