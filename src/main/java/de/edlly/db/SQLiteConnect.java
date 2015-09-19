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
    private Connection connection;

    public SQLiteConnect() {

    }

    public Connection getSqlConnection() {
	return connection;
    }

    public void setSqlConnection(SQLiteConnect connection) {
	this.connection = connection.getSqlConnection();
    }

    public void dbConnect() throws SQLiteException {

	dbConnect("org.sqlite.JDBC", SQLITE_FILE_PATH);
    }

    public void dbConnect(String className, String sqliteFliePath) throws SQLiteException {
	try {
	    Class.forName(className);
	    if (sqliteFliePath == null || sqliteFliePath == "") {
		throw new SQLiteException("Fehler bei der SQL Verbidung: Angabe des Datenbanknamens fehlt.");
	    }
	    connection = DriverManager.getConnection("jdbc:sqlite:" + sqliteFliePath);

	} catch (ClassNotFoundException e) {
	    throw new SQLiteException("Fehler bei der SQL Verbidung: Treiber Klasse nicht gefunden.");
	} catch (SQLException e) {
	    throw new SQLiteException("Fehler bei der SQL Verbidung:" + e.getLocalizedMessage());
	}
    }

    public void close() throws SQLException {
	if (connection != null && !connection.isClosed()) {
	    connection.close();
	}
    }

    public static void isClosedOrNull(SQLiteConnect sqlConnection) throws SQLiteException, SQLException {

	if (sqlConnection == null) {
	    throw new SQLiteException(
		    "Fehler bei der SQL Verbindung: Die Verbindung ist nicht initialisiert worde.");
	} else {
	    isClosed(sqlConnection);
	}
    }

    public static void isClosed(SQLiteConnect connection) throws SQLiteException, SQLException {
	Connection sqlConnectionGet = connection.getSqlConnection();
	if (sqlConnectionGet == null || sqlConnectionGet.isClosed()) {
	    throw new SQLiteException(
		    "Fehler bei der SQL Verbindung: Die Verbindung ist nicht gesetzt worden.");
	}
    }
}
