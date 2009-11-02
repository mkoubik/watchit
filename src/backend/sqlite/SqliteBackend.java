/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite;

import backend.IBackend;
import exceptions.BackendException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Data-storage backend based on sqlite3 database.
 * @author matej
 */
public final class SqliteBackend implements IBackend {

    /**
     * Name of the database file stored in users home directory.
     */
    private static String DATABASE_FILENAME = ".watchit.db";

    /**
     * Checks and initializes sqlite database (file).
     * @throws BackendException
     */
    public void initialize() throws BackendException {
        String path = System.getProperty("user.home")+File.separator+DATABASE_FILENAME;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:"+path);
        } catch (Exception ex) {
            throw new BackendException(ex.getMessage());
        }
    }

}
