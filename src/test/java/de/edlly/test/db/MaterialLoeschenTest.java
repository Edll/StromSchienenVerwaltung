package de.edlly.test.db;

import java.sql.SQLException;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialLoeschen;
import junit.framework.TestCase;

public class MaterialLoeschenTest extends TestCase {
    
    MaterialLoeschen materialLoeschen;
    SQLiteConnect sqlConnection;
    
    @Override
    public void setUp() throws IllegalArgumentException, SQLException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	materialLoeschen = new MaterialLoeschen(sqlConnection);
    }
    
    @Test
    public void testLoeschen(){
	/*try{
	materialLoeschen.loschen(0);
	fail("Exception fehlt: Material nicht vorhanden");
	}catch(IllegalArgumentException expected){
	    String erwartet = "Material Id nicht vorhanden";
	    boolean check = expected.getLocalizedMessage().contains(erwartet);
	    assertTrue("Fehler falsche Exception: " + expected.getLocalizedMessage(), check);	    
	}*/
    }
    
    @Override
    public void tearDown() throws SQLException {
	sqlConnection.close();
    }
}
