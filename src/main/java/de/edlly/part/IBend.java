package de.edlly.part;

import de.edlly.material.Material;

/**
 * Bend Objekt ist die Grundlage für PartBend. Wird eine neue Biegung in einem Part erzeugt ist dies ein Bend Objekt und
 * wird von PartBend verwaltet.
 * 
 * @author Edlly java@edlly.de
 *
 * @param <T>
 */

public interface IBend<T extends Number> {

    /**
     * Grenzwerte für den Angel
     */

    public final static Number ANGEL_MAX = 90;
    public final static Number ANGEL_MIN = -90;

    /**
     * Minimaler Grenzwert für Y.
     */
    public final static Number Y_MIN = Material.MIN_Y;

    /**
     * Maximaler wert den y annehmen kann.
     * 
     * @param yMax
     */
    public void setYMax(T yMax);

    /**
     * Gibt den Maximalen Y Wert zurück.
     * 
     * @return
     */
    public T getYMax();

    /**
     * Setz den Winkel
     * 
     * @param angel
     * @throws PartException
     */

    public void setAngel(T angel) throws PartException;

    /**
     * Gibt den Winkel zurück
     * 
     * @return
     */

    public T getAngel();

    /**
     * Setz die Position der Biegung auf der Y Achse.
     * 
     * @param y
     * @throws PartException
     */

    public void setY(T y) throws PartException;

    /**
     * Gibt die Position auf der Y Achse zurück
     * 
     * @return
     */

    public T getY();

}
