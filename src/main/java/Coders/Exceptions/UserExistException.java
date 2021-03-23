/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Exceptions;

/**
 *
 * @author Chahir Chalouati
 */
public class UserExistException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserExistException(String message) {
        super(message);
    }
    
}
