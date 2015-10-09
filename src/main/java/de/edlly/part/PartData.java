package de.edlly.part;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import de.edlly.db.*;

public class PartData implements IPartData {
    private SQLiteStatement sql;
    private IPart part = new Part();

    public PartData(SQLiteConnect sqlConnection) throws PartException, SQLiteException {
	SQLiteConnect.isClosedOrNull(sqlConnection);
	sql = new SQLiteStatement(sqlConnection);
    }

    public List<Integer> getIdList() throws SQLiteException {

	List<Integer> idList = new ArrayList<Integer>();
	idListeAusDbAbfragen(idList);
	return idList;
    }

    public IPart getPart() {
	return part;
    }

    public void setPart(IPart part) {
	this.part = part;
    }

    /**
     * TODO: CODE Erstellen
     * 
     * @throws SQLiteException
     */
    public boolean idVorhanden(int id) throws SQLiteException {

	// Muster CODE!
	if (getIdList().get(0) == 0) {
	    return false;
	} else {

	    for (int i = 0; i < getIdList().size(); i++) {

		if (getIdList().get(i) == id) {
		    return true;
		}

	    }
	    return false;

	}

    }

    public IPart getData(int id) throws PartException {
	datensatzAusDbAbfragen(id);
	return part;
    }

    public List<IPart> getDataList() throws PartException, SQLiteException {
	List<IPart> datensatz = new ArrayList<IPart>();
	
	for (int i = 0; i < getIdList().size(); i++) {
	    part = new Part();
	    part.datensatzAusDbAbfragen(getIdList().get(i), sql);

	    datensatz.add(part);
	}
	return datensatz;
    }

    public List<IPart> getDataList(int... id) throws PartException, SQLiteException {
	List<IPart> datensatz = new ArrayList<IPart>();

	for (int i = 0; i < id.length; i++) {
	    
	    part = new Part();
	    part.datensatzAusDbAbfragen(id[i], sql);

	    datensatz.add(part);
	}
	return datensatz;

    }

    private void idListeAusDbAbfragen(List<Integer> idList) throws SQLiteException, IllegalArgumentException {
	try {
	    sql.setQuery("Select id From Werkstueck");

	    int anzahlDerDatensatze = sql.anzahlDatenseatze();
	    sql.statmentExecute(sql.getQuery());

	    if (anzahlDerDatensatze == 0) {

		idList.add(new Integer(0));
	    } else {
		while (sql.getResult().next()) {

		    idList.add(sql.getResult().getInt(1));
		}
	    }
	    sql.closeStatmentAndResult();

	} catch (SQLException sqlException) {
	    throw new SQLiteException(sqlException.getLocalizedMessage());

	} catch (IllegalArgumentException illegalException) {
	    throw new IllegalArgumentException(illegalException);

	} finally {
	    sql.closeStatmentAndResult();
	}
    }

    public void datensatzAusDbAbfragen(int id) throws IllegalArgumentException, PartException {
	try {
	    part.setId(id);
	    sql.setQuery("SELECT materialId, name, projektNr, erstellDatum FROM Werkstueck WHERE id = " + part.getId());
	    sql.statmentVorbereitenUndStarten(sql.getQuery());

	    if (sql.resultOhneErgebniss(sql.getResult())) {
		part.setMaterialId(0);
		part.setName("KeinDatensatzVorhanden");
		part.setProjektNr(1);
		part.setErstellDatum(1);
	    }
	    part.setMaterialId(sql.getResult().getInt(1));
	    part.setName(sql.getResult().getString(2));
	    part.setProjektNr(sql.getResult().getInt(3));
	    part.setErstellDatum(sql.getResult().getLong(4));

	    sql.closeStatmentAndResult();
	} catch (SQLiteException e) {
	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	} catch (SQLException e) {
	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	} finally {
	    try {
		sql.closeStatmentAndResult();
	    } catch (SQLiteException e) {
		throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	    }
	}
    }

}
