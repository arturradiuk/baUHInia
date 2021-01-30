package gameGUI.adminControllers;

import gameGUI.SimulationController2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.service.admin.AdminException;
import maps.api.Map;
import maps.api.MapObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class MapManagerController implements Initializable {

    @FXML
    private VBox listVBox;
    @FXML
    private AnchorPane listScroll;

    private AdminManager adminManager;
    private List<Map> test = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminManager = new AdminManager();
        initList();
    }

    public void initList() {

        test = null;
        listVBox.getChildren().clear();
        try {
            test = adminManager.getMapTemplates();
        } catch (AdminException e) {
            e.printStackTrace();
        }
        System.out.println(test.size());
        //try {
        //    adminManager.removeMap(UUID.fromString("44d39cb8-90d1-4460-a15e-904b65e2f571"));
        //} catch (AdminException e) {
        //    e.printStackTrace();
        //}
        int size = test.size();
        ArrayList<BorderPane> objectPanes = new ArrayList<>();
        for(int i=0; i<size; i++) {
            BorderPane borderPane = new BorderPane();
            borderPane.setPadding((new Insets(10, 10, 10, 10)));
            borderPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f0e2d0"), CornerRadii.EMPTY, Insets.EMPTY)));
            HBox hbox = new HBox();
            UUID id = test.get(i).getGuid();
            Button editBtn = new Button("Edytuj");
            editBtn.setOnMouseClicked(actionEvent -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/admin_edit_map_view.fxml"));
                    EditMapController controller = new EditMapController();
                    controller.setCurrentMap(id);
                    loader.setController(controller);
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Map editor");
                    stage.setScene(new Scene(root, 1000, 600));

                    //loader.setController(controller2);

                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            String name = test.get(i).getName();
            UUID guid = test.get(i).getGuid();
            Button deleteBtn = new Button("Usuń");
            deleteBtn.setOnMouseClicked(actionEvent -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + name + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    try {
                        adminManager.removeMap(guid);

                    } catch (AdminException e) {
                        e.printStackTrace();
                    }
                    initList();
                }
            });
            hbox.setPadding((new Insets(45, 0, 0, 0)));
            hbox.setSpacing(10);
            hbox.getChildren().addAll(editBtn, deleteBtn);

            VBox vbox = new VBox();
            vbox.setPadding((new Insets(15, 0, 0, 20)));
            Label guidAndName = new Label("GUID: " + test.get(i).getGuid() + ", Name: " + test.get(i).getName());
            Label dates = new Label("Created: " + test.get(i).getCreated() + ", Modified: " + test.get(i).getModified());
            Label mapSize = new Label("Size: " + test.get(i).getSize());
            Label userID = new Label("User ID: " + test.get(i).getUserId());
            Label priceAndHeatFactor = new Label("Objects: " + test.get(i).getObjects() + ", State: " + test.get(i).getState());

            vbox.getChildren().addAll(guidAndName, dates, mapSize, userID, priceAndHeatFactor);

            borderPane.setCenter(vbox);
            borderPane.setRight(hbox);
            BorderPane.setAlignment(hbox, Pos.CENTER);
            BorderPane.setAlignment(vbox, Pos.CENTER);
            //borderPane.setCenter(pic);
            objectPanes.add(borderPane);

        }
        int length = size*(10+120+10+2);

        if(length < 545) {
            listScroll.setPrefHeight(546);
        } else listScroll.setPrefHeight(length);
        listVBox.setPrefHeight(length);
        listVBox.getChildren().addAll(objectPanes);
    }

    @FXML
    public void addNewMap() {
        adminManager.createNewMapTemplate();
        initList();
    }

    //private void openMapEditor()

}
