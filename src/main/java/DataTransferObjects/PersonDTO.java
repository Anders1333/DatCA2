/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObjects;

import Entities.Address;
import Entities.Hobby;
import Entities.Person;
import Entities.Phone;
import Facades.AddressFacade;
import Facades.HobbyFacade;
import Facades.PhoneFacade;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author AndersHC
 */
public class PersonDTO implements JsonMessage<Person> {

    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public List<Integer> hobbyIds;
    public List<Integer> phoneIds;
    public Address address;

    public PersonDTO(Person entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
       

        
        
        Stream<Hobby> hobbyEntities = entity.getHobbyCollection().stream();
        Stream<Integer> hobbyIds = hobbyEntities.map(c -> c.getHobbyId());
        this.hobbyIds = hobbyIds.collect(Collectors.toList());

        Stream<Phone> phoneEntities = entity.getPhoneCollection().stream();
        Stream<Integer> phoneIds = phoneEntities.map(p -> p.getNumber());
        this.phoneIds = phoneIds.collect(Collectors.toList());
    }

    @Override
    public Person toInternal() {
        ArrayList<Hobby> hobbies = new ArrayList<>();
        for (int id : hobbyIds) {
         hobbies.add(HobbyFacade.getHobbyFromId(id));
        }
        ArrayList<Phone> phoneNumbers = new ArrayList<>();
        for (int id : phoneIds) {
            phoneNumbers.add(PhoneFacade.getPhoneByPersonId(id));
        }
       
        return new Person(firstName, lastName, email, hobbies, phoneNumbers,address);
    }

}
