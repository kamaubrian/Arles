/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.*;
import static org.junit.Assert.*;

/**
 *
 * @author brian-kamau
 */
public class BaseTest {
    Base instance = new BaseImplementation();
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() throws SQLException{
        instance.closeConnection();
    }

    @Test
    public void testConnection() throws SQLException {
        System.out.println("Database Connection Test");
        boolean expectedResult = true;
        boolean result = instance.getConnection();
        assertEquals(expectedResult,result);
    }
    public class BaseImplementation extends Base{
        
    }
}
