/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Person;
import ErrorHandling.PersonNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author AndersHC
 */
public class PersonFacade {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataCA2PU");

    public static Person getPersonFromId(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createNamedQuery("Person.findById");
            q.setParameter("id", id);
            Person p = (Person) q.getSingleResult();
            return p;

        } catch (Exception e) {
            throw new PersonNotFoundException("Person not found");

        } finally {
            em.close();
        }

    }

    public static List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Person.findAll");
        return q.getResultList();
    }

    public static Person getPersonFromPhone(int phonenumber) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT p.Person_id FROM Phone p WHERE p.number = : phonenumber");
        q.setParameter("phonenumber", phonenumber);
        int personId = (Integer) q.getSingleResult();
        return null;
    }

    public static int getNumberOfPersonWithHobby(String hobby) {
        EntityManager em = emf.createEntityManager();
        // First we get the hobby_id from the hobby name : 
        Query getHobby = em.createQuery("SELECT h.hobbyId FROM Hobby h WHERE h.hobbyName = : hobby");
        getHobby.setParameter("hobbyName", hobby);
        int hobbyId = (Integer) getHobby.getSingleResult();
        // Then we find the person_id from the Person_Has_Hobby list :

        Query q = em.createQuery("SELECT p.Person_id FROM Person_has_Hobby p WHERE p.Hobby_hobbyId = : hobbyId");
        q.setParameter("hobbyId", hobbyId);
        List<Integer> personIdList = q.getResultList();
        int returnNumber = personIdList.size();
        return returnNumber;
    }

    public static List<Person> getPersonsFromCity(String cityName) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c.zipCode FROM CityInfo c WHERE c.city =:cityName");
        q.setParameter("cityName", cityName);
        int zipCode = (Integer) q.getSingleResult();
        // We now have the zipcode. We must get all persons with an address in the zipCode
        if (zipCode == 0) {
            throw new PersonNotFoundException("No city with that name");
        }

        Query x = em.createQuery("SELECT p FROM Person p WHERE p.addressId.zipCode.zipCode =:zipCode");
        x.setParameter("zipCode", zipCode);
        List<Person> returnList = x.getResultList();
        return returnList;
    }

    public static List<Person> getPersonsFromHobby(String hobbyName) throws PersonNotFoundException {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT h.hobbyId FROM Hobby h WHERE h.hobbyName =:hobbyName");
        q.setParameter("hobbyName", hobbyName);
        int hobbyId = (Integer) q.getSingleResult();
        if (hobbyId == 0) {
            throw new PersonNotFoundException(hobbyName + " does not excist");
        }
        Query x = em.createQuery("SELECT p FROM Person p LEFT JOIN p.hobbyList g WHERE g.hobbyId =:hobbyId");
        x.setParameter("hobbyId", hobbyId);
        List<Person> returnList = x.getResultList();
        return returnList;
    }

    public static String removePersonFromId(Integer id) {
        EntityManager em = emf.createEntityManager();
        Person deleted = getPersonFromId(id);
        Query q = em.createQuery("DELETE FROM Person p WHERE p.id =:id");
        q.setParameter("id", id);
        em.getTransaction().begin();
        q.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return "Person has been removed from the database: " + deleted.getFirstName() + " " + deleted.getLastName() + " using ID: " + deleted.getId();
    }

}
