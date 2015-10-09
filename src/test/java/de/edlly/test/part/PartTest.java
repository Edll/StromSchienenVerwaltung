package de.edlly.test.part;

import org.junit.Test;
import de.edlly.db.SQLiteException;
import de.edlly.part.IPart;
import de.edlly.part.Part;
import de.edlly.part.PartException;
import junit.framework.TestCase;

public class PartTest extends TestCase {

    IPart part;

    @Override
    public void setUp() throws PartException, SQLiteException {



	part = new Part();
    }

    @Test
    public void testGetId() {
	int id = part.getId();
	assertEquals(0, id);
    }

    @Test
    public void testSetId() throws SQLiteException, PartException {
	int id = 0;
	    part.setId(id);
	assertEquals(0, part.getId());

    }


    
    @Test
    public void testGetMaterialYMax() throws IllegalArgumentException, SQLiteException, PartException {
	part.setMaterialId(1);
	int actual = part.getMaterialYMax();
	int expected = 4000;
	assertEquals(expected, actual);

    }
    @Test
    public void testGetName() {
	String actual = part.getName();
	String expected = "N/A";

	assertEquals("Der string ist nicht korrekt", expected, actual);
    }

    @Test
    public void testSetName() {
	try {
	    part.setName("");
	    fail("Name nicht angeben Exception nicht ausgelösen!");
	} catch (PartException e) {

	    boolean condition = e.getLocalizedMessage().contains("Es wurde kein Name angegeben!");
	    assertTrue("Der fehler String ist nicht korrekt.", condition);
	}
    }

    @Test
    public void testGetMaterialId() throws PartException {

	try {
	    part.getMaterialId();
	    fail("Erwarted wurde Exeption: Die MaterialId ist nicht initalisiert worden!");
	} catch (PartException actual) {

	    String expected = "Die MaterialId ist nicht initalisiert worden!";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testSetMaterialId() throws SQLiteException {

	try {
	    part.setMaterialId(0);
	    fail("Material id Fehler nicht vorhanden wurde nicht ausgelöst");
	} catch (IllegalArgumentException e) {

	    boolean condition = e.getLocalizedMessage().contains("Material Id nicht vorhanden");
	    assertTrue("Der fehler String ist nicht korrekt.", condition);
	}
    }

    @Test
    public void testGetProjektNr() throws PartException {
	part.setProjektNr(1);
	int actual = part.getProjektNr();
	int expected = 1;

	assertEquals("Die Projekt nummer ist nicht korrekt übergeben worden", expected, actual);
    }

    @Test
    public void testSetProjektNr() {
	try {
	    part.setProjektNr(0);
	    fail("Projekt Nummer 0 Exception nicht ausgelöst");
	} catch (PartException e) {
	    boolean condition = e.getLocalizedMessage().contains("Es wurde keine ProjektNr angegeben!");
	    assertTrue("Der fehler String ist nicht korrekt.", condition);
	}
    }

    @Test
    public void testGetErstellDatum() {
	long actual = part.getErstellDatum();
	long expected = 0;

	assertEquals("Fehler bei der Datums übergabe", expected, actual);
    }

    @Test
    public void testSetErstellDatum() {
	try {
	    part.setErstellDatum(0);
	    fail("Datum in der Vergangheit muss fehler Auslösen");
	} catch (PartException e) {
	    boolean condition = e.getLocalizedMessage().contains("Das Datum darf nicht in der Vergangenheit liegen.");
	    assertTrue("Der fehler String ist nicht korrekt.", condition);
	}
    }
    
    @Test
    public void testSetData() {
	try {
	    part.setData("", 0, 0, 0);
	    fail("Muss Fehler auslösen");
	} catch (Exception e) {
	    boolean condition = e.getLocalizedMessage().contains("Das Datum darf nicht in der Vergangenheit liegen.");
	    assertTrue("Der fehler String ist nicht korrekt: " + e.getLocalizedMessage(), condition);
	}

    }


    
    


    @Override
    public void tearDown() {
    }

}
