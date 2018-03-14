/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resource;

import Entities.Address;
import Entities.Person;
import Entities.Phone;
import Facades.PersonFacade;
import java.util.Arrays;

/**
 *
 * @author AndersHC
 */
public class Main {
    public static void main(String[] args) {
        
        PersonFacade pf = new PersonFacade();
        
        Person p = pf.getPersonFromId(2);
        
        System.out.println(p.getPhoneCollection());
        Address a1 = new Address("Hilsevej2", "2800");
                
        Person p1 = new Person("Hej", "Farvel", "hej@farvel.com", null, Arrays.asList(new Phone[]{new Phone(42)}), a1);
        pf.createPerson(p1);
    }
}
