package de.edlly.material;

import java.sql.*;

/**
 * MaterialSorten Aus der Datenbank erfragen eine Liste mit Namen, den Name nach Id oder die Id nach Namen.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialSorte extends Material {
    private int materialSorteId;
    private String materialSorteName = "";
    private String[] materialSorteNameListe = new String[0];

    public MaterialSorte(Connection sqlConnection) {
	super(sqlConnection);
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
	    setQuery("SELECT MaterialSorte FROM MaterialSorten Where id=" + materialSorteId);
	    sqlAbfrageVorbereitenUndStarten(getQuery());

	    if (sqlAbfrageOhneErgebniss(result)) {
		sqlCloseStatmentUndErgebiss();
		materialSorteName = "N/A";
	    } else {

		materialSorteName = result.getString(1);
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
		setQuery("SELECT id FROM MaterialSorten Where MaterialSorte='" + materialSorteName + "'");
		sqlAbfrageVorbereitenUndStarten(getQuery());

		if (sqlAbfrageOhneErgebniss(result)) {
		    sqlCloseStatmentUndErgebiss();
		    materialSorteId = 0;
		} else {
		    materialSorteId = result.getInt(1);

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
	    setQuery("SELECT MaterialSorte FROM MaterialSorten");
	    sqlAbfrageVorbereitenUndStarten(getQuery());

	    int anzahlDerDatensatze = 0;
	    while (result.next()) {
		anzahlDerDatensatze++;
	    }

	    materialSorteNameListe = new String[anzahlDerDatensatze];

	    sqlExecuteStatment(getQuery()); // nötig um den Datensatz zurück zu setzten. SQLite bietet hier keine
					    // bessere Möglichkeit.
	    int count = 0;
	    while (result.next()) {
		materialSorteNameListe[count] = result.getString(1);
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
    public boolean materialsorteIdVorhanden(int id) throws SQLException{
	if(getMaterialSorteName(id) == "N/A"){
		return false;    
	}else{
	    return true;
	}

    }

}
