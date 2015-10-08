package de.edlly.part;

import de.edlly.db.*;
import de.edlly.material.*;
import de.edlly.part.PartUtil;

public class Part implements IPart {
    private SQLiteConnect sqlConnection;

    private int id;
    private String name;
    private Integer projektNr;
    private long erstellDatum;
    private MaterialIds materialId;

    public Part(SQLiteConnect sqlConnection) throws PartException {

	this.sqlConnection = sqlConnection;
	try {
	    materialId = new MaterialIds(sqlConnection);
	} catch (IllegalArgumentException e) {

	    throw new PartException("Format Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	} catch (SQLiteException e) {

	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	}
    }

    public int getId() {
	return id;
    }

    public void setId(int id) throws PartException, SQLiteException {
	SQLiteConnect sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	IPartData idPruefung = new PartData(sqlConnection);
	if (id == 0) {
	    this.id = 0;
	} else if (!idPruefung.idVorhanden(id)) {
	    throw new PartException("Fehler bei der Werkstückid, die angebene id: " + id + " gibt es nicht.");
	} else {
	    this.id = id;
	}
	sqlConnection.close();
    }

    public String getName() {
	if (name == null || name.isEmpty()) {
	    return "N/A";
	} else {
	    return name;
	}
    }

    public void setName(String name) throws PartException {
	if (name == null || name.isEmpty()) {
	    throw new PartException("Es wurde kein Name angegeben!");
	} else {
	    PartUtil.checkPartName(name);
	    this.name = name;
	}

    }

    public Integer getProjektNr() {
	return this.projektNr;
    }

    public void setProjektNr(Integer projektNr) throws PartException {
	if (projektNr == 0) {
	    this.projektNr = 0;
	    throw new PartException("Es wurde keine ProjektNr angegeben!");
	}
	this.projektNr = projektNr;
    }

    public long getErstellDatum() {
	return this.erstellDatum;
    }

    public void setErstellDatum(long erstellDatum) throws PartException {
	if (erstellDatum <= 0L) {
	    this.erstellDatum = 0L;
	    throw new PartException("Das Datum darf nicht in der Vergangenheit liegen.");
	}
	this.erstellDatum = erstellDatum;
    }

    public Integer getMaterialId() {
	return materialId.getId();
    }

    public void setMaterialId(Integer materialId) throws SQLiteException {
	this.materialId.setId(materialId);
    }

    public int getMaterialYMax() throws IllegalArgumentException, SQLiteException {
	MaterialDatensatz abfrage = new MaterialDatensatz(sqlConnection);
	int[] daten = abfrage.getMaterialDatensatzAusDatenbank(getMaterialId());

	return daten[4];
    }

    public SQLiteConnect getSqlConnection() {
	return sqlConnection;
    }
    
    public void setSqlConnection(SQLiteConnect sqlConnection) {
	this.sqlConnection = sqlConnection;
    }

}
