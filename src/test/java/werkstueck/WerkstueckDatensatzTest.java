package werkstueck;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;

public class WerkstueckDatensatzTest extends TestCase {
    IWerkstueckDatensatz<?> datensatz;

    @Override
    public void setUp() throws WerkstueckException {
	SQLiteConnect sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	datensatz = new WerkstueckDatensatz<IWerkstueckDatensatz<?>>(sqlConnection);
    }

    @Test
    public void testGetName() {
	String actual = datensatz.getName();
	String expected = "N/A";

	assertEquals("Der string ist nicht korrekt", expected, actual);
    }

    @Test
    public void testSetName() {
	try {
	    datensatz.setName("");
	    fail("Name nicht angeben Exception nicht ausgelösen!");
	} catch (WerkstueckException e) {

	    boolean condition = e.getLocalizedMessage().contains("Es wurde kein Name angegeben!");
	    assertTrue("Der fehler String ist nicht korrekt.", condition);
	}
    }
    
    @Test
    public void testGetMaterialId() {
	int actual = datensatz.getMaterialId();
	int expected = 0;

	assertEquals(expected, actual);
    }

    @Test
    public void testSetMaterialId() throws SQLException {

	try {
	    datensatz.setMaterialId(0);
	    fail("Material id Fehler nicht vorhanden wurde nicht ausgelöst");
	} catch (IllegalArgumentException e) {

	    boolean condition = e.getLocalizedMessage().contains("Material Id nicht vorhanden");
	    assertTrue("Der fehler String ist nicht korrekt.", condition);
	}
    }

    @Test
    public void testGetProjektNr() {
	int actual = datensatz.getProjektNr();
	int expected = 0;

	assertEquals("Die Projekt nummer ist nicht korrekt übergeben worden", expected, actual);
    }

    @Test
    public void testSetProjektNr() {
	try {
	    datensatz.setProjektNr(0);
	    fail("Projekt Nummer 0 Exception nicht ausgelöst");
	} catch (WerkstueckException e) {
	    boolean condition = e.getLocalizedMessage().contains("Es wurde keine ProjektNr angegeben!");
	    assertTrue("Der fehler String ist nicht korrekt.", condition);
	}
    }

    @Test
    public void testGetErstellDatum() {
	int actual = datensatz.getErstellDatum();
	int expected = 0;

	assertEquals("Fehler bei der Datums übergabe", expected, actual);
    }
    
    @Test
    public void testSetErstellDatum() {
	try {
	    datensatz.setErstellDatum(0);
	    fail("Datum in der Vergangheit muss fehler Auslösen");
	} catch (WerkstueckException e) {
	    boolean condition = e.getLocalizedMessage().contains("Das Datum darf nicht in der Vergangenheit liegen.");
	    assertTrue("Der fehler String ist nicht korrekt.", condition);
	}
    }
    
    @Test
    public void testSetDatensatz() {
	try {
	    datensatz.setDatensatz("", 0, 0, 0);
	    fail("Muss Fehler auslösen");
	} catch (Exception e) {
	    boolean condition = e.getLocalizedMessage().contains("Angaben nicht korrekt: ");
	    assertTrue("Der fehler String ist nicht korrekt: " + e.getLocalizedMessage(), condition);
	}

    }

    @Test
    public void testGetDatensatz() throws WerkstueckException {
	List<IWerkstueckDatensatz<?>> list = datensatz.getDatensatz(1);
	IWerkstueckDatensatz<?> actual =  list.get(0);
	
	List<IWerkstueckDatensatz<?>> mock = datensatz.getDatensatz(1);
	IWerkstueckDatensatz<?> expected =  mock.get(0);
	
	assertEquals("Die test Liste stimmt nicht mit der Dummy Liste über einen!", expected, actual);
    }
    @Test
    public void testEintragenInDb() throws IllegalArgumentException, WerkstueckException, SQLException {
	datensatz.setDatensatz("TestWerkstueck", 1, 1, 1);
	boolean condition = datensatz.eintragenInDb();
	assertTrue(condition);
    }

    @Override
    public void tearDown() {
    }

}
