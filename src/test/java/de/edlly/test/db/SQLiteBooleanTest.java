package de.edlly.test.db;

import org.junit.Test;

import de.edlly.db.SQLiteUtil;
import junit.framework.TestCase;

/**
 * 
 * Test für die Transformation von Boolean in Integer und zurück.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class SQLiteBooleanTest extends TestCase {

    SQLiteUtil booleanTransformer = new SQLiteUtil();

    @Test
    public void testToBoolean() {
        int integreReturnEins = SQLiteUtil.booleanToInteger(true);
        assertSame("Sollte 1 sein", 1, integreReturnEins);

        int integreReturnNull = SQLiteUtil.booleanToInteger(false);
        assertSame("Sollte 0 sein", 0, integreReturnNull);

    }

    @Test
    public void testToInteger() {
        boolean boolReturnTrue = SQLiteUtil.integerToBoolean(1);
        assertTrue(boolReturnTrue);

        boolean boolReturnFalse = SQLiteUtil.integerToBoolean(0);
        assertFalse(boolReturnFalse);

    }

}
