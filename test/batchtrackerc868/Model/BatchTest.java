/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.Model;

import java.sql.Timestamp;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fborojan
 */
public class BatchTest {
    
    public BatchTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPkBatch method, of class Batch.
     */
    @Test
    public void testGetPkBatch() {
        System.out.println("getPkBatch");
        Batch instance = new Batch();
        Integer expResult = 0;
        Integer result = instance.getPkBatch().get();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setPkBatch method, of class Batch.
     */
    @Test
    public void testSetPkBatch() {
        System.out.println("setPkBatch");
        Integer pkBatch = 0;
        Batch instance = new Batch();
        instance.setPkBatch(pkBatch);
        // TODO review the generated test code and remove the default call to fail.
        if (instance.getPkBatch().get() != pkBatch){
                       fail("The test case is a prototype.");
        }
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getBatchNumber method, of class Batch.
     */
    @Test
    public void testGetBatchNumber() {
        System.out.println("getBatchNumber");
        Batch instance = new Batch();
        String expResult = null;
        String result = instance.getBatchNumber().get();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setBatchNumber method, of class Batch.
     */
    @Test
    public void testSetBatchNumber() {
        System.out.println("setBatchNumber");
        String batchNumber = "";
        Batch instance = new Batch();
        instance.setBatchNumber(batchNumber);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        if (!instance.getBatchNumber().get().equals(batchNumber)){
                        fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getEmployeeName method, of class Batch.
     */
    @Test
    public void testGetEmployeeName() {
        System.out.println("getEmployeeName");
        Batch instance = new Batch();
        String expResult = null;
        String result = instance.getEmployeeName().get();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmployeeName method, of class Batch.
     */
    @Test
    public void testSetEmployeeName() {
        System.out.println("setEmployeeName");
        String employeeName = "";
        Batch instance = new Batch();
        instance.setEmployeeName(employeeName);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        if (!instance.getEmployeeName().get().equals(employeeName)){
                       fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getTimeIn method, of class Batch.
     */
    @Test
    public void testGetTimeIn() {
        System.out.println("getTimeIn");
        Batch instance = new Batch();
        ObjectProperty expResult = null;
        ObjectProperty result = instance.getTimeIn();
        assertEquals(expResult, result.get());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeIn method, of class Batch.
     */
    @Test
    public void testSetTimeIn() {
        System.out.println("setTimeIn");
        Timestamp timeIn = null;
        Batch instance = new Batch();
        instance.setTimeIn(timeIn);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        if (timeIn != instance.getTimeIn().getValue()){
            fail("The test case is a prototype.");        
        }
    }

    /**
     * Test of getTimeOut method, of class Batch.
     */
    @Test
    public void testGetTimeOut() {
        System.out.println("getTimeOut");
        Batch instance = new Batch();
        ObjectProperty expResult = null;
        ObjectProperty result = instance.getTimeOut();
        assertEquals(expResult, result.get());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeOut method, of class Batch.
     */
    @Test
    public void testSetTimeOut() {
        System.out.println("setTimeOut");
        Timestamp timeOut = null;
        Batch instance = new Batch();
        instance.setTimeOut(timeOut);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        if (timeOut != instance.getTimeOut().getValue()){
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getEmployeeDepartment method, of class Batch.
     */
    @Test
    public void testGetEmployeeDepartment() {
        System.out.println("getEmployeeDepartment");
        Batch instance = new Batch();
        StringProperty expResult = null;
        StringProperty result = instance.getEmployeeDepartment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");

    }
    
}
