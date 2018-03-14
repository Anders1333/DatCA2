/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Address;
import Entities.Person;
import static Entities.Person_.address;
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
public class AddressFacade {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataCA2PU");

    public static String getAddressFromPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Address.findByStreet");
        q.setParameter("street", p.getAddress().getStreet());
        String address = (String) q.getSingleResult();
        return address;
    }

    public static List<Address> getAllAddress() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Address.findAll");
        return q.getResultList();

    }

    public static List<Address> getAddressZipcode(int zipCode) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT a FROM Address a WHERE Cityinfo_zipCode = :zipcode");
        q.setParameter("zipcode", zipCode);
        return q.getResultList();
    }

}
