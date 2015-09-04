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

    public AbfrageMaterialListe(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;

    }

    public Object[] GetMaterialListe(boolean visibily) throws SQLException {
	Object[] materialListe = null;
	int[] materialIds;
	// TODO: Code schreiben
	
	materialIds = sqlAbfrageMaterialIds();
	
	if(materialIds[0] == 0){
	    return materialListe = new Object[0];
	}
	
	//return sqlMaterialIdErgebniss;

	// -> null object lerr machen

	// Mit den Ids in einer Schleife die Objekt Datenabfragen

	// Liste aus diesen Datensätzen bauen
	    return materialListe = new Object[0];  
    }

    
    public int[] sqlAbfrageMaterialIds() throws SQLException {
	Statement sqlStatment = null;
	ResultSet ergebnissDerDatensatzZaehlung = null;
	ResultSet abfrageDerDatensatze = null;
	int anzahlDerDatensatze = 0;
	int[] listeDerIds;
	

	try {
	    sqlStatment = sqlConnection.createStatement();
	    String query = "SELECT id FROM Material";
	    ergebnissDerDatensatzZaehlung = sqlStatment.executeQuery(query);
	    
	    while (ergebnissDerDatensatzZaehlung.next()) {
		anzahlDerDatensatze++;
	    }
	    abfrageDerDatensatze = sqlStatment.executeQuery(query);
	    if (anzahlDerDatensatze != 0) {
		
		listeDerIds = new int[anzahlDerDatensatze];

		int AnzahlDerAbgefragtenDatensatze = 0;
		
		while (ergebnissDerDatensatzZaehlung.next()) {
		    listeDerIds[AnzahlDerAbgefragtenDatensatze] = abfrageDerDatensatze.getInt(1);
		}
     
	    }else{
		 listeDerIds = new int[] {0};
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
