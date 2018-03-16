/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Hobby;
import Entities.Phone;
import static Facades.PhoneFacade.emf;
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
public class HobbyFacade {
     static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataCA2PU");
     
      public static Hobby getHobbyFromId(int id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT h FROM Hobby h WHERE hobbyId = : id");
        q.setParameter("id", id);
         Hobby hobbyToReturn = (Hobby) q.getSingleResult();
        return hobbyToReturn;
    }

    public static List<Hobby> getAllHobbies() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Hobby.findAll");
       return (List) q.getResultList();
    


    }
    
}
