/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.ViewController;

import static batchtrackerc868.Model.DBQueries.*;
import batchtrackerc868.Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author flavius8
 */
public class DeleteUserController implements Initializable {
    User currentUser;
    private ObservableList<String> userList = FXCollections.observableArrayList();
    @FXML ComboBox userName;
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleDeleteButton(ActionEvent e)throws Exception{
        
        if (userName.getValue().toString() != null){
        deleteUser(userName.getValue().toString());
        Stage viewHomeStage; 
        Parent viewHomeRoot; 
        viewHomeStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        viewHomeRoot = loader.load();
        
        HomeController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene viewHomeScene = new Scene(viewHomeRoot);
        viewHomeStage.setScene(viewHomeScene);           
        viewHomeStage.show(); 
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Parameters");
            alert.setHeaderText("Select a name to delete");
            alert.setContentText("You must select a name to delete");
       
            alert.showAndWait();
        }
    }
    @FXML
    private void handleReturnHome(ActionEvent e)throws Exception{
        Stage viewHomeStage; 
        Parent viewHomeRoot; 
        viewHomeStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        viewHomeRoot = loader.load();
        
        HomeController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene viewHomeScene = new Scene(viewHomeRoot);
        viewHomeStage.setScene(viewHomeScene);           
        viewHomeStage.show(); 
    }    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        userName.setItems(getEmployees());
        
    }        
    
    public void setCurrentUser(User passCurrentUser){
        this.currentUser = passCurrentUser;
    }  
}
