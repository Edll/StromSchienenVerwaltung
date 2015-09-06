package de.edlly.test.materialTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialIds;

public class MaterialIdsTest {
    Connection sqlConnection;
    MaterialIds materialIds;
    
    @Before
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();
	materialIds = new MaterialIds(sqlConnection);
    }



    @Test
    public void testsqlAbfrageMaterialIdsKeineNullDatensatzAnzeigen() throws SQLException, IllegalArgumentException{
	boolean ausgelendeteDatensatzeNichtAnzeigen = true;
	int[] materialListe =  materialIds.sqlAbfrageMaterialIds(ausgelendeteDatensatzeNichtAnzeigen);
	
	assertNotNull("methode darf keine Null liefern", materialListe);
    }
    
    @Test
    public void testsqlAbfrageMaterialIdsKeineNullDatensatzAusblenden() throws SQLException, IllegalArgumentException{
	boolean ausgelendeteDatensatzeNichtAnzeigen = false;
	int[] materialListe =  materialIds.sqlAbfrageMaterialIds(ausgelendeteDatensatzeNichtAnzeigen);
	
	assertNotNull("methode darf keine Null liefern", materialListe);
    }
    
    @After
    public void tearDown() {
    }

}
