package de.edlly.part;

import de.edlly.db.*;
import de.edlly.material.*;
import de.edlly.part.PartUtil;

public class Part implements IPart {
    private SQLiteConnect sqLite = new SQLiteConnect();

    private int id;
    private String name;
    private Integer projektNr;
    private long erstellDatum;
    private MaterialIds materialId;

    public Part() throws PartException {
    }

    public int getId() {
	return id;
    }

    public void setId(int id) throws PartException, SQLiteException {
	sqLite.dbConnect();
	IPartData idPruefung = new PartData(sqLite);
	if (id == 0) {
	    this.id = 0;
	} else if (!idPruefung.idVorhanden(id)) {
	    sqLite.close();
	    throw new PartException("Fehler bei der Werkstückid, die angebene id: " + id + " gibt es nicht.");
	} else {
	    this.id = id;
	}
	sqLite.close();
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

    public Integer getMaterialId() throws PartException {
	if (materialId == null) {
	    throw new PartException("Die MaterialId ist nicht initalisiert worden!");
	}
	return materialId.getId();
    }

    public void setMaterialId(Integer materialId) throws SQLiteException {
	sqLite.dbConnect();
	this.materialId = new MaterialIds(sqLite);
	this.materialId.setId(materialId);
	sqLite.close();
    }

    public int getMaterialYMax() throws IllegalArgumentException, SQLiteException, PartException {
	sqLite.dbConnect();
	MaterialDatensatz abfrage = new MaterialDatensatz(sqLite);
	int[] daten = abfrage.getMaterialDatensatzAusDatenbank(getMaterialId());
	sqLite.close();
	return daten[4];
    }
}
