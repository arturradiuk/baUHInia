package gameGUI.adminControllers;

import common.enums.CellType;
import common.enums.ObjectType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.service.admin.AdminException;
import maps.api.MapObject;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;


public class ObjectManagerController implements Initializable {

    @FXML
    private VBox listVBox;
    @FXML
    private AnchorPane listScroll;

    private AdminManager adminManager;
    private List<MapObject> test = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminManager = new AdminManager();
        initList();


    }

    public void initList() {
        test = null;
        listVBox.getChildren().clear();
        try {
            test = adminManager.getAllObjects();
        } catch (AdminException e) {
            e.printStackTrace();
        }
        ArrayList<BorderPane> objectPanes = new ArrayList<>();



        for(int i=0; i<test.size(); i++) {
            BorderPane borderPane = new BorderPane();
            borderPane.setPadding((new Insets(10, 10, 10, 10)));
            borderPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f0e2d0"), CornerRadii.EMPTY, Insets.EMPTY)));
            String name = test.get(i).getName();
            UUID guid = test.get(i).getGuid();
            int height = test.get(i).getHeight();
            int width = test.get(i).getWidth();
            int length = test.get(i).getLength();
            CellType cellType = test.get(i).getAllowedTerrainType();
            ObjectType objType = test.get(i).getType();
            int price = test.get(i).getPrice();
            double heatFactor = test.get(i).getHeatFactor();
            HBox hbox = new HBox();
            Button editBtn = new Button("Edytuj");
            editBtn.setOnMouseClicked(actionEvent -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/admin_edit_object.fxml"));
                    EditObjectController editObjectController = new EditObjectController(guid, name,width,length,height,cellType,objType,price,heatFactor);
                    editObjectController.setAdminManager(adminManager);
                    //editObjectController.setName(name);
                    //controller2.setCurrentMap(id);
                    loader.setController(editObjectController);
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("User map");
                    stage.setScene(new Scene(root, 700, 300));

                    //loader.setController(controller2);

                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Button deleteBtn = new Button("Usuń");

            deleteBtn.setOnMouseClicked(actionEvent -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + name + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    try {
                        adminManager.removeMapObject(guid);
                        initList();
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
            System.out.println(test.get(i).getGuid());
            Label guidAndName = new Label("GUID: " + test.get(i).getGuid() + ", Name: " + test.get(i).getName());
            Label dimensions = new Label("Width: " + test.get(i).getWidth() + ", length: " + test.get(i).getWidth() + ", height: " + test.get(i).getHeight());
            Label objectType = new Label("Object Type: " + test.get(i).getType());
            Label allowedTerrainType = new Label("Allowed Terrain Type: " + test.get(i).getAllowedTerrainType());
            Label priceAndHeatFactor = new Label("Price: " + test.get(i).getPrice() + ", Heat Factor: " + test.get(i).getHeatFactor());

            vbox.getChildren().addAll(guidAndName, dimensions, objectType, allowedTerrainType, priceAndHeatFactor);
            try {
                Image image;
                //System.out.println("objects/" + test.get(i).getGuid() + ".png");
                //System.out.println(String.valueOf(this.getClass().getResource(".\\resources\\objects" + test.get(i).getName() + ".png")));
                //System.out.println(".\\resources\\objects"+ test.get(i).getName() + ".png");
                File fileName = new File(".\\resources\\objects\\"+ test.get(i).getName() + ".png");
                image = new Image(fileName.toURI().toString());
                ImageView pic = new ImageView();
                pic.setFitWidth(120);
                pic.setFitHeight(120);
                pic.setImage(image);
                borderPane.setLeft(pic);
                BorderPane.setAlignment(pic, Pos.CENTER);
            } catch (Exception e) {
                e.printStackTrace();
            }



            borderPane.setCenter(vbox);
            borderPane.setRight(hbox);

            BorderPane.setAlignment(hbox, Pos.CENTER);
            BorderPane.setAlignment(vbox, Pos.CENTER);
            //borderPane.setCenter(pic);
            objectPanes.add(borderPane);

        }
        int i = test.size();
        int length = i*(10+120+10+2);

        if(length < 545) {
            listScroll.setPrefHeight(546);
        } else listScroll.setPrefHeight(length);

        //listScroll.setPrefHeight(3*(10+120+10+2));
        //listScroll.setPrefHeight(200);
        listVBox.setPrefHeight(length);
        listVBox.getChildren().addAll(objectPanes);
    }

    public void addNewObject() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/admin_add_object.fxml"));
            SaveObjectController saveObjectController = new SaveObjectController();
            saveObjectController.setAdminManager(adminManager);
            loader.setController(saveObjectController);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New Object");
            stage.setScene(new Scene(root, 600, 350));

            //loader.setController(controller2);

            stage.showAndWait();
            initList();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }




}


