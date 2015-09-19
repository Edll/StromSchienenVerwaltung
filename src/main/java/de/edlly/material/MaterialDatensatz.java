package de.edlly.material;

import java.sql.*;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteStatement;

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
    private MaterialIds materialId;
    SQLiteStatement sqlLite;

    public MaterialDatensatz(SQLiteConnect sqlConnection) throws IllegalArgumentException, SQLException{
	super(sqlConnection);
	sqlLite = new SQLiteStatement(sqlConnection);
	materialId = new MaterialIds(sqlConnection);
    }

    public void setMaterialId(int id) throws IllegalArgumentException, SQLException {
	  materialId.setId(id);
    }

    public int getMaterialId() {
	return materialId.getId();
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

	    sqlLite.setQuery("SELECT id, MaterialSorteId, x, z, yMax, visibly FROM Material WHERE id = \""
		    + this.materialId.getId() + "\" ");
	    sqlLite.statmentVorbereitenUndStarten(sqlLite.getQuery());

	    if (sqlLite.resultOhneErgebniss(sqlLite.getResult())) {
		materialDatensatz = new int[] { 0, 0, 0, 0, 0, 0 };

	    } else {
		materialDatensatz[getOrdinal("ID")] = sqlLite.getResult().getInt(1); // id
		materialDatensatz[getOrdinal("MATERIALSORTE_ID")] = sqlLite.getResult().getInt(2); // MaterialSorteId
		materialDatensatz[getOrdinal("X")] = sqlLite.getResult().getInt(3); // x
		materialDatensatz[getOrdinal("Z")] = sqlLite.getResult().getInt(4); // z
		materialDatensatz[getOrdinal("YMAX")] = sqlLite.getResult().getInt(5); // yMax
		materialDatensatz[getOrdinal("SICHTBARKEIT")] = sqlLite.getResult().getInt(6); // visibly
	    }

	    sqlLite.closeStatmentAndResult();

	} catch (SQLException e) {
	    throw new SQLException(e);

	} finally {
	    try {
		sqlLite.closeStatmentAndResult();

	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	}
    }

}
