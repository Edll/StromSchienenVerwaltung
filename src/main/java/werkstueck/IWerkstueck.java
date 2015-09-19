package werkstueck;

import de.edlly.db.SQLiteConnect;

public interface IWerkstueck {
    
    /**
     * Getter Funktion für die WerkstückId
     * @return gibt die Aktuelle Werkstück Id zurück
     */
    public int getId();
    
    /**
     * Setzen der Werkstück Id
     * @param id
     * @throws WerkstueckException
     */
    public void setId(int id) throws WerkstueckException;
    
    /**
     * Abfragen der Aktuellen SqlConnection
     * @return
     */
    public SQLiteConnect getSqlConnection();    

}
