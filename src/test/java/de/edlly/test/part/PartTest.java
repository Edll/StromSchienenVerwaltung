package de.edlly.test.part;

import org.junit.Test;
import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.part.IPart;
import de.edlly.part.Part;
import de.edlly.part.PartException;
import junit.framework.TestCase;

public class PartTest extends TestCase {

    IPart part;

    @Override
    public void setUp() throws PartException, SQLiteException {
	SQLiteConnect sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();

	part = new Part(sqlConnection);
    }

    @Test
    public void testGetId() {
	int id = part.getId();
	assertEquals(0, id);
    }

    @Test
    public void testSetId() throws SQLiteException, PartException {
	int id = 0;
	    part.setId(id);
	assertEquals(0, part.getId());

    }

    @Test
    public void testGetSqlConnection() {
	SQLiteConnect connection = part.getSqlConnection();
	try {
	    SQLiteConnect.isClosed(connection);
	} catch (SQLiteException e) {
	    fail("Verbindung wurde Fehlerhaft übergeben.");
	}
    }
    
    @Test
    public void testGetMaterialYMax() throws IllegalArgumentException, SQLiteException {
	part.setMaterialId(1);
	int actual = part.getMaterialYMax();
	int expected = 4000;
	assertEquals(expected, actual);

    }
    


    @Override
    public void tearDown() {
    }

}
