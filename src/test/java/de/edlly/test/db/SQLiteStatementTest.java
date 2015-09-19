package de.edlly.test.db;

import java.sql.SQLException;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteStatement;
import junit.framework.TestCase;

public class SQLiteStatementTest extends TestCase {

    SQLiteStatement statement;
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	statement = new SQLiteStatement(sqlConnection);
    }

    @Test
    public void testCloseStatmentUndResult() {
	try {
	    statement.statmentVorbereiten();
	    statement.statmentExecute("SELECT * FROM material");
	    statement.closeStatmentAndResult();
	} catch (Exception e) {
	    fail("Sollte keine Exception werfen, ist aber:" + e.getMessage() + e.getLocalizedMessage());
	    e.printStackTrace();
	}
    }

    @Test
    public void testStatmentAusfuehren() {
	try {
	    statement.statmentExecute(null);
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
	statement.statmentVorbereiten();
	statement.statmentExecute("SELECT * FROM material");
	boolean ergebniss = statement.resultOhneErgebniss(statement.getResult());

	assertFalse(ergebniss);
    }

    @Test
    public void testAbfrageVorbereitenUndStarten() throws SQLException {
	statement.statmentVorbereitenUndStarten("SELECT * FROM material");
	boolean ergebniss = statement.resultOhneErgebniss(statement.getResult());

	assertFalse(ergebniss);

	try {
	    statement.statmentVorbereitenUndStarten(null);
	    fail("Muss eine IllegalArgumentException auslösen");
	} catch (IllegalArgumentException exception) {
	    String erwarteteException = "Der SQL Query String darf nicht null sein.";
	    assertEquals(erwarteteException, exception.getMessage());
	}
    }

    @Override
    public void tearDown() throws SQLException {
	try {
	    statement.closeStatmentAndResult();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	sqlConnection.close();
    }
}
