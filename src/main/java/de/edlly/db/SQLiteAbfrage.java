package de.edlly.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Stellt die nötigen Funktionen bereit um SQLite operationen durchzuführen.
 * 
 * Der query String muss private sein, denn sollte null als wert an das executeQuery übergeben werden wird ein fatal
 * error EXCEPTION_ACCESS_VIOLATION (0xc0000005) von der JVM erkannt.
 * 
 * TODO: Die anderen Objekt Variablen auf Privat setzten.
 * 
 * @author Edlly java@edlly.de
 *
 */
public class SQLiteAbfrage {
    private Connection sqlConnection;
    private Statement statment = null;
    private PreparedStatement preparedStatment = null;
    private ResultSet result = null;
    private String query = "";

    public void setSqlConnection(Connection sqlConnection) {
	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	this.sqlConnection = sqlConnection;
    }

    public Connection getSqlConnection() {
	return sqlConnection;
    }

    public Statement getStatment() {
	return statment;
    }

    public void setStatment(Statement statment) {
	this.statment = statment;
    }

    public PreparedStatement getPreparedStatment() {
	return preparedStatment;
    }

    public void setPreparedStatment(PreparedStatement preparedStatment) {
	this.preparedStatment = preparedStatment;
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

    private void queryNotNull(String query) throws IllegalArgumentException {
	if (query == null) {

	    throw new IllegalArgumentException("Der SQL Query String darf nicht null sein.");
	}
    }

    public String getQuery() {
	return this.query;
    }

    public void statmentVorbereitenUndStarten(String query) throws SQLException {
	statmentVorbereiten();
	statmentAusfuehren(query);
    }

    public void statmentVorbereiten() throws SQLException {
	statment = sqlConnection.createStatement();
    }

    public void statmentAusfuehren(String query) throws SQLException {
	queryNotNull(query);
	result = statment.executeQuery(query);
    }

    public void preparedStatmentVorbereiten(String query) throws SQLException {
	queryNotNull(query);
	preparedStatment = getSqlConnection().prepareStatement(query);
    }

    public void preparedStatmentAusfuehren() throws SQLException {
	preparedStatment.executeUpdate();
    }

    public void closeStatmentUndResult() throws SQLException {
	if (statment != null) {
	    statment.close();
	}
	if (result != null) {
	    result.close();
	}
    }

    public void closePrepareStatment() throws SQLException {
	if (preparedStatment != null) {
	    preparedStatment.close();
	}
    }

    public boolean resultOhneErgebniss(ResultSet result) throws SQLException {
	return !result.isBeforeFirst();
    }
}
