package gameGUI;
import appGUI.CitizenGameController;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class NewGameController {

    @FXML
    private ScrollPane map_list_view;

    @FXML
    private BorderPane simulation_view;

    @FXML
    public void loadMap() {
        map_list_view.setVisible(false);
        map_list_view.setManaged(false);
        simulation_view.setVisible(true);
        simulation_view.setManaged(true);
    }
}
