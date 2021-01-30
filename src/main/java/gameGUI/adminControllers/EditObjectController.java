package gameGUI.adminControllers;

import common.enums.CellType;
import common.enums.ObjectType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.service.admin.AdminException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class EditObjectController implements Initializable {

    @FXML
    public TextField nameField;
    @FXML
    public TextField widthField;
    @FXML
    public TextField lengthField;
    @FXML
    public TextField heightField;
    @FXML
    public ChoiceBox<ObjectType> objectTypeList;
    @FXML
    public ChoiceBox<CellType> allowedTypeList;
    @FXML
    public TextField priceField;
    @FXML
    public TextField heatFactorField;
    @FXML
    public Button saveBtn;

    UUID id;
    String name;
    int width;
    int length;
    int height;
    ObjectType type;
    CellType allowedTerrainType;
    int price;
    double heatFactor;
    private AdminManager adminManager;

    public void setAdminManager(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    public EditObjectController(UUID id, String name, int width, int length, int height, CellType allowedTerrainType, ObjectType objectType, int price, double heatFactor) {

        this.id = id;
        this.name = name;
        this.width = width;
        this.length = length;
        this.height = height;
        this.type = objectType;
        this.allowedTerrainType = allowedTerrainType;
        this.price = price;
        this.heatFactor = heatFactor;

   }


    public void saveObject() throws AdminException {
        width = parseInt(widthField.getText());
        length = parseInt(lengthField.getText());
        height = parseInt(heightField.getText());
        type = objectTypeList.getSelectionModel().getSelectedItem();
        allowedTerrainType = allowedTypeList.getSelectionModel().getSelectedItem();
        price = parseInt(priceField.getText());
        heatFactor = parseDouble(heatFactorField.getText());
        adminManager.updateMapObject(id, name, height, type, allowedTerrainType, price, heatFactor);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        objectTypeList.getItems().setAll(ObjectType.values());
        allowedTypeList.getItems().setAll(CellType.values());

        nameField.setText(name);
        widthField.setText(String.valueOf(width));
        lengthField.setText(String.valueOf(length));
        heightField.setText(String.valueOf(height));
        objectTypeList.getSelectionModel().select(type);
        allowedTypeList.getSelectionModel().select(allowedTerrainType);
        priceField.setText(String.valueOf(price));
        heatFactorField.setText(String.valueOf(heatFactor));


    }
}
