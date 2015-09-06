package de.edlly.db;

import java.sql.*;

/**
 * 
 * Verbindung zur SQLite Datenbank Verwalten.
 * 
 * TODO: Funktion einbauen um zu prüfen ob das DB File da ist/Sonst Funktion einleiten zum erstellen/fehlermeldung!.
 * 
 * @author Edlly java@edlly.de
 *
 */



public class SQLiteConnect {
    static Connection sqlConnection = null;

    public static Connection dbConnection() throws IllegalArgumentException {

	try {
	    Class.forName("org.sqlite.JDBC");
	    sqlConnection = DriverManager.getConnection("jdbc:sqlite:kupfer.sqlite");
	    
	    return sqlConnection;

	} catch (SQLException e) {

	    e.printStackTrace();
	    throw new IllegalArgumentException("Verbindung zur SQLite DB nicht möglich");

	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    return sqlConnection;
	}
    }

    public static void closeSqlConnection(Connection sqlConnection) {

	try {
	    sqlConnection.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public static void sqlConnectionCloseorNull(Connection sqlConnection) throws IllegalArgumentException {

	try {
	    if (sqlConnection == null || sqlConnection.isClosed()) {
		throw new IllegalArgumentException("Fehler bei der SQL Verbindung!");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
