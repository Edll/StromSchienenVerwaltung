package de.edlly.material;

import java.sql.*;

import de.edlly.material.MaterialKonstanten;

/**
 * Legt einen neuen Material Datensatz aus den objektVariabeln an.
 * 
 * 
 * @author Edlly java@edlly.de
 *
 */
public class NeuerMaterialDatensatz {

    private int koordinateX;
    private int koordinateZ;
    private int koordinateyMax;
    private int materialSorteId;
    private Connection sqlConnection;

    public NeuerMaterialDatensatz(Connection SqlConnection) {
	this.sqlConnection = SqlConnection;

    }

    public void setMaterialDaten(int koordinateX, int koordinateZ, int koordinateyMax, int materialSorteId)
	    throws IllegalArgumentException {

	try {
	    if (koordinateXIstImDefinitionsbereich(koordinateX)) {
		this.koordinateX = koordinateX;
	    }

	    if (koordinateZIstImDefinitionsbereich(koordinateZ)) {
		this.koordinateZ = koordinateZ;
	    }

	    if (koordinateyMaxIstImDefinitionsbereich(koordinateyMax)) {
		this.koordinateyMax = koordinateyMax;
	    }

	    if (materialSorteIdIstVorhanden(materialSorteId)) {
		this.materialSorteId = materialSorteId;
	    }

	} catch (IllegalArgumentException datenSetzenFehlgeschlagen) {

	    clearObjektWerte();

	    throw new IllegalArgumentException(datenSetzenFehlgeschlagen);
	}
    }

    public Boolean datensatzAusObjektWertenAnlegen() throws SQLException, IllegalArgumentException {
	PreparedStatement sqlPreparedStatment = null;

	if (objektWerteSindNull()) {
	    throw new IllegalArgumentException(
		    "Die Objektwerte sind nicht gesetzt worden /n ein leerer Datensatz kann nich angelegt werden.");
	}

	try {
	    String query = "INSERT INTO Material (\"MaterialSorteId\",\"x\",\"z\",\"yMax\",\"visibly\") VALUES (?1,?2,?3,?4,?5)";

	    sqlPreparedStatment = sqlConnection.prepareStatement(query);

	    sqlPreparedStatment.setInt(1, this.materialSorteId);
	    sqlPreparedStatment.setInt(2, this.koordinateX);
	    sqlPreparedStatment.setInt(3, this.koordinateZ);
	    sqlPreparedStatment.setInt(4, this.koordinateyMax);
	    sqlPreparedStatment.setBoolean(5, true);
	    sqlPreparedStatment.executeUpdate();
	    sqlPreparedStatment.close();
	} catch (SQLException sqlException) {

	    throw new SQLException(sqlException);
	} finally {
	    try {
		sqlPreparedStatment.close();
	    } catch (Exception closeException) {
		closeException.printStackTrace();
	    }
	}
	return true;
    }

    public Boolean koordinateXIstImDefinitionsbereich(int koordinateX) throws IllegalArgumentException {

	if (koordinateX < MaterialKonstanten.MINIMALER_X_WERT) {
	    throw new IllegalArgumentException("Die Materialbreite darf nicht Negativ oder 0 sein.");
	}

	if (koordinateX > MaterialKonstanten.MAXIMALER_X_WERT) {
	    throw new IllegalArgumentException(
		    "Die maximal Material Breite ist: " + MaterialKonstanten.MAXIMALER_X_WERT);
	}

	return true;
    }

    public Boolean koordinateZIstImDefinitionsbereich(int koordinateZ) throws IllegalArgumentException {

	if (koordinateZ < MaterialKonstanten.MINIMALER_Z_WERT) {
	    throw new IllegalArgumentException("Die Materialbreite darf nicht Negativ oder 0 sein.");
	}

	if (koordinateZ > MaterialKonstanten.MAXIMALER_Z_WERT) {
	    throw new IllegalArgumentException(
		    "Die maximal Material Dicke ist: " + MaterialKonstanten.MAXIMALER_Z_WERT);
	}

	return true;
    }

    public Boolean koordinateyMaxIstImDefinitionsbereich(int koordinatey) throws IllegalArgumentException {

	if (koordinatey < MaterialKonstanten.MINIMALER_Y_WERT) {
	    throw new IllegalArgumentException("Die Materialbreite darf nicht Negativ sein.");
	}

	if (koordinatey > MaterialKonstanten.MAXIMALER_Y_WERT) {
	    throw new IllegalArgumentException(
		    "Die maximal Material L�nge ist: " + MaterialKonstanten.MAXIMALER_Y_WERT);
	}

	return true;
    }

    public Boolean materialSorteIdIstVorhanden(int materialSorteId) throws IllegalArgumentException {

	if (materialSorteId < 0) {
	    throw new IllegalArgumentException("Die materialSorteId darf nicht Negativ sein.");
	}
	/**
	 * @TODO Abfrage einbauen die abfragt ob die Sorte in der Datenbank vorhanden ist.
	 */

	return true;
    }

    public Boolean objektWerteSindNull() {
	if (this.koordinateX == 0 || this.koordinateyMax == 0 || this.materialSorteId == 0 || this.koordinateZ == 0) {
	    return true;
	} else {
	    return false;
	}
    }

    private void clearObjektWerte() {
	this.koordinateX = MaterialKonstanten.MINIMALER_X_WERT - 1;
	this.koordinateZ = MaterialKonstanten.MINIMALER_Z_WERT - 1;
	this.koordinateyMax = MaterialKonstanten.MINIMALER_Y_WERT - 1;
	this.materialSorteId = 0;
    }

}
