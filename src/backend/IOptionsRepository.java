/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend;

import exceptions.BackendException;

/**
 * Repository for options/settings persistency.
 * @author matej
 */
public interface IOptionsRepository {

    /**
     * Retrieving option
     * @param option option name
     * @return option value
     * @throws BackendException
     */
    public String getOption(String option) throws BackendException;

    /**
     * Saving option.
     * @param option  option name
     * @param value option value
     * @throws BackendException
     */
    public void setOption(String option, String value) throws BackendException;
    
}
