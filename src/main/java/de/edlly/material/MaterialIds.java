package de.edlly.material;

import java.sql.*;
import de.edlly.db.*;

/**
 * Stellt eine MaterialIds Liste, prüft ob überhaupt ob eine vorhanden ist wenn nicht gibt eine liste mit 0 zurück.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialIds {

    private Connection sqlConnection;
    private boolean ausgeblendetDatenAnzeigen;
    private int[] idListe = new int[] { 0 };

    private Statement sqlStatment = null;
    private ResultSet sqlErgebniss = null;
    private String sqlQuery = "";

    public MaterialIds(Connection sqlConnection) {

	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	this.sqlConnection = sqlConnection;
    }

    public void setAusgeblendetDatenAnzeigenn(boolean ausgelendeteDatensatzeBeruecksichtigen) {
	this.ausgeblendetDatenAnzeigen = ausgelendeteDatensatzeBeruecksichtigen;
    }

    public int[] getIdListe() throws IllegalArgumentException, SQLException {
	materialListeErstellen();
	return idListe;
    }
    
    public boolean materialIdVorhanden(int materialId) throws IllegalArgumentException, SQLException {

	setAusgeblendetDatenAnzeigenn(false);
	int[] idListe = getIdListe();
	for(int id : idListe){
	    
	    if(id == materialId ){
		return true;
	    }
	}
	return false;
    }

    public void materialListeErstellen() throws SQLException, IllegalArgumentException {

	try {
	    queryAuswahl();
	    int anzahlDerDatensatze = anzahlDatenseatze();

	    sqlErgebniss = sqlStatment.executeQuery(sqlQuery);

	    if (anzahlDerDatensatze == 0) {
		idListe = new int[] { 0 };
	    } else {

		idListe = new int[anzahlDerDatensatze];
		int zaehlerDesArrayIndexes = 0;

		while (sqlErgebniss.next()) {

		    idListe[zaehlerDesArrayIndexes] = sqlErgebniss.getInt(1);
		    zaehlerDesArrayIndexes++;
		}
	    }
	    sqlStatment.close();
	    sqlErgebniss.close();

	} catch (SQLException sqlException) {
	    throw new SQLException(sqlException);

	} catch (IllegalArgumentException illegalException) {
	    throw new SQLException(illegalException);

	} finally {
	    sqlStatment.close();
	    sqlErgebniss.close();
	}
    }

    public int anzahlDatenseatze() {
	int anzahlDerDatensatze = 0;

	try {

	    sqlStatment = sqlConnection.createStatement();
	    sqlErgebniss = sqlStatment.executeQuery(sqlQuery);

	    while (sqlErgebniss.next()) {
		anzahlDerDatensatze++;
	    }
	} catch (SQLException sqlException) {

	    anzahlDerDatensatze = 0;
	}

	return anzahlDerDatensatze;
    }

    public String getQuery() {
	return sqlQuery;
    }

    public void queryAuswahl() {
	if (ausgeblendetDatenAnzeigen) {

	    sqlQuery = "SELECT id FROM Material";

	} else {
	    sqlQuery = "SELECT id FROM Material WHERE visibly = 1";
	}
    }

}