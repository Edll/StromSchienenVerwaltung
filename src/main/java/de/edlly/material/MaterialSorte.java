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
	    statmentVorbereitenUndStarten(getQuery());

	    if (resultOhneErgebniss(getResult())) {
		closeStatmentUndResult();
		materialSorteName = "N/A";
	    } else {

		materialSorteName = getResult().getString(1);
		closeStatmentUndResult();
	    }
	} catch (SQLException sqlException) {
	    throw new SQLException(sqlException);
	} finally {
	    try {
		closeStatmentUndResult();
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
		statmentVorbereitenUndStarten(getQuery());

		if (resultOhneErgebniss(getResult())) {
		    closeStatmentUndResult();
		    materialSorteId = 0;
		} else {
		    materialSorteId = getResult().getInt(1);

		    closeStatmentUndResult();
		}
	    }
	} catch (SQLException sqlException) {

	    throw new SQLException(sqlException);

	} finally {
	    try {
		closeStatmentUndResult();
	    } catch (SQLException sqlException) {
		sqlException.printStackTrace();
	    }
	}
    }

    public String[] materialSorteNamensListe() throws SQLException {
	try {
	    setQuery("SELECT MaterialSorte FROM MaterialSorten");
	    statmentVorbereitenUndStarten(getQuery());

	    int anzahlDerDatensatze = 0;
	    while (getResult().next()) {
		anzahlDerDatensatze++;
	    }

	    materialSorteNameListe = new String[anzahlDerDatensatze];

	    statmentAusfuehren(getQuery()); // nötig um den Datensatz zurück zu setzten. SQLite bietet hier keine
					 // bessere Möglichkeit.
	    int count = 0;
	    while (getResult().next()) {
		materialSorteNameListe[count] = getResult().getString(1);
		count++;
	    }

	    closeStatmentUndResult();
	    return materialSorteNameListe;

	} catch (SQLException sqlException) {
	    throw new SQLException(sqlException);

	} finally {
	    try {
		closeStatmentUndResult();
	    } catch (SQLException sqlException) {
		sqlException.printStackTrace();
	    }
	}
    }

    public boolean materialsorteIdVorhanden(int id) throws SQLException {
	if (getMaterialSorteName(id) == "N/A") {
	    return false;
	} else {
	    return true;
	}

    }

}
