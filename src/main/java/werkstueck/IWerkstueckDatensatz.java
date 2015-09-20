package werkstueck;

import java.sql.SQLException;
import java.util.List;

public interface IWerkstueckDatensatz<T> extends IWerkstueck {

    /**
     * Gibt das Werkstück zurück das mit id angefragt worden ist.
     * 
     * @param id
     *            Id des Werkstücks in der Datenbank
     * @return List Objekt mit dem Generic Typ
     * @throws WerkstueckException
     */

    public List<IWerkstueckDatensatz<?>> getDatensatz(int id) throws WerkstueckException;

    /**
     * MOCK Objekt
     * 
     * Gibt das Werkstück zurück das mit id angefragt worden ist.
     * 
     * @param id
     *            Id des Werkstücks in der Datenbank
     * @return List Objekt mit dem Generic Typ
     * @throws WerkstueckException
     */

    public List<IWerkstueckDatensatz<?>> getDummyDatensatz(int id) throws WerkstueckException;

    /**
     * Trägt die Objekt werte in die Datenbank ein
     * 
     * @return
     */
    public boolean eintragenInDb();

    /**
     * Gibt den Namen des Werkstücks zurück.
     * 
     * @return String Namen des Werkstücks.
     */

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
    public String getName();

    /**
     * Name des Werkstücks in die Objektwerte setzten
     * 
     * @param name
     */

    public void setName(String name) throws WerkstueckException;

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

    public void setProjektNr(Integer projektNr) throws WerkstueckException;

    /**
     * Die ProjektNr des Werkstücks auslesen
     * 
     * @return
     */

    public Integer getErstellDatum();

    /**
     * Die ProjektNr wozu das Werkstück gehört
     * 
     * 
     * @param projektNr
     */

    public void setErstellDatum(Integer erstellDatum) throws WerkstueckException;

    /**
     * Setzt alle Werte eines Objekt in einem zug
     * 
     * @param name
     *            Name des Werkstücks
     * @param materialId
     *            Material das verwendet wird.
     * @param erstellDatum
     *            Datum Datum wann das Werkstück erstellt worden ist.
     * @param projektNr
     *            Projekt des des Werkstücks
     * @throws WerkstueckException
     * @throws SQLException 
     * @throws IllegalArgumentException 
     */

    public void setDatensatz(String name, int materialId, int erstellDatum, int projektNr) throws WerkstueckException, IllegalArgumentException, SQLException;

}
