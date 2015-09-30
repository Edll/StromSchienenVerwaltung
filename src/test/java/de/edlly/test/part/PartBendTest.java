package de.edlly.test.part;

import org.junit.Test;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.part.IPartBend;
import de.edlly.part.PartException;
import junit.framework.TestCase;

public class PartBendTest extends TestCase {
    SQLiteConnect sql;
    IPartBend bend;

    @Override
    public void setUp() throws PartException, SQLiteException {
	sql = new SQLiteConnect();
	sql.dbConnect();
	bend = new PartBend(sql);
    }

    @Test
    public void testSetBend() {
	bend.setBend(null);
    }
    
    @Test
    public void testGetBend() {
	bend.getBend(0);
    }
    
    @Test
    public void isBendKolision() {
	bend.isBendKolision(null);
    }

    @Override
    public void tearDown() throws SQLiteException {
	sql.close();
    }

}
