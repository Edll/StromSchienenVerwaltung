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
	    statmentVorbereitenUndStarten(getQuery());

	    if (resultOhneErgebniss(getResult())) {
		materialDatensatz = new int[] { 0, 0, 0, 0, 0, 0 };

	    } else {
		materialDatensatz[getOrdinal("ID")] = getResult().getInt(1); // id
		materialDatensatz[getOrdinal("MATERIALSORTE_ID")] = getResult().getInt(2); // MaterialSorteId
		materialDatensatz[getOrdinal("X")] = getResult().getInt(3); // x
		materialDatensatz[getOrdinal("Z")] = getResult().getInt(4); // z
		materialDatensatz[getOrdinal("YMAX")] = getResult().getInt(5); // yMax
		materialDatensatz[getOrdinal("SICHTBARKEIT")] = getResult().getInt(6); // visibly
	    }

	    closeStatmentUndResult();

	} catch (SQLException e) {
	    throw new SQLException(e);

	} finally {
	    try {
		closeStatmentUndResult();

	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	}
    }

}
