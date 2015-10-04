package de.edlly.part;

import java.sql.SQLException;
import java.util.List;

import de.edlly.db.SQLiteException;

public interface IPartData extends IPart {

    /**
     * Abfragen eines einzelnen Datensatzes aus der Datenbank.
     * 
     * @param id
     *            des Datensatzes in der Datenbank
     * @return
     * @return Gibt das Objekt selber zurück als Typ IPartData
     * @throws PartException
     */

    List<Integer> getIdList() throws SQLiteException;

    /**
     * Gibt eine komplette liste mit allen Ids zurück
     * 
     * @return
     */
    public IPartData getData(int id) throws PartException;

    /**
     * Erstellt eine Liste mit allen Werkstücken in der Datenbank
     * 
     * @return List Objekt mit dem Generic Typ IPartData
     * @throws PartException
     */

    public List<IPartData> getDataList() throws PartException, SQLiteException;

    /**
     * Erstellt eine Liste mit den Werkstücken die in der Id Liste abgefragt worden sind.
     * 
     * @param id
     *            Integre als Array
     * @return List Objekt mit dem Generic Typ IPartData
     * @throws PartException
     */

    public List<IPartData> getDataList(int... id) throws PartException;

    /**
     * MOCK Objekt
     * 
     * Gibt das Werkstück zurück das mit id angefragt worden ist.
     * 
     * @param id
     *            Id des Werkstücks in der Datenbank
     * @return List Objekt mit dem Generic Typ
     * @throws PartException
     * @throws SQLiteException 
     */

    public List<IPartData> getDummyData(int id) throws PartException, SQLiteException;

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
     * @throws SQLiteException 
     */
    boolean idVorhanden(int id) throws SQLiteException;

    /**
     * Abfrage eines Datensatzes aus der DB Ergebiss wird ins Objekt geschrieben.
     * 
     * @param id
     *            Die Id des Datensatzes der abgefragt werden soll
     *
     */

    public void datensatzAusDbAbfragen(int id) throws IllegalArgumentException, PartException;
}
