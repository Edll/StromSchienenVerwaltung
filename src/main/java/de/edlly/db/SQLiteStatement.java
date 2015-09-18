package de.edlly.db;

import java.sql.*;

public class SQLiteStatement extends SQLiteQueryAndResult {

    private Statement statment = null;

    public SQLiteStatement(SQLiteConnect sqlConnection) throws IllegalArgumentException {
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
	statmentAusfuehren(query);
    }

    public void statmentVorbereiten() throws SQLException {
	statment = getSqlConnection().createStatement();
    }

    public void statmentAusfuehren(String query) throws SQLException {
	queryNotNull(query);
	setResult(statment.executeQuery(query));
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
