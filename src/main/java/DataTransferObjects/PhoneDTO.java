/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObjects;

import Entities.Phone;

/**
 *
 * @author AndersHC
 */
public class PhoneDTO implements JsonMessage<Phone> {

    String number;

    public PhoneDTO(Phone p) {
        this.number = p.getNumber();
    }
    
    
    
    
    
    
    
    @Override
    public Phone toInternal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
