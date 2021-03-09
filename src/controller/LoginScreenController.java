package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sean Fenner
 */
public class LoginScreenController implements Initializable {
    // Text fields
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    // Label
    @FXML private Label errorLabel;
    
    // =========================================================================
    // MEHTODS
    // =========================================================================
    
    private boolean checkCredentials(String username, String password){
        return username.equals("username") && password.equals("password");
    }
    // -------------------------------------------------------------------------
    
    @FXML public void loginButtonPushed(ActionEvent event) throws IOException {
        if (checkCredentials(usernameTextField.getText(), passwordTextField.getText())){
            Parent changeParent = FXMLLoader.load(getClass().getResource("/view/DashboardScreen.fxml"));
            Scene changeScene = new Scene(changeParent);
            changeScene.getStylesheets().add(getClass().getResource("/files/chartStyle.css").toExternalForm());

            Stage stage;
            stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
            stage.setScene(changeScene);
            stage.setX(200);
            stage.setY(100);
            stage.show();  
        } else {
            errorLabel.setText("Invalid Username and Password!");
        }
    } 
    // -------------------------------------------------------------------------
    
    @FXML public void exitButtonPushed(ActionEvent event) throws IOException {
        Stage stage;
        stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
        stage.close();
    }
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // INITIALIZE METHOD
    // =========================================================================
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setText("");
    }    
    
}
