package de.edlly.test.part;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;

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
    public void testDBAdd() throws IllegalArgumentException, PartException {
	java.util.Date date = new java.util.Date();
	datensatz.setData("TestDaten", 1, 666, date.getTime());

	boolean condition = datensatz.dbAdd();

	assertTrue("Das Eintragen ist fehlgeschlagen", condition);
    }

    @Override
    public void tearDown() throws SQLiteException {
	sqlConnection.close();
    }

}
