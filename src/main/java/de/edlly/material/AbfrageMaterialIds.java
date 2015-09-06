package de.edlly.material;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.edlly.db.SQLiteBoolean;

public class AbfrageMaterialIds {
    private Connection sqlConnection;

    public AbfrageMaterialIds(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;
	
    }

    public int[] sqlAbfrageMaterialIds(boolean visibily) throws SQLException, IllegalArgumentException {
	Statement sqlStatment = null;
	ResultSet ergebnissDerDatensatzZaehlung = null;
	ResultSet abfrageDerDatensatze = null;
	int anzahlDerDatensatze = 0;
	int[] listeDerIds = new int[] { 0 };

	try {
	    sqlStatment = sqlConnection.createStatement();
	    String query = "SELECT id FROM Material WHERE visibly=\"" + SQLiteBoolean.booleanToInteger(visibily) + "\"";
	    ergebnissDerDatensatzZaehlung = sqlStatment.executeQuery(query);

	    while (ergebnissDerDatensatzZaehlung.next()) {
		anzahlDerDatensatze++;
	    }
	    abfrageDerDatensatze = sqlStatment.executeQuery(query);
	    if (anzahlDerDatensatze != 0) {

		listeDerIds = new int[anzahlDerDatensatze];

		int zaehlerDesArrayIndexes = 0;

		while (ergebnissDerDatensatzZaehlung.next()) {
		    listeDerIds[zaehlerDesArrayIndexes] = abfrageDerDatensatze.getInt(1);
		    zaehlerDesArrayIndexes++;
		}

	    } else {
		listeDerIds = new int[] { 0 };
	    }

	    sqlStatment.close();
	    abfrageDerDatensatze.close();

	} catch (SQLException sqlException) {
	    throw new SQLException(sqlException);

	} finally {
	    sqlStatment.close();
	    abfrageDerDatensatze.close();
	}
	return listeDerIds;
    }

}