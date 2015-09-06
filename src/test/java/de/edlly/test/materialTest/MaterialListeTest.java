package de.edlly.test.materialTest;

import java.sql.*;

import org.junit.Test;
import junit.framework.TestCase;
import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialListe;

/**
 * Tests für die AbfrageDatensatzMaterialTabelle
 * 
 * TODO: Code ist WIP!
 * 
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialListeTest extends TestCase {
    
    MaterialListe materialDatensatz;
    Connection sqlConnection;
    
    
    @Override
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();
	materialDatensatz = new MaterialListe(sqlConnection);
    }
    
    @Test
    public void testGetMaterialListeKeineNullDatensatzAnzeigen() throws IllegalArgumentException, SQLException {
	boolean ausgelendeteDatensatzeNichtAnzeigen = true;
	Object[][] materialListe =  materialDatensatz.getMaterialListe(ausgelendeteDatensatzeNichtAnzeigen);
	
	assertNotNull("methode darf keine Null liefern", materialListe);
    }    
    @Test
    public void testGetMaterialListeKeineNullDatensatzAusblenden() throws IllegalArgumentException, SQLException {
	boolean ausgelendeteDatensatzeNichtAnzeigen = false;
	try{
	    Object[][] materialListe =  materialDatensatz.getMaterialListe(ausgelendeteDatensatzeNichtAnzeigen);
	assertNotNull("methode darf keine Null liefern", materialListe);
	}catch(IllegalArgumentException e){
	    
	}

    }
    
    @Test
    public void testGetMaterialListeIstObject() throws SQLException, IllegalArgumentException{
	boolean ausgelendeteDatensatzeNichtAnzeigen = true;
	Object[][] vergleichsFormate = new Object[0][0];  
	assertEquals(vergleichsFormate.getClass(), materialDatensatz.getMaterialListe(ausgelendeteDatensatzeNichtAnzeigen).getClass());
    }
    
    
   
    
    @Test 
    public void testgetMaterialListeFormatiertKeineNullDatensatzAnzeigen() throws SQLException, IllegalArgumentException{
	boolean ausgelendeteDatensatzeNichtAnzeigen = true;
	
	Object[][] materialListe =  materialDatensatz.getMaterialListeFormatiert(ausgelendeteDatensatzeNichtAnzeigen);
	assertNotNull("methode darf keine Null liefern", materialListe);
    }
    
    @Test 
    public void testgetMaterialListeFormatiertKeineNullDatensatzAusblenden() throws SQLException, IllegalArgumentException{
	boolean ausgelendeteDatensatzeNichtAnzeigen = false;
	
	Object[][] materialListe =  materialDatensatz.getMaterialListeFormatiert(ausgelendeteDatensatzeNichtAnzeigen);
	assertNotNull("methode darf keine Null liefern", materialListe);

    }
    
    
    
    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }

}
