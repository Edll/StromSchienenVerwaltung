package de.edlly.test.materialTest;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import junit.framework.TestCase;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialDatensatz;

/**
 * Tests für die AbfrageMaterialDatensatz Klasse.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialDatensatzTest extends TestCase {

    MaterialDatensatz materialDatensatz;
    Connection sqlConnection;

    int[] materialIdList = new int[] { 0, 1, 2, 3, 4 };

    @Override
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();
	materialDatensatz = new MaterialDatensatz(sqlConnection);
    }

    @Test
    public void testSetMaterialIdNull() {

	materialDatensatz.setMaterialId(0);

	assertEquals(0, materialDatensatz.getMaterialId());
    }

    @Test
    public void testSetMaterialIdEins() {
	materialDatensatz.setMaterialId(1);

	assertEquals(1, materialDatensatz.getMaterialId());
    }

    @Test
    public void testGetMaterialId() {
	assertEquals(0, materialDatensatz.getMaterialId());
    }

    @Test
    public void testGetMaterialDatensatzWennMaterialIdNull() throws SQLException {
	materialDatensatz.setMaterialId(0);

	int[] abfrageErgebnissEins = materialDatensatz.getMaterialDatensatz(0);

	int[] testDatensatzEins = { 0, 0, 0, 0, 0, 0 };

	org.junit.Assert.assertArrayEquals(testDatensatzEins, abfrageErgebnissEins);
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
