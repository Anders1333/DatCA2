/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.CityInfo;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jonas
 */
public class CityInfoFacadeTest {
    
    public CityInfoFacadeTest() {
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
     * Test of getAllZips method, of class CityInfoFacade.
     */
    @Test
    public void testGetAllZips() {
        System.out.println("getAllZips");
        List<CityInfo> expResult = null;
        List<CityInfo> result = CityInfoFacade.getAllZips();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllCitys method, of class CityInfoFacade.
     */
    @Test
    public void testGetAllCitys() {
        System.out.println("getAllCitys");
        List<CityInfo> expResult = null;
        List<CityInfo> result = CityInfoFacade.getAllCitys();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCityFromZip method, of class CityInfoFacade.
     */
    @Test
    public void testGetCityFromZip() {
        System.out.println("getCityFromZip");
        int zipCode = 0;
        CityInfo expResult = null;
        CityInfo result = CityInfoFacade.getCityFromZip(zipCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
