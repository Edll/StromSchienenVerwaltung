package de.edlly.db;

import java.sql.Connection;

public abstract class SQLite {
    private SQLiteConnect sqlConnection;

    public SQLite() {

    }

    public Connection getSqlConnection() {
	return sqlConnection.getSqlConnection();
    }

    public void setSqlConnection(SQLiteConnect sqlConnection) throws IllegalArgumentException, SQLiteException {
	SQLiteConnect.isClosedOrNull(sqlConnection);
	this.sqlConnection = sqlConnection;
    }

    public static final String SQLITE_FILE_PATH = "kupfer.sqlite";
}
