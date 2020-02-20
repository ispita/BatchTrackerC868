/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static utils.DBConnection.getDbConn;
/**
 *
 * @author fborojan
 */
public class DBQueries {
    public static Connection DBConn = utils.DBConnection.getDbConn();
    
    
    public static ObservableList assembleBatchData(){        
    ObservableList batchData = FXCollections.observableArrayList();
    try{      
        String SQL = "SELECT * FROM batch_input";            
        ResultSet result = DBConn.createStatement().executeQuery(SQL);  
        while(result.next()){
            Batch batch = new Batch();
            batch.setBatchNumber(result.getString("batch_number"));
            batch.setEmployeeName(result.getString("employee_name"));
            batch.setEmployeeDepartment(result.getString("employee_department"));            
            batchData.add(batch);            
        }
     
    }
    catch(SQLException e){

          System.out.println("Error: " + e);            
    }
    return batchData;
}
}
