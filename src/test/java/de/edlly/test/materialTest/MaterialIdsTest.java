package de.edlly.test.materialTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialIds;

/**
 * Tests für die MaterialIds Klasse.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialIdsTest {
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
	materialIds.setAusgelendeteDatensatzeBeruecksichtigen(ausgelendeteDatensatzeNichtAnzeigen);
	int[] idListeBekommen = materialIds.getIdListe();

	assertNotNull("methode darf keine Null liefern", idListeBekommen);
    }

    @Test
    public void testGetIdListeDatensatzeFalse() throws SQLException, IllegalArgumentException {
	boolean ausgelendeteDatensatzeNichtAnzeigen = false;

	materialIds.setAusgelendeteDatensatzeBeruecksichtigen(ausgelendeteDatensatzeNichtAnzeigen);
	int[] idListeBekommen = materialIds.getIdListe();

	assertNotNull("methode darf keine Null liefern", idListeBekommen);
    }

    @Test
    public void testQueryAuswahlFalse() {
	materialIds.setAusgelendeteDatensatzeBeruecksichtigen(false);
	materialIds.queryAuswahl();

	String bekommenesQuery = materialIds.getQuery();
	String erwartetesQuery = "SELECT id FROM Material WHERE visibly = 1";

	assertEquals(erwartetesQuery, bekommenesQuery);
    }

    @Test
    public void testQueryAuswahlTrue() {
	materialIds.setAusgelendeteDatensatzeBeruecksichtigen(true);
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

    @After
    public void tearDown() {
    }

}
