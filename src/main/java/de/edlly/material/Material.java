package de.edlly.material;

import java.sql.Connection;

import de.edlly.db.SQLiteAbfrage;

/**
 * 
 * Oberklasse für alle Objekte vom Typ Material
 * 
 * @author Edlly java@edlly.de
 *
 */
public abstract class Material extends SQLiteAbfrage {

    public enum datensatzNamen {
	ID, MATERIALSORTE_ID, X, Z, YMAX, SICHTBARKEIT
    }

    public Material(Connection sqlConnection) {
	setSqlConnection(sqlConnection);
    }

    static int getOrdinal(String datensatzNamen) {
	return MaterialDatensatz.datensatzNamen.valueOf(datensatzNamen).ordinal();
    }

}
