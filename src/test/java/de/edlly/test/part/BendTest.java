package de.edlly.test.part;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.db.SQLitePreparedStatement;
import de.edlly.db.SQLiteStatement;
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
	    int expected = IBend.Y_MIN.intValue();
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
    public void testSetRandYAbstandNichtEingehalten() {
	try {
	    int expected = IBend.ABSTAND_RAND.intValue();
	    expected--;
	    bend.setY((double) expected);
	    fail("Wert ist kleiner als erlaubt muss eine Exption auslösen");
	} catch (Exception actual) {
	    String expected = "Der Angegebene Y Wert ist zu klein. Der mindest Abstand zu";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testSetOverRandYAbstandNichtEingehalten() {
	try {
	    int expected = 1000 - IBend.ABSTAND_RAND.intValue();
	    expected++;
	    bend.setY((double) expected);
	    fail("Wert ist kleiner als erlaubt muss eine Exption auslösen");
	} catch (Exception actual) {
	    String expected = "Der Angegebene Y Wert ist zu groß. Der Maximale Wert ist:";
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
    public void testSetAngelAndY() throws PartException {
	double angel = IBend.ANGEL_MAX.doubleValue();
	double y = IBend.Y_MIN.doubleValue() + IBend.ABSTAND_RAND.doubleValue();
	bend.setAngelAndY(angel, y);
    }

    @Test
    public void testGetDB() throws Exception {
	SQLiteStatement sql = new SQLiteStatement(sqLite);
	int id = 1;
	bend.getDB(id, sql);
	double actual = bend.getAngel();
	assertNotSame(0D, actual);
    }

    @Test
    public void testGetDBFailIdNichtVorhanden() throws Exception {
	SQLiteStatement sql = new SQLiteStatement(sqLite);

	try {
	    int id = 1000;
	    bend.getDB(id, sql);
	    fail("Abrufen des Bends aus der Datenbank fehlgeschlagen.");
	} catch (Exception actual) {

	    String expected = "Abrufen des Bends aus der Datenbank fehlgeschlagen.";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testGetDBFloat() throws Exception {
	SQLiteStatement sql = new SQLiteStatement(sqLite);
	IBend<Float> bendFloat = new Bend<Float>(4000F);

	bendFloat.getDB(1, sql);

	assertTrue(bendFloat.getAngel().getClass() == Float.class);
	assertNotSame(0L, bendFloat.getAngel());

	assertTrue(bendFloat.getY().getClass() == Float.class);
	assertNotSame(0L, bendFloat.getY());
    }

    @Test
    public void testGetDBLong() throws Exception {
	SQLiteStatement sql = new SQLiteStatement(sqLite);
	IBend<Long> bendLong = new Bend<Long>(4000L);

	bendLong.getDB(1, sql);
	Long actual = bendLong.getAngel();

	assertTrue(bendLong.getAngel().getClass() == Long.class);
	assertNotSame(0L, actual);

	assertTrue(bendLong.getY().getClass() == Long.class);
	assertNotSame(0L, bendLong.getY());
    }

    @Test
    public void testGetDBInteger() throws Exception {
	SQLiteStatement sql = new SQLiteStatement(sqLite);
	IBend<Integer> bendInt = new Bend<Integer>(4000);

	bendInt.getDB(1, sql);
	Integer actual = bendInt.getAngel();

	assertTrue(bendInt.getAngel().getClass() == Integer.class);
	assertNotSame(0L, actual);

	assertTrue(bendInt.getY().getClass() == Integer.class);
	assertNotSame(0L, bendInt.getY());
    }

    @Test
    public void testGetDBDouble() throws Exception {
	SQLiteStatement sql = new SQLiteStatement(sqLite);
	IBend<Double> bendInt = new Bend<Double>(4000D);

	bendInt.getDB(1, sql);
	Double actual = bendInt.getAngel();

	assertTrue(bendInt.getAngel().getClass() == Double.class);
	assertNotSame(0D, actual);

	assertTrue(bendInt.getY().getClass() == Double.class);
	assertNotSame(0D, bendInt.getY());
    }

    @Test
    public void testGetDBFailNumberFormatNichtVorhanden() throws Exception {
	SQLiteStatement sql = new SQLiteStatement(sqLite);
	byte test = 100;
	IBend<Byte> bendInt = new Bend<Byte>(test);

	try {
	    bendInt.getDB(1, sql);
	    bendInt.getAngel();

	    fail("Nicht vorhandenes Zahlenformat angefordert.");
	} catch (Exception actual) {

	    String expected = "Nicht vorhandenes Zahlenformat angefordert.";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testAddDBDouble() throws SQLiteException, PartException {
	SQLitePreparedStatement sql = new SQLitePreparedStatement(sqLite);

	IPart part = new Part();
	part.setId(1);

	IBend<Double> bend = new Bend<Double>(4000D, 80D, 200D);
	bend.setPart(part);

	bend.addDB(sql);
	int actual = bend.getId();

	assertNotSame(0, actual);
    }

    @Test
    public void testAddDBFloat() throws SQLiteException, PartException {
	SQLitePreparedStatement sql = new SQLitePreparedStatement(sqLite);

	IPart part = new Part();
	part.setId(1);

	IBend<Float> bend = new Bend<Float>(4000F, 80.10F, 200.20F);
	bend.setPart(part);

	bend.addDB(sql);
	int actual = bend.getId();

	assertNotSame(0, actual);
    }

    @Test
    public void testSetPart() throws SQLiteException, PartException {
	try {
	    IPart part = null;
	    bend.setPart(part);
	    fail("Nicht gesetz worden.");
	} catch (Exception actual) {

	    String expected = "Part Id nicht angelegt";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }

    @Override
    public void tearDown() throws SQLiteException {
	sqLite.close();
    }

}
