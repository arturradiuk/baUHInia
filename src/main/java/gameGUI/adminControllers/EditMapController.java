package gameGUI.adminControllers;

import common.enums.CellType;
import common.enums.ObjectType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class EditMapController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane anchorScroll;

    @FXML
    private GridPane grid;

    @FXML
    private Label locationBox;

    @FXML
    public ChoiceBox<CellType> cellTypeList;

    private final ObservableList<Button> buttons = FXCollections.observableArrayList();
    private final ObservableList<StackPane> stackPanes = FXCollections.observableArrayList();
    private AdminManager adminService;
    private IMapsService mapsService;
    private Map map;
    private UUID currentMap;
    private int currentCellX = -1, currentCellY = -1;
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;
    private String[] colors = {"#62AD53", "#8F8F8F", "#80682E"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapsService = new MapsService(new FilesystemMapsProvider(".\\resources\\maps"));
        adminService = new AdminManager();
                //grid.add(rec,0,0);



        //if(map.getState() == State.CREATED) System.out.println("Good #1");

       // map.setName("Nazwa dla mapy");

        // jakaś edycja
        //mapsService.saveMap(map);
        cellTypeList.getItems().setAll(CellType.values());
        genMap2();

    }

    public void setCurrentMap(UUID id) {
        currentMap = id;
        System.out.println("Setter:" + currentMap);
    }

    private void genMap2(){
        map = mapsService.getMap(currentMap);
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
                int finalI = i;
                int finalJ = j;
                rec.setOnMouseClicked(e -> {
                    System.out.printf("Mouse enetered cell [%d, %d]%n", finalI, finalJ);
                    locationBox.setText("x: " + finalI + ", y: " + finalJ);
                    currentCellX = finalI;
                    currentCellY = finalJ;
                });

                grid.add(rec,i,j);
            }
        }
    }

    public void switchLockCell() {
        if(currentCellX > 0 && currentCellY > 0){
            try {
                adminService.switchLockCell(currentMap, currentCellX, currentCellY);
                System.out.println("test");
                grid.getChildren().clear();
                genMap2();
                System.out.println("test2");
            } catch (AdminException e) {
                e.printStackTrace();
            }

        }
    }

    public void updateCellType() {
        CellType type = cellTypeList.getSelectionModel().getSelectedItem();
        if(currentCellX > 0 && currentCellY > 0 && type != null){
            try {
                adminService.updateCellType(currentMap, currentCellX, currentCellY, type);
                System.out.println("test");
                grid.getChildren().clear();
                genMap2();
                System.out.println("test2");
            } catch (AdminException e) {
                e.printStackTrace();
            }

        }

    }



}
