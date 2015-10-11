package de.edlly.part;

import java.sql.SQLException;

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
	IPartList idPruefung = new PartList(sqLite);
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
	if(materialId != 0){
	sqLite.dbConnect();
	this.materialId = new MaterialIds(sqLite);
	this.materialId.setId(materialId);
	sqLite.close();
	}
    }

    public int getMaterialYMax() throws IllegalArgumentException, SQLiteException, PartException {
	sqLite.dbConnect();
	MaterialDatensatz abfrage = new MaterialDatensatz(sqLite);
	int[] daten = abfrage.getMaterialDatensatzAusDatenbank(getMaterialId());
	sqLite.close();
	return daten[4];
    }

    public void setData(String name, int materialId, int projektNr, long erstellDatum)
	    throws PartException, SQLiteException {

	setErstellDatum(erstellDatum);
	setName(name);
	setProjektNr(projektNr);
	setMaterialId(materialId);
    }

    public void getDB(int id, SQLiteStatement sql) throws PartException, SQLiteException {
	try {
	    setId(id);
	    SQLiteConnect.isClosedOrNull(sql);

	    sql.setQuery("SELECT materialId, name, projektNr, erstellDatum FROM Werkstueck WHERE id = " + getId());
	    sql.statmentVorbereitenUndStarten(sql.getQuery());

	    if (sql.resultOhneErgebniss(sql.getResult())) {
		setMaterialId(0);
		setName("KeinDatensatzVorhanden");
		setProjektNr(1);
		setErstellDatum(1);
	    } else {
		setMaterialId(sql.getResult().getInt(1));
		setName(sql.getResult().getString(2));
		setProjektNr(sql.getResult().getInt(3));
		setErstellDatum(sql.getResult().getLong(4));
	    }

	    sql.closeStatmentAndResult();
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	} finally {
	    sql.closeStatment();

	}
    }
}
