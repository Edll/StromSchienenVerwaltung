package de.edlly.test.part;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.part.*;
import junit.framework.TestCase;

public class PartListTest extends TestCase {

    IPartList datensatz;
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws PartException, SQLiteException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	datensatz = new PartList(sqlConnection);
    }

 

    @Test
    public void testGetDataListAll() throws PartException, SQLiteException {
	List<IPart> list = datensatz.getDataList();

	assertEquals("Das erste Listenelement Stimmt nicht", 1, list.get(0).getId());
	assertEquals("Das zweite Listenelement Stimmt nicht", 2, list.get(1).getId());
	assertEquals("Das zweite Listenelement Stimmt nicht", 3, list.get(2).getId());

    }

    @Test
    public void testGetDataListId() throws PartException, SQLiteException {
	List<IPart> list = datensatz.getDataList(new int[] { 1, 2, 3 });

	assertEquals("Das erste Listenelement Stimmt nicht", 1, list.get(0).getId());
	assertEquals("Das zweite Listenelement Stimmt nicht", 2, list.get(1).getId());
	assertEquals("Das zweite Listenelement Stimmt nicht", 3, list.get(2).getId());

	assertEquals("Die größe ist nicht korrekt", 3, list.size());
    }
    
    @Test
    public void testGetDataListSort() throws PartException, SQLiteException {
	List<IPart> list = datensatz.getDataListSort();

	assertNotSame("Das erste Listenelement Stimmt nicht", 1, list.get(1).getId());
    }
    

    
    @Test
    public void testGetDataListIdNull() throws SQLiteException, PartException {

	    List<IPart> list = datensatz.getDataList(new int[] { 0 });
       assertEquals("Das erste Listenelement Stimmt nicht", 0, list.get(0).getId());
    }

    @Test
    public void testIdVorhanden() throws SQLiteException {
	int id = 0;
	boolean check = datensatz.idVorhanden(id);
	assertFalse("Bei einer 0 sollte ein False anzeigt werden.", check);
    }

    @Test
    public void testGetIdList() throws SQLiteException {
	List<Integer> list = datensatz.getIdList();
	List<Integer> idTestList = new ArrayList<Integer>();

	idTestList.add(new Integer(1));
	idTestList.add(new Integer(2));
	idTestList.add(new Integer(3));

	assertEquals("Das erste Listenelement Stimmt nicht", idTestList.get(0), list.get(0));
	assertEquals("Das zweite Listenelement Stimmt nicht", idTestList.get(1), list.get(1));
	assertEquals("Das dritte Listenelement Stimmt nicht", idTestList.get(2), list.get(2));
    }

    @Override
    public void tearDown() throws SQLiteException {
	sqlConnection.close();
    }

}
