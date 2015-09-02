package de.edlly.test.materialTest;

import java.sql.Connection;
import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialKonstanten;
import de.edlly.material.NeuerMaterialDatensatz;

import junit.framework.TestCase;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.Assert.*;

/**
 * Lädt die einzelnen Tests für die Klasse NeuerMaterialDatensatz, diese werden dann ausgewertet.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class NeuerMaterialDatensatzTest extends TestCase {

    private NeuerMaterialDatensatz materialDatensatzTest;
    private Connection sqlConnection;

    private int koordinatenMaxX = MaterialKonstanten.MAXIMALER_X_WERT;
    private int koordiantenMaxY = MaterialKonstanten.MAXIMALER_Y_WERT;
    private int koordiantenMaxZ = MaterialKonstanten.MAXIMALER_Z_WERT;

    private int koordinatenMinX = MaterialKonstanten.MINIMALER_X_WERT;
    private int koordinatenMinY = MaterialKonstanten.MINIMALER_Y_WERT;
    private int koordiantenMinZ = MaterialKonstanten.MINIMALER_Z_WERT;

    private int sortenId = 1;

    @Override
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();
	materialDatensatzTest = new NeuerMaterialDatensatz(sqlConnection);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSetMaximaleMaterialDaten() throws IllegalArgumentException {

	materialDatensatzTest.setMaterialDaten(koordinatenMaxX, koordiantenMaxZ, koordiantenMaxY, sortenId);
	
	thrown.expect(IllegalArgumentException.class);

	assertFalse("Maximalewerte sind nicht gesetzt worden", materialDatensatzTest.objektWerteSindNull());
    }

    @Test
    public void testMinimaleObjektDatensetzen() throws IllegalArgumentException {

	materialDatensatzTest.setMaterialDaten(koordinatenMinX, koordiantenMinZ, koordinatenMinY, sortenId);
	thrown.expect(IllegalArgumentException.class);

	assertFalse("Minimalewerte sind nicht gesetzt worden", materialDatensatzTest.objektWerteSindNull());
    }

    @Test
    public void testNeuerDatensatztausObjektDaten() throws IllegalArgumentException, SQLException {

	materialDatensatzTest.setMaterialDaten(koordinatenMinX, koordiantenMinZ, koordinatenMinY, sortenId);

	boolean materialDatenEingetragen = materialDatensatzTest.datensatzAusObjektWertenAnlegen();

	assertTrue("Neuer Datensatz aus Objektdaten", materialDatenEingetragen);
    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);

    }

}
