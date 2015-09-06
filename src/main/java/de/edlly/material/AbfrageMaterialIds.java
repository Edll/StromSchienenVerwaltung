package de.edlly.material;

import java.sql.*;
import de.edlly.db.*;

public class AbfrageMaterialIds {
    private Connection sqlConnection;
    private Statement sqlStatment = null;
    private ResultSet ergebnissDerDatensatzZaehlung = null;
    private ResultSet abfrageDerDatensatze = null;
    private String query;
    private int[] listeDerIds = new int[] { 0 };
    

    public AbfrageMaterialIds(Connection sqlConnection) {
	try {
	    SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	} 
	
	this.sqlConnection = sqlConnection;
    }

    public int[] sqlAbfrageMaterialIds(boolean ausgelendeteDatensatzeBeruecksichtigen)
	    throws SQLException, IllegalArgumentException {

	int anzahlDerDatensatze = 0;
	if (ausgelendeteDatensatzeBeruecksichtigen) {
	    query = "SELECT id FROM Material WHERE visibly = 1";
	} else {
	    query = "SELECT id FROM Material";
	}

	try {
	    sqlStatment = sqlConnection.createStatement();

	    ergebnissDerDatensatzZaehlung = sqlStatment.executeQuery(query);

	    while (ergebnissDerDatensatzZaehlung.next()) {
		anzahlDerDatensatze++;
	    }

	    abfrageDerDatensatze = sqlStatment.executeQuery(query);

	    if (anzahlDerDatensatze == 0) {

		listeDerIds = new int[] { 0 };
	    } else {

		listeDerIds = new int[anzahlDerDatensatze];
		int zaehlerDesArrayIndexes = 0;

		while (ergebnissDerDatensatzZaehlung.next()) {
		    listeDerIds[zaehlerDesArrayIndexes] = abfrageDerDatensatze.getInt(1);
		    zaehlerDesArrayIndexes++;
		}
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