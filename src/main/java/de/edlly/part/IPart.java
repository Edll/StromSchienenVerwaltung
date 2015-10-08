package de.edlly.part;

import de.edlly.db.SQLiteException;

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

}
