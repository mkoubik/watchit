/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
     * Escapes string for sqlite database.
     * @param str String to be escaped.
     * @return Escaped string.
     */
    public static String escapeString(String str) {
        return str.replaceAll("\"", "\"\"");
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

    /**
     * Executes SQL query, that returns a result set.
     * @param sql SQL query.
     * @return EnhancedResultSet.
     * @throws SQLException
     */
    public EnhancedReslutSet executeQuery(String sql) throws SQLException {
        ResultSet rs = this.connection.createStatement().executeQuery(sql);
        return new EnhancedReslutSet(rs);
    }

    /**
     * Executes general SQL command.
     * @param sql SQL command.
     * @return
     * @throws SQLException
     */
    public boolean execute(String sql) throws SQLException {
        return this.connection.createStatement().execute(sql);
    }

    /**
     * Wrapper for executeUpdate.
     * @param sql SQL statement.
     * @return int Count of affected rows, or 0.
     * @throws SQLException
     */
    public int executeUpdate(String sql) throws SQLException {
        return this.connection.createStatement().executeUpdate(sql);
    }

}
