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
    public void setUp() throws IllegalArgumentException, SQLException {
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

	int[] abfrageErgebnissEins = materialDatensatz.getMaterialDatensatzAusDatenbank(0);

	int[] testDatensatzEins = { 0, 0, 0, 0, 0, 0 };

	org.junit.Assert.assertArrayEquals(testDatensatzEins, abfrageErgebnissEins);
    }

    @Test
    public void testGetMaterialDatensatz() throws SQLException {
	int[] testDatensatzEins = { 1, 1, 50, 10, 4000, 1 };

	int[] abfrageErgebnissEins = materialDatensatz.getMaterialDatensatzAusDatenbank(1);

	org.junit.Assert.assertArrayEquals(testDatensatzEins, abfrageErgebnissEins);

    }
    
    @Test
    public void testSetMaterialDatensatz(){
	int[] materialDatensatzOhneWerte = { 0, 0, 0, 0, 0, 0};
	int[] materialDatensatzNull = null;
	
	materialDatensatz.setMaterialDatensatz(materialDatensatzOhneWerte);
	int[] ergebnissOhneWerte = materialDatensatz.getMaterialDatensatz();
	org.junit.Assert.assertArrayEquals(materialDatensatzOhneWerte, ergebnissOhneWerte);

	String exceptionErwartet = "Der Materialdatensatz bei eintragen darf nicht null sein." + materialDatensatz.getClass();
	try{
	materialDatensatz.setMaterialDatensatz(materialDatensatzNull);
	fail("IllegalArgumentException erwartet: " + exceptionErwartet);
	}catch(IllegalArgumentException e){
	    
	    assertEquals(exceptionErwartet, e.getMessage());
	}

	
	
	
	
    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }
}
