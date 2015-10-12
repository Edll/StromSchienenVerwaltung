package de.edlly.test;

import de.edlly.test.bend.BendListTest;
import de.edlly.test.bend.BendTest;
import de.edlly.test.db.*;
import de.edlly.test.gui.part.PartTableTest;
import de.edlly.test.material.*;
import de.edlly.test.part.*;
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
        TestSuite materialDatensatz = new TestSuite("Alle Tests");
        materialDatensatz.addTestSuite(NeuerMaterialDatensatzTest.class);
        materialDatensatz.addTestSuite(MaterialDatensatzTest.class);
        materialDatensatz.addTestSuite(UpdateMaterialDatensatzTest.class);
        materialDatensatz.addTestSuite(MaterialListeTest.class);
        materialDatensatz.addTestSuite(MaterialIdsTest.class);
        materialDatensatz.addTestSuite(MaterialSorteTest.class);
        materialDatensatz.addTestSuite(MaterialLoeschenTest.class);
        // materialDatensatz.addTestSuite(MaterialTabelleTest.class);

        materialDatensatz.addTestSuite(PartTest.class);
        materialDatensatz.addTestSuite(PartListTest.class);
        materialDatensatz.addTestSuite(PartNewTest.class);
        materialDatensatz.addTestSuite(PartUtilTest.class);
        materialDatensatz.addTestSuite(PartTableTest.class);
        materialDatensatz.addTestSuite(BendListTest.class);
        materialDatensatz.addTestSuite(BendTest.class);

        materialDatensatz.addTestSuite(SQLiteBooleanTest.class);
        materialDatensatz.addTestSuite(SQLiteConnectTest.class);
        materialDatensatz.addTestSuite(SQLiteQueryAndResultTest.class);
        materialDatensatz.addTestSuite(SQLiteStatementTest.class);
        materialDatensatz.addTestSuite(SQLitePreparedStatementTest.class);

        return materialDatensatz;
    }
}
