package de.edlly.material;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Abfrage der MaterialDaten für eine Tabelle
 * 
 * TODO: Code ist WIP!
 * 
 * @author Edlly java@edlly.de
 *
 */

public class AbfrageMaterialListe {

    Connection sqlConnection;
    Object[] materialListe = null;

    public AbfrageMaterialListe(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;

    }

    public Object[] getMaterialListe(boolean visibily) throws SQLException, IllegalArgumentException {

	materialListeAusDatenbankAbrufen(visibily);
	return materialListe;
    }

    private void materialListeAusDatenbankAbrufen(boolean visibily) throws SQLException, IllegalArgumentException  {
	int[] materialIds = new int[0];

	materialIds = sqlAbfrageMaterialIds(visibily);

	if (materialIds[0] == 0) {
	    materialListe = new Object[0];
	}

	materialListe = new Object[materialIds.length];

	int zaehlerDesArrayIndexes = 0;

	for (int materialIdZumAbrufen : materialIds) {

	    AbfrageMaterialDatensatz abrufenDerWerte = new AbfrageMaterialDatensatz(sqlConnection);
	    materialListe[zaehlerDesArrayIndexes] = abrufenDerWerte.getMaterialDatensatz(materialIdZumAbrufen);
	    zaehlerDesArrayIndexes++;

	}
    }

    public int[] sqlAbfrageMaterialIds(boolean visibily) throws SQLException {
	Statement sqlStatment = null;
	ResultSet ergebnissDerDatensatzZaehlung = null;
	ResultSet abfrageDerDatensatze = null;
	int anzahlDerDatensatze = 0;
	int[] listeDerIds = new int[] { 0 };

	try {
	    sqlStatment = sqlConnection.createStatement();
	    String query = "SELECT id FROM Material WHERE visibly=\"" + booleanToIntegre(visibily) + "\"";
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

    private int booleanToIntegre(boolean bool) {
	int integre = 0;
	if (bool) {
	    integre = 1;
	}
	return integre;
    }
}
