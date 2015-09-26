package de.edlly.test.part;

import java.sql.SQLException;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.part.IPartData;
import de.edlly.part.PartDataAdd;
import de.edlly.part.PartException;
import junit.framework.TestCase;

public class PartDataAddTest extends TestCase {
    SQLiteConnect sqlConnection;
    PartDataAdd<?> datensatz;

    @Override
    public void setUp() throws PartException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	datensatz = new PartDataAdd<IPartData<?>>(sqlConnection);
    }

    @Test
    public void testDBAdd() throws IllegalArgumentException, PartException {
	java.util.Date date = new java.util.Date();
	datensatz.setData("TestDaten", 1, 666, date.getTime());

	boolean condition = datensatz.dbAdd();

	assertTrue("Das Eintragen ist fehlgeschlagen", condition);
    }

    @Override
    public void tearDown() throws SQLException {
	sqlConnection.close();
    }

}
