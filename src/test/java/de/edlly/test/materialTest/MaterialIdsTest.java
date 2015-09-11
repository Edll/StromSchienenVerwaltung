package de.edlly.test.materialTest;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialIds;
import junit.framework.TestCase;

/**
 * Tests für die MaterialIds Klasse.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialIdsTest extends TestCase {
    Connection sqlConnection;
    MaterialIds materialIds;

    @Before
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();
	materialIds = new MaterialIds(sqlConnection);
    }

    @Test
    public void testGetIdListeDatensatzeTrue() throws SQLException, IllegalArgumentException {
	boolean ausgelendeteDatensatzeNichtAnzeigen = true;
	materialIds.setAusgeblendetDatenAnzeigen(ausgelendeteDatensatzeNichtAnzeigen);
	int[] idListeBekommen = materialIds.getIdListe();

	assertNotNull("methode darf keine Null liefern", idListeBekommen);
    }

    @Test
    public void testGetIdListeDatensatzeFalse() throws SQLException, IllegalArgumentException {
	boolean ausgelendeteDatensatzeNichtAnzeigen = false;

	materialIds.setAusgeblendetDatenAnzeigen(ausgelendeteDatensatzeNichtAnzeigen);
	int[] idListeBekommen = materialIds.getIdListe();

	assertNotNull("methode darf keine Null liefern", idListeBekommen);
    }

    @Test
    public void testQueryAuswahlFalse() {
	materialIds.setAusgeblendetDatenAnzeigen(false);
	materialIds.queryAuswahl();

	String bekommenesQuery = materialIds.getQuery();
	String erwartetesQuery = "SELECT id FROM Material WHERE visibly = 1";

	assertEquals(erwartetesQuery, bekommenesQuery);
    }

    @Test
    public void testQueryAuswahlTrue() {
	materialIds.setAusgeblendetDatenAnzeigen(true);
	materialIds.queryAuswahl();

	String queryBekommen = materialIds.getQuery();
	String queryErwartet = "SELECT id FROM Material";

	assertEquals(queryErwartet, queryBekommen);
    }

    @Test
    public void testanzahlDerDatensatze() {
	int anzahlBekommen = materialIds.anzahlDatenseatze();
	int anzahlErwartet = 0;

	assertEquals(anzahlErwartet, anzahlBekommen);
    }
    @Test
    public void testMaterialIdVorhandenFalse() throws IllegalArgumentException, SQLException{
	boolean idnichtvorhanden = materialIds.materialIdVorhanden(0);
	
	assertFalse("Wenn 0 als id abgefragt wird sollte das ergebniss false sein", idnichtvorhanden);
    }
    @Test
    public void testMaterialIdVorhandenTrue() throws IllegalArgumentException, SQLException{
	int[] idListeBekommen = materialIds.getIdListe();
	boolean idvorhanden = materialIds.materialIdVorhanden(idListeBekommen[0]);
	
	assertTrue("Id vorhanden sollte true sein", idvorhanden);
    }

    @After
    public void tearDown() {
    }

}
