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
public class SQLiteQueryAndResultTest extends TestCase {
    SQLiteQueryAndResult sqlAbfrage;
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLiteException {
        sqlConnection = new SQLiteConnect();
        sqlConnection.dbConnect();
        sqlAbfrage = new SQLiteQueryAndResult(sqlConnection);
    }

    @Test
    public void testSetQuery() {
        try {
            sqlAbfrage.setQuery(null);
            fail("Muss eine IllegalArgumentException auslösen");
        } catch (SQLiteException exception) {
            String erwarteteException = "Der SQL Query String darf nicht null sein.";
            assertEquals(erwarteteException, exception.getMessage());
        }
    }

    @Test
    public void testSetSqlConnection() throws SQLException {
        try {
            sqlAbfrage.setSqlConnection(null);
            fail("Muss eine IllegalArgumentException auslösen");
        } catch (SQLiteException exception) {
            String bekommenException = exception.getMessage();
            String erwarteteException = "Fehler bei der SQL Verbindung";
            boolean check = bekommenException.contains(erwarteteException);
            assertTrue("Sollte \"Fehler bei der SQL Verbindung. Bitte prüfen sie den SQLite File\" werfen ", check);
        }

    }

    @Test
    public void testGetQuery() throws SQLiteException {
        String erwartet = "TestQuery";
        sqlAbfrage.setQuery(erwartet);
        String bekommen = sqlAbfrage.getQuery();

        assertEquals(erwartet, bekommen);
    }

    @Override
    public void tearDown() throws SQLiteException {
        sqlConnection.close();
    }
}
