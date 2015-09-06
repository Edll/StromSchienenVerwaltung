package de.edlly.test.db;

import static org.junit.Assert.*;

import java.sql.*;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;

public class SQLiteConnectTest {
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
