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
    
        public static User setCurrentUser(String uname){
    User currentUser = null;
    try{
        String uName = null;
        Integer userID = null;
        String department = null;
        String query = "SELECT userId,userName,department FROM user where userName = ?";
        PreparedStatement prepStmnt = DBConn.prepareStatement(query);
        prepStmnt.setString(1,uname);
        ResultSet result = prepStmnt.executeQuery();

       while (result.next()){
            userID = result.getInt("userId");
            uName = result.getString("userName");
            department = result.getString("department");
       }
        currentUser = new User(userID,uName,department);
        }
        catch(SQLException e){
            System.out.println("Error: " + e);
        }
         return currentUser;
    }
        
    public static boolean userCredential(String uname, String pass){
           try{ 
      String pWord = null;

      String query = "SELECT password FROM user where userName = ?";
      PreparedStatement prepStmnt = DBConn.prepareStatement(query);
      prepStmnt.setString(1,uname);
      ResultSet result = prepStmnt.executeQuery();
  
       while (result.next()){
            if(result.getString(1) == null){
                return false;
            }
            else{

                 pWord = result.getString("password");
                     if (pWord.equals(pass)){
                       
                        return true;
                     }
                      else{
                       return false;
                       }
            }
            }      
      }
      catch(SQLException er){
          System.out.println("Error: " + er);
      }
           
      return false;
    }
        
    
    
    public static boolean setPassword(String username, String password){
        try{
            String SQL = "UPDATE user SET password = ? where username = ?";
            PreparedStatement prepStmnt = DBConn.prepareStatement(SQL);
            prepStmnt.setString(1, password);
            prepStmnt.setString(2, username);
            prepStmnt.executeUpdate();
            return true;
        }
        catch(SQLException e){
            System.out.println("Error: " + e);
        }
        return false;
    }
    
    
    public static ObservableList assembleBatchData(){        
    ObservableList batchData = FXCollections.observableArrayList();
    try{      
        String SQL = "SELECT * FROM batch_input ORDER BY pk_batch DESC";            
        ResultSet result = DBConn.createStatement().executeQuery(SQL);  
        while(result.next()){
            Batch batch = new Batch();
            switch (result.getString("employee_department")){
                    case "Extractions":
                       batch = new ExtractionsBatch();
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name")); 
//                       batch.setTimeIn(result.getTimestamp("time_in"));
//                       batch.setTimeOut(result.getTimestamp("time_out"));
                       batchData.add(batch);            
                       break;
                    case "Screening":
                       batch = new ScreeningBatch();
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name")); 
//                       batch.setTimeIn(result.getTimestamp("time_in"));
//                       batch.setTimeOut(result.getTimestamp("time_out"));                       
                       batchData.add(batch);                         
                        break;
                        
                    case "Data Review":
                       batch = new DataReviewBatch();
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name"));          
//                       batch.setTimeIn(result.getTimestamp("time_in"));
//                       batch.setTimeOut(result.getTimestamp("time_out"));                      
                       batchData.add(batch);                         
                        break;
            }
        }
    }
    catch(SQLException e){

          System.out.println("Error: " + e);            
    }
    return batchData;
}
    

    public static ObservableList assembleBatchDataPostInput(String department){        
    ObservableList batchData = FXCollections.observableArrayList();
    try{      
        String SQL = "SELECT * FROM batch_input where employee_department = ? ORDER BY pk_batch DESC";
        PreparedStatement prepStmt = DBConn.prepareStatement(SQL);
        prepStmt.setString(1, department);
        ResultSet result = prepStmt.executeQuery();  
        while(result.next()){
            Batch batch = new Batch();
            switch (result.getString("employee_department")){
                    case "Extractions":
                       batch = new ExtractionsBatch();
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name"));          
//                       batch.setTimeIn(result.getTimestamp("time_in"));
//                       batch.setTimeOut(result.getTimestamp("time_out"));                      
                       batchData.add(batch);            
                       break;
                    case "Screening":
                       batch = new ScreeningBatch();
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name"));          
//                       batch.setTimeIn(result.getTimestamp("time_in"));
//                       batch.setTimeOut(result.getTimestamp("time_out"));
                       batchData.add(batch);                         
                        break;
                        
                    case "Data Review":
                       batch = new DataReviewBatch();
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name"));          
//                       batch.setTimeIn(result.getTimestamp("time_in"));
//                       batch.setTimeOut(result.getTimestamp("time_out"));
                       batchData.add(batch);                         
                        break;
            }
        }
    }
    catch(SQLException e){

          System.out.println("Error: " + e);            
    }
    return batchData;
}    
    
    
public static void insertBatchIn(String batch, String name, String department){        
    try{      
        
        String SQL1 = "SELECT * FROM batch_input WHERE batch_number = ?";
        PreparedStatement prepStmt = DBConn.prepareStatement(SQL1);
        prepStmt.setString(1, batch);
        ResultSet result = prepStmt.executeQuery(); 
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
                System.out.println("TEST");
                Batch sqlBatch = new Batch();
                switch (result.getString("employee_department")){
                    case "Extractions":
                      sqlBatch = new ExtractionsBatch();
                      sqlBatch.setPkBatch(result.getInt("pk_batch"));
                      sqlBatch.setBatchNumber(result.getString("batch_number"));
                      sqlBatch.setEmployeeName(result.getString("employee_name"));
//                      sqlBatch.setTimeIn(result.getTimestamp("time_in"));
//                      sqlBatch.setTimeOut(result.getTimestamp("time_out"));
                        break;
                    case "Screening":
                      sqlBatch = new ScreeningBatch();
                      sqlBatch.setPkBatch(result.getInt("pk_batch"));
                      sqlBatch.setBatchNumber(result.getString("batch_number"));
                      sqlBatch.setEmployeeName(result.getString("employee_name"));
//                      sqlBatch.setTimeIn(result.getTimestamp("time_in"));
//                      sqlBatch.setTimeOut(result.getTimestamp("time_out"));                        
                        break;
                    case "Data Review":
                      sqlBatch = new DataReviewBatch();
                      sqlBatch.setPkBatch(result.getInt("pk_batch"));
                      sqlBatch.setBatchNumber(result.getString("batch_number"));
                      sqlBatch.setEmployeeName(result.getString("employee_name"));
//                      sqlBatch.setTimeIn(result.getTimestamp("time_in"));
//                      sqlBatch.setTimeOut(result.getTimestamp("time_out"));                        
                        break;
                }
                System.out.println("exiting case");
//      USE RESULT INSTEAD OF BATCH?
//                if (sqlBatch.getBatchNumber() != null && sqlBatch.getTimeIn() != null && sqlBatch.getTimeOut() != null){
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
                    String SQL2 = "UPDATE batch_input set time_out = CURRENT_TIMESTAMP WHERE employee_name = ? AND pk_batch = ? AND batch_number = ? AND employee_department = ?";
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
}
