/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    
    
public static void insertBatchIn(String batch, String name, String department){        
    try{      
        String SQL = "INSERT INTO batch_input (batch_number,employee_name,employee_department) " 
                + "VALUES (?,?,?)";
        PreparedStatement sqlInsert = DBConn.prepareStatement(SQL);
        sqlInsert.setString(1, batch);
        sqlInsert.setString(2, name);
        sqlInsert.setString(3, department);
        sqlInsert.executeUpdate();  
     
    }
    catch(SQLException e){

          System.out.println("Error: " + e);            
    }

}
}
