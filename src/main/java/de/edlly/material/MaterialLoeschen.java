package de.edlly.material;

import de.edlly.db.*;

public class MaterialLoeschen extends MaterialDatensatz {
    private SQLiteStatement sqlLite;

    public MaterialLoeschen(SQLiteConnect sqlConnection) throws IllegalArgumentException, SQLiteException {
	super(sqlConnection);
	sqlLite = new SQLiteStatement(sqlConnection);
    }

    public boolean loschen(int id) throws IllegalArgumentException, SQLiteException {

	setMaterialId(id);

	try {
	    sqlLite.setQuery("DELETE FROM Material WHERE id = " + getMaterialId());

	    sqlLite.statmentVorbereiten();
	    sqlLite.statmentVorbereitenUndUpdate(sqlLite.getQuery());
	    sqlLite.closeStatmentAndResult();

	    return true;
	} catch (SQLiteException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	} finally {
	    sqlLite.closeStatmentAndResult();
	}

    }

}
