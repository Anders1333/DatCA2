/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObjects;

import Entities.Hobby;

/**
 *
 * @author AndersHC
 */
public class HobbyDTO implements JsonMessage<Hobby> {

    
    String name;

    public HobbyDTO(Hobby h) {
        this.name = h.getHobbyName();
    }
    
    
    
    
    
    
    
    @Override
    public Hobby toInternal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
