package gameGUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import logic.service.IObjectsService;
import logic.service.ObjectsManager;
import login.LoginService;

public class SimulationController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane anchorScroll;

    private final ObservableList<Button> buttons = FXCollections.observableArrayList();
    private ObjectsManager objectsManager;
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        objectsManager = new ObjectsManager();
        List<Map<String, String>> test = objectsManager.getAllObjectsInfo();
        for(int i=0; i<test.size()+1; i++) {
            Button b = new Button("Obiekt " + (i+1));
            b.setCursor(Cursor.HAND);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    addObject();
                }
            });

            buttons.add(b);
        }
        anchorScroll.setPrefHeight(30*38+5+10*28);
        vbox.getChildren().addAll(buttons);
    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            orgSceneX = mouseEvent.getSceneX();
            orgSceneY = mouseEvent.getSceneY();
            orgTranslateX = ((Circle)(mouseEvent.getSource())).getTranslateX();
            orgTranslateY = ((Circle)(mouseEvent.getSource())).getTranslateY();
        }
    };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            double offsetX = mouseEvent.getSceneX() - orgSceneX;
            double offsetY = mouseEvent.getSceneY() - orgSceneY;

            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            ((Circle)(mouseEvent.getSource())).setTranslateX(newTranslateX);
            ((Circle)(mouseEvent.getSource())).setTranslateY(newTranslateY);
        }
    };

    public void addObject() {
        Circle circle = new Circle();
        circle.setRadius(50);
        circle.setCenterX(200);
        circle.setCenterY(200);
        mainPane.getChildren().add(circle);
        circle.setCursor(Cursor.OPEN_HAND);
        circle.setOnMousePressed(circleOnMousePressedEventHandler);
        circle.setOnMouseDragged(circleOnMouseDraggedEventHandler);
    }


}
