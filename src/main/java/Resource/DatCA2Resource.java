/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resource;

import Facades.PersonFacade;
import com.google.gson.Gson;
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
@Path("data")
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
     * @return an instance of java.lang.String
     */
    @Path("persons/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") Integer id) {
        //TODO return proper representation object
       return gson.toJson(PersonFacade.getPersonFromId(id));
    }

    /**
     * PUT method for updating or creating an instance of DatCA2Resource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
}
