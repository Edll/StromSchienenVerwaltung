package de.edlly.part;

import java.util.List;

public interface IPartBend extends IPart {

    /**
     * Fügt eine Biegung der Liste Hinzu.
     * 
     * @param bend
     *            Als IPartBend Objekt.
     */
    public void addBend(IPartBend bend);

    /**
     * Fragt das Objekt Bend anhand der ID ab.
     * 
     * @param partId
     *            der Bend in der DB.
     * @return IPartBend Objekt
     */

    public List<IPartBend> getBends(int partId);

    /**
     * Prüft ob es eine Kollision innerhalb des Werkstücks gibt.
     * 
     * @param bend
     * @return boolean wert True wenn es eine Kollision gibt.
     */

    public boolean isBendKollision(IPartBend bend);

}
