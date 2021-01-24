package appGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class AdminGameController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @FXML
    public void importMap() {

    }

    @FXML
    public void saveMap() {

    }

    @FXML
    public void logOff() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("scenes/login.fxml"));
        Parent root = loader.load();
        Main.setView(root);
    }

    @FXML
    public void quitApplication() {
        Platform.exit();
        System.exit(0);
    }
}
