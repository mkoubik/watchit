/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite.repositories;

import backend.IOptionsRepository;
import backend.sqlite.db.Database;
import backend.sqlite.db.EnhancedReslutSet;
import exceptions.BackendException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Repository for options/settings persistency into sqlite database.
 * @author matej
 */
public class OptionsRepository implements IOptionsRepository {

    /**
     * JDBC Connection wrapper.
     */
    private Database db;

    /**
     *
     * @param db JDBC connection wrapper
     */
    public OptionsRepository(Database db) {
        this.db = db;
    }

    /**
     * Retrieve option from database.
     * @param option option name
     * @return option value
     */
    public String getOption(String option) throws BackendException {
        try {
            option = Database.escapeString(option);
            String sql = "SELECT value FROM options WHERE name = \""+option+"\"";
            EnhancedReslutSet rs = db.executeQuery(sql);
            Map<String, String> row = rs.fetch();
            if (row!=null) {
                return row.get("value");
            }
        } catch (SQLException ex) {
            throw new BackendException(ex.getMessage());
        }
        return null;
    }

    /**
     * Save option to database.
     * @param option option name
     * @param value option value
     */
    public void setOption(String option, String value) throws BackendException {
        try {
            option = Database.escapeString(option);
            value = Database.escapeString(value);
            String sql = "UPDATE options SET value = \"" + value + "\" WHERE name = \"" + option + "\";";
            if (db.executeUpdate(sql) == 0) {
                sql = "INSERT INTO options (name, value) VALUES (\"" + option + "\", \"" + value + "\");";
                db.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            throw new BackendException(ex.getMessage());
        }
    }

}
