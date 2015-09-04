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
	TestSuite neuerMaterialDatensatz = new TestSuite("-> Neuer Material Datensatz");
	neuerMaterialDatensatz.addTestSuite(NeuerMaterialDatensatzTest.class);

	TestSuite abfrageMaterialDatensatz = new TestSuite("-> Abfrage Material Datensatz");
	abfrageMaterialDatensatz.addTestSuite(AbfrageMaterialDatensatzTest.class);

	TestSuite updateMaterialDatensatz = new TestSuite("-> Abfrage Material Datensatz");
	updateMaterialDatensatz.addTestSuite(UpdateMaterialDatensatzTest.class);
	
	TestSuite AbfrageMaterialTabelle = new TestSuite("-> Abfrage Material Datensatz");
	AbfrageMaterialTabelle.addTestSuite(AbfrageMaterialListeTest.class);

	return neuerMaterialDatensatz;
    }
}
