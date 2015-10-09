package de.edlly.test.part;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.part.*;
import junit.framework.TestCase;

public class BendTest extends TestCase {
    IBend<Double> bend;
    SQLiteConnect sqLite;

    @Override
    public void setUp() throws SQLiteException {
	bend = new Bend<Double>(1000D);
	sqLite = new SQLiteConnect();
	sqLite.dbConnect();
    }

    @Test
    public void testSetGetAngel() throws PartException {
	Double expected = 89.9D;
	bend.setAngel(expected);

	Number actual = bend.getAngel();
	assertEquals(expected, actual);
    }

    @Test
    public void testSetOverMaxAngel() {
	try {
	    Double expected = IBend.ANGEL_MAX.doubleValue();
	    expected++;
	    bend.setAngel(expected);
	    fail("PartException muss ausgelöst werden da Winkel größer als erlaubt.");
	} catch (PartException actual) {

	    String expected = "Der angegebene Winkel ist zu groß. Maximal";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testSetUnderMinAngel() {
	try {
	    double expected = IBend.ANGEL_MIN.doubleValue();
	    expected--;
	    bend.setAngel(expected);
	    fail("PartException muss ausgelöst werden da Winkel kleiner als erlaubt.");
	} catch (PartException actual) {

	    String expected = "Der angegebene Winkel ist zu klein. Minimal";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testSetGetY() throws PartException {
	Double expected = 900D;
	bend.setY(expected);
	Number actual = bend.getY();
	assertEquals(expected, actual);
    }

    @Test
    public void testSetOverMaXY() {
	try {
	    IPart part = new Part();
	    part.setMaterialId(1);
	    int expected = part.getMaterialYMax();
	    bend.setYMax((double) expected);
	    expected++;
	    bend.setY((double) expected);
	    fail("Wert ist größer als erlaubt muss eine Exption auslösen");
	} catch (Exception actual) {
	    String expected = "Der Angegebene Y Wert ist zu größ. Maximal";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testSetUnderMinY() {
	try {
	    int expected = IBend.ANGEL_MIN.intValue();
	    expected--;
	    bend.setY((double) expected);
	    fail("Wert ist kleiner als erlaubt muss eine Exption auslösen");
	} catch (Exception actual) {
	    String expected = "Der Angegebene Y Wert ist zu klein. Minimal";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testSetGetYMax() {
	double expected = 1000D;
	bend.setYMax(expected);
	Number actual = bend.getYMax();
	assertEquals(expected, actual);

    }

    @Test
    public void testCreate() throws PartException {
	double angel = IBend.ANGEL_MAX.doubleValue();
	double y = IBend.Y_MIN.doubleValue() + IBend.ABSTAND_RAND.doubleValue();
	bend.create(angel, y);
    }

    @Override
    public void tearDown() throws SQLiteException {
	sqLite.close();
    }

}
