/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.Model;

import java.sql.Timestamp;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author fborojan
 */
public class Batch {
    private IntegerProperty pkBatch;
    private StringProperty batchNumber,employeeName,employeeDepartment;
    private Timestamp timeIn,timeOut;

    
    public Batch(){
        this.pkBatch = new SimpleIntegerProperty();
        this.batchNumber = new SimpleStringProperty();
        this.employeeName = new SimpleStringProperty();

    }
    
    public IntegerProperty getPkBatch() {
        return pkBatch;
    }

    public void setPkBatch(Integer pkBatch) {
        this.pkBatch.set(pkBatch);
    }

    public StringProperty getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber.set(batchNumber);
    }

    public StringProperty getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName.set(employeeName);
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Timestamp timeOut) {
        this.timeOut = timeOut;
    }
    public StringProperty getEmployeeDepartment(){
        return employeeDepartment;
        
    }
}
