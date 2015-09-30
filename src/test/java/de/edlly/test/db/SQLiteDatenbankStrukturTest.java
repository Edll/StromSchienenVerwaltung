package de.edlly.test.db;

import org.junit.Test;

import de.edlly.db.SQLiteDatenbankStruktur;
import de.edlly.db.SQLiteException;
import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;

public class SQLiteDatenbankStrukturTest extends TestCase {
    SQLiteDatenbankStruktur datenbank;
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLiteException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	datenbank = new SQLiteDatenbankStruktur(sqlConnection);

    }

    @Test
    public void testDatenbankCheckUndAnlegen() throws IllegalArgumentException, SQLiteException {
	// TODO: SetUp schreiben mit dem der Datenbankpfad umgelenkt werden kann damit eine Exception ausgelöst wird!
	datenbank.datenbankCheckUndAnlegen();
    }

    @Override
    public void tearDown() throws SQLiteException {
	sqlConnection.close();
    }
}
