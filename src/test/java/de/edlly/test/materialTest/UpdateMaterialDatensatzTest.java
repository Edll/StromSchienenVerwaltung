package de.edlly.test.materialTest;

import java.sql.Connection;
import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.UpdateMaterialDatensatz;
import junit.framework.TestCase;

public class UpdateMaterialDatensatzTest extends TestCase {

    private Connection sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLException {
	sqlConnection = SQLiteConnect.dbConnection();

    }

    public void testSetMaterialDaten() {
	assertTrue("Set Material Daten in Objekt", setMaterialDaten());
    }

    public void testSetMaterialVisibly() {
	assertTrue("Set Material Visibly in Objekt", setMaterialVisibly());
    }

    public void testSetMaterialId()  {
	assertTrue("Set Material Id in Objekt", setMaterialId());
    }

    public void testUpdateVisibly() {
	assertTrue("Update Visibly in Objekt", updateVisibly());
    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);

    }

    private Boolean setMaterialDaten() {
	// TODO: Funktion einbauen
	return true;
    }

    private Boolean setMaterialVisibly() {
	// TODO: Funktion einbauen
	return true;
    }

    private Boolean setMaterialId() {
	try {
	    UpdateMaterialDatensatz materialDatensetzten = new UpdateMaterialDatensatz(sqlConnection);

	    materialDatensetzten.setMaterialId(1);
	    materialDatensetzten.setMaterialId(2);
	    materialDatensetzten.setMaterialId(3);
	    return true;
	} catch (IllegalArgumentException e) {
	    // e.printStackTrace();
	    return false;
	}

    }

    private Boolean updateVisibly() {
	// TODO: Funktion einbauen
	return true;
    }

}
