/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.CityInfo;
import ErrorHandling.PersonNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author AndersHC
 */
public class CityInfoFacade {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataCA2PU");

    public static List<CityInfo> getAllZips() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("CityInfo.findZipCodes");
        List<CityInfo> zipCodes = query.getResultList();

        return zipCodes;
    }

    public static List<CityInfo> getAllCitys() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("CityInfo.findAll");
        List<CityInfo> citys = query.getResultList();

        return citys;
    }

    public static CityInfo getCityFromZip(int zipCode) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
        Query q = em.createNamedQuery("CityInfo.findByZipCode");
        q.setParameter("zipCode", zipCode);
        return (CityInfo) q.getSingleResult();
            
        } catch (Exception e) {
        throw new PersonNotFoundException("Zipcode does not exsist");
        }
    }

}
