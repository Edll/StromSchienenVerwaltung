package de.edlly.material;

import java.sql.*;

/**
 * Erstellt einen Material Datensatz anhand einer der materialId
 * 
 * TODO: Raus finden wie eine Methode überladen wird
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialDatensatz extends Material {

    private int[] materialDatensatz = new int[6];
    private int materialId;

    public MaterialDatensatz(Connection sqlConnection) {
	super(sqlConnection);
    }

    public void setMaterialId(int materialId) {
	this.materialId = materialId;
    }

    public int getMaterialId() {
	return materialId;
    }

    public int[] getMaterialDatensatz(int materialId) throws IllegalArgumentException, SQLException {
	setMaterialId(materialId);

	materialDatenAbrufen();

	return materialDatensatz;
    }

    private void materialDatenAbrufen() throws SQLException {

	try {
	    setQuery("SELECT id, MaterialSorteId, x, z, yMax, visibly FROM Material WHERE id = \"" + this.materialId
		    + "\" ");
	    sqlAbfrageVorbereitenUndStarten(getQuery());

	    if (sqlAbfrageOhneErgebniss(result)) {
		materialDatensatz = new int[] { 0, 0, 0, 0, 0, 0 };

	    } else {
		materialDatensatz[0] = result.getInt(1); // id
		materialDatensatz[1] = result.getInt(2); // MaterialSorteId
		materialDatensatz[2] = result.getInt(3); // x
		materialDatensatz[3] = result.getInt(4); // z
		materialDatensatz[4] = result.getInt(5); // yMax
		materialDatensatz[5] = result.getInt(6); // visibly
	    }

	    sqlCloseStatmentUndErgebiss();

	} catch (SQLException e) {
	    throw new SQLException(e);

	} finally {
	    try {
		sqlCloseStatmentUndErgebiss();

	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	}
    }
}
