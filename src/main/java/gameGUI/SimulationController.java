package gameGUI;

import common.enums.CellType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.service.admin.AdminException;
import logic.service.admin.AdminService;
import maps.api.Cell;
import maps.api.Map;
import maps.api.MapObject;
import maps.api.State;
import maps.api.services.FilesystemMapsProvider;
import maps.api.services.IMapsService;
import maps.api.services.MapsService;

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
    private final ObservableList<StackPane> stackPanes = FXCollections.observableArrayList();
    private AdminService adminService;
    private IMapsService mapsService;
    private Map map;
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;
    private String[] colors = {"#62AD53", "#8F8F8F", "#80682E"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapsService = new MapsService(new FilesystemMapsProvider("C:\\bauhinia"));
        adminService = new AdminService();
                //grid.add(rec,0,0);

        List<MapObject> test = null;
        try {
            test = adminService.getAllObjects();
        } catch (AdminException e) {
            e.printStackTrace();
        }
        for(int i=0; i<test.size(); i++) {
            System.out.println("objects/" + test.get(i).getGuid() + ".png");
            StackPane stackPane = new StackPane();
            stackPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f0e2d0"), CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane.setPadding((new Insets(10, 10, 10, 10)));
            stackPane.setPrefSize(100,100);
            BorderPane borderPane = new BorderPane();
            borderPane.setPadding((new Insets(10, 10, 10, 10)));
            borderPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f0e2d0"), CornerRadii.EMPTY, Insets.EMPTY)));
            borderPane.setPrefSize(20,40);
            borderPane.setCursor(Cursor.HAND);
            VBox myvbox = new VBox();
            Label dimensions = new Label("Width: " + test.get(i).getWidth() + ", length: " + test.get(i).getWidth() + ", height: " + test.get(i).getHeight());
            Label objectType = new Label("Object Type: " + test.get(i).getType());
            Label allowedTerrainType = new Label("Allowed Terrain Type: " + test.get(i).getAllowedTerrainType());
            Label priceAndHeatFactor = new Label("Price: " + test.get(i).getPrice() + ", Heat Factor: " + test.get(i).getHeatFactor());

            myvbox.getChildren().addAll(dimensions, objectType, allowedTerrainType, priceAndHeatFactor);
            Text title = new Text(test.get(i).getName());
            //Text t = new Text("info:\nThis is very nice circle\nPower over 9k");

            try {
                Image image;
                System.out.println("objects/" + test.get(i).getGuid() + ".png");
                image = new Image("objects/" + test.get(i).getGuid() + ".png");
                ImageView pic = new ImageView();
                pic.setFitWidth(30);
                pic.setFitHeight(30);
                pic.setImage(image);
                borderPane.setCenter(pic);
                borderPane.setOnMouseClicked(actionEvent -> addObject(image));

            } catch (Exception e) {
                e.printStackTrace();
            }

            borderPane.setTop(title);
            borderPane.setBottom(myvbox);
            BorderPane.setAlignment(myvbox, Pos.CENTER);
            BorderPane.setAlignment(title, Pos.CENTER);

            stackPane.getChildren().add(borderPane);
            stackPanes.add(stackPane);
        }
        anchorScroll.setPrefHeight(30*38+5+10*28);
        vbox.getChildren().addAll(stackPanes);

        map = mapsService.generateMap();
        if(map.getState() == State.CREATED) System.out.println("Good #1");

        map.setName("Nazwa dla mapy");

        // jakaś edycja
        //mapsService.saveMap(map);
        genMap2();

    }

    private void genMap2(){
        for(int i=0; i<50; i++) {
            for(int j=0; j<50; j++) {
                Cell cell = map.get(i, j);
                CellType type = cell.getType();
                //System.out.println(type);

                Rectangle rec = new Rectangle();
                Paint paint;
                switch (type){
                    case Green:
                        paint = Paint.valueOf(colors[0]);
                        break;
                    case Road:
                        paint = Paint.valueOf(colors[1]);
                        break;
                    case Building:
                        paint = Paint.valueOf(colors[2]);
                        break;
                    default:
                        paint = Paint.valueOf("#000000");
                        break;
                }
                rec.setFill(paint);
                rec.setWidth(12);
                rec.setHeight(12);
                rec.setStroke(Color.BLACK);
                rec.setStrokeWidth(0.15);
                rec.setId("map" + i + j);
                grid.add(rec,i,j);
            }
        }
    }



    private void genMap(){
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
    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            orgSceneX = mouseEvent.getSceneX();
            orgSceneY = mouseEvent.getSceneY();
            orgTranslateX = ((ImageView)(mouseEvent.getSource())).getTranslateX();
            orgTranslateY = ((ImageView)(mouseEvent.getSource())).getTranslateY();
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
                ((ImageView)(mouseEvent.getSource())).setTranslateX(newTranslateX);
            }
            if(mouseEvent.getSceneY()>0 && mouseEvent.getSceneY()<600){
                ((ImageView)(mouseEvent.getSource())).setTranslateY(newTranslateY);
            }
        }
    };

    EventHandler<MouseEvent> circleOnMouseReleasedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            ImageView circ = (ImageView)(mouseEvent.getSource());
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
            //System.out.println(Paint.valueOf("#66FF99"));
            if(rec.getFill().equals(Paint.valueOf("#62AD53"))) {
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

    public void addObject(Image image) {
        ImageView pic = new ImageView();
        pic.setFitWidth(20);
        pic.setFitHeight(20);
        pic.setImage(image);
        pic.setCursor(Cursor.OPEN_HAND);
        pic.setOnMousePressed(circleOnMousePressedEventHandler);
        pic.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        pic.setOnMouseReleased(circleOnMouseReleasedEventHandler);
        grid.add(pic,0,0);
    }

    public void simulate() {
        resetSimulation();
        ArrayList<Circle> circles = getObjects();
        for(int i=0; i<50; i++) {
            for(int j=0; j<50; j++) {
                Rectangle rec = new Rectangle();
                rec.setFill(Color.RED);
                rec.setId("rec" + i + j);
                rec.setWidth(12);
                rec.setHeight(12);
                rec.setOpacity(0.4);
                grid.add(rec,i,j);
            }
        }
    }

    public void resetSimulation() {
        for(int i=0; i<50; i++) {
            for(int j=0; j<50; j++) {
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
