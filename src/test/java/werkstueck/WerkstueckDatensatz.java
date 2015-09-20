package werkstueck;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import de.edlly.db.*;
import de.edlly.material.MaterialIds;

public class WerkstueckDatensatz<T> extends Werkstueck implements IWerkstueckDatensatz<T> {
    private String name;
    private MaterialIds materialId;
    @SuppressWarnings("unused")
    private Integer projektNr;
    @SuppressWarnings("unused")
    private Integer erstellDatum;
    SQLiteStatement sql;

    public WerkstueckDatensatz(SQLiteConnect sqlConnection) throws WerkstueckException {
	super(sqlConnection);
	try {
	    materialId = new MaterialIds(sqlConnection);
	    sql = new SQLiteStatement(sqlConnection);
	} catch (IllegalArgumentException e) {

	    throw new WerkstueckException("Format Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	} catch (SQLException e) {

	    throw new WerkstueckException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	}
    }

    public String getName() {
	if (name == null || name.isEmpty()) {
	    return "N/A";
	} else {
	    return name;
	}
    }

    public void setName(String name) throws WerkstueckException {
	if (name == null || name.isEmpty()) {
	    throw new WerkstueckException("Es wurde kein Name angegeben!");
	} else {
	    this.name = name;
	}

    }

    public Integer getMaterialId() {
	return this.materialId.getId();
    }

    public void setMaterialId(Integer materialId) throws IllegalArgumentException, SQLException {
	this.materialId.setId(materialId);
    }

    public Integer getProjektNr() {
	return 0;
    }

    public void setProjektNr(Integer projektNr) throws WerkstueckException {
	if (projektNr == 0) {
	    this.projektNr = 0;
	    throw new WerkstueckException("Es wurde keine ProjektNr angegeben!");
	}
	this.projektNr = projektNr;
    }

    public Integer getErstellDatum() {
	return 0;
    }

    public void setErstellDatum(Integer erstellDatum) throws WerkstueckException {
	if (erstellDatum <= 0) {
	    this.erstellDatum = 0;
	    throw new WerkstueckException("Das Datum darf nicht in der Vergangenheit liegen.");
	}
	this.erstellDatum = erstellDatum;
    }

    public void setDatensatz(String name, int materialId, int erstellDatum, int projektNr) throws WerkstueckException {

	try {
	    setErstellDatum(erstellDatum);
	    setName(name);
	    setProjektNr(projektNr);
	    setMaterialId(materialId);
	} catch (WerkstueckException e) {
	    throw new WerkstueckException("Angaben nicht korrekt: " + e.getLocalizedMessage() + " in " + e.getClass());
	} catch (IllegalArgumentException e) {
	    throw new WerkstueckException("Angaben nicht korrekt: " + e.getLocalizedMessage() + " in " + e.getClass());

	} catch (SQLException e) {
	    throw new WerkstueckException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
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

	return false;
    }

    public List<IWerkstueckDatensatz<?>> getDatensatz(int id) throws WerkstueckException {
	List<IWerkstueckDatensatz<?>> datensatz = new ArrayList<IWerkstueckDatensatz<?>>();

	dbAbfrageDatensatz(id);
	datensatz.add(this);

	return datensatz;
    }

    private void dbAbfrageDatensatz(int id) throws IllegalArgumentException, WerkstueckException {
	try {
	    setId(id);
	    sql.setQuery("SELECT materialId, name, projektNr, erstellDatum FROM Werkstueck WHERE id = " + getId());
	    sql.statmentVorbereitenUndStarten(sql.getQuery());

	    if (sql.resultOhneErgebniss(sql.getResult())) {
		throw new WerkstueckException("SQL Fehler: Kein Datensatz unter dieser Id vorhanden");

	    }
	    setMaterialId(sql.getResult().getInt(1));
	    setName(sql.getResult().getString(2));
	    setProjektNr(sql.getResult().getInt(3));
	    setErstellDatum(sql.getResult().getInt(4));

	    sql.closeStatmentAndResult();

	} catch (SQLException e) {
	    throw new WerkstueckException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	} finally {
	    try {
		sql.closeStatmentAndResult();
	    } catch (SQLException e) {
		throw new WerkstueckException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	    }
	}
    }

    public List<IWerkstueckDatensatz<?>> getDummyDatensatz(int id) throws WerkstueckException {
	// MOCK OBJEKT
	List<IWerkstueckDatensatz<?>> datensatz = new ArrayList<IWerkstueckDatensatz<?>>();
	IWerkstueckDatensatz<?> daten2 = new WerkstueckDatensatz<String>(getSqlConnection());
	daten2.setId(1);
	daten2.setName("Test");
	daten2.setProjektNr(0);
	daten2.setErstellDatum(0);

	datensatz.add(daten2);

	return datensatz;

    }

}
