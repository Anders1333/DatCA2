/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObjects;

import Entities.CityInfo;

/**
 *
 * @author AndersHC
 */
public class CityInfoDTO implements JsonMessage<CityInfo> {

    
    int zipCode;
    String city;

    public CityInfoDTO(CityInfo c) {
        this.zipCode = c.getZipCode();
        this.city = c.getCity();
    }
    
    
    
    
    
    
    
    
    @Override
    public CityInfo toInternal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
