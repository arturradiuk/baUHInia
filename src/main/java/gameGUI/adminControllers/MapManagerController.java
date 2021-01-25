package gameGUI.adminControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import logic.service.admin.AdminException;
import maps.api.Map;
import maps.api.MapObject;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminManager = new AdminManager();
        List<Map> test = null;
        try {
            test = adminManager.getMapTemplates();
        } catch (AdminException e) {
            e.printStackTrace();
        }
        System.out.println(test.size());

        int size = test.size();
        ArrayList<BorderPane> objectPanes = new ArrayList<>();
        for(int i=0; i<size; i++) {
            BorderPane borderPane = new BorderPane();
            borderPane.setPadding((new Insets(10, 10, 10, 10)));
            borderPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f0e2d0"), CornerRadii.EMPTY, Insets.EMPTY)));
            HBox hbox = new HBox();
            Button editBtn = new Button("Edytuj");
            editBtn.setOnMouseClicked(actionEvent -> {
                //addObject();
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

            Image image;
            image = new Image("objects/bench.png");
            ImageView pic = new ImageView();
            pic.setFitWidth(120);
            pic.setFitHeight(120);
            pic.setImage(image);
            borderPane.setLeft(pic);
            borderPane.setCenter(vbox);
            borderPane.setRight(hbox);
            BorderPane.setAlignment(pic, Pos.CENTER);
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
    }

}
