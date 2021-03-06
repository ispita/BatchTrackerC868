/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.ViewController;

import static batchtrackerc868.Model.DBQueries.setUser;
import batchtrackerc868.Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
public class AddUserController implements Initializable {
    User currentUser;
    private ObservableList<String> departmentList = FXCollections.observableArrayList();
    @FXML TextField userName, password;
    @FXML ComboBox department;
    Pattern pattern = Pattern.compile("\\s");
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleSaveButton(ActionEvent e)throws Exception{
        String uName = userName.getText().trim();
        Matcher matcher = pattern.matcher(uName);
        boolean whiteSpaceExists = matcher.find();
        if (!uName.equals("") && department.getValue() != null && !whiteSpaceExists){
        setUser(uName,department.getValue().toString(),password.getText());
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
            alert.setHeaderText("Enter a name and select a department!");
            alert.setContentText("You must enter a Username(no spaces!) and select a department");
       
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
        departmentList.addAll("Extractions","Screening","Data Review","admin");
        department.setItems(departmentList);
        
    }        
    
    public void setCurrentUser(User passCurrentUser){
        this.currentUser = passCurrentUser;
    }
    
    
}
