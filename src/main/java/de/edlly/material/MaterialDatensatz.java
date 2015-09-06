package de.edlly.material;

import java.sql.*;

import de.edlly.db.SQLiteConnect;

/**
 * Erstellt einen Material Datensatz anhand einer der materialId
 * 
 * TODO: Raus finden wie eine Methode überladen wird
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialDatensatz {

    private int[] materialDatensatz;
    private int materialId;
    private Connection sqlConnection;

    private Statement sqlStatment = null;
    private ResultSet sqlErgebniss = null;
    private String sqlQuery = "";

    public MaterialDatensatz(Connection sqlConnection) {
	
	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	this.sqlConnection = sqlConnection;
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
	    sqlQueryVorbereiten();
	    sqlErgebniss = sqlStatment.executeQuery(sqlQuery);

	    if (sqlAbfrageOhneErgebniss(sqlErgebniss)) {
		materialDatensatz = new int[] { 0, 0, 0, 0, 0, 0 };

	    } else {
		materialDatensatz[0] = sqlErgebniss.getInt(1); // id
		materialDatensatz[1] = sqlErgebniss.getInt(2); // MaterialSorteId
		materialDatensatz[2] = sqlErgebniss.getInt(3); // x
		materialDatensatz[3] = sqlErgebniss.getInt(4); // z
		materialDatensatz[4] = sqlErgebniss.getInt(5); // yMax
		materialDatensatz[5] = sqlErgebniss.getInt(6); // visibly
	    }
	    sqlStatment.close();
	    sqlErgebniss.close();
	} catch (SQLException e) {

	    throw new SQLException(e);

	} finally {
	    try {
		sqlStatment.close();
		sqlErgebniss.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	}
    }

    private void sqlQueryVorbereiten() throws SQLException {
	sqlStatment = sqlConnection.createStatement();
	materialDatensatz = new int[6];
	sqlQuery = "SELECT id, MaterialSorteId, x, z, yMax, visibly FROM Material WHERE id = \"" + this.materialId
		+ "\" ";
    }

    private boolean sqlAbfrageOhneErgebniss(ResultSet sqlResultSet) throws SQLException {
	return !sqlResultSet.isBeforeFirst();
    }
}
