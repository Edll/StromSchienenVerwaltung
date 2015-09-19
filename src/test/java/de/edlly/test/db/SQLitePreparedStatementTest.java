package de.edlly.test.db;

import java.sql.SQLException;

import org.junit.Test;
import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLitePreparedStatement;
import junit.framework.TestCase;

public class SQLitePreparedStatementTest extends TestCase {
    SQLitePreparedStatement preparedStatement;
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	preparedStatement = new SQLitePreparedStatement(sqlConnection);

    }

    @Test
    public void testClosePrepareStatment() {
	try {
	    preparedStatement.preparedStatmentVorbereiten("INSERT INTO Material (\"MaterialSorteId\") VALUES (?1)");
	    preparedStatement.closePrepareStatment();
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Sollte keine Exception werfen, ist aber:" + e.getMessage() + e.getLocalizedMessage());
	}
    }

    @Test
    public void testClosePrepareStatmentAndResult() {
	try {
	    preparedStatement.preparedStatmentVorbereiten("INSERT INTO Material (\"MaterialSorteId\") VALUES (?1)");
	    preparedStatement.getResult();
	    preparedStatement.closePrepareStatmentAndResult();
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Sollte keine Exception werfen, ist aber:" + e.getMessage() + e.getLocalizedMessage());
	}
    }

    @Test
    public void testPreparedStatmentVorbereiten() {
	try {
	    preparedStatement.preparedStatmentVorbereiten(null);
	    fail("Muss eine IllegalArgumentException auslösen");
	} catch (IllegalArgumentException exception) {
	    String erwarteteException = "Der SQL Query String darf nicht null sein.";
	    assertEquals(erwarteteException, exception.getMessage());
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void tearDown() throws SQLException {

	sqlConnection.close();
    }

}
