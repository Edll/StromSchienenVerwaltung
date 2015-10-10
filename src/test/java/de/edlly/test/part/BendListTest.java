package de.edlly.test.part;

import java.util.List;
import org.junit.Test;
import junit.framework.TestCase;
import de.edlly.db.*;
import de.edlly.part.*;

public class BendListTest extends TestCase {
    IBendList partBend;
    SQLiteConnect sql = new SQLiteConnect();

    @Override
    public void setUp() throws PartException, SQLiteException {
	partBend = new BendList();
	sql.dbConnect();
    }

    @Test
    public void testAddGetBend() throws PartException {
	IBend<Double> bend1 = new Bend<Double>(4000D, 80D, 100D);
	IBend<Double> bend2 = new Bend<Double>(4000D, 70D, 200D);
	IBend<Double> bend3 = new Bend<Double>(4000D, -80D, 400D);

	partBend.addBendSort(bend1);
	partBend.addBendSort(bend2);
	partBend.addBendSort(bend3);

	List<IBend<?>> list = partBend.getBends();

	assertNotNull("Liste darf Nicht leer sein", list);
	assertEquals("erstes Element nicht gleich", list.get(0), bend1);
	assertEquals("erstes Element nicht gleich", list.get(1), bend2);
	assertEquals("erstes Element nicht gleich", list.get(2), bend3);
    }

    @Test
    public void testFailAddBend() throws PartException {
	IBend<Double> bend1 = new Bend<Double>(4000D, 80D, 100D);
	IBend<Double> bend2 = new Bend<Double>(4000D, 70D, 100D);

	try {
	    partBend.addBendSort(bend1);
	    partBend.addBendSort(bend2);
	    fail("Fehler Kollision zweier Bends");
	} catch (Exception actual) {
	    String expected = "Fehler: Der minimal Abstand ist nicht eingehalten worden ";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}
    }

    @Test
    public void testIsBendKollision() throws PartException {
	IBend<Double> bend1 = new Bend<Double>(4000D, 80D, 100D);
	IBend<Double> bend2 = new Bend<Double>(4000D, 80D, 200D);
	IBend<Double> bend3 = new Bend<Double>(4000D, -80D, 400D);
	IBend<Double> bend4 = new Bend<Double>(4000D, -80D, 400D + IBend.ABSTAND_BEND.doubleValue() - 1D);
	IBend<Double> bend5 = new Bend<Double>(4000D, -80D, 400D - IBend.ABSTAND_BEND.doubleValue() + 1D);

	partBend.addBendSort(bend1);
	boolean condition = partBend.isBendKollision(bend1);

	assertTrue("Kollision auf dem Objekt wurde erwartet", condition);

	partBend.addBendSort(bend2);
	boolean condition2 = partBend.isBendKollision(bend3);
	assertFalse("Keine Kollision", condition2);

	partBend.addBendSort(bend3);
	boolean condition3 = partBend.isBendKollision(bend3);
	assertTrue("Kollision auf dem Objekt wurde erwartet", condition3);

	boolean condition4 = partBend.isBendKollision(bend4);
	assertTrue("Kollision auf dem Objekt wurde erwartet", condition4);

	boolean condition5 = partBend.isBendKollision(bend5);
	assertTrue("Kollision auf dem Objekt wurde erwartet", condition5);
    }

    @Test
    public void testSortList() throws Exception {
	IBend<Double> bend1 = new Bend<Double>(4000D, 80D, 100D);
	IBend<Double> bend2 = new Bend<Double>(4000D, 70D, 200D);
	IBend<Double> bend3 = new Bend<Double>(4000D, 60D, 300D);
	IBend<Double> bend4 = new Bend<Double>(4000D, 50D, 400D);
	IBend<Double> bend5 = new Bend<Double>(4000D, 40D, 500D);

	partBend.addBend(bend1);
	partBend.addBend(bend3);
	partBend.addBend(bend5);
	partBend.addBend(bend4);
	partBend.addBend(bend2);

	List<IBend<?>> list = partBend.getBends();

	assertEquals(list.get(0), bend1);
	assertEquals(list.get(1), bend3);
	assertEquals(list.get(2), bend5);
	assertEquals(list.get(3), bend4);
	assertEquals(list.get(4), bend2);

	partBend.sortList();

	List<IBend<?>> listSort = partBend.getBends();

	assertEquals(listSort.get(0), bend1);
	assertEquals(listSort.get(1), bend2);
	assertEquals(listSort.get(2), bend3);
	assertEquals(listSort.get(3), bend4);
	assertEquals(listSort.get(4), bend5);
    }

    @Test
    public void testAddListToDBListNull() throws SQLiteException {
	try {
	    partBend.addListToDB();
	    fail("Fehler wurde nicht ausgelöst: Liste wurde nicht angeleget.");
	} catch (PartException actual) {

	    String expected = "Liste wurde nicht angelegt";
	    boolean check = actual.getLocalizedMessage().contains(expected);
	    assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
	}

    }

    @Test
    public void testAddListToDBListEingetragen() throws SQLiteException, PartException {
	IBend<Double> bend1 = new Bend<Double>(4000D, 80D, 100D);
	IBend<Double> bend2 = new Bend<Double>(4000D, 70D, 200D);
	IBend<Double> bend3 = new Bend<Double>(4000D, 60D, 300D);

	partBend.addBend(bend1);
	partBend.addBend(bend2);
	partBend.addBend(bend3);

	boolean check = partBend.addListToDB();

	assertTrue("Liste nicht eingetragen", check);
    }
    
    @Test
    public void testBendGetPrimaryPartId() throws PartException, SQLiteException{
	IBend<Double> bend1 = new Bend<Double>(4000D, 80D, 100D);
	IBend<Double> bend2 = new Bend<Double>(4000D, 70D, 200D);
	IBend<Double> bend3 = new Bend<Double>(4000D, 60D, 300D);
	partBend.addBend(bend1);
	partBend.addBend(bend2);
	partBend.addBend(bend3);
	
	IPart datensatz = new Part();
	java.util.Date date = new java.util.Date();
	datensatz.setData("TestDaten", 1, 666, date.getTime());
	
	IPartNew partDataAdd = new PartNew(sql);
	partDataAdd.setPart(datensatz);
	partDataAdd.addToDdAndSetPartId();
	partBend.setPartId(datensatz.getId());
	
	partBend.addListToDB();
	
    }

    @Override
    public void tearDown() throws SQLiteException {
	sql.close();
    }

}
