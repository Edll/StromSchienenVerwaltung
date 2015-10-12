package de.edlly.test.db;

import org.junit.Test;
import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.db.SQLitePreparedStatement;
import junit.framework.TestCase;

public class SQLitePreparedStatementTest extends TestCase {
    SQLitePreparedStatement preparedStatement;
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLiteException {
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
    public void testClosePrepareStatmentKey() {
        double expected = 0D;
        try {
            preparedStatement.preparedStatmentWithKeyVorbereiten(
                    "INSERT INTO Bend (\"partId\",\"angel\",\"y\") VALUES (?1, ?2, ?3)");
            preparedStatement.getPreparedStatment().setDouble(1, 1D);
            preparedStatement.getPreparedStatment().setDouble(2, 90D);
            preparedStatement.getPreparedStatment().setDouble(3, 1000D);

            preparedStatement.preparedStatmentWithKeyAusfuehren();
            double actual = preparedStatement.getPrimaryKey();
            assertNotSame("Fehler die Rückgabe ist 0 muss aber eine ID sein größer 0", expected, actual);
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
        } catch (SQLiteException exception) {
            String erwarteteException = "Der SQL Query String darf nicht null sein.";
            assertEquals(erwarteteException, exception.getMessage());
        }
    }

    @Override
    public void tearDown() throws SQLiteException {

        sqlConnection.close();
    }

}
