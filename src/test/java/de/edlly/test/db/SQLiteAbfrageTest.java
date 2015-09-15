package de.edlly.test.db;

import java.sql.*;
import org.junit.Test;
import junit.framework.TestCase;

import de.edlly.db.*;

/**
 * 
 * @author Edlly java@edlly.de
 *
 */
public class SQLiteAbfrageTest extends TestCase {
    SQLiteQueryAndResult sqlAbfrage;
    Connection sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLException {
	sqlConnection = SQLiteConnect.dbConnection();
	sqlAbfrage = new SQLiteQueryAndResult();
	sqlAbfrage.setSqlConnection(sqlConnection);
    }

    @Test
    public void testSetQuery() {
	try {
	    sqlAbfrage.setQuery(null);
	    fail("Muss eine IllegalArgumentException auslösen");
	} catch (IllegalArgumentException exception) {
	    String erwarteteException = "Der SQL Query String darf nicht null sein.";
	    assertEquals(erwarteteException, exception.getMessage());
	}
    }

    @Test
    public void testSetSqlConnection() {
	try {
	    sqlAbfrage.setSqlConnection(null);
	    fail("Muss eine IllegalArgumentException auslösen");
	} catch (IllegalArgumentException exception) {
	    String erwarteteException = "Fehler bei der SQL Verbindung!";
	    assertEquals(erwarteteException, exception.getMessage());
	}

    }

    @Test
    public void testGetQuery() {
	String erwartet = "TestQuery";
	sqlAbfrage.setQuery(erwartet);
	String bekommen = sqlAbfrage.getQuery();

	assertEquals(erwartet, bekommen);
    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }
}
