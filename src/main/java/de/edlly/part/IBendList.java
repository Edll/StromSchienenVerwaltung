package de.edlly.part;

import java.util.List;

import de.edlly.db.SQLiteException;

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
     * Fragt das Objekt Bend anhand der ID ab.
     * 
     * @param partId
     *            der Bend in der DB.
     * @return IPartBend Objekt
     */

    public List<IBend<?>> getBends(int partId);

    /**
     * Gibt die Bend Liste im Objekt zurück
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
}