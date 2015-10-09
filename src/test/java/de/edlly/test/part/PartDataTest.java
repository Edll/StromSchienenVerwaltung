package de.edlly.test.part;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.part.*;
import junit.framework.TestCase;

public class PartDataTest extends TestCase {

    IPartData datensatz;
    SQLiteConnect sqlConnection;

    @Override
    public void setUp() throws PartException, SQLiteException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	datensatz = new PartData(sqlConnection);
    }

  

    @Test
    public void testGetData() throws PartException, SQLiteException {
	IPart data = new Part();
	data = datensatz.getData(1);

	int actual = data.getId();
	int expected = 1;

	assertEquals("Die erhaltene Id weicht von der erwarteten ab.", expected, actual);
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
