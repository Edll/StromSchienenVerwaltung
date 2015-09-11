package de.edlly.material;

import java.sql.Connection;

import de.edlly.db.SQLiteAbfrage;
import de.edlly.db.SQLiteConnect;

public class Material extends SQLiteAbfrage {

    public Material(Connection sqlConnection) {
	SQLiteConnect.sqlConnectionCloseorNull(sqlConnection);
	this.sqlConnection = sqlConnection;
    }

}
