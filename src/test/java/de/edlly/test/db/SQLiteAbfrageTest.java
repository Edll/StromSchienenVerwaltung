package de.edlly.test.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import de.edlly.db.SQLiteAbfrage;
import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;

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
    public void testSetQueryNull() {
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
    public void testSqlCloseStatmentUndErgebiss() throws SQLException {
	sqlAbfrage.setStatment(null);
	sqlAbfrage.setResult(null);
	sqlAbfrage.sqlCloseStatmentUndErgebiss();
    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }
}
