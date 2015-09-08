package de.edlly.material;

import java.sql.*;

import de.edlly.db.SQLiteConnect;

/**
 * MaterialSorten Aus der Datenbank erfragen eine Liste mit Namen, den Name nach Id oder die Id nach Namen.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialSorte {
    private int materialSorteId;
    private String materialSorteName = "";
    private String[] materialSorteNameListe = new String[0];
    private Connection sqlConnection;

    private Statement sqlStatment = null;
    private ResultSet sqlErgebniss = null;
    private String sqlQuery = "";

    public MaterialSorte(Connection sqlConnection) {
	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	this.sqlConnection = sqlConnection;
    }

    public int getMaterialSorteId(String materialSorteName) throws IllegalArgumentException, SQLException {
	setMaterialSorteName(materialSorteName);
	materialSorteNameToId();

	return materialSorteId;
    }

    public void setMaterialSorteId(int materialSorteId) {
	this.materialSorteId = materialSorteId;
    }

    public String getMaterialSorteName(int materialSorteId) throws SQLException {
	setMaterialSorteId(materialSorteId);
	materialSortenIdToName();

	return materialSorteName;
    }

    public void setMaterialSorteName(String materialSorteName) {
	this.materialSorteName = materialSorteName;
    }

    public String[] getMaterialSorteNameListeBla() {
	return materialSorteNameListe;
    }

    public void materialSortenIdToName() throws SQLException {
	try {
	    sqlQuery = "SELECT MaterialSorte FROM MaterialSorten Where id=" + materialSorteId;
	    sqlAbfrageVorbereitenUndStarten(sqlQuery);

	    if (sqlAbfrageOhneErgebniss()) {
		sqlCloseStatmentUndErgebiss();
		materialSorteName = "N/A";
	    } else {

		materialSorteName = sqlErgebniss.getString(1);
		sqlCloseStatmentUndErgebiss();
	    }
	} catch (SQLException sqlException) {
	    throw new SQLException(sqlException);
	} finally {
	    try {
		sqlCloseStatmentUndErgebiss();
	    } catch (SQLException sqlException) {
		sqlException.printStackTrace();
	    }
	}
    }

    public void materialSorteNameToId() throws SQLException, IllegalArgumentException {
	try {
	    if (materialSorteName == null) {
		materialSorteId = 0;
	    } else {
		sqlQuery = "SELECT id FROM MaterialSorten Where MaterialSorte='" + materialSorteName + "'";
		sqlAbfrageVorbereitenUndStarten(sqlQuery);

		if (sqlAbfrageOhneErgebniss()) {
		    sqlCloseStatmentUndErgebiss();
		    materialSorteId = 0;
		} else {
		    materialSorteId = sqlErgebniss.getInt(1);

		    sqlCloseStatmentUndErgebiss();
		}
	    }
	} catch (SQLException sqlException) {

	    throw new SQLException(sqlException);

	} finally {
	    try {
		sqlCloseStatmentUndErgebiss();
	    } catch (SQLException sqlException) {
		sqlException.printStackTrace();
	    }
	}
    }

    public String[] materialSorteNamensListe() throws SQLException {
	try {
	    sqlQuery = "SELECT MaterialSorte FROM MaterialSorten";
	    sqlAbfrageVorbereitenUndStarten(sqlQuery);

	    int anzahlDerDatensatze = 0;
	    while (sqlErgebniss.next()) {
		anzahlDerDatensatze++;
	    }

	    materialSorteNameListe = new String[anzahlDerDatensatze];

	    sqlAbfrageStarten(sqlQuery); // nötig um den Datensatz zurück zu setzten. SQLite bietet hier keine bessere
					 // Möglichkeit.
	    int count = 0;
	    while (sqlErgebniss.next()) {
		materialSorteNameListe[count] = sqlErgebniss.getString(1);
		count++;
	    }

	    sqlCloseStatmentUndErgebiss();
	    return materialSorteNameListe;

	} catch (SQLException sqlException) {
	    throw new SQLException(sqlException);

	} finally {
	    try {
		sqlCloseStatmentUndErgebiss();
	    } catch (SQLException sqlException) {
		sqlException.printStackTrace();
	    }
	}
    }

    private void sqlCloseStatmentUndErgebiss() throws SQLException {
	if (sqlStatment != null) {
	    sqlStatment.close();
	}
	if (sqlErgebniss != null) {
	    sqlErgebniss.close();
	}
    }

    private boolean sqlAbfrageOhneErgebniss() throws SQLException {
	return !sqlErgebniss.isBeforeFirst();
    }

    private void sqlAbfrageVorbereitenUndStarten(String sqlQuery) throws SQLException {
	sqlAbfrageVorbereiten();
	sqlAbfrageStarten(sqlQuery);
    }

    private void sqlAbfrageVorbereiten() throws SQLException {
	sqlStatment = sqlConnection.createStatement();
    }

    private void sqlAbfrageStarten(String sqlQuery) throws SQLException {
	sqlErgebniss = sqlStatment.executeQuery(sqlQuery);
    }
}
