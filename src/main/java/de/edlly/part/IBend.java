package de.edlly.part;

import de.edlly.db.SQLiteException;
import de.edlly.db.SQLiteStatement;
import de.edlly.material.Material;

/**
 * Bend Objekt ist die Grundlage für PartBend. Wird eine neue Biegung in einem Part erzeugt, wird ein Bend Objekt
 * erzeugt und dieses Bend Objekt dann von PartBend verwaltet.
 * 
 * Bend ist als Generic angelegt und kann mit jeder Art von Objekt genutzt werden die von Number abstammen.
 * 
 * Achtung! bei BigDecimal ist die Prüfung der Grenzwerte im oberen Bereich über Double ungenau.
 * 
 * @author Edlly java@edlly.de
 *
 * @param <T>
 */

public interface IBend<T extends Number & Comparable<T>> extends Comparable<IBend<?>> {

    /**
     * Konstanten
     */

    // Grenzwerte für den Angel
    public final static Number ANGEL_MAX = 90;
    public final static Number ANGEL_MIN = -90;

    // Minimaler Grenzwert für Y
    public final static Number Y_MIN = Material.MIN_Y;

    // Abstand zwischen zwei Biegungen
    public final static Number ABSTAND_BEND = 75;

    // Abstand zum Part Rand
    public final static Number ABSTAND_RAND = 45;

    /**
     * Der maximal nutzbarer Wert auf der Y-Achse. Wird durch das Material bestimmt das im IPart Objekt gewählt worden
     * ist.
     * 
     * @param yMax
     *            <T extends Number & Comparable<T>>
     */
    public void setYMax(T yMax);

    /**
     * Der Maximal nutzbarer Wert auf der Y-Achse. Wird durch das Material bestimmt das im IPart Objekt gewählt worden
     * ist.
     * 
     * @return <T extends Number & Comparable<T>>
     */
    public T getYMax();

    /**
     * Winkel der Biegung je nach Richtung dieser wird er als + oder - wert angegeben. Es wird geprüft ob er innerhalb
     * der Konstanten ANGEL_MAX / ANGEL_MIN liegt. Ist dies nicht der Fall wird eine PartException ausgelöst.
     * 
     * @param angel
     *            <T extends Number & Comparable<T>>
     * @throws PartException
     */

    public void setAngel(T angel) throws PartException;

    /**
     * Winkel der Biegung. Wird je nach Richtung als + oder - wert zurück geben.
     * 
     * @return <T extends Number & Comparable<T>>
     */

    public T getAngel();

    /**
     * Setz die Position der Biegung auf der Y Achse. Wird geprüft ob er innerhalb der konstanten Y_MIN bzw des wertes
     * von yMax liegt.
     * 
     * @param y
     *            <T extends Number & Comparable<T>>
     * @throws PartException
     */

    public void setY(T y) throws PartException;

    /**
     * Gibt die Position der Biegung auf der Y Achse zurück.
     * 
     * @return <T extends Number & Comparable<T>>
     */

    public T getY();

    /**
     * Setzt den Winkel und die Position mit den Methoden setY und setAngel.
     * 
     * @param angel
     *            <T extends Numbers>
     * @param y
     *            <T extends Numbers>
     * @throws PartException
     */
    public void setAngelAndY(T angel, T y) throws PartException;

    /**
     * Setz die Id des Bends, es wird geprüft ob diese in der Datenbank vorhanden ist. Wenn nicht wird eine
     * PartException ausgelöst.
     * 
     * @param id
     */
    void setId(int id) throws PartException;

    /**
     * Gibt die Id des Bend zurück wenn dieser aus der Datenbank gelesen wurde. Wenn eine neues Bend in der Datenbank
     * erstellt worden ist, wird die neue Id eingetragen.
     */
    int getId();

    /**
     * Lädt ein Bend Objekt mit id aus der Datenbank. Dabei wird je nach Generic Typ der Klasse geladen. Gibt es keine
     * Möglichkeit das Objekt mit diesem Generic Typ zu Laden gibt es eine RuntimeExcpetion.
     * 
     * @param id
     *           die id des zu ladenden Objekts.
     * @param sql
     *            SQLiteStatement mit gültiger DB Connection.
     * @throws PartException
     * @throws SQLiteException
     */
    void getDB(int id, SQLiteStatement sql) throws PartException, SQLiteException;

}
