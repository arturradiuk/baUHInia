package gameGUI.clientControllers;
import gameGUI.SimulationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import maps.api.Map;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class NewGameController implements Initializable {

    @FXML
    private VBox listVBox;
    @FXML
    private AnchorPane listScroll;

    private ClientManager clientManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientManager = new ClientManager();
        List<Map> test = null;
        test = clientManager.returnAvailableUserMaps();

        //System.out.println(test.size());
        int size = test.size();
        ArrayList<BorderPane> objectPanes = new ArrayList<>();
        for(int i=0; i<size; i++) {
            BorderPane borderPane = new BorderPane();
            borderPane.setPadding((new Insets(10, 10, 10, 10)));
            borderPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f0e2d0"), CornerRadii.EMPTY, Insets.EMPTY)));
            HBox hbox = new HBox();
            Button showMapBtn = new Button("Nowa gra");
            UUID id = test.get(i).getGuid();
            showMapBtn.setOnMouseClicked(actionEvent -> {
                try {
                    clientManager.createNewUserMap(id);
                    UUID newID = clientManager.getMap().getGuid();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/simulation_view.fxml"));
                    SimulationController controller2 = new SimulationController();
                    controller2.setCurrentMap(newID);
                    controller2.setClientManager(clientManager);
                    loader.setController(controller2);
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Map");
                    stage.setScene(new Scene(root, 1000, 600));

                    //loader.setController(controller2);

                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            });

            hbox.setPadding((new Insets(45, 0, 0, 0)));
            hbox.setSpacing(10);
            hbox.getChildren().addAll(showMapBtn);

            VBox vbox = new VBox();
            vbox.setPadding((new Insets(15, 0, 20, 20)));
            Label guidAndName = new Label("GUID: " + test.get(i).getGuid() + ", Nazwa: " + test.get(i).getName());
            Label dates = new Label("Stworzona: " + test.get(i).getCreated() + ", Zmieniona: " + test.get(i).getModified());
            Label mapSize = new Label("Rozmiar: " + test.get(i).getSize());
            Label userID = new Label("User ID: " + test.get(i).getUserId());
            Label priceAndHeatFactor = new Label("Obiekty: " + test.get(i).getObjects() + ", Stan: " + test.get(i).getState());

            vbox.getChildren().addAll(guidAndName, dates, mapSize, userID, priceAndHeatFactor);

            borderPane.setCenter(vbox);
            borderPane.setRight(hbox);
            BorderPane.setAlignment(hbox, Pos.CENTER);
            BorderPane.setAlignment(vbox, Pos.CENTER);
            //borderPane.setCenter(pic);
            objectPanes.add(borderPane);

        }
        int length = size*(10+120+10+2);

        if(length < 645) {
            listScroll.setPrefHeight(646);
        } else listScroll.setPrefHeight(length);
        listVBox.setPrefHeight(length);
        listVBox.getChildren().addAll(objectPanes);


    }
}
