package de.edlly.part;

import java.util.List;

import de.edlly.db.SQLiteException;

public interface IPartData  {

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
    public IPart getData(int id) throws PartException;

    /**
     * Erstellt eine Liste mit allen Werkstücken in der Datenbank
     * 
     * @return List Objekt mit dem Generic Typ IPartData
     * @throws PartException
     */

    public List<IPart> getDataList() throws PartException, SQLiteException;

    /**
     * Erstellt eine Liste mit den Werkstücken die in der Id Liste abgefragt worden sind.
     * 
     * @param id
     *            Integre als Array
     * @return List Objekt mit dem Generic Typ IPartData
     * @throws PartException
     * @throws SQLiteException
     */

    public List<IPart> getDataList(int... id) throws PartException, SQLiteException;



    /**
     * Prüft in der Datenbank ob eine PartId vorhanden ist
     * 
     * @param id
     *            Id die geprüft werden soll
     * @return
     * @throws SQLiteException 
     */
    boolean idVorhanden(int id) throws SQLiteException;
}
