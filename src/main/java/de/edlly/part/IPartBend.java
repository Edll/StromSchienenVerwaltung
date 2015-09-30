package de.edlly.part;

public interface IPartBend extends IPart {

    /**
     * Setz das bend in das das Objekt. Dies wird geprüft auf Kollisionen.
     * 
     * @param bend
     *            Als IPartBend Objekt.
     */
    public void setBend(IPartBend bend);

    /**
     * Fragt das Objekt Bend anhand der ID ab.
     * 
     * @param id
     *            der Bend in der DB.
     * @return IPartBend Objekt
     */

    public IPartBend getBend(int id);

    /**
     * Prüft ob es eine Kollision innerhalb des Werkstücks gibt.
     * 
     * @param bend
     * @return boolean wert True wenn es eine Kollision gibt.
     */

    public boolean isBendKolision(IPartBend bend);

}
