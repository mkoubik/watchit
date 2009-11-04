/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package exceptions;

/**
 * A superclass for exceptions thrown by backend to the application.
 * @author matej
 */
public class BackendException extends Exception {

    /**
     * Creates a new instance of <code>BackendException</code> without detail message.
     */
    public BackendException() {
    }


    /**
     * Constructs an instance of <code>BackendException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public BackendException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>BackendException</code> with the specified cause.
     * @param cause the cause.
     */
    public BackendException(Throwable cause) {
        super(cause);
    }
}
