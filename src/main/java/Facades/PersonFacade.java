/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author AndersHC
 */
public class PersonFacade {
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataCA2PU");
    
    public static Person getPersonFromId(int id){
    EntityManager em = emf.createEntityManager();
       Query q = em.createNamedQuery("Person.findById");
       q.setParameter("id", id);
       return (Person) q.getSingleResult(); 
    }
    
    public static List<Person> getAllPersons(){
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Person.findAll");
        return q.getResultList();
    }
    
    public static Person getPersonFromPhone(int phonenumber){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p.Person_id FROM Phone p WHERE p.number = : phonenumber");
        q.setParameter("phonenumber", phonenumber);
        int personId = (Integer) q.getSingleResult();
        return (Person) getPersonFromId(personId);
    }
    
    public static List<Person> getPersonsFromHobby(String hobby){
        EntityManager em = emf.createEntityManager();
        // First we get the hobby_id from the hobby name : 
        Query getHobby = em.createQuery("SELECT h.hobbyId FROM Hobby h WHERE h.hobbyName = : hobby");
        getHobby.setParameter("hobbyName", hobby);
        int hobbyId = (Integer) getHobby.getSingleResult();
        // Then we find the person_id from the Person_Has_Hobby list :
        
        Query q = em.createQuery("SELECT p.Person_id FROM Person_has_Hobby p WHERE p.Hobby_hobbyId = : hobbyId");
        q.setParameter("hobbyId", hobbyId);
        List<Integer> personIdList = q.getResultList();
        // Now we create a list of persons from the Ids
        List<Person> returnList = new ArrayList<>();
        for (int i = 0; i < personIdList.size(); i++) {
            int personToFind = personIdList.get(i);
            Person personToAdd  = getPersonFromId(personToFind);
            returnList.add(personToAdd);
        }
        return returnList;
    }
    
    
    
    public static int getNumberOfPersonWithHobby(String hobby){
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
    
    
    
    public static List<Person> getPersonsFromCity(String cityName){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c.zipCode FROM CityInfo c WHERE c.city = : city");
        q.setParameter("city", cityName);
        int zipCode = (Integer) q.getSingleResult();
        // We now have the zipcode. We must get all persons with an address in the zipCode
        Query x = em.createQuery("SELECT p FROM Person p WHERE p.address.cityInfo.zipCode = : zipCode");
        x.setParameter("zipCode", zipCode);
        List<Person> returnList = x.getResultList();
        return returnList;
    }
}
