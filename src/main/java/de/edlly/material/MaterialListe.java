package de.edlly.material;

import java.sql.*;
import java.sql.SQLException;

import de.edlly.db.*;

/**
 * Abfrage der MaterialDaten für eine Tabelle
 * 
 * TODO: Object wirklich die beste lösung?
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialListe {

    private Connection sqlConnection;
    boolean ausgeblendetDatenAnzeigen = false;

    private Object[][] materialListeUnformatiert = null;
    private Object[][] materialListeFormatiert = null;

    public MaterialListe(Connection sqlConnection) {

	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	this.sqlConnection = sqlConnection;
    }

    public void setAusgeblendetDatenAnzeigen(boolean ausgeblendetDatenAnzeigen) {
	this.ausgeblendetDatenAnzeigen = ausgeblendetDatenAnzeigen;
    }

    public Object[][] getMaterialListe() throws SQLException, IllegalArgumentException {

	materialListeAusDatenbankAbrufen();
	return materialListeUnformatiert;
    }

    public Object[][] getMaterialListeFormatiert() throws SQLException, IllegalArgumentException {

	materialListeAusDatenbankAbrufen();
	materialListeFormatieren();
	return materialListeFormatiert;
    }

    private void materialListeFormatieren() throws SQLException {
	materialListeFormatiert = new Object[materialListeUnformatiert.length][5];
	MaterialSorte materialSorte = new MaterialSorte(SQLiteConnect.dbConnection());
	for (int DatensatzCounter = 0; DatensatzCounter != materialListeUnformatiert.length; DatensatzCounter++) {
	    materialListeFormatiert[DatensatzCounter][0] = materialListeUnformatiert[DatensatzCounter][0];

	    materialListeFormatiert[DatensatzCounter][1] = (String) materialSorte
		    .getMaterialSorteName((Integer) materialListeUnformatiert[DatensatzCounter][1]);

	    materialListeFormatiert[DatensatzCounter][2] = (String) ((Integer) materialListeUnformatiert[DatensatzCounter][2]
		    + "x" + (Integer) materialListeUnformatiert[DatensatzCounter][3]);

	    materialListeFormatiert[DatensatzCounter][3] = materialListeUnformatiert[DatensatzCounter][4];

	    materialListeFormatiert[DatensatzCounter][4] = SQLiteBoolean
		    .integerToBoolean((Integer) materialListeUnformatiert[DatensatzCounter][5]);
	}
    }

    private void materialListeAusDatenbankAbrufen() throws SQLException, IllegalArgumentException {
	int[] materialIds = new int[0];

	MaterialIds abfrageMaterialIds = new MaterialIds(this.sqlConnection);
	
	abfrageMaterialIds.setAusgeblendetDatenAnzeigenn(ausgeblendetDatenAnzeigen);
	materialIds = abfrageMaterialIds.getIdListe();

	if (materialIds[0] == 0) {
	    materialListeUnformatiert = new Object[0][0];
	}

	materialListeUnformatiert = new Object[materialIds.length][6];

	int zaehlerDesArrayIndexes = 0;
	for (int materialIdZumAbrufen : materialIds) {

	    MaterialDatensatz abrufenDerWerte = new MaterialDatensatz(sqlConnection);
	    
	    int ArrayPostionsZahler = 0;
	    for (int werte : abrufenDerWerte.getMaterialDatensatz(materialIdZumAbrufen)) {

		materialListeUnformatiert[zaehlerDesArrayIndexes][ArrayPostionsZahler] = (Integer) werte;
		ArrayPostionsZahler++;
	    }
	    zaehlerDesArrayIndexes++;
	}
    }
}
