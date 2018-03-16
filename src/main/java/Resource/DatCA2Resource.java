/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resource;

import DataTransferObjects.JsonMessage;
import DataTransferObjects.PersonDTO;
import Entities.CityInfo;
import Entities.Person;
import Facades.CityInfoFacade;
import Facades.PersonFacade;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

    /**
     * Retrieves representation of an instance of Entities.DatCA2Resource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return gson.toJson("hej");
    }
    
    @Path("persons")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons(){
          ArrayList<JsonMessage> messages = new ArrayList<>();
        for(Person p : PersonFacade.getAllPersons()){
            messages.add(new PersonDTO(p));
        }
        return gson.toJson(messages);
    }
    
    @Path("persons/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonFromId(@PathParam("id") Integer id){
       
         Person p = PersonFacade.getPersonFromId(id);
         PersonDTO pd = new PersonDTO(p);
         return gson.toJson(pd);
    }
        
    
    
    
    @Path("zipCodes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllZipCodes() {
        List<CityInfo> zipCodes = CityInfoFacade.getAllZips();

        return gson.toJson(zipCodes);
    }
    
    @Path("allCitys")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCitys() {
        List<CityInfo> citys = CityInfoFacade.getAllCitys();

        return gson.toJson(citys);
    }
    
    @Path("getCity/{zipCode }")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCitysWithZip(@PathParam ("zipCode") Integer zipCode) {
        CityInfo citys = CityInfoFacade.getCityFromZip(zipCode);

        return gson.toJson(citys);
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
