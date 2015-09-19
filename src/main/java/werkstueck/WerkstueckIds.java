package werkstueck;

import de.edlly.db.SQLiteConnect;

public class WerkstueckIds extends Werkstueck implements IWerkstueckIds {

    public WerkstueckIds(SQLiteConnect sqlConnection) {
	super(sqlConnection);
    }
    
    public boolean IdVorhanden(int id) {
	// TODO: Platzhalte code!
	return true;	
    }

    public int[] getIdList() {
	// TODO: Platzhalte code!
	return new int[] {1, 2, 3, 4, 5};
    }

}
