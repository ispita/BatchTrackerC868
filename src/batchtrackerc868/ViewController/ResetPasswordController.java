/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.ViewController;

import static batchtrackerc868.Model.DBQueries.setPassword;
import batchtrackerc868.Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author flavius8
 */
public class ResetPasswordController implements Initializable {
    User currentUser;
    /**
     * Initializes the controller class.
     */
    @FXML TextField passwordInput,passwordConfirmInput;
    
    @FXML
    public void handleResetPasswordButton(ActionEvent e) throws Exception{
        if(passwordInput.getText().equals(passwordConfirmInput.getText()) && !passwordInput.getText().equals("BATCHTRACKER")
                && passwordInput.getText().length() >= 8 && passwordInput.getText().matches(".*\\d.*") && passwordInput.getText().matches(".*[a-z].*")){
        boolean successPass = setPassword(currentUser.getUsername().get(), passwordInput.getText());
        
        if (successPass){
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
            System.out.println(passwordInput.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Password Chosen");
            alert.setHeaderText("Enter a new password!");
            alert.setContentText("Passwords must match, cannot be the same as the previous password, and meet the following criteria: \n"
                    + "Be at least 8 characters or longer \n"
                    + "Contain at least 1 number \n"
                    + "Contain at least 1 character");
       
            alert.showAndWait();
        }
        }
        else{
            System.out.println(passwordInput.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Password Chosen");
            alert.setHeaderText("Enter a new password!");
            alert.setContentText("Passwords must match, cannot be the same as the previous password, and meet the following criteria: \n"
                    + "Be at least 8 characters or longer \n"
                    + "Contain at least 1 number"
                    + "Contain at least 1 character");
       
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        public void setCurrentUser(User passCurrentUser){
        this.currentUser = passCurrentUser;
    }
}
