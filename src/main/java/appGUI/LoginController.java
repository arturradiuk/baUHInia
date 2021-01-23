package appGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import login.LoginService;
import login.UserType;
import login.exceptions.IncorrectDataException;
import login.exceptions.UserNotFoundException;

public class LoginController implements Initializable {
    
    @FXML
    private TextField emailField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Text warningText;
    
    private LoginService loginService;
    private UserType userType;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginService = LoginService.getInstance();
        resetStyle();
        warningText.setText("");
    }
    
    @FXML
    public void login() throws IOException {
        resetStyle();
        
        if(authenticate()) {
            if (userType == UserType.General) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("scenes/citizen_game.fxml"));
                Parent root = loader.load();
                Main.setView(root);
            } else if (userType == UserType.Administrator) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("scenes/admin_game.fxml"));
                Parent root = loader.load();
                Main.setView(root);
            }
        }
    }
    
    @FXML
    public void goToRegistration() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("scenes/registration.fxml"));
        Parent root = loader.load();
        Main.setView(root);
    }
    
    private boolean authenticate() {
        if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            markFieldAsInvalid(emailField);
            markFieldAsInvalid(passwordField);
            warningText.setText("Uzupełnij dane logowania!");
        }
        try {
            userType = loginService.login(emailField.getText(), passwordField.getText());
        } catch (UserNotFoundException e) {
            warningText.setText("Nie znaleziono użytkownika o podanym adresie e-mail.");
            markFieldAsInvalid(emailField);
            return false;
        } catch (IncorrectDataException e) {
            warningText.setText("Nieprawidłowe hasło.");
            markFieldAsInvalid(passwordField);
            return false;
        }
        return true;
    }

    private void resetStyle() {
        warningText.setText("");
        emailField.setStyle("-fx-border-color: #70af85");
        passwordField.setStyle("-fx-border-color: #70af85");
    }

    private void markFieldAsInvalid(TextField field) {
        field.setStyle("-fx-border-color: #ff0303");
    }

    @FXML
    public void quitApplication() {
        Platform.exit();
        System.exit(0);
    }
}
