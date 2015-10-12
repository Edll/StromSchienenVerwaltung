package de.edlly.test.material;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.material.Material;
import de.edlly.material.MaterialIds;
import de.edlly.material.MaterialLoeschen;
import de.edlly.material.NeuerMaterialDatensatz;
import junit.framework.TestCase;

public class MaterialLoeschenTest extends TestCase {

    MaterialLoeschen materialLoeschen;
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws IllegalArgumentException, SQLiteException {
        sqlConnection = new SQLiteConnect();
        sqlConnection.dbConnect();
        materialLoeschen = new MaterialLoeschen(sqlConnection);
    }

    @Test
    public void testLoeschenIdNichtVorhanden() throws SQLiteException {
        try {
            materialLoeschen.loschen(0);
            fail("Exception fehlt: Material nicht vorhanden");
        } catch (IllegalArgumentException expected) {
            String erwartet = "Material Id nicht vorhanden";
            boolean check = expected.getLocalizedMessage().contains(erwartet);
            assertTrue("Fehler falsche Exception: " + expected.getLocalizedMessage(), check);
        }
    }

    @Test
    public void testLoeschen() throws SQLiteException {

        NeuerMaterialDatensatz materialDatensatz = new NeuerMaterialDatensatz(sqlConnection);
        materialDatensatz.setMaterialDaten(Material.MAX_X, Material.MAX_Z, Material.MAX_Y, 1);
        materialDatensatz.datensatzAusObjektWertenAnlegen();

        MaterialIds materialid = new MaterialIds(sqlConnection);
        int[] id = materialid.getIdListe();
        int lastId = id[id.length - 1];

        boolean check = materialLoeschen.loschen(lastId);

        assertTrue("Fehler beim löschen des Test Datensatz", check);
    }

    @Override
    public void tearDown() throws SQLiteException {
        sqlConnection.close();
    }
}
