package de.edlly.test.db;


import java.sql.*;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;

public class SQLiteConnectTest extends TestCase {
    Connection sqlConnection;

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
