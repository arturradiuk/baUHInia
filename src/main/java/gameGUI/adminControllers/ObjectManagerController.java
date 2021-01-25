package gameGUI.adminControllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class ObjectManagerController implements Initializable {

    @FXML
    private VBox listVBox;
    @FXML
    private AnchorPane listScroll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<BorderPane> objectPanes = new ArrayList<>();
        for(int i=0; i<7; i++) {
            BorderPane borderPane = new BorderPane();
            borderPane.setPadding((new Insets(10, 10, 10, 10)));
            borderPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f0e2d0"), CornerRadii.EMPTY, Insets.EMPTY)));
            HBox hbox = new HBox();
            Button editBtn = new Button("Edytuj");
            editBtn.setOnMouseClicked(actionEvent -> {
                //addObject();
            });

            Button deleteBtn = new Button("Usuń");
            deleteBtn.setOnMouseClicked(actionEvent -> {
                //addObject();
            });
            hbox.setPadding((new Insets(45, 0, 0, 0)));
            hbox.setSpacing(10);
            hbox.getChildren().addAll(editBtn, deleteBtn);

            VBox vbox = new VBox();
            vbox.setPadding((new Insets(15, 0, 0, 20)));
            Label name = new Label("Name: ");
            Label objectName = new Label("Black dress");
            Label t = new Label("info:\nThis is very nice circle\nPower over 9k");
            vbox.getChildren().addAll(name, objectName, t);
            try {
                Image image;
                image = new Image("objects/bench.png");
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
        int i = 7;
        int length = i*(10+120+10+2);

        if(length < 545) {
            listScroll.setPrefHeight(546);
        } else listScroll.setPrefHeight(length);

        //listScroll.setPrefHeight(3*(10+120+10+2));
        //listScroll.setPrefHeight(200);
        listVBox.setPrefHeight(length);
        listVBox.getChildren().addAll(objectPanes);

    }
}


