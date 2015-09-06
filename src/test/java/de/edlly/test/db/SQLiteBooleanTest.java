package de.edlly.test.db;

import org.junit.Test;

import de.edlly.db.SQLiteBoolean;
import junit.framework.TestCase;

/**
 * 
 * Test für die Transformation von Boolean in Integer und zurück.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class SQLiteBooleanTest extends TestCase {

    SQLiteBoolean booleanTransformer = new SQLiteBoolean();

    @Test
    public void testSetBoolean() {
	booleanTransformer.setBool(true);
	assertTrue(booleanTransformer.isBool());

	booleanTransformer.setBool(false);
	assertFalse(booleanTransformer.isBool());
    }

    @Test
    public void testSetInteger() {
	booleanTransformer.setInteger(0);
	assertSame("Sollte 0 sein", 0, booleanTransformer.getInteger());

	booleanTransformer.setInteger(1);
	assertSame("Sollte 1 sein", 1, booleanTransformer.getInteger());
    }

    @Test
    public void testToBoolean() {
	int integreReturnEins = SQLiteBoolean.booleanToInteger(true);
	assertSame("Sollte 1 sein", 1, integreReturnEins);

	int integreReturnNull = SQLiteBoolean.booleanToInteger(false);
	assertSame("Sollte 0 sein", 0, integreReturnNull);

    }

    @Test
    public void testToInteger() {
	boolean boolReturnTrue = SQLiteBoolean.integerToBoolean(1);
	assertTrue(boolReturnTrue);

	boolean boolReturnFalse = SQLiteBoolean.integerToBoolean(0);
	assertFalse(boolReturnFalse);

    }

}
