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

    @Test
    public void testCloseStatmentUndResult() {
	try {
	    sqlAbfrage.statmentVorbereiten();
	    sqlAbfrage.statmentAusfuehren("SELECT * FROM material");
	    sqlAbfrage.closeStatmentUndResult();
	} catch (Exception e) {
	    fail("Sollte keine Exception werfen, ist aber:" + e.getMessage() + e.getLocalizedMessage());
	    e.printStackTrace();
	}
    }

    @Test
    public void testStatmentAusfuehren() {
	try {
	    sqlAbfrage.statmentAusfuehren(null);
	    fail("Muss eine IllegalArgumentException auslösen");
	} catch (IllegalArgumentException exception) {
	    String erwarteteException = "Der SQL Query String darf nicht null sein.";
	    assertEquals(erwarteteException, exception.getMessage());
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testClosePrepareStatment() {
	try {
	    sqlAbfrage.preparedStatmentVorbereiten("INSERT INTO Material (\"MaterialSorteId\") VALUES (?1)");
	    sqlAbfrage.closePrepareStatment();
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Sollte keine Exception werfen, ist aber:" + e.getMessage() + e.getLocalizedMessage());
	}
    }

    @Test
    public void testPreparedStatmentVorbereiten() {
	try {
	    sqlAbfrage.preparedStatmentVorbereiten(null);
	    fail("Muss eine IllegalArgumentException auslösen");
	} catch (IllegalArgumentException exception) {
	    String erwarteteException = "Der SQL Query String darf nicht null sein.";
	    assertEquals(erwarteteException, exception.getMessage());
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testResultOhneErgebniss() throws SQLException {
	sqlAbfrage.statmentVorbereiten();
	sqlAbfrage.statmentAusfuehren("SELECT * FROM material");
	boolean ergebniss = sqlAbfrage.resultOhneErgebniss(sqlAbfrage.getResult());

	assertFalse(ergebniss);
    }

    @Test
    public void testAbfrageVorbereitenUndStarten() throws SQLException {
	sqlAbfrage.statmentVorbereitenUndStarten("SELECT * FROM material");
	boolean ergebniss = sqlAbfrage.resultOhneErgebniss(sqlAbfrage.getResult());

	assertFalse(ergebniss);

	try {
	    sqlAbfrage.statmentVorbereitenUndStarten(null);
	    fail("Muss eine IllegalArgumentException auslösen");
	} catch (IllegalArgumentException exception) {
	    String erwarteteException = "Der SQL Query String darf nicht null sein.";
	    assertEquals(erwarteteException, exception.getMessage());
	}
    }

    @Override
    public void tearDown() {
	try {
	    sqlAbfrage.closeStatmentUndResult();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }
}
