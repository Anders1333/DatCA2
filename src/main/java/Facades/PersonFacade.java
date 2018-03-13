/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Person;
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
    
}
