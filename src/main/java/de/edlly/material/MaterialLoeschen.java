package de.edlly.material;

import java.sql.SQLException;

import de.edlly.db.*;

public class MaterialLoeschen extends MaterialDatensatz {
    private SQLiteStatement sqlLite;

    public MaterialLoeschen(SQLiteConnect sqlConnection) throws IllegalArgumentException, SQLException {
	super(sqlConnection);
	sqlLite = new SQLiteStatement(sqlConnection);
    }

    public boolean loschen(int id) throws IllegalArgumentException, SQLException {

	setMaterialId(id);

	try {
	    sqlLite.setQuery("DELETE FROM Material WHERE id = " + getMaterialId());

	    sqlLite.statmentVorbereiten();
	    sqlLite.statmentVorbereitenUndUpdate(sqlLite.getQuery()); 
	    sqlLite.closeStatmentAndResult();
	    
	    return true;
	} catch (SQLException e) {
	    throw new SQLException(e);
	}finally{
	    sqlLite.closeStatmentAndResult();
	}

    }

}
