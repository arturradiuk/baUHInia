package appGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;


public class CitizenGameController implements Initializable {

    @FXML
    private AnchorPane game_view;

    @FXML
    private BorderPane new_game_view;

    @FXML
    private BorderPane load_game_view;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void newGame() {
        hideAllViews();
        new_game_view.setVisible(true);
        new_game_view.setManaged(true);

    }

    @FXML
    public void importMap() {
        hideAllViews();
        load_game_view.setVisible(true);
        load_game_view.setManaged(true);

    }


    public void hideAllViews() {
        game_view.setVisible(false);
        game_view.setManaged(false);
        new_game_view.setVisible(false);
        new_game_view.setManaged(false);
        load_game_view.setVisible(false);
        load_game_view.setManaged(false);
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
