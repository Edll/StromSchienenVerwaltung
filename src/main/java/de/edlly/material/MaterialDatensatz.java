package de.edlly.material;

import java.sql.*;

/**
 * Erstellt einen Material Datensatz anhand einer der materialId
 * 
 * TODO: Zusammenlegen mit NeuerMaterialDatensatz
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

    public int[] getMaterialDatensatzAusDatenbank(int materialId) throws IllegalArgumentException, SQLException {
	setMaterialId(materialId);

	materialDatenAbrufen();

	return materialDatensatz;
    }

    public int[] getMaterialDatensatz() {
	return materialDatensatz;
    }

    public void setMaterialDatensatz(int[] materialDatensatz) throws IllegalArgumentException {
	if (materialDatensatz == null) {
	    throw new IllegalArgumentException(
		    "Der Materialdatensatz bei eintragen darf nicht null sein." + this.getClass());
	}
	this.materialDatensatz = materialDatensatz;
    }

    private void materialDatenAbrufen() throws SQLException {

	try {
	    setQuery("SELECT id, MaterialSorteId, x, z, yMax, visibly FROM Material WHERE id = \"" + this.materialId
		    + "\" ");
	    abfrageVorbereitenUndStarten(getQuery());

	    if (abfrageOhneErgebniss(result)) {
		materialDatensatz = new int[] { 0, 0, 0, 0, 0, 0 };

	    } else {
		materialDatensatz[getOrdinal("ID")] = result.getInt(1); // id
		materialDatensatz[getOrdinal("MATERIALSORTE_ID")] = result.getInt(2); // MaterialSorteId
		materialDatensatz[getOrdinal("X")] = result.getInt(3); // x
		materialDatensatz[getOrdinal("Z")] = result.getInt(4); // z
		materialDatensatz[getOrdinal("YMAX")] = result.getInt(5); // yMax
		materialDatensatz[getOrdinal("SICHTBARKEIT")] = result.getInt(6); // visibly
	    }

	    closeStatmentUndErgebiss();

	} catch (SQLException e) {
	    throw new SQLException(e);

	} finally {
	    try {
		closeStatmentUndErgebiss();

	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	}
    }

}
