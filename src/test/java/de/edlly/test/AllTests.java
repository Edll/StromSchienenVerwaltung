package de.edlly.test;

import de.edlly.test.materialTest.NeuerMaterialDatensatzTest;
import de.edlly.test.materialTest.AbfrageMaterialListeTest;
import de.edlly.test.materialTest.AbfrageMaterialDatensatzTest;
import de.edlly.test.materialTest.UpdateMaterialDatensatzTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Läde die einzelnen tests.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class AllTests extends TestSuite {
    public static Test suite() {
	TestSuite materialDatensatz = new TestSuite("Material Datenbank Abfragen");
	materialDatensatz.addTestSuite(NeuerMaterialDatensatzTest.class);
	materialDatensatz.addTestSuite(AbfrageMaterialDatensatzTest.class);
	materialDatensatz.addTestSuite(UpdateMaterialDatensatzTest.class);
	materialDatensatz.addTestSuite(AbfrageMaterialListeTest.class);
	return materialDatensatz;
    }
}
