package werkstueck;

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
	} catch (SQLException e) {

	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	}
    }

    public boolean IdVorhanden(int id) {
	if (id == 0) {
	    return false;
	}
	return true;
    }

    public int[] getIdList() {
	// TODO: Platzhalte code!
	return new int[] { 1, 2, 3, 4, 5 };
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

	} catch (SQLException e) {
	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	}

    }

    public boolean eintragenInDb() {
	/*
	 * erstellDatum projektNr name materialId
	 */
	// Prüfen ob Objekt wert korrekt
	// return false!
	// UnterObjekt NeuerDatensatz erstellen
	// Objekt werte übergeben
	// Eintrage
	// return true!

	return true;
    }

    public List<IPartData<?>> getData(int id) throws PartException {
	List<IPartData<?>> datensatz = new ArrayList<IPartData<?>>();

	dbAbfrageDatensatz(id);
	datensatz.add(this);

	return datensatz;
    }

    private void dbAbfrageDatensatz(int id) throws IllegalArgumentException, PartException {
	try {
	    setId(id);
	    sql.setQuery("SELECT materialId, name, projektNr, erstellDatum FROM Werkstueck WHERE id = " + getId());
	    sql.statmentVorbereitenUndStarten(sql.getQuery());

	    if (sql.resultOhneErgebniss(sql.getResult())) {
		throw new PartException("SQL Fehler: Kein Datensatz unter dieser Id vorhanden");

	    }
	    setMaterialId(sql.getResult().getInt(1));
	    setName(sql.getResult().getString(2));
	    setProjektNr(sql.getResult().getInt(3));
	    setErstellDatum(sql.getResult().getLong(4));

	    sql.closeStatmentAndResult();

	} catch (SQLException e) {
	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	} finally {
	    try {
		sql.closeStatmentAndResult();
	    } catch (SQLException e) {
		throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	    }
	}
    }

    public List<IPartData<?>> getDummyData(int id) throws PartException {
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
