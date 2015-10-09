package de.edlly.test.part;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.part.Part;
import de.edlly.part.PartDataAdd;
import de.edlly.part.PartException;
import junit.framework.TestCase;

public class PartDataAddTest extends TestCase {
    SQLiteConnect sqlConnection;
    PartDataAdd datensatz;

    @Override
    public void setUp() throws PartException, SQLiteException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	datensatz = new PartDataAdd(sqlConnection);
    }

    @Test
    public void testDBAdd() throws IllegalArgumentException, PartException, SQLiteException {
	java.util.Date date = new java.util.Date();
	Part part = new Part();
	
	part.setData("TestDaten", 1, 666, date.getTime());
	
	datensatz.setPart(part);
	boolean condition = datensatz.dbAdd();

	assertTrue("Das Eintragen ist fehlgeschlagen", condition);
    }

    @Override
    public void tearDown() throws SQLiteException {
	sqlConnection.close();
    }

}
