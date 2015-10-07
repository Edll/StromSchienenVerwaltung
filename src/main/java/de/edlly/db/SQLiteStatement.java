package de.edlly.db;

import java.sql.*;

public class SQLiteStatement extends SQLiteQueryAndResult {

    private Statement statment = null;

    public SQLiteStatement(SQLiteConnect sqlConnection) throws SQLiteException {
	super(sqlConnection);
    }
    
    public Statement getStatment() {
	return statment;
    }

    public void setStatment(Statement statment) {
	this.statment = statment;
    }

    public void statmentVorbereitenUndStarten(String query) throws SQLiteException {
	statmentVorbereiten();
	statmentExecute(query);
    }

    public void statmentVorbereitenUndUpdate(String query) throws SQLiteException {
	statmentVorbereiten();
	statmentUpdaten(query);
    }

    public void statmentVorbereiten() throws SQLiteException {
	try {
	    statment = getSqlConnection().createStatement();
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}
    }

    public void statmentExecute(String query) throws SQLiteException {
	try {
	    queryNotNull(query);
	    setResult(statment.executeQuery(query));
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}
    }

    public void statmentUpdaten(String query) throws SQLiteException {
	try {
	    queryNotNull(query);
	    statment.executeUpdate(query);
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}
    }

    public void closeStatmentAndResult() throws SQLiteException {
	closeStatment();
	closeResult();
    }

    public void closeStatment() throws SQLiteException {
	try {
	    if (statment != null && statment.isClosed()) {
		statment.close();
	    }
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}
    }

    public int anzahlDatenseatze() {
	int anzahlDerDatensatze = 0;

	try {
	    statmentVorbereitenUndStarten(getQuery());

	    while (getResult().next()) {
		anzahlDerDatensatze++;
	    }
	} catch (SQLiteException sqlException) {
	    anzahlDerDatensatze = 0;

	} catch (SQLException e) {
	    anzahlDerDatensatze = 0;
	}

	return anzahlDerDatensatze;
    }

}
