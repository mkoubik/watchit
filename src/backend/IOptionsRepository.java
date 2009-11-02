/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend;

/**
 * Repository for options/settings persistency.
 * @author matej
 */
public interface IOptionsRepository {

    /**
     * Retrieving option
     * @param option option name
     * @return option value
     */
    public String getOption(String option);

    /**
     * Saving option.
     * @param option  option name
     * @param value option value
     */
    public void setOption(String option, String value);
    
}
