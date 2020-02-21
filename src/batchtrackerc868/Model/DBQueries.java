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
        
        String SQL1 = "SELECT pk_batch,batch_number,time_in,time_out,pk_batch,department FROM batches WHERE batch_number = ?";
        PreparedStatement prepStmt = DBConn.prepareStatement(SQL1);
        prepStmt.setString(1, batch);
        ResultSet result = DBConn.createStatement().executeQuery(SQL1); 
        if (!result.isBeforeFirst()){
        String SQL2 = "INSERT INTO batch_input (batch_number,employee_name,employee_department) " 
                + "VALUES (?,?,?)";
        PreparedStatement sqlInsert = DBConn.prepareStatement(SQL2);
        sqlInsert.setString(1, batch);
        sqlInsert.setString(2, name);
        sqlInsert.setString(3, department);
        sqlInsert.executeUpdate();  
        }
        else{
            while(result.next()){
                Batch sqlBatch = new Batch();
                sqlBatch.setPkBatch(result.getInt("pk_batch"));
                sqlBatch.setBatchNumber(result.getString("batch_number"));
                sqlBatch.setEmployeeName(result.getString("employee_name"));
                sqlBatch.setEmployeeDepartment(result.getString("employee_department"));
                sqlBatch.setTimeIn(result.getTimestamp("time_in"));
                sqlBatch.setTimeOut(result.getTimestamp("time_out"));
                if (sqlBatch.getBatchNumber() != null && sqlBatch.getTimeIn() != null && sqlBatch.getTimeOut() != null){
                       String SQL2 = "INSERT INTO batch_input (batch_number,employee_name,employee_department) " 
                                + "VALUES (?,?,?)";
                        PreparedStatement sqlInsert = DBConn.prepareStatement(SQL2);
                          sqlInsert.setString(1, batch);
                          sqlInsert.setString(2, name);
                          sqlInsert.setString(3, department);
                          sqlInsert.executeUpdate();                      
                }
                //else if statement to cover if an employee forgets to check their batch out at the end of a shift. This allows the next department to work with the batch
                //without having any issues when scanning the batch into the system.
                else if (sqlBatch.getBatchNumber().get() != null && sqlBatch.getTimeIn() != null && sqlBatch.getTimeOut() == null && !sqlBatch.getEmployeeDepartment().get().equals(department)){
                                           String SQL2 = "INSERT INTO batch_input (batch_number,employee_name,employee_department) " 
                                + "VALUES (?,?,?)";
                        PreparedStatement sqlInsert = DBConn.prepareStatement(SQL2);
                          sqlInsert.setString(1, batch);
                          sqlInsert.setString(2, name);
                          sqlInsert.setString(3, department);
                          sqlInsert.executeUpdate();    
                }
                else if (sqlBatch.getBatchNumber().get() != null && sqlBatch.getTimeOut() == null){
                    String SQL2 = "UPDATE batches set time_out = CURRENT_TIMESTAMP,tech_out = ? WHERE pk_batch = ? AND batch_number = ? AND department = ?";
                          PreparedStatement sqlInsert = DBConn.prepareStatement(SQL2);
                          sqlInsert.setString(1, name);
                          sqlInsert.setInt(2, sqlBatch.getPkBatch().get());
                          sqlInsert.setString(3, batch);
                          sqlInsert.setString(4, department);
                          sqlInsert.executeUpdate();    
                    
                }
        }
        }
        }
    catch(SQLException e){

          System.out.println("Error: " + e);            
    }

}


//    connect.query(
//      'SELECT batch_number,time_in,time_out,pk_batch,department FROM batches WHERE batch_number = ?',
//      [this.state.batch_number],
//      function(err, result) {
//        if (err) throw err;
//        console.log(result);
//        if (result.length === 0) {
//          connect.query(
//            'INSERT INTO batches (batch_number,tech_in,department) VALUES (?)',
//            [recordsInput],
//            function(err, result) {
//              if (err) throw err;
//              console.log(result);
//            }
//          );
//        } else if (
//          result[result.length - 1].batch_number !== null &&
//          result[result.length - 1].time_in !== null &&
//          result[result.length - 1].time_out !== null
//        ) {
//          console.log(result);
//          connect.query(
//            'INSERT INTO batches (batch_number,tech_in,department) VALUES (?)',
//            [recordsInput],
//            function(err, result) {
//              if (err) throw err;
//              console.log(result);
//            }
//          );
//        } else if (
//          result[result.length - 1].batch_number !== null &&
//          result[result.length - 1].time_in !== null &&
//          result[result.length - 1].time_out == null &&
//          result[result.length - 1].department !== this.state.department
//        ) {
//          console.log(result[result.length - 1].department);
//          console.log(result);
//          connect.query(
//            'INSERT INTO batches (batch_number,tech_in,department) VALUES (?)',
//            [recordsInput],
//            function(err, result) {
//              if (err) throw err;
//              console.log(result);
//            }
//          );
//        } else if (
//          result[result.length - 1].batch_number !== null &&
//          result[result.length - 1].time_out === null
//        ) {
//          connect.query(
//            'UPDATE batches set time_out = CURRENT_TIMESTAMP,tech_out = ? WHERE pk_batch = ? AND batch_number = ? AND department = ?',
//            [
//              this.state.tech,
//              result[result.length - 1].pk_batch,
//              this.state.batch_number,
//              this.state.department
//            ],
//
//            function(err, result) {
//              if (err) throw err;
//              console.log(result);
//            }
//          );
//        }
//        this.setState({
//          batch_number: '',
//          tech: '',
//          department: ''
//        });
//      }.bind(this)
//    );
//  };


}
