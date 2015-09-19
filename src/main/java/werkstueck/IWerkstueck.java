package werkstueck;

import de.edlly.db.SQLiteConnect;

public interface IWerkstueck {
    
    /**
     * Getter Funktion für die WerkstückId
     * @return gibt die Aktuelle Werkstück Id zurück
     */
    public int getId();

    public void setId(int id) throws WerkstueckException;

    public SQLiteConnect getSqlConnection();    

}
