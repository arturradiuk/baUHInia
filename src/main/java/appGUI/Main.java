package appGUI;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Main extends Application {

    static Stage stage;
    static double xOffset = 0;
    static double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            Parent root = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource("scenes/admin_game.fxml"))
            );
            
            setView(root);
            
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setOnCloseRequest((WindowEvent we) -> System.exit(0));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setView(Parent root) {
        //setWindowDraggable(root);
        stage.setScene(new Scene(root));
    }
    
    private static void setWindowDraggable(Parent root) {
        root.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });

        root.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
        });
    }
}
