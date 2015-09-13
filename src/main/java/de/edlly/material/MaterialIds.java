package de.edlly.material;

import java.sql.*;

/**
 * Stellt eine MaterialIds Liste, prüft ob überhaupt ob eine vorhanden ist wenn nicht gibt eine liste mit 0 zurück.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialIds extends Material {

    private boolean ausgeblendetDatenAnzeigen;
    private int[] idListe = new int[] { 0 };

    public MaterialIds(Connection sqlConnection) {
	super(sqlConnection);
    }

    public void setAusgeblendetDatenAnzeigen(boolean ausgelendeteDatensatzeBeruecksichtigen) {
	this.ausgeblendetDatenAnzeigen = ausgelendeteDatensatzeBeruecksichtigen;
    }

    public int[] getIdListe() throws IllegalArgumentException, SQLException {
	materialListeErstellen();
	return idListe;
    }

    public boolean materialIdVorhanden(int materialId) throws IllegalArgumentException, SQLException {
	setAusgeblendetDatenAnzeigen(false);

	int[] idListe = getIdListe();
	for (int id : idListe) {

	    if (id == materialId) {
		return true;
	    }
	}
	return false;
    }

    public void materialListeErstellen() throws SQLException, IllegalArgumentException {

	try {
	    queryAuswahl();
	    int anzahlDerDatensatze = anzahlDatenseatze();
	    statmentAusfuehren(getQuery());

	    if (anzahlDerDatensatze == 0) {

		idListe = new int[] { 0 };
	    } else {

		idListe = new int[anzahlDerDatensatze];
		int zaehlerDesArrayIndexes = 0;

		while (getResult().next()) {

		    idListe[zaehlerDesArrayIndexes] = getResult().getInt(1);
		    zaehlerDesArrayIndexes++;
		}
	    }
	    closeStatmentUndResult();

	} catch (SQLException sqlException) {
	    throw new SQLException(sqlException);

	} catch (IllegalArgumentException illegalException) {
	    throw new IllegalArgumentException(illegalException);

	} finally {
	    closeStatmentUndResult();
	}
    }

    public int anzahlDatenseatze() {
	int anzahlDerDatensatze = 0;

	try {
	    statmentVorbereitenUndStarten(getQuery());

	    while (getResult().next()) {
		anzahlDerDatensatze++;
	    }
	} catch (SQLException sqlException) {

	    anzahlDerDatensatze = 0;
	}

	return anzahlDerDatensatze;
    }

    public void queryAuswahl() {
	if (ausgeblendetDatenAnzeigen) {

	    setQuery("SELECT id FROM Material");
	} else {

	    setQuery("SELECT id FROM Material WHERE visibly = 1");
	}
    }

}