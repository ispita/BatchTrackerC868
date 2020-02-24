/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author flavius8
 */
public class User {
    private final IntegerProperty userID;
    private final StringProperty username;
    private final StringProperty department;

    
    public User (Integer ID,String uName, String department){
        this.userID = new SimpleIntegerProperty(ID);
        this.username = new SimpleStringProperty(uName);
        this.department = new SimpleStringProperty(department);
    }
    
    public User(){
        this.userID = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.department = new SimpleStringProperty();
    }
    public IntegerProperty getUserId(){
        return userID;
    }
    public StringProperty getUsername(){
        return username;
    } 
    public StringProperty getDepartment(){
        return department;
    }

    
    
}