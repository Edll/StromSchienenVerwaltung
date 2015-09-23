package de.edlly.test.werkstueck;

import org.junit.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import de.edlly.db.SQLiteConnect;
import de.edlly.werkstueck.*;

import junit.framework.TestCase;

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
    public void testSetData() {
	try {
	    datensatz.setData("", 0, 0, 0);
	    fail("Muss Fehler auslösen");
	} catch (Exception e) {
	    boolean condition = e.getLocalizedMessage().contains("Angaben nicht korrekt: ");
	    assertTrue("Der fehler String ist nicht korrekt: " + e.getLocalizedMessage(), condition);
	}

    }

    @Test
    public void testGetData() throws PartException {
	IPartData<?> data = new PartData<IPartData<?>>(sqlConnection);
	data = datensatz.getData(1);

	int actual = data.getId();
	int expected = 1;

	assertEquals("Die erhaltene Id weicht von der erwarteten ab.", expected, actual);
    }

    @Test
    public void testGetDataListAll() throws PartException, SQLException {
	List<IPartData<?>> list = datensatz.getDataList();

	assertEquals("Das erste Listenelement Stimmt nicht", 1, list.get(0).getId());
	assertEquals("Das zweite Listenelement Stimmt nicht", 2, list.get(1).getId());
	assertEquals("Das zweite Listenelement Stimmt nicht", 3, list.get(2).getId());

    }

    /**
     * 
     * TODO: umstellen auf ein Mock Objekt?
     */
    @Test
    public void testGetDataListId() throws PartException {
	List<IPartData<?>> list = datensatz.getDataList(new int[] { 1, 2, 3 });

	assertEquals("Das erste Listenelement Stimmt nicht", 1, list.get(0).getId());
	assertEquals("Das zweite Listenelement Stimmt nicht", 2, list.get(1).getId());
	assertEquals("Das zweite Listenelement Stimmt nicht", 3, list.get(2).getId());

	assertEquals("Die größe ist nicht korrekt", 3, list.size());
    }

    @Test
    public void testIdVorhanden() {
	int id = 0;
	boolean check = datensatz.IdVorhanden(id);
	assertFalse("Bei einer 0 sollte ein False anzeigt werden.", check);
    }

    @Test
    public void testGetIdList() throws SQLException {
	List<Integer> list = datensatz.getIdList();
	List<Integer> idTestList = new ArrayList<Integer>();

	idTestList.add(new Integer(1));
	idTestList.add(new Integer(2));
	idTestList.add(new Integer(3));

	assertEquals("Das erste Listenelement Stimmt nicht", idTestList.get(0), list.get(0));
	assertEquals("Das zweite Listenelement Stimmt nicht", idTestList.get(1), list.get(1));
	assertEquals("Das dritte Listenelement Stimmt nicht", idTestList.get(2), list.get(2));
    }

    @Override
    public void tearDown() throws SQLException {
	sqlConnection.close();
    }

}
