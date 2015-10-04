package de.edlly.part;

import java.util.List;

/**
 * Erstellt aus einem Bend Objekt eine Liste. Prüft ob es eine Kollision innerhalb der Liste gibt.
 * 
 * @author Edlly java@edlly.de
 *
 */

public interface IPartBend extends IPart {

    /**
     * Fügt eine Biegung der Liste hinzu. Prüft davor ob es eine Kollision auf dem Werkstück gibt.
     * 
     * @param bend
     *            Als IPartBend Objekt.
     * @throws PartException
     */
    public void addBend(IBend<?> bend) throws PartException;

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
}
