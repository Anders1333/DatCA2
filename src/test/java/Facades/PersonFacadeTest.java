/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Person;
import static Facades.PersonFacade.emf;
import com.google.gson.Gson;
import com.mysql.cj.api.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class PersonFacadeTest {

    static Gson gson = new Gson();

    public PersonFacadeTest() {
    }

    public static void makeSQL(List<Person> person) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_TEST");
        EntityManager em = emf.createEntityManager();
        Random r = new Random();
        for (int i = 0; i < person.size(); i++) {

//            String sql = "INSERT INTO PersonTest(id,addressId, firstName,lastName,email) VALUES(
//                    + int " + person.get(i).getId() + "," 
//                    + "int " + r.nextInt(20) 
//                    + ", String " + person.get(i).getFirstName() 
//                    + ", String " + person.get(i).getLastName() 
//                    + ", String " + person.get(i).getEmail() 
//                    + ");";
            //String sql = "INSERT INTO DataCA2Test.PersonTest ( addressId, firstName, lastName, email) VALUES (" + r.nextInt(20) + "," + "'" + person.get(i).getFirstName() + "', " + "'" + person.get(i).getLastName() + "'," + "'" + person.get(i).getEmail() + "');";
            // this is for the real database
            String sql = "INSERT INTO DataCA2.Person ( addressId, firstName, lastName, email) VALUES (" + r.nextInt(20) + "," + "'" + person.get(i).getFirstName() + "', " + "'" + person.get(i).getLastName() + "'," + "'" + person.get(i).getEmail() + "');";

            //javax.persistence.Query q = em.createQuery(sql);
            System.out.println(sql);

        }

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    public static List<Person> generateData() {
        String[] firstNames = {"Mathias", "Hans", "Emil", "Mikkel", "Tom", "Jerry", "Fred Flintstone", "HolgaFraPjort"};
        String[] lastNames = {"Jones", "Baggings", "POTTER", "VOLDEMORT", "WULFRIC", "BRIAN", "DUMbLEDORE", "WATSON"};

        int id = 0;
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int fIndex = (int) Math.floor(Math.random() * firstNames.length);
            int lIndex = (int) Math.floor(Math.random() * lastNames.length);
            String email = (String) firstNames[fIndex] + "." + lastNames[lIndex] + "@gmail" + ".com";
            persons.add(new Person(id, firstNames[fIndex], lastNames[lIndex], email));
            id++;

        }

        return persons;
    }

    @Before
    public void setUp() {
        

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPersonFromId method, of class PersonFacade.
     */
    @Test
    public void testGetPersonFromId() {
//        System.out.println("getPersonFromId");
//        int id = 0;
//        Person expResult = null;
//        Person result = PersonFacade.getPersonFromId(id);
//        assertEquals(expResult, result);
        System.out.println("start");
        makeSQL(generateData());
        System.out.println("slut");

    }
//
//    /**
//     * Test of getAllPersons method, of class PersonFacade.
//     */
//    @Test
//    public void testGetAllPersons() {
//        System.out.println("getAllPersons");
//        List<Person> expResult = null;
//        List<Person> result = PersonFacade.getAllPersons();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPersonFromPhone method, of class PersonFacade.
//     */
//    @Test
//    public void testGetPersonFromPhone() {
//        System.out.println("getPersonFromPhone");
//        int phonenumber = 0;
//        Person expResult = null;
//        Person result = PersonFacade.getPersonFromPhone(phonenumber);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPersonsFromHobby method, of class PersonFacade.
//     */
//    @Test
//    public void testGetPersonsFromHobby() {
//        System.out.println("getPersonsFromHobby");
//        String hobby = "";
//        List<Person> expResult = null;
//        List<Person> result = PersonFacade.getPersonsFromHobby(hobby);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of createPerson method, of class PersonFacade.
//     */
//    @Test
//    public void testCreatePerson() {
//        System.out.println("createPerson");
//        Person p = null;
//        PersonFacade.createPerson(p);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNumberOfPersonWithHobby method, of class PersonFacade.
//     */
//    @Test
//    public void testGetNumberOfPersonWithHobby() {
//        System.out.println("getNumberOfPersonWithHobby");
//        String hobby = "";
//        int expResult = 0;
//        int result = PersonFacade.getNumberOfPersonWithHobby(hobby);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPersonsFromCity method, of class PersonFacade.
//     */
//    @Test
//    public void testGetPersonsFromCity() {
//        System.out.println("getPersonsFromCity");
//        String cityName = "";
//        List<Person> expResult = null;
//        List<Person> result = PersonFacade.getPersonsFromCity(cityName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
