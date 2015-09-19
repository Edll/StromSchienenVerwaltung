package werkstueck;

import org.junit.Test;
import static org.junit.Assert.*;
import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;

public class WerkstueckIdsTest extends TestCase {
    IWerkstueckIds werkstueckIds;

    @Override
    public void setUp() {
	SQLiteConnect sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	werkstueckIds = new WerkstueckIds(sqlConnection);
    }

    @Test
    public void testIdVorhanden() {
	int id = 0;
	boolean check = werkstueckIds.IdVorhanden(id);
	assertTrue("Bei einer 0 sollte ein False anzeigt werden.", check);	
    }
    @Test
    public void testGetIdList(){
	int[] list = werkstueckIds.getIdList();
	int[] listGet = {1, 2, 3, 4, 5};
 	assertArrayEquals(listGet, list);
    }
    
    

    @Override
    public void tearDown() {
    }

}
