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
public class EntityStoreException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EntityStoreException() {
    }

    public EntityStoreException(String message) {
        super(message);
    }

    public EntityStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityStoreException(Throwable cause) {
        super(cause);
    }

    public EntityStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
