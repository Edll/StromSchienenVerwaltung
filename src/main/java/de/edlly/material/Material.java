package de.edlly.material;

import java.sql.Connection;

import de.edlly.db.SQLiteAbfrage;
import de.edlly.db.SQLiteConnect;
/**
 * 
 * Oberklasse für alle Objekte vom Typ Material
 * 
 * @author Edlly java@edlly.de
 *
 */
public abstract class Material extends SQLiteAbfrage {

    public Material(Connection sqlConnection) {
	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	this.sqlConnection = sqlConnection;
    }

}
