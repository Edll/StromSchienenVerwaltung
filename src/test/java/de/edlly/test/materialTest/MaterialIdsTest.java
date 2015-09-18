package de.edlly.test.materialTest;

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
    SQLiteConnect sqlConnection;
    MaterialIds materialIds;

    @Before
    public void setUp() throws IllegalArgumentException, SQLException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnection();
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
	materialIds.setAusgeblendetDatenAnzeigen(false);
	materialIds.queryAuswahl();
	int anzahlBekommen = materialIds.anzahlDatenseatze();

	assertTrue(0 < anzahlBekommen);
    }

    @Test
    public void testanzahlDerDatensatzeFail() {
	try {
	    materialIds.anzahlDatenseatze();
	    fail("muss eine IllegalArgumentException ergeben da Query String nicht gesezt worden ist.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Der SQL Query String darf nicht null sein.", e.getLocalizedMessage());
	}
    }

    @Test
    public void testMaterialIdVorhandenFalse() throws IllegalArgumentException, SQLException {
	boolean idnichtvorhanden = materialIds.materialIdVorhanden(0);

	assertFalse("Wenn 0 als id abgefragt wird sollte das ergebniss false sein", idnichtvorhanden);
    }

    @Test
    public void testMaterialIdVorhandenTrue() throws IllegalArgumentException, SQLException {
	int[] idListeBekommen = materialIds.getIdListe();
	boolean idvorhanden = materialIds.materialIdVorhanden(idListeBekommen[0]);

	assertTrue("Id vorhanden sollte true sein", idvorhanden);
    }

    @After
    public void tearDown() throws SQLException {
	sqlConnection.closeSqlConnection();
    }

}
