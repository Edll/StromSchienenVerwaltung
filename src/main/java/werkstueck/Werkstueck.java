package werkstueck;

import de.edlly.db.SQLiteConnect;

public class Werkstueck implements IWerkstueck {
    private SQLiteConnect SQLiteConnect;
    private int id;
    
    public Werkstueck(SQLiteConnect SQLiteConnect ) {
	this.SQLiteConnect  = SQLiteConnect;
    }
    
    public int getId() {
	return id;
    }
    
    public void setId(int id) throws WerkstueckException {
	if(id == 0){
	    throw new WerkstueckException("Fehler bei der Werkstückid, die angebene id: " + id + " gibt es nicht.");
	}
    }

    public SQLiteConnect getSqlConnection() {
	
	return SQLiteConnect;
	
    }

}
