package de.edlly.test.materialTest;

import org.junit.Test;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialSorte;


public class MaterialSorteTest extends TestCase  {
    MaterialSorte materialSorte;
    Connection sqlConnection;
    
    
    @Override
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();
	materialSorte = new MaterialSorte(sqlConnection);
    }
    

    @Test
    public void testMaterialSortenName() throws SQLException {
	String nameBekommen = materialSorte.materialSortenName(0); 
	String nameErwartet = "N/A";
	assertEquals(nameErwartet, nameBekommen);
    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }


}
