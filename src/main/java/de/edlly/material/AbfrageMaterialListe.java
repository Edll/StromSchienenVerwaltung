package de.edlly.material;

import java.sql.*;
import java.sql.SQLException;


import de.edlly.db.*;

/**
 * Abfrage der MaterialDaten für eine Tabelle
 * 
 * TODO: Code ist WIP!
 * 
 * @author Edlly java@edlly.de
 *
 */

public class AbfrageMaterialListe {

    private Object[][] materialListeUnformatiert = null;
    private Object[][] materialListeFormatiert = null;
    private Connection sqlConnection;

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
          materialListeFormatiert[DatensatzCounter][4] = SQLiteBoolean.integerToBoolean( (Integer)materialListeUnformatiert[DatensatzCounter][5]);
	    
	}	
    }

    private void materialListeAusDatenbankAbrufen(boolean visibily) throws SQLException, IllegalArgumentException {
	int[] materialIds = new int[0];

	
	AbfrageMaterialIds abfrageMaterialIds = new AbfrageMaterialIds(this.sqlConnection);
	materialIds = abfrageMaterialIds.sqlAbfrageMaterialIds(visibily);

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
}
   


