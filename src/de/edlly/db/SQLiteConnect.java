package de.edlly.db;

import java.sql.*;

/**
 * Attribute: Connection conn enthält die Aktuelle Connection für die SQL
 * 
 * Methoden: dbConnection() Stellt die Verbindung zu Datenbank her.
 * 
 * @author Edlly
 *
 */

public class SQLiteConnect {
    Connection conn = null;

    public static Connection dbConnection() {
	try {
	    Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:kupfer.sqlite");
	    return conn;
	} catch (Exception e) {
	    throw new IllegalArgumentException("Verbindung zur SQLite DB nicht möglich");
	}

    }

    public static void closeSqlConnection(Connection sqlConnection) {

	try {
	    sqlConnection.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
