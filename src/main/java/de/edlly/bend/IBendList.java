package de.edlly.bend;

import java.util.List;

import de.edlly.db.SQLiteException;
import de.edlly.db.SQLiteStatement;
import de.edlly.part.IPart;
import de.edlly.part.PartException;

/**
 * Erstellt aus einem Bend Objekt eine Liste und verwaltet dieses Prüft ob es eine Kollision innerhalb der Liste gibt.
 * Sortiert die Liste neu wenn ein Objekt hinzugefügt wird.
 * 
 * @author Edlly java@edlly.de
 *
 */

public interface IBendList {

    /**
     * Setz das Part Objekt auf das Das Bend Objekt eine Referenz mit der PartId hat.
     * 
     * @param part
     * @throws PartException
     */

    void setPart(IPart part) throws PartException;

    /**
     * Fügt eine Biegung der Liste hinzu. Prüft davor ob es eine Kollision auf dem Werkstück gibt.
     * 
     * 
     * @param bend
     *            Als IPartBend Objekt.
     * @throws PartException
     */
    public void addBend(IBend<?> bend) throws PartException;

    /**
     * Fügt eine Biegung der Liste hinzu. Prüft davor ob es eine Kollision auf dem Werkstück gibt. Sortiert diese dann
     * direkt nach der Größe von Y.
     * 
     * 
     * @param bend
     *            Als IPartBend Objekt.
     * @throws PartException
     */
    public void addBendSort(IBend<?> bend) throws PartException;

    /**
     * Fragt die Id anhand der Part Id ab.
     * 
     * @param bend
     *            Objekt für die Abfrage damit von außerhalb festgelegt werden kann welchen Typ die BendListe haben
     *            soll.
     * @param partId
     * @return
     * @throws PartException
     * @throws SQLiteException
     */

    public List<IBend<?>> getBendAfterPartId(IBend<?> bend, int partId) throws PartException, SQLiteException;

    /**
     * Gibt die Bendliste im Objekt zurück
     * 
     * @return IBend Liste.
     */
    public List<IBend<?>> getBends();

    /**
     * Prüft ob es eine Kollision innerhalb des Werkstücks gibt.
     * 
     * @param bend
     * @return boolean wert True wenn es eine Kollision gibt.
     */
    public boolean isBendKollision(IBend<?> bend);

    /**
     * Sortiert die Liste
     */
    public void sortList();

    /**
     * Fügt die Liste in die Datenbank ein.
     * 
     * @return boolean true wenn eintragen ein erfolg war
     * 
     * @throws SQLiteException
     * @throws PartException
     */
    public boolean addListToDB() throws PartException, SQLiteException;

    /**
     * Erzeugt eine Liste mit allen Ids die die in der Datenbank vorhanden sind.
     * 
     * @return List<IBend<?>> mit den Ids
     * 
     * @throws SQLiteException
     * @throws PartException
     */
    public List<Integer> getIdList(SQLiteStatement sql) throws SQLiteException, PartException;

    /**
     * Prüft ob eine Id vorhanden ist.
     * 
     * @param id
     * @return true wenn die Id vorhanden ist.
     * @throws SQLiteException
     * @throws PartException
     */
    public boolean isIdVorhanden(int id) throws SQLiteException, PartException;

    /**
     * Gibt eine Liste mit allen Id zurück.
     * 
     * @param sql
     *            SQLiteStatement mit SQl Verbindung
     * @param partId
     *            die Part Id zu der die Ids gehören oder aber -1 wenn alle abgefragt werden sollen
     * @return
     * @throws SQLiteException
     * @throws PartException
     */
    public List<Integer> getIdList(SQLiteStatement sql, int partId) throws SQLiteException, PartException;

}
