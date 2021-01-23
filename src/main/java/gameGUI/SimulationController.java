package gameGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.service.ObjectsManager;

import java.net.URL;
import java.util.*;

public class SimulationController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane anchorScroll;

    @FXML
    private GridPane grid;

    private final ObservableList<Button> buttons = FXCollections.observableArrayList();
    private ObjectsManager objectsManager;
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;
    private String[] colors = {"#00CCFF", "#66FF99"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        objectsManager = new ObjectsManager();
        Button bu = new Button("Obiekt");
        //grid.add(bu,0,0);
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                Rectangle rec = new Rectangle();
                rec.setFill(Paint.valueOf(colors[i%2]));
                rec.setWidth(20);
                rec.setHeight(20);
                rec.setStroke(Color.BLACK);
                rec.setStrokeWidth(0.15);
                rec.setId("map" + i + j);
                grid.add(rec,i,j);
            }
        }
        //grid.add(rec,0,0);

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
            if(mouseEvent.getSceneX()>200 && mouseEvent.getSceneX()<800){
                ((Circle)(mouseEvent.getSource())).setTranslateX(newTranslateX);
            }
            if(mouseEvent.getSceneY()>0 && mouseEvent.getSceneY()<600){
                ((Circle)(mouseEvent.getSource())).setTranslateY(newTranslateY);
            }
        }
    };

    EventHandler<MouseEvent> circleOnMouseReleasedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Circle circ = (Circle)(mouseEvent.getSource());
            int nodeX = (int) mouseEvent.getSceneX() - 190;
            int nodeY = (int) mouseEvent.getSceneY();
            nodeX = nodeX/20;
            nodeY = nodeY/20;
            if (nodeX < 0) {
                nodeX = 0;
            }
            if (nodeY < 0) {
                nodeY = 0;
            }
            if (nodeX > 29) {
                nodeX = 29;
            }
            if (nodeY > 29) {
                nodeY = 29;
            }
            Rectangle rec = (Rectangle) grid.lookup("#map" + nodeX + nodeY);
            System.out.println(rec.getFill());
            System.out.println(Paint.valueOf("#66FF99"));
            if(rec.getFill().equals(Paint.valueOf("#66FF99"))) {
                grid.getChildren().remove(circ);
                circ.setTranslateX(0);
                circ.setTranslateY(0);
                grid.add(circ,nodeX,nodeY);
            }
            else {
                int nodeX2 = (int) orgSceneX - 190;
                int nodeY2 = (int) orgSceneY;
                nodeX2 = nodeX2/20;
                nodeY2 = nodeY2/20;
                if (nodeX2 < 0) {
                    nodeX2 = 0;
                }
                if (nodeY2 < 0) {
                    nodeY2 = 0;
                }
                if (nodeX2 > 29) {
                    nodeX2 = 29;
                }
                if (nodeY2 > 29) {
                    nodeY2 = 29;
                }


                grid.getChildren().remove(circ);
                circ.setTranslateX(0);
                circ.setTranslateY(0);
                grid.add(circ,nodeX2,nodeY2);
            }

            System.out.println(mouseEvent.getSceneY() );

            //double offsetX = mouseEvent.getSceneX() - orgSceneX;
           // double offsetY = mouseEvent.getSceneY() - orgSceneY;

           // double newTranslateX = orgTranslateX + offsetX;
           // double newTranslateY = orgTranslateY + offsetY;
            //if(newTranslateX>0 && newTranslateX<580){
           //     ((Circle)(mouseEvent.getSource())).setTranslateX(newTranslateX);
           // }

           // ((Circle)(mouseEvent.getSource())).setTranslateY(newTranslateY);
        }
    };

    public void addObject() {
        Circle circle = new Circle();
        circle.setRadius(10);
        //circle.setCenterX(200);
        //circle.setCenterY(200);
        //mainPane.getChildren().add(circle);
        //grid.getChildren().add(circle);
        grid.add(circle,0,0);
        circle.setCursor(Cursor.OPEN_HAND);
        circle.setOnMousePressed(circleOnMousePressedEventHandler);
        circle.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        circle.setOnMouseReleased(circleOnMouseReleasedEventHandler);
    }

    public void simulate() {
        resetSimulation();
        ArrayList<Circle> circles = getObjects();
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                Rectangle rec = new Rectangle();
                rec.setFill(Color.RED);
                rec.setId("rec" + i + j);
                rec.setWidth(20);
                rec.setHeight(20);
                rec.setOpacity(0.6);
                grid.add(rec,i,j);
            }
        }
    }

    public void resetSimulation() {
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                Rectangle rec = (Rectangle) grid.lookup("#rec" + i + j);
                grid.getChildren().remove(rec);
            }
        }
    }

    public ArrayList<Circle> getObjects() {
        ArrayList<Circle> circles = new ArrayList<>();

        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                Rectangle rec = (Rectangle) grid.lookup("#rec" + i + j);
                grid.getChildren().remove(rec);
            }
        }
        return circles;
    }



}
