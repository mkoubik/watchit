/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite.exceptions;

import exceptions.BackendException;

/**
 * Operation requires backend to be connected to the database (but it isn't).
 * @author matej
 */
public class NotConnectedException extends BackendException {

    /**
     * Creates a new instance of <code>NotConnectedException</code> without detail message.
     */
    public NotConnectedException() {
    }


    /**
     * Constructs an instance of <code>NotConnectedException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NotConnectedException(String msg) {
        super(msg);
    }
}
