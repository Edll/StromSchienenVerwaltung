package de.edlly.test.materialTest;

import java.sql.Connection;

import junit.framework.TestCase;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.AbfrageMaterialDatensatz;

/**
 * Test Klasse fuer die AbfrageMaterialDatensatz Klasse.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class AbfrageMaterialDatensatzTest extends TestCase {

    private Connection sqlConnection;

    @Override
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();

    }

    public void testMaterialIdVorhanden() {
	assertTrue("Material Id Vorhanden in Datenbank", abfrageMaterialIdVorhanden());
    }

    public void testGetMaterialIdAbfragen() {
	assertTrue("Material Id Abfragen in Datenbank", getMaterialIdAbfragen());
    }

    public void testGetMaterialDatensatz() {
	assertTrue("Material Datensatz anhand Id Abfragen in Datenbank", getMaterialDatensatz());
    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);

    }

    private Boolean abfrageMaterialIdVorhanden() {
	Boolean ergebnissDesTests = false;
	int[] materialIdList = new int[] { 0, 1, 2, 3, 4 };

	AbfrageMaterialDatensatz materialDatensatz = new AbfrageMaterialDatensatz(sqlConnection);

	for (int i : materialIdList) {

	    switch (i) {
	    case 0:
		if (!materialDatensatz.materialIdAbfragen(materialIdList[i])) {
		    ergebnissDesTests = true;
		}
		break;
	    case 1:
		if (materialDatensatz.materialIdAbfragen(materialIdList[i])) {
		    ergebnissDesTests = true;
		}
		break;
	    case 2:
		if (materialDatensatz.materialIdAbfragen(materialIdList[i])) {
		    ergebnissDesTests = true;
		}
		break;

	    case 3:
		if (materialDatensatz.materialIdAbfragen(materialIdList[i])) {
		    ergebnissDesTests = true;
		}
		break;

	    case 4:
		if (!materialDatensatz.materialIdAbfragen(materialIdList[i])) {
		    ergebnissDesTests = true;
		}
		break;
	    default:
		ergebnissDesTests = false;

	    }

	}
	return ergebnissDesTests;
    }

    private Boolean getMaterialIdAbfragen() {
	Boolean ergebnissDesTests = false;
	int[] materialIdList = new int[] { 0, 1, 2, 3, 4 };

	AbfrageMaterialDatensatz materialDatensatz = new AbfrageMaterialDatensatz(sqlConnection);

	for (int i : materialIdList) {

	    try {
		materialDatensatz.materialIdVorhanden(i);
		ergebnissDesTests = true;
	    } catch (Exception e) {
		if (i == 0 | i == 4) {
		    ergebnissDesTests = true;
		} else {
		    ergebnissDesTests = false;
		}

	    }
	}
	return ergebnissDesTests;

    }

    private Boolean getMaterialDatensatz() {
	// TODO: Funktion einbauen
	return true;
    }

}
