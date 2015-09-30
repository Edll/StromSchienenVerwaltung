package de.edlly.test.material;

import org.junit.Test;
import junit.framework.TestCase;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.material.MaterialDatensatz;

/**
 * Tests für die AbfrageMaterialDatensatz Klasse.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialDatensatzTest extends TestCase {

    MaterialDatensatz materialDatensatz;
    SQLiteConnect sqlConnection;

    int[] materialIdList = new int[] { 0, 1, 2, 3, 4 };

    @Override
    public void setUp() throws IllegalArgumentException, SQLiteException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	materialDatensatz = new MaterialDatensatz(sqlConnection);
    }

    @Test
    public void testSetMaterialIdNull() throws SQLiteException {

	try {
	    materialDatensatz.setMaterialId(0);
	    fail("Muss einen fehler bringen das die Id nicht vorhanden ist.");
	} catch (IllegalArgumentException expected) {
	    String erwartet = "Material Id nicht vorhanden";
	    boolean check = expected.getLocalizedMessage().contains(erwartet);
	    assertTrue("Fehler falsche Exception: " + expected.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testSetMaterialIdEins() throws IllegalArgumentException, SQLiteException {
	materialDatensatz.setMaterialId(1);

	assertEquals(1, materialDatensatz.getMaterialId());
    }

    @Test
    public void testGetMaterialId() {
	assertEquals(0, materialDatensatz.getMaterialId());
    }

    @Test
    public void testGetMaterialDatensatz() throws SQLiteException {
	int[] testDatensatzEins = { 1, 1, 50, 10, 4000, 1 };

	int[] abfrageErgebnissEins = materialDatensatz.getMaterialDatensatzAusDatenbank(1);

	org.junit.Assert.assertArrayEquals(testDatensatzEins, abfrageErgebnissEins);

    }

    @Test
    public void testSetMaterialDatensatz() {
	int[] materialDatensatzOhneWerte = { 0, 0, 0, 0, 0, 0 };
	int[] materialDatensatzNull = null;

	materialDatensatz.setMaterialDatensatz(materialDatensatzOhneWerte);
	int[] ergebnissOhneWerte = materialDatensatz.getMaterialDatensatz();
	org.junit.Assert.assertArrayEquals(materialDatensatzOhneWerte, ergebnissOhneWerte);

	String exceptionErwartet = "Der Materialdatensatz bei eintragen darf nicht null sein."
		+ materialDatensatz.getClass();
	try {
	    materialDatensatz.setMaterialDatensatz(materialDatensatzNull);
	    fail("IllegalArgumentException erwartet: " + exceptionErwartet);
	} catch (IllegalArgumentException e) {

	    assertEquals(exceptionErwartet, e.getMessage());
	}

    }

    @Override
    public void tearDown() throws SQLiteException {
	sqlConnection.close();
    }
}
