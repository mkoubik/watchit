/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite.db.structure;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for automated database structure checking/creating.
 * @author matej
 */
public class DatabaseStructure {

    /**
     * Each of these objects takes care of one part of the database structure.
     */
    private List<DatabaseStructureItem> items = new ArrayList<DatabaseStructureItem>();

    /**
     * Adds db checking/building object to the list.
     * @param item
     */
    public void addItem(DatabaseStructureItem item) {
        items.add(item);
    }

    /**
     * Iterates through all db building objects, checks the database and creates
     * missing parts.
     * @throws SQLException
     */
    public void checkAndCreate() throws SQLException {
        for(DatabaseStructureItem item: this.items) {
            if (item.check()==false) {
                item.create();
            }
        }
    }
}
