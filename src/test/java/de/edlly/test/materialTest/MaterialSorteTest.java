package de.edlly.test.materialTest;

import org.junit.Test;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialSorte;

public class MaterialSorteTest extends TestCase {
    MaterialSorte materialSorte;
    Connection sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLException {
	sqlConnection = SQLiteConnect.dbConnection();
	materialSorte = new MaterialSorte(sqlConnection);
    }

    @Test
    public void testGetMaterialSorteNameNichtVorhanden() throws SQLException {
	String nameBekommen = materialSorte.getMaterialSorteName(0);
	String nameErwartet = "N/A";
	assertEquals(nameErwartet, nameBekommen);
    }

    @Test
    public void testGetMaterialSorteNameVorhanden() throws SQLException {
	String nameBekommen = materialSorte.getMaterialSorteName(1);
	String nameErwartet = "Kupfer";
	assertEquals(nameErwartet, nameBekommen);
    }

    @Test
    public void testGetMaterialSorteIdNichtVorhanden() throws SQLException {
	int idBekommen = materialSorte.getMaterialSorteId("FooBar");
	int idErwartet = 0;
	assertEquals(idErwartet, idBekommen);
    }

    @Test
    public void testGetMaterialSorteIdVorhanden() throws SQLException {
	int idBekommen = materialSorte.getMaterialSorteId("Kupfer");
	int idErwartet = 1;
	assertEquals(idErwartet, idBekommen);
    }

    @Test
    public void testGetMaterialSorteIdNameNull() throws SQLException {
	int idBekommen = materialSorte.getMaterialSorteId(null);
	int idErwartet = 0;

	assertEquals(idErwartet, idBekommen);
    }

    public void testMaterialSorteListe() throws SQLException {
	String[] materialSorteListe = materialSorte.materialSorteNamensListe();
	String[] materialSorteVergleichsListe = new String[] { "Kupfer", "Kupfer Verz.", "Alu" };

	assertEquals(materialSorteListe[0], materialSorteVergleichsListe[0]);
	assertEquals(materialSorteListe[1], materialSorteVergleichsListe[1]);
	assertEquals(materialSorteListe[2], materialSorteVergleichsListe[2]);
    }

    @Test
    public void testMateriaSorteIdVorhande() throws SQLException {
	boolean nichtVorhanden = materialSorte.materialsorteIdVorhanden(0);
	assertFalse(nichtVorhanden);

	boolean vorhanden = materialSorte.materialsorteIdVorhanden(1);
	assertTrue(vorhanden);
    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }

}
