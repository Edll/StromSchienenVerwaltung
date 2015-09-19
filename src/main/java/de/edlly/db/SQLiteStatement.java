package de.edlly.db;

import java.sql.*;

public class SQLiteStatement extends SQLiteQueryAndResult {

    private Statement statment = null;

    public SQLiteStatement(SQLiteConnect sqlConnection) throws IllegalArgumentException, SQLException {
	super(sqlConnection);
    }

    public Statement getStatment() {
	return statment;
    }

    public void setStatment(Statement statment) {
	this.statment = statment;
    }

    public void statmentVorbereitenUndStarten(String query) throws SQLException {
	statmentVorbereiten();
	statmentExecute(query);
    }
    
    public void statmentVorbereitenUndUpdate(String query) throws SQLException {
	statmentVorbereiten();
	statmentUpdaten(query);
    }

    public void statmentVorbereiten() throws SQLException {
	statment = getSqlConnection().createStatement();
    }

    public void statmentExecute(String query) throws SQLException {
	queryNotNull(query);
	setResult(statment.executeQuery(query));
    }
    
    public void statmentUpdaten(String query) throws SQLException {
	queryNotNull(query);
	statment.executeUpdate(query);
    }
    
    public void closeStatmentAndResult() throws SQLException {
	closeStatment();
	closeResult();
    }

    public void closeStatment() throws SQLException {
	if (statment != null && statment.isClosed()) {
	    statment.close();
	}
    }

}
