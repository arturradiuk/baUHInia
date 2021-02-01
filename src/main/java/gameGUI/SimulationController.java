package gameGUI;

import common.enums.CellType;
import database.DataBaseException;
import gameGUI.clientControllers.ClientManager;
import gamelogic.GameLogic;
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

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class SimulationController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane anchorScroll;

    @FXML
    private GridPane grid;

    private final ObservableList<StackPane> stackPanes = FXCollections.observableArrayList();
    private ClientManager clientManager;
    //private IMapsService mapsService;
    private Map map;
    private UUID currentObjectID;
    private MapObject currentObject;
    private Image currentImage;
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;
    private String[] colors = {"#62AD53", "#8F8F8F", "#80682E"};

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<MapObject> test = null;
        try {
            test = clientManager.returnAvailableObjects();

            for(int i=0; i<test.size(); i++) {
                System.out.println("objects/" + test.get(i).getGuid() + ".png");
                StackPane stackPane = new StackPane();
                stackPane.setId("pane" + i);
                stackPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f0e2d0"), CornerRadii.EMPTY, Insets.EMPTY)));
                stackPane.setPadding((new Insets(3, 3, 3, 3)));
                stackPane.setPrefSize(100,170);
                BorderPane borderPane = new BorderPane();
                borderPane.setPadding((new Insets(10, 10, 10, 10)));
                borderPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f0e2d0"), CornerRadii.EMPTY, Insets.EMPTY)));
                borderPane.setPrefSize(20,40);
                borderPane.setCursor(Cursor.HAND);
                VBox myvbox = new VBox();
                Label width = new Label("Szerokość: " + test.get(i).getWidth());
                Label length = new Label("Długość: " + test.get(i).getWidth());
                Label height = new Label("Wysokość: " + test.get(i).getHeight());
                Label objectType = new Label("Typ obiektu: " + test.get(i).getType());
                Label allowedTerrainType = new Label("Podłoże: " + test.get(i).getAllowedTerrainType());
                Label price = new Label("Cena: " + test.get(i).getPrice());
                Label heatFactor = new Label("Współczynnik ciepła: " + test.get(i).getHeatFactor());

                myvbox.getChildren().addAll(width, length, height, objectType, allowedTerrainType, price, heatFactor);
                Text title = new Text(test.get(i).getName());

                try {
                    Image image;
                    //System.out.println(".\\resources\\objects\\" + test.get(i).getGuid() + ".png");
                    File fileName = new File(".\\resources\\objects\\"+ test.get(i).getName() + ".png");
                    image = new Image(fileName.toURI().toString());
                    ImageView pic = new ImageView();
                    pic.setFitWidth(30);
                    pic.setFitHeight(30);
                    pic.setImage(image);
                    borderPane.setCenter(pic);
                    UUID id = test.get(i).getGuid();
                    MapObject finalTest = test.get(i);
                    borderPane.setOnMouseClicked(actionEvent -> {
                                for(int j=0; j<stackPanes.size(); j++) {
                                    StackPane pane = (StackPane) vbox.lookup("#pane" + j);
                                    pane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f0e2d0"), CornerRadii.EMPTY, Insets.EMPTY)));
                                }
                                stackPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#000000"), CornerRadii.EMPTY, Insets.EMPTY)));
                                currentImage = image;
                                currentObjectID = id;
                                currentObject = finalTest;
                            }
                    );

                } catch (Exception e) {
                    e.printStackTrace();
                }

                borderPane.setTop(title);
                borderPane.setBottom(myvbox);
                BorderPane.setAlignment(myvbox, Pos.CENTER);
                BorderPane.setAlignment(title, Pos.CENTER);

                stackPane.getChildren().add(borderPane);
                stackPanes.add(stackPane);
                //if(test.get(i).getGuid().equals(map.getObjectsMetadata().get(0).getMapObjectGuid()))
                    //System.out.println(test.get(i).getGuid());
            }
            int i = test.size();
            int length = i*(13+165+13+2);

            if(length < 545) {
                anchorScroll.setPrefHeight(546);
            } else anchorScroll.setPrefHeight(length);


            vbox.getChildren().addAll(stackPanes);
        } catch (DataBaseException e) {
            e.printStackTrace();


        }


        map = clientManager.returnMap();
        genMap2();
        System.out.println(map.getObjects());
        System.out.println(map.getObjectsGuidList());
        //System.out.println(clientManager.);
        //System.out.println(map.getObjectsMetadata().get(0).getX());
        //System.out.println(map.getObjectsMetadata().get(0).getY());

    }

    private void genMap2(){
        for(int i=0; i<50; i++) {
            for(int j=0; j<50; j++) {
                Cell cell = map.get(i, j);
                CellType type = cell.getType();
                System.out.println(type);

                Rectangle rec = new Rectangle();
                Paint paint;
                switch (type){
                    case Green:
                        paint = Paint.valueOf(colors[0]);
                        break;
                    case Road:
                        paint = Paint.valueOf(colors[1]);
                        break;
                    case Concrete:
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
                int finalI = i;
                int finalJ = j;
                rec.setOnMouseClicked(e -> {
                    System.out.printf("Mouse enetered cell [%d, %d]%n", finalI, finalJ);
                    if(currentImage!=null)
                        addObject(currentImage, finalI, finalJ);
                    //locationBox.setText("x: " + finalI + ", y: " + finalJ);
                    //currentCellX = finalI;
                    //currentCellY = finalJ;
                });
                grid.add(rec,i,j);
            }
        }
    }

    private void loadObjects(){
        for(int i=0; i<map.getObjectsMetadata().size(); i++) {
            int cellX = map.getObjectsMetadata().get(i).getX();
            int cellY = map.getObjectsMetadata().get(i).getY();
            File fileName = new File(".\\resources\\objects\\"+ map.getObjectsMetadata().get(i).getMapObject().getName() + ".png");
            Image imageLocal = new Image(fileName.toURI().toString());
            ImageView pic = new ImageView();
            int base = 12;
            int multipleX = map.getObjectsMetadata().get(i).getMapObject().getWidth();
            int multipleY = map.getObjectsMetadata().get(i).getMapObject().getLength();
            pic.setTranslateY(6 * (multipleY - 1));
            pic.setFitWidth(multipleX * base);
            pic.setFitHeight(multipleY * base);
            pic.setImage(imageLocal);
            pic.setOnMouseClicked(e -> removeObject(pic, cellX, cellY) );
            grid.add(pic,cellX,cellY);
        }
    }


    public void addObject(Image image, int i, int j) {
        try {
            clientManager.placeObject(new Point(i,j), currentObjectID);

            ImageView pic = new ImageView();
            int base = 12;
            int multipleX = currentObject.getWidth();
            int multipleY = currentObject.getLength();
            pic.setTranslateY(6 * (multipleY - 1));
            pic.setFitWidth(multipleX * base);
            pic.setFitHeight(multipleY * base);
            pic.setImage(image);
            //pic.setCursor(Cursor.OPEN_HAND);
            pic.setOnMouseClicked(e -> removeObject(pic, i, j) );
            //pic.setOnMousePressed(circleOnMousePressedEventHandler);
            //pic.setOnMouseDragged(circleOnMouseDraggedEventHandler);
            //pic.setOnMouseReleased(circleOnMouseReleasedEventHandler);
            grid.add(pic,i,j);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }


    }

    public void removeObject(ImageView imageView, int i, int j) {
        try {
            clientManager.removeObject(new Point(i,j));
            grid.getChildren().remove(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void simulate() {
        ArrayList<ArrayList<Double>> sim;
        sim = clientManager.returnHeatMap();

        System.out.println(sim);

        resetSimulation();
        for(int i=0; i<50; i++) {
            for(int j=0; j<50; j++) {
                Rectangle rec = new Rectangle();

                double r = sim.get(i).get(j) * 5.1;
                double g = 255 - r;
                System.out.println(r + " " + g);
                if(r > 255)
                    r = 255;
                if(r < 0)
                    r = 0;
                if(g > 255)
                    g = 255;
                if(g < 0)
                    g = 0;
                rec.setFill(Color.rgb((int) r,(int) g,0));
                rec.setId("rec" + i + j);
                rec.setWidth(12);
                rec.setHeight(12);
                rec.setOpacity(0.8);
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

    public void save() {
        clientManager.saveMap();
        System.out.println("Saving");
    }




}
