package de.edlly.test.gui.part;

import javax.swing.JTable;

import org.junit.Test;

import de.edlly.db.SQLiteException;
import de.edlly.gui.elements.PartTable;
import de.edlly.part.PartException;
import junit.framework.TestCase;

public class PartTableTest extends TestCase {
    PartTable table;

    @Override
    public void setUp() {
        table = new PartTable();
    }

    @Test
    public void test() throws SQLiteException, PartException {
        JTable actual = table.getPartTable();
        JTable expected = new JTable();

        assertNotNull(actual);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Override
    public void tearDown() {
    }

}