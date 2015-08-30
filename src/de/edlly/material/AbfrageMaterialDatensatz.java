package de.edlly.material;

import java.sql.Connection;

/**
 * Gibt Datensätze aus der DB zurück
 * 
 * TODO: Dummy Klasse in echte umbauen
 * 
 * @author Edlly java@edlly.de
 *
 */

public class AbfrageMaterialDatensatz {

    private int[] materialDatensatz;
    private int materialId;
    @SuppressWarnings("unused")
    private Connection sqlConnection;

    public AbfrageMaterialDatensatz(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;

    }

    public int[] getMaterialDatensatz(int materialId) {

	this.materialId = materialId;

	// MaterialDaten id, materialSortenId, x, z, yMax, visibly

	switch (this.materialId) {
	case 0:
	    materialDatensatz = new int[] { 0, 0, 0, 0, 0, 0 };
	    break;
	case 1:
	    materialDatensatz = new int[] { 1, 1, 30, 20, 4000, 1 };
	    break;
	case 2:
	    materialDatensatz = new int[] { 1, 1, 40, 10, 5000, 0 };
	    break;
	case 3:
	    materialDatensatz = new int[] { 1, 1, 50, 15, 3000, 1 };
	    break;
	default:
	    materialDatensatz = new int[] { 0, 0, 0, 0, 0, 0 };
	    break;

	}

	return materialDatensatz;
    }

    public Boolean materialIdAbfragen(int materialId) {

	this.materialId = materialId;

	switch (this.materialId) {
	case 0:
	    return false;
	case 1:
	    return true;
	case 2:
	    return true;
	case 3:
	    return true;
	default:
	    return false;
	}

    }

    public void materialIdVorhanden(int materialId) throws IllegalArgumentException {

	if (materialIdAbfragen(materialId)) {
	    throw new IllegalArgumentException("Die Material Id ist nicht vorhanden.");
	}

    }

}
