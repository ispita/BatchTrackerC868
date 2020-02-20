/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.ViewController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fborojan
 */
public class BatchInputController implements Initializable {
    
    @FXML private TextField batchNumber,employeeName,employeeDepartment; 
    
    
    
    @FXML
    private void handleSubmitButton(ActionEvent e){
        batchNumber.setText("");
        employeeName.setText("");
        employeeDepartment.setText("");
    }
    
    @FXML
    private void handleReturnHome(ActionEvent e)throws Exception{
        Stage viewHomeStage; 
        Parent viewHomeRoot; 
        viewHomeStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        viewHomeRoot = loader.load();
//        
//        HomeController controller = loader.getController();
//        controller.setCurrentUser(currentUser);
        Scene viewHomeScene = new Scene(viewHomeRoot);
        viewHomeStage.setScene(viewHomeScene);           
        viewHomeStage.show(); 
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
