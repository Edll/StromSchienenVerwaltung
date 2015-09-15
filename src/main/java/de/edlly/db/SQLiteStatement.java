package de.edlly.db;

import java.sql.*;


public class SQLiteStatement extends SQLiteQueryAndResult {
    
    private Statement statment = null;
    
    public SQLiteStatement(Connection sqlConnection){
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

  

    public void closeStatmentUndResult() throws SQLException {
	if (statment != null) {
	    statment.close();
	}
	 ResultSet result = getResult();
	if (result != null) {   
	    result.close();
	}
    }

}
