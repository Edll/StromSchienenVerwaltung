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
     * Der Maximal nutzbarer Wert auf der Y-Achse
     * 
     * @param yMax
     *            <T extends Numbers>
     */
    public void setYMax(T yMax);

    /**
     * Der Maximal nutzbarer Wert auf der Y-Achse
     * 
     * @return <T extends Numbers>
     */
    public T getYMax();

    /**
     * Winkel der Biegung. Wird geprüft ob er innerhalb der Konstanten ANGEL_MAX / ANGEL_MIN liegt.
     * 
     * @param angel
     *            <T extends Numbers>
     * @throws PartException
     */

    public void setAngel(T angel) throws PartException;

    /**
     * Winkel der Biegung.
     * 
     * @return <T extends Numbers>
     */

    public T getAngel();

    /**
     * Setz die Position der Biegung auf der Y Achse. Wird geprüft ob er innerhalb der konstanten Y_MIN bzw des wertes
     * von yMax liegt.
     * 
     * @param y
     *            <T extends Numbers>
     * @throws PartException
     */

    public void setY(T y) throws PartException;

    /**
     * Gibt die Position auf der Y Achse zurück
     * 
     * @return
     */

    public T getY();

    /**
     * Erzeugt ein neues Bend mit den settern Methoden des Objekts.
     * 
     * @param angel
     *            <T extends Numbers>
     * @param y
     *            <T extends Numbers>
     * @throws PartException
     */
    public void setAngelAndY(T angel, T y) throws PartException;

    /**
     * Gibt die Id des Bends zurück wenn dieser aus der Datenbank gelesen wurde oder wenn eine neues Bend in die
     * Datenbank eingetragen worden ist.
     */
    int getId();

    /**
     * Setz die Id des Bends, es wird geprüft ob diese in der Datenbank vorhanden ist wenn nicht gibt es eine
     * PartException
     * 
     * @param id
     */
    void setId(int id) throws PartException;

    /**
     * Lädt ein Bend Objekt mit id aus der Datenbank. Dabei wird je nach Generic Typ der Klasse geladen. Gibt es keine
     * Möglichkeit das Objekt mit diesem Generic Typ zu Laden gibt es eine RuntimeExcpetion
     * 
     * @param id des zu ladenden Objekts
     * @param sql SqliteStatement mit gültiger DB Connection
     * @throws PartException
     * @throws SQLiteException
     */
    void getDB(int id, SQLiteStatement sql) throws PartException, SQLiteException;

}
