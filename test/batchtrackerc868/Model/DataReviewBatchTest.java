/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.Model;

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
public class DataReviewBatchTest {
    
    public DataReviewBatchTest() {
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
     * Test of getEmployeeDepartment method, of class DataReviewBatch.
     */
    @Test
    public void testGetEmployeeDepartment() {
        System.out.println("getEmployeeDepartment");
        DataReviewBatch instance = new DataReviewBatch();
        String expResult = "Data Review";
        String result = instance.getEmployeeDepartment().get();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
