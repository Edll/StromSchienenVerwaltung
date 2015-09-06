package de.edlly.material;

import java.sql.*;

/**
 * Erstellt einen Material Datensatz anhand einer der materialId
 * 
 * TODO: Klasse zerlegen abfrage wie MaterialIds umbauen.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialDatensatz {
    private int[] materialDatensatz;
    private int materialId;

    private Connection sqlConnection;

    public MaterialDatensatz(Connection sqlConnection) {
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

	materialDatensatzInObjektdatenSchreiben();

	return materialDatensatz;
    }

    private void materialDatensatzInObjektdatenSchreiben() throws SQLException {

	Statement sqlStatment = null;
	ResultSet sqlResultSet = null;
	try {
	    sqlStatment = sqlConnection.createStatement();
	    materialDatensatz = new int[6];
	    String sqlQuery = "SELECT id, MaterialSorteId, x, z, yMax, visibly FROM Material WHERE id = \""
		    + this.materialId + "\" ";

	    sqlResultSet = sqlStatment.executeQuery(sqlQuery);

	    if (sqlAbfrageOhneErgebniss(sqlResultSet)) {
		materialDatensatz = new int[] { 0, 0, 0, 0, 0, 0 };
	    } else {
		materialDatensatz[0] = sqlResultSet.getInt(1);
		materialDatensatz[1] = sqlResultSet.getInt(2);
		materialDatensatz[2] = sqlResultSet.getInt(3);
		materialDatensatz[3] = sqlResultSet.getInt(4);
		materialDatensatz[4] = sqlResultSet.getInt(5);
		materialDatensatz[5] = sqlResultSet.getInt(6);

		sqlStatment.close();
		sqlResultSet.close();
	    }

	} catch (SQLException e) {

	    throw new SQLException(e);

	} finally {
	    try {
		sqlStatment.close();
		sqlResultSet.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	}
    }

    private boolean sqlAbfrageOhneErgebniss(ResultSet sqlResultSet) throws SQLException {
	return !sqlResultSet.isBeforeFirst();
    }
}
