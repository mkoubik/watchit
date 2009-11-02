/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite;

import java.sql.Connection;

/**
 * Class for communication with database - JDBC Connection wrapper.
 * @author matej
 */
public class Database {

    private Connection connection;

    public Database(Connection c) {
        this.connection = c;
    }

}
