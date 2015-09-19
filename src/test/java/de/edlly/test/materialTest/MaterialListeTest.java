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
    SQLiteConnect sqlConnection;
    
    
    @Override
    public void setUp() throws IllegalArgumentException, SQLException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	materialDatensatz = new MaterialListe(sqlConnection);
    }
    
    @Test
    public void testGetMaterialListeKeineNullDatensatzAnzeigen() throws IllegalArgumentException, SQLException {
	
	materialDatensatz.setAusgeblendetDatenAnzeigen(true);
	Object[][] materialListe = materialDatensatz.getMaterialListe();
	
	assertNotNull("methode darf keine Null liefern", materialListe);
    }    
    @Test
    public void testGetMaterialListeKeineNullDatensatzAusblenden() throws IllegalArgumentException, SQLException {
	try{
	    materialDatensatz.setAusgeblendetDatenAnzeigen(false);
	    Object[][] materialListe = materialDatensatz.getMaterialListe();
	assertNotNull("methode darf keine Null liefern", materialListe);
	}catch(IllegalArgumentException e){
	    
	}

    }
    
    @Test
    public void testGetMaterialListeIstObject() throws SQLException, IllegalArgumentException{
	  materialDatensatz.setAusgeblendetDatenAnzeigen(false);
	Object[][] vergleichsFormate = new Object[0][0];  
	assertEquals(vergleichsFormate.getClass(), materialDatensatz.getMaterialListe().getClass());
    }
    
    
   
    
    @Test 
    public void testgetMaterialListeFormatiertKeineNullDatensatzAnzeigen() throws SQLException, IllegalArgumentException{

	  materialDatensatz.setAusgeblendetDatenAnzeigen(true);
	Object[][] materialListe =  materialDatensatz.getMaterialListeFormatiert();
	assertNotNull("methode darf keine Null liefern", materialListe);
    }
    
    @Test 
    public void testgetMaterialListeFormatiertKeineNullDatensatzAusblenden() throws SQLException, IllegalArgumentException{

	  materialDatensatz.setAusgeblendetDatenAnzeigen(false);
	Object[][] materialListe =  materialDatensatz.getMaterialListeFormatiert();
	assertNotNull("methode darf keine Null liefern", materialListe);

    }
    
    
    
    @Override
    public void tearDown() throws SQLException {
	sqlConnection.close();
    }

}
