/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.ViewController;

import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import batchtrackerc868.Model.Batch;
import batchtrackerc868.Model.User;
import static batchtrackerc868.Model.DBQueries.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author flavius8
 */
public class ModifyBatchController implements Initializable {
Batch modifiedBatch;
User currentUser;

    @FXML
    TextField batchID;
    @FXML
    TextField batchNumber;
    @FXML
    TextField employeeName;
    @FXML
    TextField employeeDepartment;
    @FXML
    DatePicker start;
    @FXML
    DatePicker end;
    @FXML
    private ComboBox<String> pickStartHour;
    @FXML
    private ComboBox<String> pickStartMin;
    @FXML
    private ComboBox<String> pickEndHour;
    @FXML
    private ComboBox<String> pickEndMin;

    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    ObservableList<String> length = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @FXML
    public void handleModifyBatchSaveButton(ActionEvent e)throws Exception{
     LocalDate startDate = start.getValue();
     LocalDate endDate = end.getValue();

     String startHour = pickStartHour.getValue();
     String startMinute = pickStartMin.getValue();
     String endHour = pickEndHour.getValue();
     String endMinute = pickEndMin.getValue();
     LocalDateTime startDateTime = LocalDateTime.of(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), Integer.parseInt(startHour), Integer.parseInt(startMinute));
     LocalDateTime endDateTime = LocalDateTime.of(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMinute));

     Timestamp startDateTimeT = Timestamp.valueOf(startDateTime);
     Timestamp endDateTimeT = Timestamp.valueOf(endDateTime);

     modifyBatch(modifiedBatch.getPkBatch().get(),batchNumber.getText(),
                    startDateTimeT,endDateTimeT);
        Stage viewBatchStage; 
        Parent viewBatchRoot; 
        viewBatchStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BatchInput.fxml"));
        viewBatchRoot = loader.load();
        
        BatchInputController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene viewBatchScene = new Scene(viewBatchRoot);
        viewBatchStage.setScene(viewBatchScene);           
        viewBatchStage.show(); 
    }
    
    @FXML
    public void handleModifyBatchCancelButton(ActionEvent e)throws Exception{
        Stage viewBatchStage; 
        Parent viewBatchRoot; 
        viewBatchStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BatchInput.fxml"));
        viewBatchRoot = loader.load();
        
        BatchInputController controller = loader.getController();
        controller.setCurrentUser(currentUser);
        Scene viewBatchScene = new Scene(viewBatchRoot);
        viewBatchStage.setScene(viewBatchScene);           
        viewBatchStage.show(); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
       
    } 
    
    
    public void setBatch(Batch selectedBatch, User passCurrentUser){
        System.out.println("SelectedBatch department: " + selectedBatch.getPkBatch());
        this.modifiedBatch = selectedBatch;
        this.currentUser = passCurrentUser;
       batchID.setText(Integer.toString(selectedBatch.getPkBatch().get()));
       employeeName.setText(currentUser.getUsername().get());
       employeeDepartment.setText(currentUser.getDepartment().get());
       batchNumber.setText(selectedBatch.getBatchNumber().get());
       ObservableList batchTimes = getStartEndTimesBatch(selectedBatch.getPkBatch().get());
      
       LocalDate batchStart = LocalDate.parse(batchTimes.get(0).toString().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       LocalDate batchEnd = null;
       if(batchTimes.get(1) != null){
       batchEnd = LocalDate.parse(batchTimes.get(1).toString().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       }
        //System.out.println("appTimes: " + batchTimes.get(0).toString().substring(9, 2));
       LocalTime batchStartTime = LocalTime.parse(batchTimes.get(0).toString().substring(11, 16), DateTimeFormatter.ofPattern("HH:mm"));
       LocalTime batchEndTime = null;
       if(batchTimes.get(1) != null){
       batchEndTime = LocalTime.parse(batchTimes.get(1).toString().substring(11, 16), DateTimeFormatter.ofPattern("HH:mm"));
       }
        hours.addAll("08", "09", "10", "11", "12", "13", "14", "15", "16", "17");
        minutes.addAll("00", "15", "30", "45");
        length.addAll("15", "30", "45","60");
        pickStartHour.setItems(hours);
        pickStartMin.setItems(minutes);
        pickEndHour.setItems(hours);
        pickEndMin.setItems(minutes);
        start.setValue(batchStart);
        if(batchTimes.get(1) != null){
        end.setValue(batchEnd);
        }
        pickStartMin.setValue(Integer.toString(batchStartTime.getMinute()));
        pickStartHour.setValue(Integer.toString(batchStartTime.getHour()));
        if(batchTimes.get(1) != null){
        pickEndMin.setValue(Integer.toString(batchEndTime.getMinute()));
        pickEndHour.setValue(Integer.toString(batchEndTime.getHour()));
        }

       
    }

}
