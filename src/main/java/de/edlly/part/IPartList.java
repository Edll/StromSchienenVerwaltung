package de.edlly.part;

import java.util.List;

import de.edlly.db.SQLiteException;

public interface IPartList {

    /**
     * Gibt eine komplette liste mit allen Ids zurück
     * 
     * @return
     */

    List<Integer> getIdList() throws SQLiteException;

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
