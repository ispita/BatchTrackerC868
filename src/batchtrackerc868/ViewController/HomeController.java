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
import javafx.stage.Stage;

/**
 *
 * @author fborojan
 */
public class HomeController implements Initializable {

    @FXML
    private void handleButtonBatch(ActionEvent e)throws Exception {
        Stage viewBatchInputStage; 
        Parent viewBatchInputRoot; 
        viewBatchInputStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BatchInput.fxml"));
        viewBatchInputRoot = loader.load();
        
//        BatchInputController controller = loader.getCRontroller();
//        controller.setCurrentUser(currentUser);
        Scene viewBatchInputScene = new Scene(viewBatchInputRoot);
        viewBatchInputStage.setScene(viewBatchInputScene);           
        viewBatchInputStage.show(); 

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
