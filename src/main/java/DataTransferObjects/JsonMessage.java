/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObjects;

/**
 *
 * @author AndersHC
 */
public interface JsonMessage<T> {
    public T toInternal();
}
