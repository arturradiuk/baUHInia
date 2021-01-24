package appGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import login.IAuthentication;
import login.LoginService;
import login.UserData;
import login.UserType;
import login.exceptions.IncorrectDataException;
import login.exceptions.UserNotFoundException;

public class RegistrationController implements Initializable {
    
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField confirmEmailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private CheckBox registerAdminCheck;
    @FXML
    private TextField adminEmailField;
    @FXML
    private PasswordField adminPasswordField;
    @FXML
    private Text warningText;

    private IAuthentication registrationService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registrationService = LoginService.getInstance();
        resetStyle();
    }

    @FXML
    public void register() {
        resetStyle();
        if (!validateEmptiness()) return;

        if (!emailField.getText().equals(confirmEmailField.getText())) {
            markFieldAsInvalid(emailField);
            markFieldAsInvalid(confirmEmailField);
            displayWarning("Podane adresy e-mail nie pokrywają się.");
            return;
        }
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            markFieldAsInvalid(passwordField);
            markFieldAsInvalid(confirmPasswordField);
            displayWarning("Podane hasła nie pokrywają się.");
            return;
        }

        if (registerAdminCheck.isSelected()) {
            registerAsAdmin();
        } else {
           registerAsCitizen();
        }
    }
    
    private void registerAsAdmin() {
        try {
            boolean result = registrationService.registerAsAdmin(
                    adminEmailField.getText(), adminPasswordField.getText(),
                    new UserData(firstNameField.getText(), lastNameField.getText(),
                            emailField.getText(), passwordField.getText(), UserType.Administrator));
            if (result) {
                displayInfo("Dodano nowego administratora.");
            } else {
                displayWarning("Dodanie nowego administratora nie powiodło się.");
            }
        } catch (IncorrectDataException e) {
            if (e.getMessage().equals("Email wrong!")) {
                markFieldAsInvalid(emailField);
                displayWarning("Niepoprawny format adresu e-mail.");
            } else {
                markFieldAsInvalid(passwordField);
                displayWarning("Hasło musi składać się z co najmniej 6 znaków.");
            }
        } catch (UserNotFoundException e) {
            markFieldAsInvalid(adminEmailField);
            markFieldAsInvalid(adminPasswordField);
            displayWarning("Dane uwierzytelniające administratora są niepoprawne.");
        }
    }

    private void registerAsCitizen() {
        try {
            boolean result = registrationService.register(
                    new UserData(firstNameField.getText(), lastNameField.getText(),
                            emailField.getText(), passwordField.getText(), UserType.General));
            if (result) {
                displayInfo("Dodano nowego mieszkańca.");
            } else {
                displayWarning("Dodanie nowego mieszkańca nie powiodło się.");
            }
        } catch (IncorrectDataException e) {
            if (e.getMessage().equals("Email wrong!")) {
                markFieldAsInvalid(emailField);
                displayWarning("Niepoprawny format adresu e-mail.");
            } else {
                markFieldAsInvalid(passwordField);
                displayWarning("Hasło musi składać się z co najmniej 6 znaków.");
            }
        }
    }
    
    public boolean validateEmptiness() {
        if (firstNameField.getText().isEmpty()) {
            markFieldAsInvalid(firstNameField);
            displayWarning("Podaj imię.");
            return false;
        } else if (lastNameField.getText().isEmpty()) {
            markFieldAsInvalid(lastNameField);
            displayWarning("Podaj nazwisko.");
            return false;
        } else if (emailField.getText().isEmpty()) {
            markFieldAsInvalid(emailField);
            displayWarning("Podaj adres e-mail.");
            return false;
        } else if (passwordField.getText().isEmpty()) {
            markFieldAsInvalid(passwordField);
            displayWarning("Podaj hasło.");
            return false;
        }
        return true;
    }
    
    @FXML
    public void displayAdminLogin() {
        if (registerAdminCheck.isSelected()) {
            adminEmailField.setVisible(true);
            adminPasswordField.setVisible(true);
            displayInfo("Dodanie nowego administratora wymaga uwierzytelnienia.");
        } else if (!registerAdminCheck.isSelected()) {
            adminEmailField.setVisible(false);
            adminPasswordField.setVisible(false);
            warningText.setText("");
        }
    }
    
    private void resetStyle() {
        warningText.setText("");
        firstNameField.setStyle("-fx-border-color:  #aa8976");
        lastNameField.setStyle("-fx-border-color:  #aa8976");
        emailField.setStyle("-fx-border-color:  #aa8976");
        confirmEmailField.setStyle("-fx-border-color:  #aa8976");
        passwordField.setStyle("-fx-border-color:  #aa8976");
        confirmPasswordField.setStyle("-fx-border-color:  #aa8976");
        adminEmailField.setStyle("-fx-border-color:  #aa8976");
        adminPasswordField.setStyle("-fx-border-color:  #aa8976");
    }
    
    private void displayInfo(String info) {
        warningText.setFill(Color.GREEN);
        warningText.setText(info);
    }
    
    private void displayWarning(String warning) {
        warningText.setFill(Color.RED);
        warningText.setText(warning);
    }

    private void markFieldAsInvalid(TextField field) {
        field.setStyle("-fx-border-color: #ff0303");
    }
    
    @FXML
    public void goBackToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("scenes/login.fxml"));
        Parent root = loader.load();
        Main.setView(root);
    }

    @FXML
    public void quitApplication() {
        Platform.exit();
        System.exit(0);
    }
}
