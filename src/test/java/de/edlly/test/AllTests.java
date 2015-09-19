package de.edlly.test;

import de.edlly.test.db.*;
import de.edlly.test.materialTest.*;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Lädt die einzelnen tests.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class AllTests extends TestSuite {

    public static Test suite() {
	TestSuite materialDatensatz = new TestSuite("Material Datenbank Abfragen");
	materialDatensatz.addTestSuite(NeuerMaterialDatensatzTest.class);
	materialDatensatz.addTestSuite(MaterialDatensatzTest.class);
	materialDatensatz.addTestSuite(UpdateMaterialDatensatzTest.class);
	materialDatensatz.addTestSuite(MaterialListeTest.class);
	materialDatensatz.addTestSuite(MaterialIdsTest.class);
	materialDatensatz.addTestSuite(MaterialSorteTest.class);
	materialDatensatz.addTestSuite(MaterialLoeschenTest.class);
	

	materialDatensatz.addTestSuite(SQLiteBooleanTest.class);
	materialDatensatz.addTestSuite(SQLiteConnectTest.class);
	materialDatensatz.addTestSuite(SQLiteAbfrageTest.class);
	materialDatensatz.addTestSuite(SQLiteStatementTest.class);
	materialDatensatz.addTestSuite(SQLitePreparedStatementTest.class);
	
	
	
	return materialDatensatz;
    }
}
