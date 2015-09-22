package werkstueck;

import java.sql.SQLException;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;

public class WerkstuckEintragenTest extends TestCase {
    SQLiteConnect sqlConnection;
    WerkstuckEintragen<?> datensatz;
    
    @Override
    public void setUp() throws WerkstueckException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	datensatz = new  WerkstuckEintragen<IWerkstueckDatensatz<?>>(sqlConnection);
    }

    @Test
    public void testDBAdd() throws IllegalArgumentException, WerkstueckException {
	 java.util.Date date = new java.util.Date();
	datensatz.setDatensatz("TestDaten", 1, 666, date.getTime());
	
	boolean condition = datensatz.dbAdd();
	
	assertTrue("Das Eintragen ist fehlgeschlagen", condition);
    }

    @Override
    public void tearDown() throws SQLException {
	sqlConnection.close();
    }
    

}
