/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchtrackerc868.Model;

import javafx.beans.property.IntegerProperty;
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
public class UserTest {
    
    public UserTest() {
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
     * Test of getUserId method, of class User.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        User instance = new User();
        Integer expResult = 0;
        Integer result = instance.getUserId().get();
        assertEquals(expResult, result);

    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = new User();
        String expResult = null;
        String result = instance.getUsername().get();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDepartment method, of class User.
     */
    @Test
    public void testGetDepartment() {
        System.out.println("getDepartment");
        User instance = new User();
        String expResult = null;
        String result = instance.getDepartment().get();
        assertEquals(expResult, result);

    }
    
}
