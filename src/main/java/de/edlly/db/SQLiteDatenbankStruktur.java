package de.edlly.db;

/**
 * 
 * Beim ersten start des Programms wird die Datenbank Struktur geprüft sollte diese nicht vorhanden sein wird sie
 * angelegt.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class SQLiteDatenbankStruktur {
    SQLitePreparedStatement sqLite;

    public SQLiteDatenbankStruktur(SQLiteConnect sqLite) throws IllegalArgumentException, SQLiteException {
	this.sqLite = new SQLitePreparedStatement(sqLite);
    }

    public void datenbankCheckUndAnlegen() throws SQLiteException {
	boolean tableMaterialVorhanden = tableMaterialVorhanden();
	boolean tableMaterialSorteVorhanden = tableMaterialSorteVorhanden();
	boolean tableBiegenVorhanden = tableBiegenVorhanden();
	if (tableMaterialVorhanden || tableMaterialSorteVorhanden || tableBiegenVorhanden) {
	    throw new SQLiteException("Die Datenbank war nicht vorhanden. Eine neue leere Datenbank wurde angelegt.");
	}
    }

    public boolean tableMaterialVorhanden() throws SQLiteException {
	try {
	    sqLite.setQuery("SELECT * FROM Material");
	    sqLite.preparedStatmentVorbereiten(sqLite.getQuery());
	    sqLite.preparedStatmentAusfuehren();
	    sqLite.closePrepareStatment();
	} catch (SQLiteException e) {
	    String errorErwartet = "no such table: Material";
	    String errorBekommen = e.getLocalizedMessage();
	    if (errorBekommen.contains(errorErwartet)) {
		sqLite.setQuery(
			"CREATE TABLE \"Material\" (\"id\" INTEGER PRIMARY KEY  NOT NULL ,\"MaterialSorteId\" INTEGER DEFAULT (null) ,\"x\" INTEGER,\"z\" INTEGER,\"yMax\" INTEGER,\"visibly\" BOOL NOT NULL  DEFAULT (1) )");
		sqLite.preparedStatmentVorbereiten(sqLite.getQuery());
		sqLite.preparedStatmentAusfuehren();
		sqLite.closePrepareStatment();
		return true;
	    }

	}
	return false;
    }

    public boolean tableMaterialSorteVorhanden() throws SQLiteException {
	try {
	    sqLite.setQuery("SELECT * FROM MaterialSorten");
	    sqLite.preparedStatmentVorbereiten(sqLite.getQuery());
	    sqLite.preparedStatmentAusfuehren();
	    sqLite.closePrepareStatment();
	} catch (SQLiteException e) {
	    String errorErwartet = "no such table: MaterialSorten";
	    String errorBekommen = e.getLocalizedMessage();
	    if (errorBekommen.contains(errorErwartet)) {
		sqLite.setQuery(
			"CREATE TABLE \"MaterialSorten\" (\"id\" INTEGER PRIMARY KEY  NOT NULL  UNIQUE , \"MaterialSorte\" TEXT)");
		sqLite.preparedStatmentVorbereiten(sqLite.getQuery());
		sqLite.preparedStatmentAusfuehren();
		sqLite.closePrepareStatment();

		// TODO: SQLite Befehl so umstricken das er alles Datensätze auf einmal einträgt!
		sqLite.setQuery("INSERT INTO MaterialSorten  (\"id\",\"MaterialSorte\") VALUES (\"1\",\"Kupfer\") ");
		sqLite.preparedStatmentVorbereiten(sqLite.getQuery());
		sqLite.preparedStatmentAusfuehren();
		sqLite.closePrepareStatment();
		sqLite.setQuery(
			"INSERT INTO MaterialSorten  (\"id\",\"MaterialSorte\") VALUES (\"2\",\"Kupfer Verz.\") ");
		sqLite.preparedStatmentVorbereiten(sqLite.getQuery());
		sqLite.preparedStatmentAusfuehren();
		sqLite.closePrepareStatment();
		sqLite.setQuery("INSERT INTO MaterialSorten  (\"id\",\"MaterialSorte\") VALUES (\"3\",\"Alu\") ");
		sqLite.preparedStatmentVorbereiten(sqLite.getQuery());
		sqLite.preparedStatmentAusfuehren();
		sqLite.closePrepareStatment();
		return true;
	    }

	}
	return false;
    }

    public boolean tableBiegenVorhanden() throws SQLiteException {
	try {
	    sqLite.setQuery("SELECT * FROM Biegen");
	    sqLite.preparedStatmentVorbereiten(sqLite.getQuery());
	    sqLite.preparedStatmentAusfuehren();
	    sqLite.closePrepareStatment();

	} catch (SQLiteException e) {
	    String errorErwartet = "no such table: Biegen";
	    String errorBekommen = e.getLocalizedMessage();
	    if (errorBekommen.contains(errorErwartet)) {
		sqLite.setQuery(
			"CREATE TABLE \"Biegen\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , \"WerkstueckId\" INTEGER NOT NULL , \"y\" INTEGER, \"Winkel\" INTEGER, \"Richtung\" TEXT)");
		sqLite.preparedStatmentVorbereiten(sqLite.getQuery());
		sqLite.preparedStatmentAusfuehren();
		sqLite.closePrepareStatment();
		return true;
	    }
	}
	return false;
    }
}
