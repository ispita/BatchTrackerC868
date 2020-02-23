/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.ViewController;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author fborojan
 */
public class HomeController implements Initializable {
    User currentUser;
   
    @FXML Pane adminPane;

    @FXML
    private void handleButtonBatch(ActionEvent e)throws Exception {
        Stage viewBatchInputStage; 
        Parent viewBatchInputRoot; 
        viewBatchInputStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BatchInput.fxml"));
        viewBatchInputRoot = loader.load();
        
        BatchInputController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene viewBatchInputScene = new Scene(viewBatchInputRoot);
        viewBatchInputStage.setScene(viewBatchInputScene);           
        viewBatchInputStage.show(); 

    }
    
       @FXML
    private void handleButtonReports(ActionEvent e)throws Exception {
        Stage viewReportsStage; 
        Parent viewReportsRoot; 
        viewReportsStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reports.fxml"));
        viewReportsRoot = loader.load();
        
        ReportsController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene viewReportsScene = new Scene(viewReportsRoot);
        viewReportsStage.setScene(viewReportsScene);           
        viewReportsStage.show(); 

    }
    @FXML
    private void handleButtonAddUser(ActionEvent e)throws Exception {
        Stage viewAddUserStage; 
        Parent viewAddUserRoot; 
        viewAddUserStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
        viewAddUserRoot = loader.load();
        
        AddUserController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene viewAddUserScene = new Scene(viewAddUserRoot);
        viewAddUserStage.setScene(viewAddUserScene);           
        viewAddUserStage.show(); 

    }
    @FXML
    private void handleButtonDeleteUser(ActionEvent e)throws Exception {
        Stage viewDeleteUserStage; 
        Parent viewDeleteUserRoot; 
        viewDeleteUserStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteUser.fxml"));
        viewDeleteUserRoot = loader.load();
        
        DeleteUserController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene viewDeleteUserScene = new Scene(viewDeleteUserRoot);
        viewDeleteUserStage.setScene(viewDeleteUserScene);           
        viewDeleteUserStage.show(); 

    }
    @FXML
    private void handleButtonModifyUser(ActionEvent e)throws Exception {
        Stage viewModifyUserStage; 
        Parent viewModifyUserRoot; 
        viewModifyUserStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyUser.fxml"));
        viewModifyUserRoot = loader.load();
        
        ModifyUserController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene viewModifyUserScene = new Scene(viewModifyUserRoot);
        viewModifyUserStage.setScene(viewModifyUserScene);           
        viewModifyUserStage.show(); 

    }
     
    public void setCurrentUser(User passCurrentUser){
        this.currentUser = passCurrentUser;
        if (currentUser.getDepartment().get().equals("admin")){
            adminPane.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
