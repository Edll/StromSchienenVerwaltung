package de.edlly.part;

import de.edlly.db.SQLiteException;
import de.edlly.db.SQLiteStatement;

public interface IPart {

    /**
     * Getter Funktion für die WerkstückId
     * 
     * @return gibt die Aktuelle Werkstück Id zurück
     */
    public int getId();

    /**
     * Setzen der Werkstück Id. Dabei wird geprüft ob diese vorhanden ist. Ist sie es nicht gibt es eine Part Exception.
     * 
     * @param id
     * @throws PartException
     * @throws SQLiteException
     */
    public void setId(int id) throws PartException, SQLiteException;

    /**
     * Name des Werkstücks abrufen der im Objekt steht.
     * 
     * @return String
     */

    public String getName();

    /**
     * Name des Werkstücks in die Objektwerte setzten
     * 
     * @param name
     */

    public void setName(String name) throws PartException;

    /**
     * Id des Materials das bei diesem Werkstück verwendet worden ist aus der Datenbank holen
     * 
     * @return Integer id
     * @throws PartException
     */

    public Integer getMaterialId() throws PartException;

    /**
     * Id das Material in das Objekt Setzten
     * 
     * @param id
     * @throws SQLiteException
     */

    public void setMaterialId(Integer materialId) throws SQLiteException;

    /**
     * Gibt den YMax wert des ausgewählten Materials zurück. Vorher muss die MaterialId gesetzt worden sein.
     * 
     * @return
     * @throws SQLiteException
     * @throws IllegalArgumentException
     * @throws PartException
     */

    public int getMaterialYMax() throws IllegalArgumentException, SQLiteException, PartException;

    /**
     * Timestamp wann das Werkstück erstellt worden ist.
     * 
     * @return Integer Timestamp
     */
    /**
     * Abfragen der Aktuellen SqlConnection
     * 
     * @return
     */

    public Integer getProjektNr();

    /**
     * Timestamp wann das Werkstück erstellt worden ist setzten.
     * 
     * @param erstellDatum
     *            Timestamp
     *
     */

    public void setProjektNr(Integer projektNr) throws PartException;

    /**
     * Die ProjektNr des Werkstücks auslesen
     * 
     * @return
     */

    public long getErstellDatum();

    /**
     * Die ProjektNr wozu das Werkstück gehört
     * 
     * 
     * @param projektNr
     */

    public void setErstellDatum(long erstellDatum) throws PartException;

    /**
     * Setzt alle Werte eines Part.
     * 
     * @param name
     *            Name des Werkstücks
     * @param materialId
     *            Material das verwendet wird.
     * @param l
     *            Datum Datum wann das Werkstück erstellt worden ist.
     * @param projektNr
     *            Projekt des des Werkstücks
     * @throws PartException
     * @throws SQLiteException
     */

    public void setData(String name, int materialId, int projektNr, long erstellDatum)
	    throws PartException, SQLiteException;

    /**
     * Abfrage eines Datensatzes aus der DB Ergebiss wird ins Objekt geschrieben.
     * 
     * @param id
     *            Die Id des Datensatzes der abgefragt werden soll
     *
     */

    public void getDB(int id, SQLiteStatement sql) throws PartException, SQLiteException;

}
