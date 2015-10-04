package de.edlly.part;

import java.util.List;

import de.edlly.material.Material;

public interface IPartBend extends IPart {

    /**
     * Grenzwerte für den Angel
     */

    public final static Number ANGEL_MAX = 90;
    public final static Number ANGEL_MIN = -90;

    /**
     * Minimaler Grenzwert für Y. 
     */
    public final static Number Y_MIN = Material.MAX_Y;

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
