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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.Instant;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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
        
    
     public static boolean setUser(String username, String department, String password){
        try{
            String SQL = "INSERT INTO user (username,department,password) VALUES (?,?,?)";
            PreparedStatement prepStmnt = DBConn.prepareStatement(SQL);
            prepStmnt.setString(1, username);
            prepStmnt.setString(2, department);
            prepStmnt.setString(3, password);
            prepStmnt.executeUpdate();
            return true;
        }
        catch(SQLException e){
            System.out.println("Error: " + e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Parameters");
            alert.setHeaderText("Enter a UNIQUE name and select a department!");
            alert.setContentText("You must enter a UNIQUE name and select a department");
       
            alert.showAndWait();
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
    
    public static boolean deleteUser(String username){
        try{
            String SQL = "DELETE FROM user WHERE username = ? and username != 'admin'";
            PreparedStatement prepStmnt = DBConn.prepareStatement(SQL);
            prepStmnt.setString(1, username);
            prepStmnt.executeUpdate();
            return true;
        }
        catch(SQLException e){
            System.out.println("Error: " + e);
        }
        return false;
    }
    public static boolean modifyUser(String username, String newUsername, String department, String password){
        try{
            String SQL = "UPDATE user set username = ?, department = ?, password = ?  WHERE username = ? and username != 'admin'";
            PreparedStatement prepStmnt = DBConn.prepareStatement(SQL);
            prepStmnt.setString(1, newUsername);
            prepStmnt.setString(2, department);
            prepStmnt.setString(3, password);
            prepStmnt.setString(4, username);
            prepStmnt.executeUpdate();
            return true;
        }
        catch(SQLException e){
            System.out.println("Error: " + e);
        }
        return false;
    }
    
        public static String getUserDepartment(String username){
            String userDep = null;
        try{
            String SQL = "SELECT department FROM user WHERE username = ?";
            PreparedStatement prepStmnt = DBConn.prepareStatement(SQL);
            prepStmnt.setString(1, username);
            ResultSet result = prepStmnt.executeQuery();
            while(result.next()){
                userDep = result.getString("department");
                return userDep;
            }
        }
        catch(SQLException e){
            System.out.println("Error: " + e);
        }
        return userDep;
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
                       batch.setPkBatch(result.getInt("pk_batch"));
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name")); 
                       batch.setTimeIn(result.getTimestamp("time_in"));
                       batch.setTimeOut(result.getTimestamp("time_out"));
                       batchData.add(batch);            
                       break;
                    case "Screening":
                       batch = new ScreeningBatch();
                       batch.setPkBatch(result.getInt("pk_batch"));
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name")); 
                       batch.setTimeIn(result.getTimestamp("time_in"));
                       batch.setTimeOut(result.getTimestamp("time_out"));                       
                       batchData.add(batch);                         
                        break;
                        
                    case "Data Review":
                       batch = new DataReviewBatch();
                       batch.setPkBatch(result.getInt("pk_batch"));
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name"));          
                       batch.setTimeIn(result.getTimestamp("time_in"));
                       batch.setTimeOut(result.getTimestamp("time_out"));                      
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
    
    public static ObservableList assembleBatchDataEdit(String name, String department){        
    ObservableList batchDataEdit = FXCollections.observableArrayList();
    try{      
        String SQL = "SELECT * FROM batch_input WHERE employee_name = ? AND employee_department = ? ORDER BY pk_batch DESC"; 
        PreparedStatement prepStmnt = DBConn.prepareStatement(SQL);
        prepStmnt.setString(1, name);
        prepStmnt.setString(2, department);
        ResultSet result = prepStmnt.executeQuery();  
        while(result.next()){
            Batch batch = new Batch();
            switch (result.getString("employee_department")){
                    case "Extractions":
                       batch = new ExtractionsBatch();
                       batch.setPkBatch(result.getInt("pk_batch"));
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name")); 
                       batch.setTimeIn(result.getTimestamp("time_in"));
                       batch.setTimeOut(result.getTimestamp("time_out"));
                       batchDataEdit.add(batch);            
                       break;
                    case "Screening":
                       batch = new ScreeningBatch();
                       batch.setPkBatch(result.getInt("pk_batch"));
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name")); 
                       batch.setTimeIn(result.getTimestamp("time_in"));
                       batch.setTimeOut(result.getTimestamp("time_out"));                       
                       batchDataEdit.add(batch);                         
                        break;
                        
                    case "Data Review":
                       batch = new DataReviewBatch();
                       batch.setPkBatch(result.getInt("pk_batch"));
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name"));          
                       batch.setTimeIn(result.getTimestamp("time_in"));
                       batch.setTimeOut(result.getTimestamp("time_out"));                      
                       batchDataEdit.add(batch);                         
                        break;
            }
        }
    }
    catch(SQLException e){

          System.out.println("Error: " + e);            
    }
    return batchDataEdit;
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
                       batch.setPkBatch(result.getInt("pk_batch"));
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name"));          
                       batch.setTimeIn(result.getTimestamp("time_in"));
                       batch.setTimeOut(result.getTimestamp("time_out"));                      
                       batchData.add(batch);            
                       break;
                    case "Screening":
                       batch = new ScreeningBatch();
                       batch.setPkBatch(result.getInt("pk_batch"));
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name"));          
                       batch.setTimeIn(result.getTimestamp("time_in"));
                       batch.setTimeOut(result.getTimestamp("time_out"));
                       batchData.add(batch);                         
                        break;
                        
                    case "Data Review":
                       batch = new DataReviewBatch();
                       batch.setPkBatch(result.getInt("pk_batch"));
                       batch.setBatchNumber(result.getString("batch_number"));
                       batch.setEmployeeName(result.getString("employee_name"));          
                       batch.setTimeIn(result.getTimestamp("time_in"));
                       batch.setTimeOut(result.getTimestamp("time_out"));
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
    
     public static ObservableList getYears(){
     ObservableList yearData = FXCollections.observableArrayList();
     try{
         String SQL = "select distinct YEAR(time_in) as year from batch_input";
         ResultSet result = DBConn.createStatement().executeQuery(SQL);
         while(result.next()){
             yearData.add(result.getString("year"));
         }
     }
     catch(SQLException e){
         System.out.println("Error: " + e);
     }
     return yearData;
 }
     
     public static ObservableList getEmployees(){
     ObservableList employeeList = FXCollections.observableArrayList();
     try{
         String SQL = "select distinct username from user where username != 'admin'";
         ResultSet result = DBConn.createStatement().executeQuery(SQL);
         while(result.next()){
             employeeList.add(result.getString("username"));
         }
     }
     catch(SQLException e){
         System.out.println("Error: " + e);
     }
     return employeeList;
 }
          public static ObservableList getDepartments(){
     ObservableList departmentList = FXCollections.observableArrayList();
     try{
         String SQL = "select distinct department from user where username != 'admin'";
         ResultSet result = DBConn.createStatement().executeQuery(SQL);
         while(result.next()){
             departmentList.add(result.getString("department"));
         }
     }
     catch(SQLException e){
         System.out.println("Error: " + e);
     }
     return departmentList;
 }
public static void insertBatchIn(String batch, String name, String department){        
    try{      
        
        String SQL1 = "SELECT * FROM batch_input WHERE batch_number = ? ORDER BY pk_batch DESC LIMIT 1";
        PreparedStatement prepStmt = DBConn.prepareStatement(SQL1);
        prepStmt.setString(1, batch);
        ResultSet result = prepStmt.executeQuery(); 
        if (!result.isBeforeFirst()){
            System.out.println("entered first if statement");
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
                      sqlBatch.setTimeIn(result.getTimestamp("time_in"));
                      sqlBatch.setTimeOut(result.getTimestamp("time_out"));
                        break;
                    case "Screening":
                      sqlBatch = new ScreeningBatch();
                      sqlBatch.setPkBatch(result.getInt("pk_batch"));
                      sqlBatch.setBatchNumber(result.getString("batch_number"));
                      sqlBatch.setEmployeeName(result.getString("employee_name"));
                      sqlBatch.setTimeIn(result.getTimestamp("time_in"));
                      sqlBatch.setTimeOut(result.getTimestamp("time_out"));                        
                        break;
                    case "Data Review":
                      sqlBatch = new DataReviewBatch();
                      sqlBatch.setPkBatch(result.getInt("pk_batch"));
                      sqlBatch.setBatchNumber(result.getString("batch_number"));
                      sqlBatch.setEmployeeName(result.getString("employee_name"));
                      sqlBatch.setTimeIn(result.getTimestamp("time_in"));
                      sqlBatch.setTimeOut(result.getTimestamp("time_out"));                        
                        break;
                }
                System.out.println("exiting case");
                //If a batch comes back to the department and needs to be redone this checks to see if the batch exists and has been checked out.
                //If it is it will then it will check it in again.
                if (sqlBatch.getBatchNumber().get() != null && result.getTimestamp("time_out") != null){
                       String SQL2 = "INSERT INTO batch_input (batch_number,employee_name,employee_department) " 
                                + "VALUES (?,?,?)";
                        PreparedStatement sqlInsert = DBConn.prepareStatement(SQL2);
                          sqlInsert.setString(1, batch);
                          sqlInsert.setString(2, name);
                          sqlInsert.setString(3, department);
                          sqlInsert.executeUpdate();                      
                }
                //else if statement to cover if an employee forgets to check their batch out at the end of a shift. This allows the next department to work with the batch
                //without having any issues when checking the batch into the system.
                else if (sqlBatch.getBatchNumber().get() != null && result.getTimestamp("time_out") == null && !sqlBatch.getEmployeeDepartment().get().equals(department)){
                                           String SQL2 = "INSERT INTO batch_input (batch_number,employee_name,employee_department) " 
                                + "VALUES (?,?,?)";
                        PreparedStatement sqlInsert = DBConn.prepareStatement(SQL2);
                          sqlInsert.setString(1, batch);
                          sqlInsert.setString(2, name);
                          sqlInsert.setString(3, department);
                          sqlInsert.executeUpdate();    
                }
                // Checks a batch out
                else if (sqlBatch.getBatchNumber().get() != null && result.getTimestamp("time_out") == null){
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

public static void modifyBatch(Integer pk_batch, String batchNumber, Timestamp start, Timestamp end){
        try{
            
        String SQLa = "SELECT * FROM batch_input WHERE pk_batch = ?";
        PreparedStatement statementa =DBConn.prepareStatement(SQLa);
        statementa.setInt(1,pk_batch);
        ResultSet result = statementa.executeQuery();
         if (result.isBeforeFirst()){       
        String SQL = "UPDATE batch_input set batch_number = ?,"
                + " time_in = ?, time_out = ? WHERE pk_batch = ?";
        PreparedStatement statement =DBConn.prepareStatement(SQL);
        statement.setString(1,batchNumber);
        statement.setTimestamp(2, start);
        statement.setTimestamp(3, end);
        statement.setInt(4, pk_batch);
        statement.executeUpdate();
         }

    }
    catch(SQLException e){
        System.out.println("Error: " + e);

    }
    
}

public static ObservableList getStartEndTimesBatch(Integer pk_batch){
    ObservableList batchTimes = FXCollections.observableArrayList();
        try{
    String SQL = "SELECT time_in,time_out from batch_input where pk_batch = ?";
    PreparedStatement statement =DBConn.prepareStatement(SQL);
    statement.setInt(1,pk_batch);
    ResultSet result = statement.executeQuery();
    while(result.next()){
//            Date currentStartDate = result.getTimestamp("start");
//            LocalDate currentStartLocalDate = currentStartDate.toInstant().toLocalDate();
//            LocalTime currentStartLocalTime = currentStartDate.toInstant().toLocalTime(); 
//            LocalDateTime currentStartLocalDateTime = LocalDateTime.of(currentStartLocalDate, currentStartLocalTime);
        System.out.println("start: " + result.getTimestamp("time_in") + " END: " + result.getTimestamp("time_out"));
        batchTimes.add(result.getTimestamp("time_in"));

        batchTimes.add(result.getTimestamp("time_out"));
    }
    }
    catch(SQLException e){
        System.out.println("Error: " + e);
    }
        return batchTimes;
}

public static ObservableList deleteBatch(Integer pk_batch){
    ObservableList batchTimes = FXCollections.observableArrayList();
        try{
    String SQL = "DELETE FROM batch_input where pk_batch = ?";
    PreparedStatement statement =DBConn.prepareStatement(SQL);
    statement.setInt(1,pk_batch);
    statement.executeUpdate();

    }
    catch(SQLException e){
        System.out.println("Error: " + e);
    }
        return batchTimes;
}

}
