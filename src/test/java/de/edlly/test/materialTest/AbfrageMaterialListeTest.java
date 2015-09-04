package de.edlly.test.materialTest;

import java.sql.*;

import org.junit.Test;
import junit.framework.TestCase;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.AbfrageMaterialListe;

/**
 * Tests für die AbfrageDatensatzMaterialTabelle
 * 
 * TODO: Code ist WIP!
 * 
 * 
 * @author Edlly java@edlly.de
 *
 */

public class AbfrageMaterialListeTest extends TestCase {
    
    AbfrageMaterialListe materialDatensatz;
    Connection sqlConnection;
    
    
    @Override
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();
	materialDatensatz = new AbfrageMaterialListe(sqlConnection);
    }
    
    @Test
    public void testGetMaterialListe() throws SQLException{
	boolean ausgelendeteDatensatzeAnzeigen = false;
	Object[] materialListe =  materialDatensatz.GetMaterialListe(ausgelendeteDatensatzeAnzeigen);
	
	assertNotNull("methode darf keine Null liefern", materialListe);
    }
    
    
    
    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }

}
