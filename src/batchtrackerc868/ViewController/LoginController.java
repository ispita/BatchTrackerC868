/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.ViewController;

import java.io.File;
import java.io.IOException;
import batchtrackerc868.Model.DBQueries;
import batchtrackerc868.Model.User;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static batchtrackerc868.Model.DBQueries.userCredential;


/**
 *
 * @author flavius8
 */
public class LoginController implements Initializable {

    String uLabel;
    String pLabel;
    @FXML
    Label userLabel;
    @FXML
    Label passLabel;
    @FXML
    TextField passwordInput;
    @FXML
    TextField usernameInput;
    String username;
    String password;
    User currentUser = null;

    @FXML
    private void handleLoginButton(ActionEvent e)throws Exception{
        
        username = usernameInput.getText();
        password = passwordInput.getText();
        boolean credCheck = userCredential(username,password);
        if(credCheck == true){
        currentUser = DBQueries.setCurrentUser(username);
            System.out.println(currentUser.getUsername().get());
        if (!password.equals("BATCHTRACKER")){
        Stage homeStage; 
        Parent homeRoot; 
        homeStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        homeRoot = loader.load();
        
        HomeController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene homepageScene = new Scene(homeRoot);
        homeStage.setScene(homepageScene);           
        homeStage.show(); 
        }
        else{
        Stage resetPasswordStage; 
        Parent resetPasswordRoot; 
        resetPasswordStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
        resetPasswordRoot = loader.load();
        
        ResetPasswordController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene resetPasswordScene = new Scene(resetPasswordRoot);
        resetPasswordStage.setScene(resetPasswordScene);           
        resetPasswordStage.show();             
        }
        }
        else{

            System.out.println(usernameInput.getText());
            System.out.println(passwordInput.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Username/Password");
            alert.setHeaderText("Username or password entered incorrectly!");
            alert.setContentText("Please re-enter your username/password");
       
            alert.showAndWait();
        }
        
    }
    
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            this.uLabel = "username";
            this.pLabel = "password";
            this.userLabel.setText(uLabel);
            this.passLabel.setText(pLabel);
    }    
    
}