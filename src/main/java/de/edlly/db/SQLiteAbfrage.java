package de.edlly.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Ziel dieser Klasse ist es die Code Dublikate aus den ganzen Material Klassen in eine Klasse zusammen zu fassen.
 * Zudem eine sauber lösung zu schaffen für die SQLite Verbindung öffenen und schließen.
 * 
 * 
 * @author Edlly java@edlly.de
 *
 */
public class SQLiteAbfrage {
    private Connection sqlConnection;

    private Statement statment = null;
    private ResultSet result = null;
    private String query = "";

    public SQLiteAbfrage(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;
    }

    public ResultSet getResult() {
        return result;
    }

    public void setQuery(String query) throws IllegalArgumentException{
	if(query == null){
	    throw new IllegalArgumentException("Der query String darf nicht null sein.");
	}
        this.query = query;
    }


}
