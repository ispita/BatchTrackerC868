/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.ViewController;

import batchtrackerc868.Model.Batch;
import static batchtrackerc868.Model.DBQueries.*;
import batchtrackerc868.Model.User;
import java.net.URL;
import java.sql.Timestamp;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author flavius8
 */
public class ReportsController implements Initializable {
        User currentUser;
    String year;
    String month;
    String yearType;
    String monthType;
    String week;
    private ObservableList<Batch> appointmentData = FXCollections.observableArrayList();
    @FXML private TableView<Batch> employeeTable;
    @FXML private TableView<Batch> departmentTable;
    @FXML
    private TableColumn<Batch, String> batch_number;
    @FXML
    private TableColumn<Batch, String> employee_name;
    @FXML
    private TableColumn<Batch, String> employee_department;
    @FXML
    private TableColumn<Batch, Timestamp> timeIn;    
    @FXML
    private TableColumn<Batch, Timestamp> timeOut;    
//    @FXML
//    private TableColumn<Batch, Integer> pk_batchDep;
    @FXML
    private TableColumn<Batch, String> batch_numberDep;
    @FXML
    private TableColumn<Batch, String> employee_nameDep;
    @FXML
    private TableColumn<Batch, String> employee_departmentDep;
    @FXML
    private TableColumn<Batch, Timestamp> timeInDep;    
    @FXML
    private TableColumn<Batch, Timestamp> timeOutDep;    
    @FXML
    Pane addBatchPane;
    @FXML
    TextField custID;
    @FXML
    TextField title;
    @FXML
    TextField description;
    @FXML
    TextField location;
    @FXML
    TextField contact;
    @FXML
    TextField type;
    @FXML
    TextField url;
    @FXML
    DatePicker start;
    @FXML
    private ComboBox<String> pickYearType;
    @FXML
    private ComboBox<String> pickMonthType;
    @FXML
    private ComboBox<String> pickYear;
    @FXML
    private ComboBox<String> pickMonth;    
    @FXML
    private ComboBox<String> employeeNames;
    @FXML
    private ComboBox<String> departmentNames;      
    @FXML
    private Label employeeLabel;
    @FXML
    private Label departmentLabel;

    boolean addDisabled = true;
    boolean addVisible = false;
    Integer apptLength;
    @FXML
    public void handleAddBatchButton(ActionEvent e){
        System.out.println("You Pushed Add Batch Button");
       addDisabled = false;
       addVisible = true;
       addBatchPane.setDisable(addDisabled);
       addBatchPane.setVisible(addVisible);
       custID.setText("");
       title.setText("");
       description.setText("");
       location.setText("");
       contact.setText("");
       type.setText("");
       url.setText("");
       //end.setValue(null);
       start.setValue(null);
    }
   
    
    
    @FXML
    public void handleExitButton(ActionEvent e)throws Exception{
        Stage homeStage; 
        Parent homeRoot; 
        homeStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        homeRoot = loader.load();
        
        HomeController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene homeScene = new Scene(homeRoot);
        homeStage.setScene(homeScene);           
        homeStage.show(); 
    }
    
    @FXML
    public void handleYearSelect(ActionEvent e){
       year = pickYear.getValue();       
    }
    

        @FXML
    public void handleYearSelectType(ActionEvent e){
       yearType = pickYearType.getValue();       

    }

   @FXML void handleGetDepartmentProductivity(ActionEvent e){
        departmentTable.setItems(assembleBatchDataPostInput(departmentNames.getValue().toString()));
        departmentLabel.setText(departmentNames.getValue().toString() + " Productivity Tracker");
  }
   @FXML void handleGetEmployeeProductivity(ActionEvent e){
        employeeTable.setItems(assembleBatchDataEdit(employeeNames.getValue().toString(),getUserDepartment(employeeNames.getValue().toString())));
        employeeLabel.setText(employeeNames.getValue().toString() + " Productivity Tracker");
  }
    
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        batch_number.setCellValueFactory(cellData -> cellData.getValue().getBatchNumber());      
        employee_name.setCellValueFactory(cellData -> cellData.getValue().getEmployeeName());
        employee_department.setCellValueFactory(cellData -> cellData.getValue().getEmployeeDepartment());
        timeIn.setCellValueFactory(cellData -> cellData.getValue().getTimeIn());
        timeOut.setCellValueFactory(cellData -> cellData.getValue().getTimeOut());
        batch_numberDep.setCellValueFactory(cellData -> cellData.getValue().getBatchNumber());      
        employee_nameDep.setCellValueFactory(cellData -> cellData.getValue().getEmployeeName());
        employee_departmentDep.setCellValueFactory(cellData -> cellData.getValue().getEmployeeDepartment());
        timeInDep.setCellValueFactory(cellData -> cellData.getValue().getTimeIn());
        timeOutDep.setCellValueFactory(cellData -> cellData.getValue().getTimeOut());
        employeeNames.setItems(getEmployees());
        departmentNames.setItems(getDepartments());

    }   
    
    public void setCurrentUser(User passCurrentUser){
        this.currentUser = passCurrentUser;
    }
}

