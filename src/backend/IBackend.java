/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 *
 */

package backend;

import exceptions.BackendException;

/**
 * Interface to be implemented by all backends' front classes.
 * @author matej
 */
public interface IBackend {

    /**
     * Called by the application to initialize the backend.
     * @throws BackendException
     */
    public void initialize() throws BackendException;

    /**
     * Called by the application to shutdown the backend.
     * @throws BackendException
     */
    public void shutdown() throws BackendException;

    /**
     * Get repository of options/settings persistency.
     * @return repository
     */
    public IOptionsRepository getOptionsRepository() throws BackendException;
}
