
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resource;

import DataTransferObjects.AddressDTO;
import DataTransferObjects.CityInfoDTO;
import DataTransferObjects.HobbyDTO;
import DataTransferObjects.JsonMessage;
import DataTransferObjects.MessageFacade;
import DataTransferObjects.PersonDTO;
import DataTransferObjects.PhoneDTO;
import Entities.Address;
import Entities.CityInfo;
import Entities.Hobby;
import Entities.Person;
import Entities.Phone;
import ErrorHandling.ParamaterNoMatchException;
import ErrorHandling.PersonNotFoundException;
import Facades.AddressFacade;
import Facades.CityInfoFacade;
import Facades.HobbyFacade;
import Facades.PersonFacade;
import Facades.PhoneFacade;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author AndersHC
 */
@Path("Data")
public class DatCA2Resource {

    Gson gson = new Gson();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DatCA2Resource
     */
    public DatCA2Resource() {
    }

    //------------------------------  GET  ---------------------------------//
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return gson.toJson("hej");
    }

    @Path("persons")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() {
        ArrayList<JsonMessage> messages = new ArrayList<>();
        List<Person> persons = PersonFacade.getAllPersons();
        for (int i = 0; i < persons.size(); i++) {
            messages.add(new PersonDTO(persons.get(i)));
        }

        return gson.toJson(messages);
    }

    @Path("persons/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonFromId(@PathParam("id") Integer id) throws PersonNotFoundException {
        Person p = PersonFacade.getPersonFromId(id);
        PersonDTO pd = new PersonDTO(p);
        return gson.toJson(pd);

    }

    @Path("cities")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCitys() {
        List<CityInfo> citys = CityInfoFacade.getAllCitys();
        ArrayList<JsonMessage> messages = new ArrayList<>();

        for (int i = 0; i < citys.size(); i++) {
            messages.add(new CityInfoDTO(citys.get(i)));

        }
        return gson.toJson(messages);
    }

    @Path("cities/{zip}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCityFromZip(@PathParam("zip") Integer zipCode) throws PersonNotFoundException {
        CityInfo city = CityInfoFacade.getCityFromZip(zipCode);
        CityInfoDTO cityData = new CityInfoDTO(city);
        return gson.toJson(cityData);

    }

    @Path("phonenumbers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllNumbers() {
        List<Phone> phones = PhoneFacade.getAllPhoneNumbers();
        ArrayList<JsonMessage> messages = new ArrayList<>();

        for (int i = 0; i < phones.size(); i++) {
            messages.add(new PhoneDTO(phones.get(i)));
        }

        return gson.toJson(messages);

    }

    @Path("addresses")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllAddresses() {
        List<Address> addresses = AddressFacade.getAllAddress();
        ArrayList<JsonMessage> messages = new ArrayList<>();

        for (int i = 0; i < addresses.size(); i++) {
            messages.add(new AddressDTO(addresses.get(i), addresses.get(i).getZipCode()));
        }

        return gson.toJson(messages);
    }

    @Path("persons/whoLivesIn/{cityName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsFromZip(@PathParam("cityName") String city) throws PersonNotFoundException {

        List<Person> persons = PersonFacade.getPersonsFromCity(city);
        ArrayList<JsonMessage> messages = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            messages.add(new PersonDTO(persons.get(i)));
        }

        return gson.toJson(messages);

    }

    @Path("hobbies")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllHobbies() {
        List<Hobby> hobbies = HobbyFacade.getAllHobbies();
        ArrayList<JsonMessage> messages = new ArrayList<>();
        for (int i = 0; i < hobbies.size(); i++) {
            messages.add(new HobbyDTO(hobbies.get(i)));
        }

        return gson.toJson(messages);
    }

    @Path("hobbies/{hobbyName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsFromHobby(@PathParam("hobbyName") String hobbyName) throws PersonNotFoundException {
        List<Person> persons = PersonFacade.getPersonsFromHobby(hobbyName);
        ArrayList<JsonMessage> messages = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            messages.add(new PersonDTO(persons.get(i)));
        }

        return gson.toJson(messages);
    }

    //------------------------------- GET END ----------------------------------//
    //------------------------------- DELETE -----------------------------------// 
    @Path("persons/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String DeletePersonFromId(@PathParam("id") Integer id) throws PersonNotFoundException {
        String message = PersonFacade.removePersonFromId(id);
        if (message == null) {
            throw new PersonNotFoundException("No ID match");
        }

        return gson.toJson(message);
    }

    //------------------------------- POST -----------------------------------//
    @Path("persons")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createNewPerson(String content) throws ParamaterNoMatchException {
        Person toCreate = MessageFacade.fromJson(content, PersonDTO.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataCA2PU");
        EntityManager em = emf.createEntityManager();
        if (toCreate.getFirstName() == null || toCreate.getLastName() == null) {
            throw new ParamaterNoMatchException("Firstname or Lastname not entered");
        }
        em.getTransaction().begin();
        em.persist(toCreate);
        em.getTransaction().commit();
        em.close();
        // Address not in database
        // entitymanager find
    }

    /**
     * PUT method for updating or creating an instance of DatCA2Resource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}