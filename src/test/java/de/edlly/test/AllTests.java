package de.edlly.test;

import de.edlly.test.materialTest.NeuerMaterialDatensatzTest;
import de.edlly.test.materialTest.AbfrageMaterialListeTest;
import de.edlly.test.exceptionproblem.JUnitExceptionTest;
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
	TestSuite Testsuit = new TestSuite("Material Daten");
	Testsuit.addTestSuite(NeuerMaterialDatensatzTest.class);

	Testsuit.addTestSuite(AbfrageMaterialDatensatzTest.class);


	Testsuit.addTestSuite(UpdateMaterialDatensatzTest.class);
	

	Testsuit.addTestSuite(AbfrageMaterialListeTest.class);
	

	Testsuit.addTestSuite(JUnitExceptionTest.class);
	

	return Testsuit;
    }
}
