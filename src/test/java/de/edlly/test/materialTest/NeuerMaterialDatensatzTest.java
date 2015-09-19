package de.edlly.test.materialTest;

import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.Material;
import de.edlly.material.NeuerMaterialDatensatz;

import junit.framework.TestCase;
import org.junit.*;

/**
 * Lädt die einzelnen Tests für die Klasse NeuerMaterialDatensatz, diese werden dann ausgewertet.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class NeuerMaterialDatensatzTest extends TestCase {

    private NeuerMaterialDatensatz materialDatensatz;
    private SQLiteConnect sqlConnection;

    private int koordinatenMaxX = Material.MAXIMALER_X_WERT;
    private int koordiantenMaxY = Material.MAXIMALER_Y_WERT;
    private int koordiantenMaxZ = Material.MAXIMALER_Z_WERT;

    private int koordinatenMinX = Material.MINIMALER_X_WERT;
    private int koordinatenMinY = Material.MINIMALER_Y_WERT;
    private int koordiantenMinZ = Material.MINIMALER_Z_WERT;

    private int sortenId = 1;

    @Override
    public void setUp() throws IllegalArgumentException, SQLException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	materialDatensatz = new NeuerMaterialDatensatz(sqlConnection);
    }

    @Test
    public void testSetMaximaleMaterialDatenPlusEins() throws SQLException {
	try {
	    materialDatensatz.setMaterialDaten(koordinatenMaxX + 1, koordiantenMaxZ - 1, koordiantenMaxY - 1,
		    sortenId - 1); // wirft eine: IllegalArgumentException
	    fail("Sollte eine fehler werfen wenn auf X größere daten als die Maximalen gesetz werden.");
	    materialDatensatz.setMaterialDaten(koordinatenMaxX - 1, koordiantenMaxZ + 1, koordiantenMaxY - 1,
		    sortenId - 1); // wirft eine: IllegalArgumentException
	    fail("Sollte eine fehler werfen wenn auf Y größere daten als die Maximalen gesetz werden.");
	    materialDatensatz.setMaterialDaten(koordinatenMaxX - 1, koordiantenMaxZ - 1, koordiantenMaxY + 1,
		    sortenId - 1); // wirft eine: IllegalArgumentException
	    fail("Sollte eine fehler werfen wenn auf Z größere daten als die Maximalen gesetz werden.");
	    materialDatensatz.setMaterialDaten(koordinatenMaxX - 1, koordiantenMaxZ - 1, koordiantenMaxY - 1,
		    sortenId + 1); // wirft eine: IllegalArgumentException
	    fail("Sollte eine fehler werfen wenn auf Sorten Id größere daten als die Maximalen gesetz werden.");
	} catch (IllegalArgumentException expected) {
	    assertEquals(IllegalArgumentException.class, expected.getClass());
	}
    }

    @Test
    public void testSetMaximaleMaterialDaten() throws IllegalArgumentException, SQLException {
	materialDatensatz.setMaterialDaten(koordinatenMaxX, koordiantenMaxZ, koordiantenMaxY, sortenId);

	assertFalse("Maximalewerte sind nicht gesetzt worden", materialDatensatz.objektWerteSindNull());
    }

    @Test
    public void testMinimaleObjektDatensetzenMinusEins() throws SQLException {
	try {
	    materialDatensatz.setMaterialDaten(koordinatenMinX - 1, koordiantenMinZ + 1, koordinatenMinY + 1,
		    sortenId + 1); // wirft eine: IllegalArgumentException
	    fail("Sollte eine fehler werfen wenn auf X kleinere Daten als die minimalen gesetz werden.");
	    materialDatensatz.setMaterialDaten(koordinatenMinX + 1, koordiantenMinZ - 1, koordinatenMinY + 1,
		    sortenId + 1); // wirft eine: IllegalArgumentException
	    fail("Sollte eine fehler werfen wenn auf Y kleinere Daten als die minimalen gesetz werden.");
	    materialDatensatz.setMaterialDaten(koordinatenMinX + 1, koordiantenMinZ + 1, koordinatenMinY - 1,
		    sortenId + 1); // wirft eine: IllegalArgumentException
	    fail("Sollte eine fehler werfen wenn auf Z kleinere Daten als die minimalen gesetz werden.");
	    materialDatensatz.setMaterialDaten(koordinatenMinX + 1, koordiantenMinZ + 1, koordinatenMinY + 1,
		    sortenId - 1); // wirft eine: IllegalArgumentException
	    fail("Sollte eine fehler werfen wenn auf SortenId kleinere Daten als die minimalen gesetz werden.");
	} catch (IllegalArgumentException expected) {
	    assertEquals(IllegalArgumentException.class, expected.getClass());
	}

    }

    @Test
    public void testMinimaleObjektDatensetzen() throws IllegalArgumentException, SQLException {
	materialDatensatz.setMaterialDaten(koordinatenMinX, koordiantenMinZ, koordinatenMinY, sortenId);
	assertFalse("Minimalewerte sind nicht gesetzt worden", materialDatensatz.objektWerteSindNull());
    }

    @Test
    public void testNeuerDatensatztausObjektDaten() throws IllegalArgumentException, SQLException {

	materialDatensatz.setMaterialDaten(koordinatenMinX, koordiantenMinZ, koordinatenMinY, sortenId);

	boolean materialDatenEingetragen = materialDatensatz.datensatzAusObjektWertenAnlegen();

	assertTrue("Neuer Datensatz aus Objektdaten", materialDatenEingetragen);
    }

    @Override
    public void tearDown() throws SQLException {
	sqlConnection.close();

    }

}
