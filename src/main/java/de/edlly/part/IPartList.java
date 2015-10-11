package de.edlly.part;

import java.util.List;

import de.edlly.db.SQLiteException;

public interface IPartList {

    /**
     * Gibt das Lokale Part Objekt zurück das als IPart erstellt worden ist.
     * 
     * @return IPart Objekt mit den Aktuellen werten.
     */

    public IPart getPart();

    /**
     * Setz das Lokale Part Objekt aus dem die List erstellt wird.
     * 
     * @param part
     *            als IPart
     */

    public void setPart(IPart part);

    /**
     * Erstellt eine Liste mit allen PartIds in der Datenbank und gibt diese zurück. Wenn kein Objekt vorhanden ist gibt
     * es nur eine Element mit dem wert 0.
     * 
     * @return List Objekt mit allen in der Datenbank vorhandenen PartIds
     */

    List<Integer> getIdList() throws SQLiteException;

    /**
     * Erstellt ein ListObjekt mit allen Parts in der DB. Diese werden als IPart Objekt eingefügt. Ist die Datenbank
     * leer wird nur eine Listenelement eingefügt das Leer ist.
     * 
     * @return List mit alle Datenbank Parts
     * @throws PartException
     * @throws SQLiteException
     */

    public List<IPart> getDataList() throws PartException, SQLiteException;

    /**
     * Erstellt eine Liste mit den Werkstücken die in der Id Liste abgefragt worden sind. Wenn diese 0 wird ein Dummy
     * Objekt geliefert mit der ID 0
     * 
     * @param id
     *            Integre als Array
     * @return List Objekt mit dem Generic Typ IPartData
     * @throws PartException
     * @throws SQLiteException
     */

    public List<IPart> getDataList(int... id) throws PartException, SQLiteException;

    /**
     * Erstellt eine Id Liste mit allen Part Id in der Datenbank. Wenn Keine Ids vorhanden sind wird es eine Element mit
     * einer 0 zurück gegeben.
     * 
     * @throws SQLiteException
     */
    public void idListeErstellen() throws SQLiteException;

    /**
     * Prüft in der Datenbank ob eine PartId vorhanden ist
     * 
     * @param id
     *            Id die geprüft werden soll
     * @return
     * @throws SQLiteException
     */
    boolean idVorhanden(int id) throws SQLiteException;

    void sortList();

    List<IPart> getDataListSort() throws PartException, SQLiteException;
}
