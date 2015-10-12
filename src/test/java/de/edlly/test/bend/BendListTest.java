package de.edlly.test.bend;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import junit.framework.TestCase;
import de.edlly.bend.Bend;
import de.edlly.bend.BendList;
import de.edlly.bend.IBend;
import de.edlly.bend.IBendList;
import de.edlly.db.*;
import de.edlly.part.*;

public class BendListTest extends TestCase {
    IBendList bendList;
    SQLiteConnect sql = new SQLiteConnect();

    @Override
    public void setUp() throws PartException, SQLiteException {
        bendList = new BendList();
        sql.dbConnect();
    }

    @Test
    public void testAddGetBend() throws PartException {
        IBend<Double> bend1 = new Bend<Double>(4000D, 80D, 100D);
        IBend<Double> bend2 = new Bend<Double>(4000D, 70D, 200D);
        IBend<Double> bend3 = new Bend<Double>(4000D, -80D, 400D);

        bendList.addBendSort(bend1);
        bendList.addBendSort(bend2);
        bendList.addBendSort(bend3);

        List<IBend<?>> list = bendList.getBends();

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
            bendList.addBendSort(bend1);
            bendList.addBendSort(bend2);
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

        bendList.addBendSort(bend1);
        boolean condition = bendList.isBendKollision(bend1);

        assertTrue("Kollision auf dem Objekt wurde erwartet", condition);

        bendList.addBendSort(bend2);
        boolean condition2 = bendList.isBendKollision(bend3);
        assertFalse("Keine Kollision", condition2);

        bendList.addBendSort(bend3);
        boolean condition3 = bendList.isBendKollision(bend3);
        assertTrue("Kollision auf dem Objekt wurde erwartet", condition3);

        boolean condition4 = bendList.isBendKollision(bend4);
        assertTrue("Kollision auf dem Objekt wurde erwartet", condition4);

        boolean condition5 = bendList.isBendKollision(bend5);
        assertTrue("Kollision auf dem Objekt wurde erwartet", condition5);
    }

    @Test
    public void testSortList() throws Exception {
        IBend<Double> bend1 = new Bend<Double>(4000D, 80D, 100D);
        IBend<Double> bend2 = new Bend<Double>(4000D, 70D, 200D);
        IBend<Double> bend3 = new Bend<Double>(4000D, 60D, 300D);
        IBend<Double> bend4 = new Bend<Double>(4000D, 50D, 400D);
        IBend<Double> bend5 = new Bend<Double>(4000D, 40D, 500D);

        bendList.addBend(bend1);
        bendList.addBend(bend3);
        bendList.addBend(bend5);
        bendList.addBend(bend4);
        bendList.addBend(bend2);

        List<IBend<?>> list = bendList.getBends();

        assertEquals(list.get(0), bend1);
        assertEquals(list.get(1), bend3);
        assertEquals(list.get(2), bend5);
        assertEquals(list.get(3), bend4);
        assertEquals(list.get(4), bend2);

        bendList.sortList();

        List<IBend<?>> listSort = bendList.getBends();

        assertEquals(listSort.get(0), bend1);
        assertEquals(listSort.get(1), bend2);
        assertEquals(listSort.get(2), bend3);
        assertEquals(listSort.get(3), bend4);
        assertEquals(listSort.get(4), bend5);
    }

    @Test
    public void testAddListToDBListNull() throws SQLiteException {
        try {
            bendList.addListToDB();
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

        bendList.addBend(bend1);
        bendList.addBend(bend2);
        bendList.addBend(bend3);

        boolean check = bendList.addListToDB();

        assertTrue("Liste nicht eingetragen", check);
    }

    @Test
    public void testBendAddToDdAndSetPartId() throws PartException, SQLiteException {
        IBend<Double> bend1 = new Bend<Double>(4000D, 80D, 100D);
        IBend<Double> bend2 = new Bend<Double>(4000D, 70D, 200D);
        IBend<Double> bend3 = new Bend<Double>(4000D, 60D, 300D);
        bendList.addBend(bend1);
        bendList.addBend(bend2);
        bendList.addBend(bend3);

        IPart datensatz = new Part();
        java.util.Date date = new java.util.Date();
        datensatz.setData("TestDaten", 1, 666, date.getTime());

        IPartNew partDataAdd = new PartNew(sql);
        partDataAdd.setPart(datensatz);
        partDataAdd.addToDdAndSetPartId();
        bendList.setPart(datensatz);

        bendList.addListToDB();

    }

    @Test
    public void testGetIdList() throws SQLiteException, PartException {
        SQLiteStatement sqlStatment = new SQLiteStatement(sql);
        List<Integer> getList = bendList.getIdList(sqlStatment);

        assertNotNull(getList);

        List<Integer> expectedList = new ArrayList<Integer>();
        expectedList.add(1);

        assertEquals(expectedList.get(0), getList.get(0));
    }

    @Test
    public void testIsIdVorhanden() throws SQLiteException, PartException {

        assertTrue(bendList.isIdVorhanden(1));
        assertFalse(bendList.isIdVorhanden(1000));
    }

    @Test
    public void testGetBendAfterPartId() throws SQLiteException, PartException {

        SQLiteStatement sqlStatment = new SQLiteStatement(sql);
        List<IBend<?>> bendAfterId = new ArrayList<IBend<?>>();

        IPart part = new Part();
        part.getDB(1, sqlStatment);

        IBend<Double> bend = new Bend<Double>(((Number) part.getMaterialYMax()).doubleValue());
        bendAfterId = bendList.getBendAfterPartId(bend, part.getId());

        assertEquals(1, bendAfterId.get(0).getId());
    }

    @Override
    public void tearDown() throws SQLiteException {
        sql.close();
    }

}
