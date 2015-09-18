package de.edlly.test.db;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;

public class SQLiteConnectTest extends TestCase {
    SQLiteConnect sqlConnection;

    @Test
    public void testSqlConnectionCloseorNull() {
	sqlConnection = null;

	try {
	    SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);

	    fail("Sollte einen Fehler werfen: IllegalArgumentException: Fehler bei der SQL Verbindung!");

	} catch (IllegalArgumentException expected) {

	    assertEquals(IllegalArgumentException.class, expected.getClass());
	}
    }
}
