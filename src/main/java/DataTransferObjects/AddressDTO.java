/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObjects;

import Entities.Address;
import Entities.CityInfo;

/**
 *
 * @author AndersHC
 */
public class AddressDTO implements JsonMessage<Address>  {

    
   
    String street;
    int number;
    int floor;
    int zipCode;
    String city;

    public AddressDTO(Address a,CityInfo c) {
        this.street = a.getStreet() ;
        this.number = a.getNumber();
        this.floor = a.getFloor();
        this.zipCode = c.getZipCode();
        this.city = c.getCity();
    }
    
    
    
    
    
    
    
    
    
    
    @Override
    public Address toInternal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
