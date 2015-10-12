package de.edlly.test.material;

import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.material.UpdateMaterialDatensatz;
import junit.framework.TestCase;

public class UpdateMaterialDatensatzTest extends TestCase {

    private SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLiteException {
        sqlConnection = new SQLiteConnect();
        sqlConnection.dbConnect();

    }

    public void testSetMaterialDaten() {
        assertTrue("Set Material Daten in Objekt", setMaterialDaten());
    }

    public void testSetMaterialVisibly() {
        assertTrue("Set Material Visibly in Objekt", setMaterialVisibly());
    }

    public void testSetMaterialId() throws SQLiteException {
        assertTrue("Set Material Id in Objekt", setMaterialId());
    }

    public void testUpdateVisibly() {
        assertTrue("Update Visibly in Objekt", updateVisibly());
    }

    private Boolean setMaterialDaten() {
        // TODO: Funktion einbauen
        return true;
    }

    private Boolean setMaterialVisibly() {
        // TODO: Funktion einbauen
        return true;
    }

    private Boolean setMaterialId() throws SQLiteException {
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

    @Override
    public void tearDown() throws SQLException, SQLiteException {
        sqlConnection.close();

    }

}
