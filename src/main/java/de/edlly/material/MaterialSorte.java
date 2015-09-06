package de.edlly.material;

import java.sql.*;

import de.edlly.db.SQLiteConnect;

/**
 * MaterialSorten Behandlung
 * 
 * TODO: Code ist WIP! Refactoring bzw Neustrukturierung nötig!
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialSorte {
    private int materialSorteId;
    private String MaterialSorteName = "";
    private Connection sqlConnection;

    private Statement sqlStatment = null;
    private PreparedStatement sqlpreparedStatment = null;
    private ResultSet sqlErgebniss = null;
    private String sqlQuery = "";

    public MaterialSorte(Connection sqlConnection) {

	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	this.sqlConnection = sqlConnection;
    }

    public String materialSortenName(int MaterialSorteId) throws SQLException {
	try {

	    sqlStatment = sqlConnection.createStatement();
	    sqlQuery = "SELECT MaterialSorte FROM MaterialSorten Where id=" + MaterialSorteId;
	    sqlErgebniss = sqlStatment.executeQuery(sqlQuery);

	    if (sqlAbfrageOhneErgebniss()) {
		sqlStatment.close();
		sqlErgebniss.close();

		return MaterialSorteName = "N/A";
	    }

	    MaterialSorteName = sqlErgebniss.getString(1);

	    sqlStatment.close();
	    sqlErgebniss.close();
	    return MaterialSorteName;

	} catch (SQLException sqlException) {

	    throw new SQLException(sqlException);
	} finally {
	    try {
		sqlStatment.close();
		sqlErgebniss.close();
	    } catch (SQLException sqlException) {
		sqlException.printStackTrace();
	    }
	}
    }

    private boolean sqlAbfrageOhneErgebniss() throws SQLException {
	return !sqlErgebniss.isBeforeFirst();
    }

    public int SelectMaterialSorteString(String MaterialSorteString) {

	int result = 0;
	ResultSet rs = null;
	PreparedStatement pst = null;

	try {
	    if (MaterialSorteString == null) {
		throw new IllegalArgumentException("Material Sorte in Abfrage MaterialSorteId 0");

	    }
	    String query = "SELECT id FROM MaterialSorten Where MaterialSorte='" + MaterialSorteString + "'";
	    pst = sqlConnection.prepareStatement(query);
	    rs = pst.executeQuery();
	    result = rs.getInt(1);

	    return result;
	} catch (Exception e) {

	    throw new IllegalArgumentException(e);

	} finally {
	    try {
		pst.close();
		rs.close();
	    } catch (Exception e) {
		throw new IllegalArgumentException(e);
	    }
	}
    }

    public String[] SelectTableMaterialSorteString() {

	int count = 0;
	int RowNumber = 0;
	Statement pst = null;
	ResultSet rs = null, ArrayRs = null;

	try {
	    pst = sqlConnection.createStatement();
	    String query = "SELECT MaterialSorte FROM MaterialSorten";
	    rs = pst.executeQuery(query);
	    // SQLite unterstützt keine Forward Backward Kommandos deshalb diese
	    // unsaubere WorkAround um die Größe des Arrays zu ermitteln
	    while (rs.next()) {
		RowNumber++;
	    }
	    rs.close();
	    String[] result = new String[RowNumber];

	    ArrayRs = pst.executeQuery(query);
	    // Abfrage der Werte für Das Table Array.
	    while (ArrayRs.next()) {
		result[count] = rs.getString(1);
		count++;
	    }
	    return result;
	} catch (Exception e) {

	    throw new IllegalArgumentException(e);

	} finally {
	    try {
		pst.close();
		rs.close();
		ArrayRs.close();
	    } catch (Exception e) {
		// throw new IllegalArgumentException(e);
	    }
	}
    }

}
