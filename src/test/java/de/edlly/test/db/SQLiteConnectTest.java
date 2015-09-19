package de.edlly.test.db;

import java.sql.SQLException;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import junit.framework.TestCase;

public class SQLiteConnectTest extends TestCase {
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException {
	sqlConnection = new SQLiteConnect();
    }

    @Test
    public void testDbConnectKlasseFehlt() {
	try {
	    sqlConnection.dbConnect("foo.bar.Test", "");
	    fail("Exception nicht ausgelöst");
	} catch (SQLiteException expected) {
	    String erwartet = "Fehler bei der SQL Verbidung: Treiber Klasse nicht gefunden.";
	    boolean check = expected.getLocalizedMessage().contains(erwartet);
	    assertTrue("Fehler falsche Exception: " + expected.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testDbConnectDBFehlt() {
	try {
	    sqlConnection.dbConnect("org.sqlite.JDBC", "");
	    fail("Exception nicht ausgelöst");
	} catch (SQLiteException expected) {
	    String erwartet = "Fehler bei der SQL Verbidung:";
	    boolean check = expected.getLocalizedMessage().contains(erwartet);
	    assertTrue("Fehler falsche Exception: " + expected.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testSqlConnectionCloseorNull() throws SQLException {
	sqlConnection.dbConnect();
	sqlConnection.close();
	try {
	    SQLiteConnect.isClosedOrNull(sqlConnection);
	    fail("Sollte einen Fehler werfen: IllegalArgumentException: Fehler bei der SQL Verbindung:...");
	} catch (SQLiteException expected) {
	    String erwartet = "Fehler bei der SQL Verbindung:";
	    boolean check = expected.getLocalizedMessage().contains(erwartet);
	    assertTrue("Fehler falsche Exception", check);

	}

    }

    @Test
    public void testSqlConnectionIsClosed() throws SQLException {
	sqlConnection.dbConnect();
	sqlConnection.close();
	try {
	    SQLiteConnect.isClosed(sqlConnection);
	    fail("Sollte einen Fehler werfen: IllegalArgumentException: Fehler bei der SQL Verbindung:...");
	} catch (SQLiteException expected) {
	    String erwartet = "Fehler bei der SQL Verbindung:";
	    boolean check = expected.getLocalizedMessage().contains(erwartet);
	    assertTrue("Fehler falsche Exception", check);
	}
    }

    @Override
    public void tearDown() throws SQLException {
	sqlConnection.close();
    }
}
