package gameGUI.adminControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import common.enums.ObjectType;
import common.enums.CellType;
import logic.service.admin.AdminException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class SaveObjectController implements Initializable {
    @FXML
    public BorderPane boarderPane;
    @FXML
    public TextField saveField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField widthField;
    @FXML
    public TextField lengthField;
    @FXML
    public TextField heightField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField heatFactorField;
    @FXML
    public ChoiceBox<ObjectType> objectTypeList;
    @FXML
    public ChoiceBox<CellType> allowedTypeList;


    private AdminManager adminManager;

    public void setAdminManager(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    File file;
    String name;
    int width;
    int length;
    int height;
    //String type;                    //ObjectType
    //String allowedTerrainType;      //CellType
    int price;
    double heatFactor;


    public void browse() {

        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) boarderPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            saveField.setText(file.getAbsolutePath());
        }
    }

    public void saveToFile() throws IOException {
        File fileName;
        if(file.exists() && ImageIO.read(file) != null){
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(file.getAbsolutePath()));
            } catch (IOException e) {
                System.out.println("Read error for " + file.getPath() +
                        ": " + e.getMessage());
            }
            fileName = new File("D:\\root\\"+file.getName());
            try {
                ImageIO.write(img, "jpg", fileName);
            } catch(IOException e) {
                System.out.println("Write error for " + file.getPath() +
                        ": " + e.getMessage());
            }
        }
    }

    public void saveObject() throws AdminException {
        name = nameField.getText();
        width = parseInt(widthField.getText());
        length = parseInt(lengthField.getText());
        height = parseInt(heightField.getText());
        ObjectType type = objectTypeList.getSelectionModel().getSelectedItem();
        CellType cell = allowedTypeList.getSelectionModel().getSelectedItem();
        //System.out.println(type + " " + cell);
        price = parseInt(priceField.getText());
        heatFactor = parseDouble(heatFactorField.getText());
        //adminManager.addNewMapObject(name, width, length, height, type1, cell, price, heatFactor);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        objectTypeList.getItems().setAll(ObjectType.values());
        allowedTypeList.getItems().setAll(CellType.values());
    }
}
