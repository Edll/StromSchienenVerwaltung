package de.edlly.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ziel dieser Klasse ist es die Code Dublikate aus den ganzen Material Klassen in eine Klasse zusammen zu fassen. Zudem
 * eine sauber lösung zu schaffen für die SQLite Verbindung öffenen und schließen.
 * 
 * 
 * @author Edlly java@edlly.de
 *
 */
public class SQLiteAbfrage {
    protected Connection sqlConnection;
    protected Statement statment = null;

    protected ResultSet result = null;
    private String query = "";

    public SQLiteAbfrage() {

    }

    public ResultSet getResult() {
	return result;
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
	if (query == null) {
	    throw new IllegalArgumentException("Der query String darf nicht null sein.");
	}
	this.query = query;
    }
    
    public String getQuery(){
	return this.query;
    }

    public void setSqlConnection(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;
    }

    public void sqlCloseStatmentUndErgebiss() throws SQLException {
	if (statment != null) {
	    statment.close();
	}
	if (result != null) {
	    result.close();
	}
    }

    public void sqlAbfrageVorbereitenUndStarten(String sqlQuery) throws SQLException {
	sqlAbfrageVorbereiten();
	sqlExecuteStatment(sqlQuery);
    }

    public void sqlAbfrageVorbereiten() throws SQLException {
	statment = sqlConnection.createStatement();
    }

    public void sqlExecuteStatment(String query) throws SQLException {
	result = statment.executeQuery(query);
    }

    public boolean sqlAbfrageOhneErgebniss(ResultSet sqlResultSet) throws SQLException {
	return !sqlResultSet.isBeforeFirst();
    }
}
