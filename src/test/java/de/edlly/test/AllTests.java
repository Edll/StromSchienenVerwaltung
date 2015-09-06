package de.edlly.test;

import de.edlly.test.materialTest.NeuerMaterialDatensatzTest;
import de.edlly.test.materialTest.MaterialListeTest;
import de.edlly.test.materialTest.MaterialSorteTest;
import de.edlly.test.materialTest.MaterialDatensatzTest;
import de.edlly.test.materialTest.MaterialIdsTest;
import de.edlly.test.materialTest.UpdateMaterialDatensatzTest;
import de.edlly.test.db.SQLiteBooleanTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Lädt die einzelnen tests.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class AllTests extends TestSuite {

    public static Test suiteMaterial() {
	TestSuite materialDatensatz = new TestSuite("Material Datenbank Abfragen");
	materialDatensatz.addTestSuite(NeuerMaterialDatensatzTest.class);
	materialDatensatz.addTestSuite(MaterialDatensatzTest.class);
	materialDatensatz.addTestSuite(UpdateMaterialDatensatzTest.class);
	materialDatensatz.addTestSuite(MaterialListeTest.class);
	materialDatensatz.addTestSuite(MaterialIdsTest.class);
	materialDatensatz.addTestSuite(MaterialSorteTest.class);
	return materialDatensatz;
    }

    public static Test suiteDB() {
	TestSuite datenbankTest = new TestSuite("Datenbank Funktionen");
	datenbankTest.addTestSuite(SQLiteBooleanTest.class);
	return datenbankTest;
    }
}
