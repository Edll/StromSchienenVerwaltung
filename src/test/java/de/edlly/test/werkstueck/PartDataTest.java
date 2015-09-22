package de.edlly.test.werkstueck;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

import java.sql.SQLException;
import java.util.List;

import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;
import werkstueck.IPartData;
import werkstueck.PartException;
import werkstueck.PartData;

public class PartDataTest extends TestCase {

    IPartData<?> datensatz;
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws PartException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	datensatz = new PartData<IPartData<?>>(sqlConnection);
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
	} catch (PartException e) {

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
    public void testGetProjektNr() throws PartException {
	datensatz.setProjektNr(1);
	int actual = datensatz.getProjektNr();
	int expected = 1;

	assertEquals("Die Projekt nummer ist nicht korrekt übergeben worden", expected, actual);
    }

    @Test
    public void testSetProjektNr() {
	try {
	    datensatz.setProjektNr(0);
	    fail("Projekt Nummer 0 Exception nicht ausgelöst");
	} catch (PartException e) {
	    boolean condition = e.getLocalizedMessage().contains("Es wurde keine ProjektNr angegeben!");
	    assertTrue("Der fehler String ist nicht korrekt.", condition);
	}
    }

    @Test
    public void testGetErstellDatum() {
	long actual = datensatz.getErstellDatum();
	long expected = 0;

	assertEquals("Fehler bei der Datums übergabe", expected, actual);
    }

    @Test
    public void testSetErstellDatum() {
	try {
	    datensatz.setErstellDatum(0);
	    fail("Datum in der Vergangheit muss fehler Auslösen");
	} catch (PartException e) {
	    boolean condition = e.getLocalizedMessage().contains("Das Datum darf nicht in der Vergangenheit liegen.");
	    assertTrue("Der fehler String ist nicht korrekt.", condition);
	}
    }

    @Test
    public void testSetDatensatz() {
	try {
	    datensatz.setData("", 0, 0, 0);
	    fail("Muss Fehler auslösen");
	} catch (Exception e) {
	    boolean condition = e.getLocalizedMessage().contains("Angaben nicht korrekt: ");
	    assertTrue("Der fehler String ist nicht korrekt: " + e.getLocalizedMessage(), condition);
	}

    }

    @Test
    public void testGetDatensatz() throws PartException {
	List<IPartData<?>> list = datensatz.getData(1);
	IPartData<?> actual = list.get(0);

	List<IPartData<?>> mock = datensatz.getData(1);
	IPartData<?> expected = mock.get(0);

	assertEquals("Die test Liste stimmt nicht mit der Dummy Liste über einen!", expected, actual);
    }

    @Test
    public void testIdVorhanden() {
	int id = 0;
	boolean check = datensatz.IdVorhanden(id);
	assertFalse("Bei einer 0 sollte ein False anzeigt werden.", check);
    }

    @Test
    public void testGetIdList() {
	int[] list = datensatz.getIdList();
	int[] listGet = { 1, 2, 3, 4, 5 };
	assertArrayEquals(listGet, list);
    }

    @Override
    public void tearDown() throws SQLException {
	sqlConnection.close();
    }

}
