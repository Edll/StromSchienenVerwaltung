package werkstueck;

import java.sql.SQLException;
import java.util.List;

public interface IPartData<T> extends IPart {

    /**
     * Gibt das Werkstück zurück das mit id angefragt worden ist.
     * 
     * @param id
     *            Id des Werkstücks in der Datenbank
     * @return List Objekt mit dem Generic Typ
     * @throws PartException
     */

    public List<IPartData<?>> getData(int id) throws PartException;

    /**
     * MOCK Objekt
     * 
     * Gibt das Werkstück zurück das mit id angefragt worden ist.
     * 
     * @param id
     *            Id des Werkstücks in der Datenbank
     * @return List Objekt mit dem Generic Typ
     * @throws PartException
     */

    public List<IPartData<?>> getDummyData(int id) throws PartException;

    /**
     * Setzt alle Werte eines Part.
     * 
     * @param name
     *            Name des Werkstücks
     * @param materialId
     *            Material das verwendet wird.
     * @param l
     *            Datum Datum wann das Werkstück erstellt worden ist.
     * @param projektNr
     *            Projekt des des Werkstücks
     * @throws PartException
     * @throws SQLException 
     * @throws IllegalArgumentException 
     */

    public void setData(String name, int materialId, int projektNr, long erstellDatum) throws PartException, IllegalArgumentException, SQLException;

 

 
    public String getName();

    /**
     * Name des Werkstücks in die Objektwerte setzten
     * 
     * @param name
     */

    public void setName(String name) throws PartException;

    /**
     * Id des Materials das bei diesem Werkstück verwendet worden ist aus der Datenbank holen
     * 
     * @return Integer id
     */

    public Integer getProjektNr();

    /**
     * Timestamp wann das Werkstück erstellt worden ist setzten.
     * 
     * @param erstellDatum
     *            Timestamp
     *
     */

    public void setProjektNr(Integer projektNr) throws PartException;

    /**
     * Die ProjektNr des Werkstücks auslesen
     * 
     * @return
     */

    public long getErstellDatum();

    /**
     * Die ProjektNr wozu das Werkstück gehört
     * 
     * 
     * @param projektNr
     */

    public void setErstellDatum(long erstellDatum) throws PartException;
    
    /**
     * Prüft ob die Id in der Datenbank vorhanden ist
     * @param id
     * @return false wenn diese nicht vorhanden ist.
     */
    boolean IdVorhanden(int id);

    int[] getIdList();
}
