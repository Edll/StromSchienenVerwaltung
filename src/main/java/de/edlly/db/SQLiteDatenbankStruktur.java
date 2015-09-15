package de.edlly.db;

import java.sql.*;

/**
 * 
 * Beim ersten start des Programms wird die Datenbank Struktur geprüft sollte diese nicht vorhanden sein wird sie
 * angelegt.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class SQLiteDatenbankStruktur extends SQLitePreparedStatement {

    public SQLiteDatenbankStruktur(Connection sqlConnection) {
	super(sqlConnection);
    }

    public void datenbankCheckUndAnlegen() throws IllegalArgumentException, SQLException {
	setSqlConnection(SQLiteConnect.dbConnection());
	boolean tableMaterialVorhanden = tableMaterialVorhanden();
	boolean tableMaterialSorteVorhanden = tableMaterialSorteVorhanden();
	boolean tableBiegenVorhanden = tableBiegenVorhanden();
	if (tableMaterialVorhanden || tableMaterialSorteVorhanden || tableBiegenVorhanden) {
	    throw new IllegalArgumentException(
		    "Die Datenbank war nicht vorhanden. Eine neue leere Datenbank wurde angelegt.");
	}
    }

    public boolean tableMaterialVorhanden() throws SQLException, IllegalArgumentException {
	try {
	    setQuery("SELECT * FROM Material");
	    preparedStatmentVorbereiten(getQuery());
	    preparedStatmentAusfuehren();
	    closePrepareStatment();
	} catch (SQLException e) {
	    String errorErwartet = "no such table: Material";
	    String errorBekommen = e.getLocalizedMessage();
	    if (errorBekommen.contains(errorErwartet)) {
		setQuery(
			"CREATE TABLE \"Material\" (\"id\" INTEGER PRIMARY KEY  NOT NULL ,\"MaterialSorteId\" INTEGER DEFAULT (null) ,\"x\" INTEGER,\"z\" INTEGER,\"yMax\" INTEGER,\"visibly\" BOOL NOT NULL  DEFAULT (1) )");
		preparedStatmentVorbereiten(getQuery());
		preparedStatmentAusfuehren();
		closePrepareStatment();
		return true;
	    }

	}
	return false;
    }

    public boolean tableMaterialSorteVorhanden() throws SQLException, IllegalArgumentException {
	try {
	    setQuery("SELECT * FROM MaterialSorten");
	    preparedStatmentVorbereiten(getQuery());
	    preparedStatmentAusfuehren();
	    closePrepareStatment();
	} catch (SQLException e) {
	    String errorErwartet = "no such table: MaterialSorten";
	    String errorBekommen = e.getLocalizedMessage();
	    if (errorBekommen.contains(errorErwartet)) {
		setQuery(
			"CREATE TABLE \"MaterialSorten\" (\"id\" INTEGER PRIMARY KEY  NOT NULL  UNIQUE , \"MaterialSorte\" TEXT)");
		preparedStatmentVorbereiten(getQuery());
		preparedStatmentAusfuehren();
		closePrepareStatment();

		// TODO: SQLite Befehl so umstricken das er alles Datensätze auf einmal einträgt!
		setQuery("INSERT INTO MaterialSorten  (\"id\",\"MaterialSorte\") VALUES (\"1\",\"Kupfer\") ");
		preparedStatmentVorbereiten(getQuery());
		preparedStatmentAusfuehren();
		closePrepareStatment();
		setQuery("INSERT INTO MaterialSorten  (\"id\",\"MaterialSorte\") VALUES (\"2\",\"Kupfer Verz.\") ");
		preparedStatmentVorbereiten(getQuery());
		preparedStatmentAusfuehren();
		closePrepareStatment();
		setQuery("INSERT INTO MaterialSorten  (\"id\",\"MaterialSorte\") VALUES (\"3\",\"Alu\") ");
		preparedStatmentVorbereiten(getQuery());
		preparedStatmentAusfuehren();
		closePrepareStatment();
		return true;
	    }

	}
	return false;
    }

    public boolean tableBiegenVorhanden() throws SQLException, IllegalArgumentException {
	try {
	    setQuery("SELECT * FROM Biegen");
	    preparedStatmentVorbereiten(getQuery());
	    preparedStatmentAusfuehren();
	    closePrepareStatment();

	} catch (SQLException e) {
	    String errorErwartet = "no such table: Biegen";
	    String errorBekommen = e.getLocalizedMessage();
	    if (errorBekommen.contains(errorErwartet)) {
		setQuery(
			"CREATE TABLE \"Biegen\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , \"WerkstueckId\" INTEGER NOT NULL , \"y\" INTEGER, \"Winkel\" INTEGER, \"Richtung\" TEXT)");
		preparedStatmentVorbereiten(getQuery());
		preparedStatmentAusfuehren();
		closePrepareStatment();
		return true;
	    }
	}
	return false;
    }
}
