package de.edlly.test.materialTest;

import java.sql.Connection;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialKonstanten;
import de.edlly.material.NeuerMaterialDatensatz;
import junit.framework.TestCase;

/**
 * Lädt die einzelnen Tests für die Klasse NeuerMaterialDatensatz, diese werden dann ausgewertet.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class NeuerMaterialDatensatzTest extends TestCase {

    NeuerMaterialDatensatz testNeuerMaterialDatensatz;

    private Connection SQLConnection;

    public void testMaximaleObjektDatensetzen() {

	assertTrue("Maximale werte in Objekt setzen", maximaleObjektDatensetzen());
    }

    /*
     * @SuppressWarnings("unused") public void setUp() throws Exception {
     * 
     * // assertTrue( "maximaleObjektDatensetzen\t --> fail", maximaleObjektDatensetzen() ); }
     */

 

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

    /*
     * private Boolean minimaleObjektDatensetzen() {
     * 
     * int koordinatenMinX = 1; int koordinatenMinY = 1; int koordiantenMinZ = 1; int sortenId = 1;
     * 
     * try { NeuerMaterialDatensatz objektfuerDatensatz = new NeuerMaterialDatensatz(SQLConnection);
     * objektfuerDatensatz.setMaterialDaten(koordinatenMinX, koordiantenMinZ, koordinatenMinY, sortenId); } catch
     * (Exception e) { e.printStackTrace(); return false; } return true; }
     * 
     * private Boolean neuerDatensatztausObjektDaten() {
     * 
     * int koordinatenMaxX = MaterialKonstanten.MAXIMALER_X_WERT; int koordiantenMaxZ =
     * MaterialKonstanten.MAXIMALER_Z_WERT; int koordiantenMaxY = MaterialKonstanten.MAXIMALER_Y_WERT; int sortenId = 1;
     * 
     * try { NeuerMaterialDatensatz objektfuerDatensatz = new NeuerMaterialDatensatz(SQLConnection);
     * objektfuerDatensatz.setMaterialDaten(koordinatenMaxX, koordiantenMaxZ, koordiantenMaxY, sortenId); if
     * (objektfuerDatensatz.datensatzAusObjektWertenAnlegen()) { return true; } else { return false; }
     * 
     * } catch (Exception e) { e.printStackTrace(); return false; }
     * 
     * }
     */

}
