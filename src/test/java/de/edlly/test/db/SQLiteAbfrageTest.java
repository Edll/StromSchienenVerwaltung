package de.edlly.test.db;

import java.sql.Connection;

import org.junit.Test;

import de.edlly.db.SQLiteAbfrage;
import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;

public class SQLiteAbfrageTest extends TestCase {
    SQLiteAbfrage sqlAbfrage;
    Connection sqlConnection;

    @Override
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();
	sqlAbfrage = new SQLiteAbfrage(sqlConnection);
    }

    @Test
    public void testSetQueryNull() {
	try {
	    sqlAbfrage.setQuery(null);
	    fail("Muss eine IllegalArgumentException auslösen");
	} catch (IllegalArgumentException exception) {
	   String erwarteteException = "Der query String darf nicht null sein.";
	    assertEquals(erwarteteException, exception.getMessage());
	}
	

    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }
}
