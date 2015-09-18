package de.edlly.test.db;

import java.sql.*;

import org.junit.Test;

import de.edlly.db.SQLiteDatenbankStruktur;
import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;

public class SQLiteDatenbankStrukturTest extends TestCase {
    SQLiteDatenbankStruktur datenbank;
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnection();
	datenbank = new SQLiteDatenbankStruktur(sqlConnection);

    }

    @Test
    public void testDatenbankCheckUndAnlegen() throws IllegalArgumentException, SQLException {
	// TODO: SetUp schreiben mit dem der Datenbankpfad umgelenkt werden kann damit eine Exception ausgelöst wird!
	datenbank.datenbankCheckUndAnlegen();
    }

    @Override
    public void tearDown() throws SQLException {
	sqlConnection.closeSqlConnection();
    }
}
