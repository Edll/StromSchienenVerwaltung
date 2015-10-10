package de.edlly.test.part;

import org.junit.Test;

import de.edlly.db.*;
import de.edlly.part.*;
import junit.framework.TestCase;

public class PartNewTest extends TestCase {
    SQLiteConnect sqlConnection;
    IPartNew newPart;

    @Override
    public void setUp() throws PartException, SQLiteException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	newPart = new PartNew(sqlConnection);
    }
    
    @Test
    public void testSetGetPart() throws Exception {
	IPart part = new Part();
	part.setData("test", 1, 999, 12345679L);
	newPart.setPart(part);
	
	IPart actual = newPart.getPart();
	
	assertEquals(part, actual);
    }
    
    @Test
    public void testSetPartNull() throws Exception {
	IPart part = null;
	try {
	    newPart.setPart(part);
	    fail("Fehler darf nicht null sein");
	} catch (Exception actual) {
	    String expected = "Das übergebene Part ist leer.";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }
    
    @Test
    public void testDBAdd() throws IllegalArgumentException, PartException, SQLiteException {
	java.util.Date date = new java.util.Date();
	Part part = new Part();
	part.setData("TestDaten", 1, 666, date.getTime());
	
	newPart.setPart(part);
	boolean condition = newPart.addToDdAndSetPartId();

	assertTrue("Das Eintragen ist fehlgeschlagen", condition);
    }
    
    @Test
    public void testParamaterKostruktor() throws Exception {
	java.util.Date date = new java.util.Date();
	Part part = new Part();
	part.setData("TestDaten", 1, 666, date.getTime());
	
	IPartNew partPar = new PartNew(sqlConnection, part);
	
	int actual = partPar.getPart().getId();
	int expected = 0;
	assertNotSame(expected, actual);	
    }

    @Override
    public void tearDown() throws SQLiteException {
	sqlConnection.close();
    }

}
