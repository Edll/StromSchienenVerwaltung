package de.edlly.material;

import java.sql.*;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteStatement;

/**
 * Stellt eine MaterialIds Liste, prüft ob überhaupt ob eine vorhanden ist wenn nicht gibt eine liste mit 0 zurück.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialIds extends Material {

    private boolean ausgeblendetDatenAnzeigen;
    private int[] idListe = new int[] { 0 };
    SQLiteStatement sqlLite;

    public MaterialIds(SQLiteConnect sqlConnection) {
	super(sqlConnection);
	sqlLite = new SQLiteStatement(sqlConnection);
    }

    public void setAusgeblendetDatenAnzeigen(boolean ausgelendeteDatensatzeBeruecksichtigen) {
	this.ausgeblendetDatenAnzeigen = ausgelendeteDatensatzeBeruecksichtigen;
    }

    public int[] getIdListe() throws IllegalArgumentException, SQLException {
	materialListeErstellen();
	return idListe;
    }

    public boolean materialIdVorhanden(int materialId) throws IllegalArgumentException, SQLException {
	setAusgeblendetDatenAnzeigen(false);

	int[] idListe = getIdListe();
	for (int id : idListe) {

	    if (id == materialId) {
		return true;
	    }
	}
	return false;
    }

    public void materialListeErstellen() throws SQLException, IllegalArgumentException {

	try {
	    queryAuswahl();
	    int anzahlDerDatensatze = anzahlDatenseatze();
	    sqlLite.statmentAusfuehren(sqlLite.getQuery());

	    if (anzahlDerDatensatze == 0) {

		idListe = new int[] { 0 };
	    } else {

		idListe = new int[anzahlDerDatensatze];
		int zaehlerDesArrayIndexes = 0;

		while (sqlLite.getResult().next()) {

		    idListe[zaehlerDesArrayIndexes] = sqlLite.getResult().getInt(1);
		    zaehlerDesArrayIndexes++;
		}
	    }
	    sqlLite.closeStatmentAndResult();

	} catch (SQLException sqlException) {
	    throw new SQLException(sqlException);

	} catch (IllegalArgumentException illegalException) {
	    throw new IllegalArgumentException(illegalException);

	} finally {
	    sqlLite.closeStatmentAndResult();
	}
    }

    public int anzahlDatenseatze() {
	int anzahlDerDatensatze = 0;

	try{
	    sqlLite.statmentVorbereitenUndStarten(sqlLite.getQuery());

	    while (sqlLite.getResult().next()) {
		anzahlDerDatensatze++;
	    }
	} catch (SQLException sqlException) {

	    anzahlDerDatensatze = 0;
	}

	return anzahlDerDatensatze;
    }

    public void queryAuswahl() {
	if (ausgeblendetDatenAnzeigen) {

	    sqlLite.setQuery("SELECT id FROM Material");
	} else {

	    sqlLite.setQuery("SELECT id FROM Material WHERE visibly = 1");
	}
    }

    public String getQuery() {
	return sqlLite.getQuery();
    }

}