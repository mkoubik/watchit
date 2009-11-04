/*
 * Copyright 2009 Matej Koubik <mkoubik@gmail.com>
 * 
 */

package backend.sqlite.db.structure;

import backend.sqlite.db.Database;
import backend.sqlite.db.EnhancedReslutSet;
import java.sql.SQLException;

/**
 * Maintains the 'options' database table.
 * @author matej
 */
public class OptionsStructure extends DatabaseStructureItem {

    public OptionsStructure(Database db) {
        super(db);
    }

    @Override
    public boolean check() throws SQLException {
        EnhancedReslutSet rs = db.executeQuery("SELECT * FROM sqlite_master WHERE tbl_name='options' AND type='table';");
        if (rs.fetch()==null) {
            return false;
        }
        return true;
    }

    @Override
    public void create() throws SQLException {
        db.execute("CREATE TABLE options(name, value);");
    }

}
