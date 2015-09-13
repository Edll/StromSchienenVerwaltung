package de.edlly.db;

import java.sql.Connection;
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

    protected Statement statment = null;
    protected ResultSet result = null;
    private String query = "";

    public ResultSet getResult() {
	return result;
    }
    
    public void setSqlConnection(Connection sqlConnection){
	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	this.sqlConnection = sqlConnection;
    }
    
    public Connection getSqlConnection(){
 	return sqlConnection;
     }
    

    public Statement getStatment() {
	return statment;
    }

    public void setStatment(Statement statment) {
	this.statment = statment;
    }

    public void setResult(ResultSet result) {
	this.result = result;
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

    public void abfrageVorbereitenUndStarten(String query) throws SQLException {
	abfrageVorbereiten();
	executeStatment(query);
    }

    public void abfrageVorbereiten() throws SQLException {
	statment = sqlConnection.createStatement();
    }

    public void executeStatment(String query) throws SQLException {
	queryNotNull(query);
	result = statment.executeQuery(query);
    }

    public void closeStatmentUndErgebiss() throws SQLException {
	if (statment != null) {
	    statment.close();
	}
	if (result != null) {
	    result.close();
	}
    }

    public boolean abfrageOhneErgebniss(ResultSet result) throws SQLException {
	return !result.isBeforeFirst();
    }
}
