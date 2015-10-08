package de.edlly.db;

import java.sql.*;

/**
 * Stellt die nötigen Funktionen bereit um SQLite Operationen durchzuführen.
 * 
 * Der query String muss private sein, denn sollte null als wert an das executeQuery übergeben werden wird ein fatal
 * error EXCEPTION_ACCESS_VIOLATION (0xc0000005) von der JVM erkannt.
 * 
 * 
 * @author Edlly java@edlly.de
 *
 */
public class SQLiteQueryAndResult extends SQLiteConnect {
    private ResultSet result = null;
    private String query = "";

    public SQLiteQueryAndResult(SQLiteConnect sqlConnection) throws SQLiteException {
	SQLiteConnect.isClosedOrNull(sqlConnection);
	setSqlConnection(sqlConnection);
    }

    public void setResult(ResultSet result) {
	this.result = result;
    }

    public ResultSet getResult() {
	return result;
    }

    public void setQuery(String query) throws SQLiteException {
	queryNotNull(query);
	this.query = query;
    }

    public void closeResult() throws SQLiteException {
	try {
	    if (this.result != null && !this.result.isClosed()) {
		this.result.close();
	    }
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}

    }

    protected void queryNotNull(String query) throws SQLiteException {
	if (query == null || query == "") {
	    throw new SQLiteException("Der SQL Query String darf nicht null sein.");
	}
    }

    public String getQuery() {
	return this.query;
    }
    


    public boolean resultOhneErgebniss(ResultSet result) throws SQLiteException {

	try {
	    return !result.isBeforeFirst();
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}
    }
}
