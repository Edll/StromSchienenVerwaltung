package de.edlly.db;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 * 
 * Verbindung zur SQLite Datenbank Verwalten.
 * 
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

    @Override
    public void setSqlConnection(SQLiteConnect connection) throws SQLiteException {
	if (connection == null) {
	    throw new SQLiteException("Fehler bei der SQL Verbindung");
	}
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

    public void close() throws SQLiteException {
	try {
	    if (connection != null && !connection.isClosed()) {
		connection.close();
	    }
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}

    }

    public static void isClosedOrNull(SQLiteConnect sqlConnection) throws SQLiteException {

	if (sqlConnection == null) {
	    throw new SQLiteException("Fehler bei der SQL Verbindung: Die Verbindung ist nicht initialisiert worde.");
	} else {
	    isClosed(sqlConnection);
	}
    }

    public static void isClosed(SQLiteConnect connection) throws SQLiteException {
	Connection sqlConnectionGet = connection.getSqlConnection();
	try {
	    if (sqlConnectionGet == null || sqlConnectionGet.isClosed()) {
		throw new SQLiteException("Fehler bei der SQL Verbindung: Die Verbindung ist nicht gesetzt worden.");
	    }
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}
    }

    public void testDatabankStruktur() {
	try {
	    SQLiteConnect sqlConnection = new SQLiteConnect();
	    sqlConnection.dbConnect();
	    SQLiteDatenbankStruktur datenbankCheck = new SQLiteDatenbankStruktur(sqlConnection);
	    datenbankCheck.datenbankCheckUndAnlegen();
	    sqlConnection.close();
	} catch (SQLiteException e) {
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    JOptionPane.showMessageDialog(null, e.getMessage());
	}
    }
}
