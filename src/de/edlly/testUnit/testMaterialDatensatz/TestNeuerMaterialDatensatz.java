package de.edlly.testUnit.testMaterialDatensatz;

import java.sql.Connection;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialKonstanten;
import de.edlly.material.NeuerMaterialDatensatz;

/**
 * Lädt die einzelnen Tests für die Klasse NeuerMaterialDatensatz, diese werden
 * dann ausgewertet.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class TestNeuerMaterialDatensatz {

    private Connection SQLConnection;

    public TestNeuerMaterialDatensatz() {

    }

    public void callTestNeuerDatensatz() {

	SQLConnection = SQLiteConnect.dbConnection();

	System.out.println("--callTestNeuerDatensatz--\n");

	if (maximaleObjektDatensetzen()) {
	    System.out.println("maximaleObjektDatensetzen\t --> passed");
	} else {
	    System.out.println("maximaleObjektDatensetzen\t --> fail");
	}

	if (minimaleObjektDatensetzen()) {
	    System.out.println("minimaleObjektDatensetzen\t --> passed");
	} else {
	    System.out.println("minimaleObjektDatensetzen\t --> fail");
	}

	if (neuerDatensatztausObjektDaten()) {
	    System.out.println("neuerDatensatztausObjektDaten\t --> passed");
	} else {
	    System.out.println("neuerDatensatztausObjektDaten\t --> fail");
	}

	SQLiteConnect.closeSqlConnection(SQLConnection);
    }

    private Boolean maximaleObjektDatensetzen() {

	int koordinatenMaxX = MaterialKonstanten.MAXIMALER_X_WERT;
	int koordiantenMaxZ = MaterialKonstanten.MAXIMALER_Z_WERT;
	int koordiantenMaxY = MaterialKonstanten.MAXIMALER_Y_WERT;
	int sortenId = 1;

	try {
	    NeuerMaterialDatensatz objektfuerDatensatz = new NeuerMaterialDatensatz(SQLConnection);
	    objektfuerDatensatz.setMaterialDaten(koordinatenMaxX, koordiantenMaxZ, koordiantenMaxY, sortenId);
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    private Boolean minimaleObjektDatensetzen() {

	int koordinatenMinX = 1;
	int koordinatenMinY = 1;
	int koordiantenMinZ = 1;
	int sortenId = 1;

	try {
	    NeuerMaterialDatensatz objektfuerDatensatz = new NeuerMaterialDatensatz(SQLConnection);
	    objektfuerDatensatz.setMaterialDaten(koordinatenMinX, koordiantenMinZ, koordinatenMinY, sortenId);
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    private Boolean neuerDatensatztausObjektDaten() {

	int koordinatenMaxX = MaterialKonstanten.MAXIMALER_X_WERT;
	int koordiantenMaxZ = MaterialKonstanten.MAXIMALER_Z_WERT;
	int koordiantenMaxY = MaterialKonstanten.MAXIMALER_Y_WERT;
	int sortenId = 1;

	try {
	    NeuerMaterialDatensatz objektfuerDatensatz = new NeuerMaterialDatensatz(SQLConnection);
	    objektfuerDatensatz.setMaterialDaten(koordinatenMaxX, koordiantenMaxZ, koordiantenMaxY, sortenId);
	    if (objektfuerDatensatz.datensatzAusObjektWertenAnlegen()) {
		return true;
	    } else {
		return false;
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}

    }

}
