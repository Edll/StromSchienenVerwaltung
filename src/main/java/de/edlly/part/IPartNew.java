package de.edlly.part;

import de.edlly.db.SQLiteException;

/**
 * Erzeugt ein neues Part in der Datenbank. Hierbei gibt es zwei Möglichkeiten: Entweder über die Methoden aufrufe oder
 * über einen über einen Parameter Konstruktor.
 * 
 * Parameter Konstruktor:
 * 
 * 
 * PartNew(SQLiteConnect sqlConnection, IPart part)
 * 
 * 
 * sqlConnection: muss eine gültige Verbindung zur SQL Quelle enthalten.
 * 
 * part: enthält das einzutragende IPart. In diesem IPart wird dann Automatisch die PartId des gerade erstellten Parts
 * hinterlegt.
 * 
 * 
 * @author Edlly java@edlly.de
 *
 */

public interface IPartNew {

    /**
     * Liefert das gerade erstellte Part im Objekt zurück als IPart
     * 
     * @return IPart Objekt
     */
    IPart getPart();

    /**
     * Setz das Part ins Objekt das in die Datenbank eingetragen werden soll. Prüft ob diese nicht Null ist.
     * 
     * @param part
     *            PartObjekt das in die Datenbank eingetragen werden soll.
     * @throws PartException
     *             Wenn das Part Null ist.
     */
    void setPart(IPart part) throws PartException;

    /**
     * Fügt das Part auf dem Objekt in die Datenbank ein. Ist dies erfolgreich wird im Anschluss die PartId im IPart
     * Objekt innerhalb des PartNew Objekts gesetzt. Diese hat dann den Wert des erstellten Parts.
     * 
     * @return
     * @throws PartException
     * @throws SQLiteException
     */
    boolean addToDdAndSetPartId() throws PartException, SQLiteException;

}