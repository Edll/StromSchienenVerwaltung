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

    public void setData(String name, int materialId, int projektNr, long erstellDatum)
	    throws PartException, IllegalArgumentException, SQLException;

    /**
     * Prüft in der Datenbank ob eine PartId vorhanden ist
     * 
     * @param id
     *            Id die geprüft werden soll
     * @return
     */
    boolean IdVorhanden(int id);

    /**
     * Gibt eine komplette liste mit allen Ids zurück
     * 
     * @return
     */

    int[] getIdList();
}
