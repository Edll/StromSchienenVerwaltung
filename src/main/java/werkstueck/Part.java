package werkstueck;

import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialIds;

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
	} catch (SQLException e) {

	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	}
    }

    public int getId() {
	return id;
    }

    public void setId(int id) throws PartException {
	IPartData<Integer> idPruefung = new PartData<Integer>(sqlConnection);
	if (!idPruefung.IdVorhanden(id)) {
	    throw new PartException("Fehler bei der Werkstückid, die angebene id: " + id + " gibt es nicht.");
	} else {
	    this.id = id;
	}
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

    public void setMaterialId(Integer materialId) throws SQLException {
	this.materialId.setId(materialId);
    }

    public SQLiteConnect getSqlConnection() {
	return sqlConnection;
    }

}
