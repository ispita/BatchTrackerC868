/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author fborojan
 */
public class Batch {
    private IntegerProperty pkBatch;
    private StringProperty batchNumber,employeeName,employeeDepartment;

    
    public Batch(){
        this.batchNumber = new SimpleStringProperty();
        this.employeeName = new SimpleStringProperty();
        this.employeeDepartment = new SimpleStringProperty();
    }
    
    public IntegerProperty getPkBatch() {
        return pkBatch;
    }

    public void setPkBatch(IntegerProperty pkBatch) {
        this.pkBatch = pkBatch;
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

    public StringProperty getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment.set(employeeDepartment);
    }
    
}
