/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObjects;

import com.google.gson.Gson;

/**
 *
 * @author AndersHC
 */
public class MessageFacade {

    private static Gson gson = new Gson();

    //This seemingly silly wrapper ensures that the object is a JSONMessage, proving that we intend to serialize it
    public static String messagetoJson(JsonMessage jm) {
        return gson.toJson(jm);
    }

    public static <T extends JsonMessage> T jsonToMessage(String json, Class<T> c) {
        return gson.fromJson(json, c);
    }
  
    public static <T2, T extends JsonMessage<T2>> T2 fromJson(String json, Class<T> DTOClass){
        return jsonToMessage(json, DTOClass).toInternal();
    }
}