package de.edlly.testUnit;

import de.edlly.testUnit.testMaterialDatensatz.TestAbfrageMaterialDatensatz;
import de.edlly.testUnit.testMaterialDatensatz.TestNeuerMaterialDatensatz;
import de.edlly.testUnit.testMaterialDatensatz.TestUpdateMaterialDatensatz;
/**
 * L�de die einzelnen Testklassen f�r die Objekte.
 * @author Edlly java@edlly.de
 *
 */

public class MainTest {

	public static void main(String[] args) {

		TestNeuerMaterialDatensatz testNeuerDatensatz = new TestNeuerMaterialDatensatz();
		testNeuerDatensatz.callTestNeuerDatensatz();
		
		TestUpdateMaterialDatensatz testUpdateDatensatz = new TestUpdateMaterialDatensatz();
		testUpdateDatensatz.callTestUpdateDatensatz();
		
		TestAbfrageMaterialDatensatz testAbfrageDatensatz = new TestAbfrageMaterialDatensatz();
		testAbfrageDatensatz.callTestAbfrageDatensatz();
		

	}

}
