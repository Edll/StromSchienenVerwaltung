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

    private Connection sqlConnection;

    private Object[][] materialListeUnformatiert = null;
    private Object[][] materialListeFormatiert = null;

    public AbfrageMaterialListe(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;

    }

    public Object[][] getMaterialListe(boolean visibily) throws SQLException, IllegalArgumentException {

	materialListeAusDatenbankAbrufen(visibily);
	return materialListeUnformatiert;
    }

    public Object[][] getMaterialListeFormatiert(boolean visibily) throws SQLException, IllegalArgumentException {

	materialListeAusDatenbankAbrufen(visibily);
	materialListeFormatieren();
	return materialListeFormatiert;
    }

    private void materialListeFormatieren() {
	materialListeFormatiert = new Object[materialListeUnformatiert.length][5];
	for(int DatensatzCounter = 0; DatensatzCounter != materialListeUnformatiert.length; DatensatzCounter++){
	    

          materialListeFormatiert[DatensatzCounter][0] =  materialListeUnformatiert[DatensatzCounter][0];
          materialListeFormatiert[DatensatzCounter][1] = (String)( (Integer)materialListeUnformatiert[DatensatzCounter][1] + "x" +   (Integer)materialListeUnformatiert[DatensatzCounter][2]);
          materialListeFormatiert[DatensatzCounter][2] =  materialListeUnformatiert[DatensatzCounter][3];
          materialListeFormatiert[DatensatzCounter][3] =  materialListeUnformatiert[DatensatzCounter][4]; 
          materialListeFormatiert[DatensatzCounter][4] = integertoBoolean( (Integer)materialListeUnformatiert[DatensatzCounter][5]);
	    
	}	
    }

    private void materialListeAusDatenbankAbrufen(boolean visibily) throws SQLException, IllegalArgumentException {
	int[] materialIds = new int[0];

	materialIds = sqlAbfrageMaterialIds(visibily);

	if (materialIds[0] == 0) {
	    materialListeUnformatiert = new Object[0][0];
	}

	materialListeUnformatiert = new Object[materialIds.length][6];

	int zaehlerDesArrayIndexes = 0;

	for (int materialIdZumAbrufen : materialIds) {

	    AbfrageMaterialDatensatz abrufenDerWerte = new AbfrageMaterialDatensatz(sqlConnection);

	    int ArrayPostionsZahler = 0;
	    for (int werte : abrufenDerWerte.getMaterialDatensatz(materialIdZumAbrufen)) {

		materialListeUnformatiert[zaehlerDesArrayIndexes][ArrayPostionsZahler] = (Integer) werte;
		ArrayPostionsZahler++;
	    }
	    zaehlerDesArrayIndexes++;
	}
    }

    public int[] sqlAbfrageMaterialIds(boolean visibily) throws SQLException, IllegalArgumentException {
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
    private boolean integertoBoolean(int integer) {
	boolean bool = false;
	if (integer == 1) {
	    bool = true;
	}
	return bool;
    }

}
