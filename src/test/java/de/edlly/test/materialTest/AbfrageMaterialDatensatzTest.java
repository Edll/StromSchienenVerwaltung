package de.edlly.test.materialTest;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import junit.framework.TestCase;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.AbfrageMaterialDatensatz;

/**
 * Tests für die AbfrageMaterialDatensatz Klasse.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class AbfrageMaterialDatensatzTest extends TestCase {

    AbfrageMaterialDatensatz materialDatensatz;
    Connection sqlConnection;

    int[] materialIdList = new int[] { 0, 1, 2, 3, 4 };

    @Override
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();
	materialDatensatz = new AbfrageMaterialDatensatz(sqlConnection);
    }

    @Test
    public void testMaterialIdVorhandenOhneErgebniss() throws SQLException {
	try {
	    materialDatensatz.materialIdVorhanden(0); // wirft eine: IllegalArgumentException

	    fail("Sollte eine IllegalArgumentException ergeben: Material Id nicht Vorhanden.");

	} catch (IllegalArgumentException expected) {
	    assertEquals(IllegalArgumentException.class, expected.getClass());
	}
    }

    @Test
    public void testSetMaterialIdNull() throws SQLException {
	try {
	    materialDatensatz.setMaterialId(0); // wirft eine: IllegalArgumentException

	    fail("Sollte eine IllegalArgumentException ergeben: Material Id nicht Vorhanden.");
	} catch (IllegalArgumentException expected) {
	    assertEquals(IllegalArgumentException.class, expected.getClass());
	}
    }

    @Test
    public void testSetMaterialIdEins() throws SQLException {
	materialDatensatz.setMaterialId(1);

	assertEquals(1, materialDatensatz.getMaterialId());
    }

    @Test
    public void testGetMaterialId() {
	assertEquals(0, materialDatensatz.getMaterialId());
    }

    @Test
    public void testGetMaterialDatensatz() throws SQLException {

	int[] testDatensatzEins = { 1, 1, 50, 10, 4000, 1 };

	int[] abfrageErgebnissEins = materialDatensatz.getMaterialDatensatz(1);

	org.junit.Assert.assertArrayEquals(testDatensatzEins, abfrageErgebnissEins);

    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }
}
