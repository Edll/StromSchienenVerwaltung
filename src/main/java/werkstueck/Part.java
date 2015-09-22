package werkstueck;

import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.MaterialIds;

public class Part implements IPart {
    private SQLiteConnect sqlConnection;
    private int id;
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
