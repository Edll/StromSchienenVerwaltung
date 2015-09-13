package de.edlly.material;

import java.sql.*;

import de.edlly.material.MaterialKonstanten;

/**
 * Legt einen neuen Material Datensatz an nutzt dabei MaterialDatensatz.
 * 
 * @author Edlly java@edlly.de
 *
 */
public class NeuerMaterialDatensatz extends MaterialDatensatz {

    public NeuerMaterialDatensatz(Connection sqlConnection) {
	super(sqlConnection);

    }

    public void setMaterialDaten(int koordinateX, int koordinateZ, int koordinateyMax, int materialSorteId)
	    throws IllegalArgumentException, SQLException {
	int[] materialDatensatz = new int[6];
	try {

	    if (koordinateXIstImDefinitionsbereich(koordinateX)) {
		materialDatensatz[getOrdinal("X")] = koordinateX;
	    }

	    if (koordinateZIstImDefinitionsbereich(koordinateZ)) {
		materialDatensatz[getOrdinal("Z")] = koordinateZ;
	    }

	    if (koordinateyMaxIstImDefinitionsbereich(koordinateyMax)) {
		materialDatensatz[getOrdinal("YMAX")] = koordinateyMax;
	    }

	    if (materialSorteIdIstVorhanden(materialSorteId)) {
		materialDatensatz[getOrdinal("MATERIALSORTE_ID")] = materialSorteId;
	    }
	    materialDatensatz[getOrdinal("SICHTBARKEIT")] = 1;
	    setMaterialDatensatz(materialDatensatz);

	} catch (IllegalArgumentException datenSetzenFehlgeschlagen) {

	    clearObjektWerte();

	    throw new IllegalArgumentException(datenSetzenFehlgeschlagen);
	}
    }

    public Boolean datensatzAusObjektWertenAnlegen() throws SQLException, IllegalArgumentException {
	int[] materialDatensatz = getMaterialDatensatz();

	if (objektWerteSindNull()) {
	    throw new IllegalArgumentException(
		    "Die Objektwerte sind nicht gesetzt worden /n ein leerer Datensatz kann nich angelegt werden.");
	}

	try {
	    setQuery(
		    "INSERT INTO Material (\"MaterialSorteId\",\"x\",\"z\",\"yMax\",\"visibly\") VALUES (?1,?2,?3,?4,?5)");

	    preparedStatmentVorbereiten(getQuery());

	    getPreparedStatment().setInt(1, materialDatensatz[getOrdinal("MATERIALSORTE_ID")]);
	    getPreparedStatment().setInt(2, materialDatensatz[getOrdinal("X")]);
	    getPreparedStatment().setInt(3, materialDatensatz[getOrdinal("Z")]);
	    getPreparedStatment().setInt(4, materialDatensatz[getOrdinal("YMAX")]);
	    getPreparedStatment().setInt(5, materialDatensatz[getOrdinal("SICHTBARKEIT")]);

	    preparedStatmentAusfuehren();
	    closePrepareStatment();
	    
	} catch (SQLException sqlException) {

	    throw new SQLException(sqlException);
	} finally {
	    closePrepareStatment();
	}
	return true;
    }

    public Boolean koordinateXIstImDefinitionsbereich(int koordinateX) throws IllegalArgumentException {

	if (koordinateX < MaterialKonstanten.MINIMALER_X_WERT) {
	    throw new IllegalArgumentException("Die Materialbreite darf nicht Negativ oder 0 sein.");
	}

	if (koordinateX > MaterialKonstanten.MAXIMALER_X_WERT) {
	    throw new IllegalArgumentException(
		    "Die maximal Materialbreite ist: " + MaterialKonstanten.MAXIMALER_X_WERT);
	}

	return true;
    }

    public Boolean koordinateZIstImDefinitionsbereich(int koordinateZ) throws IllegalArgumentException {

	if (koordinateZ < MaterialKonstanten.MINIMALER_Z_WERT) {
	    throw new IllegalArgumentException("Die Materialbreite darf nicht Negativ oder 0 sein.");
	}

	if (koordinateZ > MaterialKonstanten.MAXIMALER_Z_WERT) {
	    throw new IllegalArgumentException("Die maximal Materialdicke ist: " + MaterialKonstanten.MAXIMALER_Z_WERT);
	}

	return true;
    }

    public Boolean koordinateyMaxIstImDefinitionsbereich(int koordinatey) throws IllegalArgumentException {

	if (koordinatey < MaterialKonstanten.MINIMALER_Y_WERT) {
	    throw new IllegalArgumentException("Die Materialbreite darf nicht Negativ sein.");
	}

	if (koordinatey > MaterialKonstanten.MAXIMALER_Y_WERT) {
	    throw new IllegalArgumentException(
		    "Die maximal Material Länge ist: " + MaterialKonstanten.MAXIMALER_Y_WERT);
	}

	return true;
    }

    public Boolean materialSorteIdIstVorhanden(int materialSorteId) throws IllegalArgumentException, SQLException {

	if (materialSorteId < 0) {
	    throw new IllegalArgumentException("Die materialSorteId darf nicht Negativ sein.");
	}

	MaterialSorte sorteVorhanden = new MaterialSorte(getSqlConnection());
	if (!sorteVorhanden.materialsorteIdVorhanden(materialSorteId)) {
	    throw new IllegalArgumentException("Die materialSorteId ist nicht vorhanden.");
	}

	return true;
    }

    public Boolean objektWerteSindNull() {
	int[] datensatz = getMaterialDatensatz();
	if (datensatz[getOrdinal("MATERIALSORTE_ID")] == 0 || datensatz[getOrdinal("X")] == 0
		|| datensatz[getOrdinal("YMAX")] == 0 || datensatz[getOrdinal("YMAX")] == 0) {
	    return true;
	} else {
	    return false;
	}
    }

    private void clearObjektWerte() {
	setMaterialDatensatz(new int[] { 0, 0, 0, 0, 0, 0 });
    }

}
