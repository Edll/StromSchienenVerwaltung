package de.edlly.test.gui.part;

import java.sql.SQLException;

import javax.swing.JTable;

import org.junit.Test;

import de.edlly.part.PartException;
import de.edlly.part.PartTable;
import junit.framework.TestCase;

public class PartTableTest extends TestCase {
    PartTable table;

    @Override
    public void setUp() {
	table = new PartTable();
    }

    @Test
    public void test() throws SQLException, PartException {
	JTable actual = table.getPartTable();
	JTable expected = new JTable();

	assertNotNull(actual);
	assertEquals(expected.getClass(), actual.getClass());
    }

    @Override
    public void tearDown() {
    }

}