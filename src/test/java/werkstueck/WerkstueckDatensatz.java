package werkstueck;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialIds;

public class WerkstueckDatensatz<T> extends Werkstueck implements IWerkstueckDatensatz<T> {
    private String name;
    private MaterialIds materialId;
    private Integer projektNr;
    private Integer erstellDatum;

    public WerkstueckDatensatz(SQLiteConnect sqlConnection) throws WerkstueckException {
	super(sqlConnection);
	try {
	    materialId = new MaterialIds(sqlConnection);
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
	    throw new WerkstueckException("Es wurde keine ProjektNr angegeben!");
	} else {
	    this.projektNr = projektNr;
	}
    }

    public Integer getErstellDatum() {
	return 0;
    }

    public void setErstellDatum(Integer erstellDatum) throws WerkstueckException {
	if (erstellDatum <= 0) {
	    throw new WerkstueckException("Das Datum darf nicht in der Vergangenheit liegen.");
	} else {
	    this.erstellDatum = erstellDatum;
	}
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
	return false;
    }

    public List<IWerkstueckDatensatz<?>> getDatensatz(int id) throws WerkstueckException {

	IWerkstueckDatensatz<?> daten = new WerkstueckDatensatz<String>(getSqlConnection());
	daten.setId(1);
	daten.setName("SuperWerkstueck");

	IWerkstueckDatensatz<?> daten2 = new WerkstueckDatensatz<String>(getSqlConnection());
	daten2.setId(2);
	daten2.setName("MegaStück");

	List<IWerkstueckDatensatz<?>> datensatz = new ArrayList<IWerkstueckDatensatz<?>>();
	datensatz.add(daten);
	datensatz.add(daten2);

	return datensatz;
    }

    public List<IWerkstueckDatensatz<?>> getDummyDatensatz(int id) throws WerkstueckException {

	IWerkstueckDatensatz<?> daten = new WerkstueckDatensatz<String>(getSqlConnection());
	daten.setId(1);
	daten.setName("SuperWerkstueck");

	IWerkstueckDatensatz<?> daten2 = new WerkstueckDatensatz<String>(getSqlConnection());
	daten2.setId(2);
	daten2.setName("MegaStück");

	List<IWerkstueckDatensatz<?>> datensatz = new ArrayList<IWerkstueckDatensatz<?>>();
	datensatz.add(daten);
	datensatz.add(daten2);

	return datensatz;

    }

}
