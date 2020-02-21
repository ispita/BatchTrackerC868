/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author fborojan
 */
public class ScreeningBatch extends Batch  {
    private StringProperty employeeDepartment;
    
    public ScreeningBatch(){
        this.employeeDepartment = new SimpleStringProperty();
        this.employeeDepartment.set("Screening");
    }
    
        public StringProperty getEmployeeDepartment() {
        return employeeDepartment;
    }
    
}
