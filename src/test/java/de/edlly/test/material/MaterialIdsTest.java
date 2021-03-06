package de.edlly.test.material;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
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
    public void setUp() throws IllegalArgumentException, SQLiteException {
        sqlConnection = new SQLiteConnect();
        sqlConnection.dbConnect();
        materialIds = new MaterialIds(sqlConnection);
    }

    @Test
    public void testGetIdListeDatensatzeTrue() throws SQLiteException, IllegalArgumentException {
        boolean ausgelendeteDatensatzeNichtAnzeigen = true;
        materialIds.setAusgeblendetDatenAnzeigen(ausgelendeteDatensatzeNichtAnzeigen);
        int[] idListeBekommen = materialIds.getIdListe();

        assertNotNull("methode darf keine Null liefern", idListeBekommen);
    }

    @Test
    public void testGetIdListeDatensatzeFalse() throws SQLiteException, IllegalArgumentException {
        boolean ausgelendeteDatensatzeNichtAnzeigen = false;

        materialIds.setAusgeblendetDatenAnzeigen(ausgelendeteDatensatzeNichtAnzeigen);
        int[] idListeBekommen = materialIds.getIdListe();

        assertNotNull("methode darf keine Null liefern", idListeBekommen);
    }

    @Test
    public void testQueryAuswahlFalse() throws SQLiteException {
        materialIds.setAusgeblendetDatenAnzeigen(false);
        materialIds.queryAuswahl();

        String bekommenesQuery = materialIds.getQuery();
        String erwartetesQuery = "SELECT id FROM Material WHERE visibly = 1";

        assertEquals(erwartetesQuery, bekommenesQuery);
    }

    @Test
    public void testQueryAuswahlTrue() throws SQLiteException {
        materialIds.setAusgeblendetDatenAnzeigen(true);
        materialIds.queryAuswahl();

        String queryBekommen = materialIds.getQuery();
        String queryErwartet = "SELECT id FROM Material";

        assertEquals(queryErwartet, queryBekommen);
    }

    @Test
    public void testanzahlDerDatensatze() throws SQLiteException {
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
        } catch (SQLiteException e) {
            assertEquals("Der SQL Query String darf nicht null sein.", e.getLocalizedMessage());
        }
    }

    @Test
    public void testMaterialIdVorhandenFalse() throws IllegalArgumentException, SQLiteException {
        boolean idnichtvorhanden = materialIds.materialIdVorhanden(0);

        assertFalse("Wenn 0 als id abgefragt wird sollte das ergebniss false sein", idnichtvorhanden);
    }

    @Test
    public void testMaterialIdVorhandenTrue() throws IllegalArgumentException, SQLiteException {
        int[] idListeBekommen = materialIds.getIdListe();
        boolean idvorhanden = materialIds.materialIdVorhanden(idListeBekommen[0]);

        assertTrue("Id vorhanden sollte true sein", idvorhanden);
    }

    @After
    public void tearDown() throws SQLiteException {
        sqlConnection.close();
    }

}
