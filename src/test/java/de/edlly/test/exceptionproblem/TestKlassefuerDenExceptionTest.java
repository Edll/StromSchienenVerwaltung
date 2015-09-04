package de.edlly.test.exceptionproblem;

import java.sql.Connection;

import de.edlly.db.SQLiteConnect;

public class TestKlassefuerDenExceptionTest {
    Connection sqlConnection;

    TestKlassefuerDenExceptionTest(Connection sqlConnection) {
	this.sqlConnection = sqlConnection = SQLiteConnect.dbConnection();
    }

    public void methodeMitFehler() {
	boolean fehlerWerfenistAn = true;

	if (fehlerWerfenistAn) {
	    throw new IllegalArgumentException("Diese Fehler soll auftreten und als richtig erkannt werden!");
	}

    }
}
