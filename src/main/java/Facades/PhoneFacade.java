/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author AndersHC
 */
public class PhoneFacade {
    //static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataCA2PU");
    // this one is for test
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_TEST");

    public static List<Phone> getAllPhoneNumbers() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Phone.findAll");
        List<Phone> allPhoneNums = query.getResultList();
        return allPhoneNums;
    }
    
    
    public static Phone getPhoneByPersonId(int id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p.number FROM Phone p WHERE p.Person_Id = : id");
        q.setParameter("id", id);
        Phone phoneToReturn = (Phone) q.getSingleResult();
        return phoneToReturn;
    }
}
  
