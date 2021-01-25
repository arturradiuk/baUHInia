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

public class AdminGameController implements Initializable {

    @FXML
    private AnchorPane admin_start_page_view;

    @FXML
    private BorderPane admin_map_manger_view;

    @FXML
    private BorderPane admin_object_manager_view;

    @FXML
    private BorderPane admin_show_all_games_view;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @FXML
    public void showMapManager() {
        hideAllViews();
        admin_map_manger_view.setVisible(true);
        admin_map_manger_view.setManaged(true);

    }

    @FXML
    public void showObjectManager() {
        hideAllViews();
        admin_object_manager_view.setVisible(true);
        admin_object_manager_view.setManaged(true);
    }

    @FXML
    public void showUserMaps() {
        hideAllViews();
        admin_show_all_games_view.setVisible(true);
        admin_show_all_games_view.setManaged(true);
    }

    public void hideAllViews() {
        admin_start_page_view.setVisible(false);
        admin_start_page_view.setManaged(false);
        admin_map_manger_view.setVisible(false);
        admin_map_manger_view.setManaged(false);
        admin_object_manager_view.setVisible(false);
        admin_object_manager_view.setManaged(false);
        admin_show_all_games_view.setVisible(false);
        admin_show_all_games_view.setManaged(false);
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
