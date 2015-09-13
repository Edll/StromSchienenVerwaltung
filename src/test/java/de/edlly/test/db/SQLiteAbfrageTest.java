package de.edlly.test.db;

import java.sql.*;
import org.junit.Test;
import junit.framework.TestCase;

import de.edlly.db.*;

/**
 * TODO: für nicht mehr Private Methoden in SQLiteAbfrage tests einbauen.
 * 
 * @author Edlly java@edlly.de
 *
 */
public class SQLiteAbfrageTest extends TestCase {
    SQLiteAbfrage sqlAbfrage;
    Connection sqlConnection;

    @Override
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();
	sqlAbfrage = new SQLiteAbfrage();
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
    public void testGetQuery() {
	String erwartet = "TestQuery";
	sqlAbfrage.setQuery(erwartet);
	String bekommen = sqlAbfrage.getQuery();

	assertEquals(erwartet, bekommen);
    }

    @Test
    public void testCloseStatmentUndErgebiss() {
	try {
	    sqlAbfrage.abfrageVorbereiten();
	    sqlAbfrage.executeStatment("SELECT * FROM material");
	    sqlAbfrage.closeStatmentUndErgebiss();
	} catch (Exception e) {
	    fail("Sollte keine Exception werfen, ist aber:" + e.getMessage() + e.getLocalizedMessage());
	    e.printStackTrace();
	}

	try {
	    sqlAbfrage.executeStatment(null);
	    fail("Muss eine IllegalArgumentException auslösen");
	} catch (IllegalArgumentException exception) {
	    String erwarteteException = "Der SQL Query String darf nicht null sein.";
	    assertEquals(erwarteteException, exception.getMessage());
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testAbfrageOhneErgebniss() throws SQLException {
	sqlAbfrage.abfrageVorbereiten();
	sqlAbfrage.executeStatment("SELECT * FROM material");
	boolean ergebniss = sqlAbfrage.abfrageOhneErgebniss(sqlAbfrage.getResult());

	assertFalse(ergebniss);
    }

    @Test
    public void testAbfrageVorbereitenUndStarten() throws SQLException {
	sqlAbfrage.abfrageVorbereitenUndStarten("SELECT * FROM material");
	boolean ergebniss = sqlAbfrage.abfrageOhneErgebniss(sqlAbfrage.getResult());

	assertFalse(ergebniss);

	try {
	    sqlAbfrage.abfrageVorbereitenUndStarten(null);
	    fail("Muss eine IllegalArgumentException auslösen");
	} catch (IllegalArgumentException exception) {
	    String erwarteteException = "Der SQL Query String darf nicht null sein.";
	    assertEquals(erwarteteException, exception.getMessage());
	}
    }

    @Override
    public void tearDown() {
	try {
	    sqlAbfrage.closeStatmentUndErgebiss();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }
}
