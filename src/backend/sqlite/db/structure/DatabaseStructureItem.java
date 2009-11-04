/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite.db.structure;

import backend.sqlite.db.Database;
import java.sql.SQLException;

/**
 * A unit for automated database structure checking/creating.
 * @author matej
 */
public abstract class DatabaseStructureItem {

    /**
     * Link to the database.
     */
    protected Database db;

    public DatabaseStructureItem(Database db) {
        this.db = db;
    }

    /**
     * Checks the part of the database structure.
     * @return boolean is the maintained part valid?
     * @throws SQLException
     */
    public abstract boolean check() throws SQLException;

    /**
     * Creates the maintained part of the database structure.
     * @throws SQLException
     */
    public abstract void create() throws SQLException;
}
