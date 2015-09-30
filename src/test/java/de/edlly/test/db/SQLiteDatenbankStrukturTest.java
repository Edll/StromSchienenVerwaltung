package de.edlly.test.db;

import java.io.File;

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
	sqlConnection.dbConnect("org.sqlite.JDBC", "kupferTest.sqlite");
	
	datenbank = new SQLiteDatenbankStruktur(sqlConnection);

    }

    @Test
    public void testDatenbankCheckUndAnlegen(){
	try {
	    datenbank.datenbankCheckUndAnlegen();
	    fail("Muss eine Exception auslösen da eine neue Datenbank Angelegt worden ist.");
	} catch (SQLiteException e) {
	    boolean condition = e.getLocalizedMessage().contains("Die Struktur in der Datenbank war nicht korrekt.");
	    assertTrue("Der Fehlerstring ist nicht korrekt.", condition);
	}
    }

    @Override
    public void tearDown() throws SQLiteException {
	sqlConnection.close();
	
        File file = new File("kupferTest.sqlite");      
        if(file.exists()){
           file.delete();
        }
    }
}
