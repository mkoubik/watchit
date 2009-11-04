/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite;

import backend.sqlite.db.Database;
import backend.sqlite.db.structure.*;
import backend.IBackend;
import backend.IOptionsRepository;
import backend.sqlite.exceptions.NotConnectedException;
import backend.sqlite.repositories.OptionsRepository;
import exceptions.BackendException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
     * JDBC database connection;
     */
    private Connection connection = null;

    /**
     * JDBC connection wrapper;
     */
    private Database db = null;

    private OptionsRepository optionsRepository = null;

    /**
     * Private getter for connection (only for validation).
     * @return JDBC database connection.
     * @throws NotConnectedException
     */
    private Connection getConnection() throws NotConnectedException {
        if (this.connection==null) {
            throw new NotConnectedException("Backend is not connected to the database.");
        }
        return this.connection;
    }

    /**
     * Lazy getter.
     * @return JDBC connection wrapper.
     */
    private Database getDatabase() throws NotConnectedException {
        if (this.db==null) {
            this.db = new Database(this.getConnection());
        }
        return this.db;
    }

    private void checkDatabase() throws NotConnectedException, SQLException {
        DatabaseStructure ds = new DatabaseStructure();

        ds.addItem(new OptionsStructure(this.getDatabase()));

        ds.checkAndCreate();
    }

    /**
     * Checks and initializes sqlite database (file).
     * @throws BackendException
     */
    public void initialize() throws BackendException {
        String path = System.getProperty("user.home")+File.separator+DATABASE_FILENAME;
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:"+path);
            this.checkDatabase();
        } catch (Exception ex) {
            throw new BackendException(ex);
        }
    }

    /**
     * Closes database connection.
     * @throws BackendException
     */
    public void shutdown() throws BackendException {
        try {
            this.getConnection().close();
        } catch (Exception ex) {
            throw new BackendException(ex);
        }
    }

    public IOptionsRepository getOptionsRepository() throws BackendException {
        if (optionsRepository==null) {
            optionsRepository = new OptionsRepository(this.getDatabase());
        }
        return optionsRepository;
    }
}
