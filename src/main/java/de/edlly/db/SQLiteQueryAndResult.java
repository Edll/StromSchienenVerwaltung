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
public class SQLiteQueryAndResult extends SQLite {
    private ResultSet result = null;
    private String query = "";

    public SQLiteQueryAndResult(SQLiteConnect sqlConnection) throws IllegalArgumentException {
	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	setSqlConnection(sqlConnection);
    }

    public void setResult(ResultSet result) {
	this.result = result;
    }

    public ResultSet getResult() {
	return result;
    }

    public void setQuery(String query) throws IllegalArgumentException {
	queryNotNull(query);
	this.query = query;
    }

    public void closeResult() throws SQLException {

	if (this.result != null && !this.result.isClosed()) {
	    this.result.close();
	}
    }

    protected void queryNotNull(String query) throws IllegalArgumentException {
	if (query == null || query == "") {
	    throw new IllegalArgumentException("Der SQL Query String darf nicht null sein.");
	}
    }

    public String getQuery() {
	return this.query;
    }

    public boolean resultOhneErgebniss(ResultSet result) throws SQLException {
	return !result.isBeforeFirst();
    }
}
