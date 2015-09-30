package de.edlly.part;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import de.edlly.db.*;

public class PartData<T> extends Part implements IPartData<T> {

    SQLiteStatement sql;

    public PartData(SQLiteConnect sqlConnection) throws PartException {
	super(sqlConnection);
	try {
	    sql = new SQLiteStatement(sqlConnection);
	} catch (IllegalArgumentException e) {

	    throw new PartException("Format Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	} catch (SQLiteException e) {

	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	}
    }

    public List<Integer> getIdList() throws SQLiteException {

	List<Integer> idList = new ArrayList<Integer>();
	idListeAusDbAbfragen(idList);
	return idList;
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

    public IPartData<T> getData(int id) throws PartException {
	IPartData<T> data = new PartData<T>(getSqlConnection());
	data.datensatzAusDbAbfragen(id);

	return data;
    }

    public List<IPartData<?>> getDataList() throws PartException, SQLiteException {
	List<IPartData<?>> datensatz = new ArrayList<IPartData<?>>();

	int i = 0;

	while (i < getIdList().size()) {
	    datensatz.add(i, getData(getIdList().get(i)));
	    i++;
	}
	return datensatz;
    }

    public List<IPartData<?>> getDataList(int... id) throws PartException {
	List<IPartData<?>> datensatz = new ArrayList<IPartData<?>>();

	int i = 0;

	while (i < id.length) {
	    datensatz.add(i, getData(id[i]));
	    i++;
	}
	return datensatz;
    }

    public void setData(String name, int materialId, int projektNr, long erstellDatum) throws PartException {

	try {
	    setErstellDatum(erstellDatum);
	    setName(name);
	    setProjektNr(projektNr);
	    setMaterialId(materialId);
	} catch (PartException e) {
	    throw new PartException("Angaben nicht korrekt: " + e.getLocalizedMessage() + " in " + e.getClass());
	} catch (IllegalArgumentException e) {
	    throw new PartException("Angaben nicht korrekt: " + e.getLocalizedMessage() + " in " + e.getClass());

	} catch (SQLiteException e) {
	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	}

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
	    setId(id);
	    sql.setQuery("SELECT materialId, name, projektNr, erstellDatum FROM Werkstueck WHERE id = " + getId());
	    sql.statmentVorbereitenUndStarten(sql.getQuery());

	    if (sql.resultOhneErgebniss(sql.getResult())) {
		    setMaterialId(0);
		    setName("KeinDatensatzVorhanden");
		    setProjektNr(1);
		    setErstellDatum(1);
	    }
	    setMaterialId(sql.getResult().getInt(1));
	    setName(sql.getResult().getString(2));
	    setProjektNr(sql.getResult().getInt(3));
	    setErstellDatum(sql.getResult().getLong(4));

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

    public List<IPartData<?>> getDummyData(int id) throws PartException, SQLiteException {
	// MOCK OBJEKT
	List<IPartData<?>> datensatz = new ArrayList<IPartData<?>>();
	IPartData<?> daten2 = new PartData<String>(getSqlConnection());
	daten2.setId(1);
	daten2.setName("TestDaten");
	daten2.setProjektNr(666);
	daten2.setErstellDatum(1442940229642L);

	datensatz.add(daten2);

	return datensatz;

    }

}
