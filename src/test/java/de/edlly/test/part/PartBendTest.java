package de.edlly.test.part;

import java.util.List;
import org.junit.Test;
import junit.framework.TestCase;
import de.edlly.db.*;
import de.edlly.part.*;


public class PartBendTest extends TestCase {
    SQLiteConnect sql;
    IPartBend partBend;

    @Override
    public void setUp() throws PartException, SQLiteException {
	sql = new SQLiteConnect();
	sql.dbConnect();
	partBend = new PartBend(sql);
    }

    @Test
    public void testAddGetBend() throws PartException {
	IBend<Double> bend1 = new Bend<Double>(4000D, 80D, 100D);
	IBend<Double> bend2 = new Bend<Double>(4000D, 70D, 200D);
	IBend<Double> bend3 = new Bend<Double>(4000D, -80D, 400D);

	partBend.addBend(bend1);
	partBend.addBend(bend2);
	partBend.addBend(bend3);

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
	    partBend.addBend(bend1);
	    partBend.addBend(bend2);
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

	partBend.addBend(bend1);
	boolean condition = partBend.isBendKollision(bend1);

	assertTrue("Kollision auf dem Objekt wurde erwartet", condition);

	partBend.addBend(bend2);
	boolean condition2 = partBend.isBendKollision(bend3);
	assertFalse("Keine Kollision", condition2);

	partBend.addBend(bend3);
	boolean condition3 = partBend.isBendKollision(bend3);
	assertTrue("Kollision auf dem Objekt wurde erwartet", condition3);

	boolean condition4 = partBend.isBendKollision(bend4);
	assertTrue("Kollision auf dem Objekt wurde erwartet", condition4);

	boolean condition5 = partBend.isBendKollision(bend5);
	assertTrue("Kollision auf dem Objekt wurde erwartet", condition5);
    }


    @Override
    public void tearDown() throws SQLiteException {
	sql.close();
    }

}
