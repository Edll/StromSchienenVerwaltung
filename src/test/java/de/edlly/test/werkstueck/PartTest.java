package de.edlly.test.werkstueck;

import java.sql.SQLException;
import org.junit.Test;
import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.werkstueck.IPart;
import de.edlly.werkstueck.Part;
import de.edlly.werkstueck.PartException;
import junit.framework.TestCase;

public class PartTest extends TestCase {

    IPart werkstueck;

    @Override
    public void setUp() throws PartException {
	SQLiteConnect sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();

	werkstueck = new Part(sqlConnection);
    }

    @Test
    public void testGetId() {
	int id = werkstueck.getId();
	assertEquals(0, id);
    }

    @Test
    public void testSetId() {
	int id = 0;

	try {
	    werkstueck.setId(id);
	    fail("Wenn die id 0 ist muss es eine Exception geben.");
	} catch (PartException e) {
	    String erwartet = "Fehler bei der Werkstückid, die angebene id:";
	    boolean check = e.getLocalizedMessage().contains(erwartet);
	    assertTrue("Die Exception Meldung entsprach nicht den vorgaben", check);
	    assertEquals("Die id Wurde nicht übernommen", id, werkstueck.getId());
	}

    }

    @Test
    public void testGetSqlConnection() {
	SQLiteConnect connection = werkstueck.getSqlConnection();
	try {
	    SQLiteConnect.isClosed(connection);
	} catch (SQLiteException e) {
	    fail("Verbindung wurde Fehlerhaft übergeben.");
	} catch (SQLException e) {
	    fail("Verbindung wurde Fehlerhaft übergeben.");
	}
    }

    @Override
    public void tearDown() {
    }

}