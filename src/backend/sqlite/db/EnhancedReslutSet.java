/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * More comfortable ResultSet.
 * @author matej
 */
public class EnhancedReslutSet {

    /**
     * ResultSet with data.
     */
    private ResultSet rs;

    /**
     * Map of column tyes indexed by column names.
     */
    private Map<String, Integer> columns = null;

    /**
     *
     * @param rs ResultSet.
     */
    public EnhancedReslutSet(ResultSet rs) {
        this.rs = rs;
    }

    /**
     * Laxy getter for columns metadata.
     * @return Map of column types indexed by column names.
     * @throws SQLException
     */
    private Map<String, Integer> getColumns() throws SQLException {
        if (this.columns==null) {
            ResultSetMetaData meta = this.rs.getMetaData();
            int count = meta.getColumnCount();
            Map<String, Integer> cols = new HashMap<String, Integer>();
            for (int i=1;i<=count;i++) {
                cols.put(meta.getColumnName(i), meta.getColumnType(i));
            }
            this.columns = cols;
        }
        return this.columns;
    }

    /**
     * Sets cursor before the first row.
     * @throws SQLException
     */
    /*public void reset() throws SQLException {
        rs.beforeFirst();
    }*/

    /**
     * Fetches one row.
     * @return Map of values indexed by column names.
     * @throws SQLException
     */
    public Map<String, String> fetch() throws SQLException {
        if (rs.next()) {
            Map<String, String> row = new HashMap<String, String>();
            for(String col: this.getColumns().keySet()) {
                row.put(col, rs.getString(col));
            }
            return row;
        }
        return null;
    }

    /**
     * Fetches all rows.
     * @return List of maps of values indexed by column names.
     * @throws SQLException
     */
    public List<Map<String, String>> fetchAll() throws SQLException {
        //this.reset();
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        while (rs.next()) {
            Map<String, String> row = this.fetch();
            if (row!=null) {
                data.add(row);
            }
        }
        return data;
    }

    /**
     * Prints data to the standard output (for testing).
     * @throws SQLException
     */
    public void print() throws SQLException {
        List<Map<String, String>> data = this.fetchAll();
        int i=0;
        for(Map<String, String> row: data) {
            System.out.println(i);
            for(String col: row.keySet()) {
                System.out.println(col+": "+row.get(col));
            }
            i++;
        }
    }
}
