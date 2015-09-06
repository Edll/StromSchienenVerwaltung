package de.edlly.material;

import java.sql.*;

import de.edlly.db.SQLiteConnect;

/**
 * MaterialSorten Behandlung
 * 
 * TODO: Klasse Sauber machen! Der Code ist so echt misst!
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialSorte {
    public int id;
    public int materialSorteId;
    public int x;
    public int z;
    public Connection sqlConnection;

    public MaterialSorte(Connection sqlConnection) {
	
	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	this.sqlConnection = sqlConnection;
    }

    public String SelectMaterialSorteId(int MaterialSorteId) {

	String result = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	try {
	    if (MaterialSorteId == 0) {
		throw new IllegalArgumentException("Material Sorte in Abfrage MaterialSorteId 0");
	    }
	    String query = "SELECT MaterialSorte FROM MaterialSorten Where id=" + MaterialSorteId;
	    pst = sqlConnection.prepareStatement(query);

	    rs = pst.executeQuery();
	    result = rs.getString(1);

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
