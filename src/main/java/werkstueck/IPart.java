package werkstueck;

import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;

public interface IPart {

    /**
     * Getter Funktion für die WerkstückId
     * 
     * @return gibt die Aktuelle Werkstück Id zurück
     */
    public int getId();

    /**
     * Setzen der Werkstück Id
     * 
     * @param id
     * @throws PartException
     */
    public void setId(int id) throws PartException;

    
    
    
    public Integer getMaterialId();

    /**
     * Id das Material in das Objekt Setzten
     * 
     * @param id
     * @throws SQLException 
     */

    public void setMaterialId(Integer materialId) throws IllegalArgumentException, SQLException;

    /**
     * Timestamp wann das Werkstück erstellt worden ist.
     * 
     * @return Integer Timestamp
     */
    /**
     * Abfragen der Aktuellen SqlConnection
     * 
     * @return
     */
    public SQLiteConnect getSqlConnection();

}
