package de.edlly.test.part;

import org.junit.Test;
import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.part.IPart;
import de.edlly.part.Part;
import de.edlly.part.PartException;
import junit.framework.TestCase;

public class PartTest extends TestCase {

    IPart werkstueck;

    @Override
    public void setUp() throws PartException, SQLiteException {
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
    public void testSetId() throws SQLiteException, PartException {
	int id = 0;
	    werkstueck.setId(id);
	assertEquals(0, werkstueck.getId());

    }

    @Test
    public void testGetSqlConnection() {
	SQLiteConnect connection = werkstueck.getSqlConnection();
	try {
	    SQLiteConnect.isClosed(connection);
	} catch (SQLiteException e) {
	    fail("Verbindung wurde Fehlerhaft übergeben.");
	}
    }

    @Override
    public void tearDown() {
    }

}
