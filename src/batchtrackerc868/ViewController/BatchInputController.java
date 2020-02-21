/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.ViewController;

import batchtrackerc868.Model.Batch;
import static batchtrackerc868.Model.DBQueries.assembleBatchData;
import batchtrackerc868.Model.ExtractionsBatch;
//import static batchtrackerc868.Model.DBQueries.insertBatchIn;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fborojan
 */
public class BatchInputController implements Initializable {
    
    @FXML private TextField batchNumber,employeeName,employeeDepartment; 
    @FXML private TextField batchNumber1,employeeName1,employeeDepartment1; 
    @FXML private TableView<Batch> batchesInTable;
    @FXML
    private TableColumn<Batch, String> batch_number;
    @FXML
    private TableColumn<Batch, String> employee_name;
    @FXML
    private TableColumn<Batch, String> employee_department;





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        batch_number.setCellValueFactory(cellData -> cellData.getValue().getBatchNumber());      
        employee_name.setCellValueFactory(cellData -> cellData.getValue().getEmployeeName());
        employee_department.setCellValueFactory(cellData -> cellData.getValue().getEmployeeDepartment());
        batchesInTable.setItems(assembleBatchData());
    }   
    
    
    
    @FXML
    private void handleSubmitButton(ActionEvent e){
//        insertBatchIn(batchNumber.getText(),employeeName.getText(),employeeDepartment.getText());
//        batchNumber.setText("");
//        employeeName.setText("");
//        employeeDepartment.setText("");
        batchesInTable.setItems(assembleBatchData());

    }
        
    @FXML
    private void handleSubmitButton1(ActionEvent e){
        batchNumber1.setText("");
        employeeName1.setText("");
        employeeDepartment1.setText("");
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
}
