package de.edlly.test;

import de.edlly.test.materialTest.NeuerMaterialDatensatzTest;
import de.edlly.test.materialTest.TestAbfrageMaterialDatensatz;
import de.edlly.test.materialTest.TestUpdateMaterialDatensatz;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Läd die einzelnen tests.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class AllTests extends TestSuite {
    public static Test suite() {

	TestSuite neuerMaterialDatensatz = new TestSuite("-> Neuer Material Datensatz");
	neuerMaterialDatensatz.addTestSuite(NeuerMaterialDatensatzTest.class);

	// TODO: ... weitere Testklassen hinzufügen

	return neuerMaterialDatensatz;
    }
}
