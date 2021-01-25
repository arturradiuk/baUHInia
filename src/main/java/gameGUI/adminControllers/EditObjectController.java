package gameGUI.adminControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class EditObjectController implements Initializable {
    @FXML
    public BorderPane boarderPane;
    @FXML
    public TextField saveField;
    @FXML
    public TextField widthField;
    @FXML
    public TextField lengthField;
    @FXML
    public TextField heightField;
    @FXML
    public TextField objectTypeField;
    @FXML
    public TextField allowedTypeField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField heatFactorField;


//    public EditObjectController(File file, int width, int length, int height, String type, String allowedTerrainType, int price, double heatFactor) {
//        this.file = file;
//        this.width = width;
//        this.length = length;
//        this.height = height;
//        this.type = type;
//        this.allowedTerrainType = allowedTerrainType;
//        this.price = price;
//        this.heatFactor = heatFactor;
//
//        widthField.setText(file.getAbsolutePath());
//        widthField.setText(String.valueOf(width));
//        widthField.setText(String.valueOf(length));
//        widthField.setText(String.valueOf(height));
//        widthField.setText(type);
//        widthField.setText(allowedTerrainType);
//        widthField.setText(String.valueOf(price));
//        widthField.setText(String.valueOf(heatFactor));
//    }

    //File file;
    int width;
    int length;
    int height;
    String type;                    //ObjectType
    String allowedTerrainType;      //CellType
    int price;
    double heatFactor;


    //public void setFile(File file) {
        //this.file = file;
   // }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAllowedTerrainType(String allowedTerrainType) {
        this.allowedTerrainType = allowedTerrainType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHeatFactor(double heatFactor) {
        this.heatFactor = heatFactor;
    }



    public void browse() {

        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) boarderPane.getScene().getWindow();
        //file = fileChooser.showOpenDialog(stage);
        //if(file != null) {
        //    saveField.setText(file.getAbsolutePath());
        //}
    }
/*
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
*/
    public void saveObject(){
        width = parseInt(widthField.getText());
        length = parseInt(lengthField.getText());
        height = parseInt(heightField.getText());
        type = objectTypeField.getText();
        allowedTerrainType = allowedTypeField.getText();
        price = parseInt(priceField.getText());
        heatFactor = parseDouble(heatFactorField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.file =  new File("C:\\Users\\szers\\Desktop\\baUHInia\\src\\main\\resources\\images\\png.png");
//        this.width = 10;
//        this.length = 10;
//        this.height = 10;
//        this.type = "type";
//        this.allowedTerrainType = "allowedTerrainType";
//        this.price = 111;
//        this.heatFactor = 1.44;

        //saveField.setText(file.getAbsolutePath());
        /*
        widthField.setText(String.valueOf(width));
        lengthField.setText(String.valueOf(length));
        heightField.setText(String.valueOf(height));
        objectTypeField.setText(type);
        allowedTypeField.setText(allowedTerrainType);
        priceField.setText(String.valueOf(price));
        heatFactorField.setText(String.valueOf(heatFactor));*/
    }
}
