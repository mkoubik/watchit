/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for communication with database - JDBC Connection wrapper.
 * @author matej
 */
public class Database {

    private Connection connection;

    public Database(Connection c) {
        this.connection = c;
    }

    /**
     * Wrapper.
     * @return JDBC statement
     * @throws SQLException
     */
    public Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

    /**
     * Wrapper.
     * @param sql SQL query.
     * @return JDBC prepared statement
     * @throws SQLException
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }

}
