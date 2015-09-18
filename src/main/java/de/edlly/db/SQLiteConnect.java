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

public class SQLiteConnect extends SQLite {
    private Connection sqlConnection;
        
    public SQLiteConnect()  {

    }

    public Connection getSqlConnection() {
	return sqlConnection;
    }

    public void setSqlConnection(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;
    }

    public void dbConnection() throws IllegalArgumentException, SQLException {

	try {
	    Class.forName("org.sqlite.JDBC");
	    sqlConnection = DriverManager.getConnection("jdbc:sqlite:" + SQLITE_FILE_PATH);

	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public void closeSqlConnection() throws SQLException {

	if (sqlConnection != null && !((Connection) sqlConnection).isClosed()) {
	    ((Connection) sqlConnection).close();
	}
    }

    public static void sqlConnectionCloseorNull(SQLiteConnect sqlConnection2) throws IllegalArgumentException {

	if (sqlConnection2 == null) {
	    throw new IllegalArgumentException("Fehler bei der SQL Verbindung: Bitte prüfen sie den SQLite File Path:" + SQLITE_FILE_PATH);
	}
    }
}
