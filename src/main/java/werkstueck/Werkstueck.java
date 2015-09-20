package werkstueck;

import de.edlly.db.SQLiteConnect;

public class Werkstueck implements IWerkstueck {
    private SQLiteConnect sqlConnection;
    private int id;

    public Werkstueck(SQLiteConnect sqlConnection) {
	this.sqlConnection = sqlConnection;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) throws WerkstueckException {
	IWerkstueckIds idPruefung = new WerkstueckIds(sqlConnection);
	if (!idPruefung.IdVorhanden(id)) {
	    throw new WerkstueckException("Fehler bei der Werkstückid, die angebene id: " + id + " gibt es nicht.");
	}
    }

    public SQLiteConnect getSqlConnection() {
	return sqlConnection;
    }

}
