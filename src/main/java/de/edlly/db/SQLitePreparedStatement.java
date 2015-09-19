package de.edlly.db;

import java.sql.*;

public class SQLitePreparedStatement extends SQLiteQueryAndResult {
    public PreparedStatement preparedStatment;

    public SQLitePreparedStatement(SQLiteConnect sqlConnection) throws IllegalArgumentException, SQLException {
	super(sqlConnection);
    }

    public PreparedStatement getPreparedStatment() {
	return preparedStatment;
    }

    public void setPreparedStatment(PreparedStatement preparedStatment) {
	this.preparedStatment = preparedStatment;
    }

    public void preparedStatmentVorbereiten(String query) throws SQLException {
	queryNotNull(query);
	preparedStatment = getSqlConnection().prepareStatement(query);
    }

    public void preparedStatmentAusfuehren() throws SQLException {
	preparedStatment.executeUpdate();
    }

    public void closePrepareStatmentAndResult() throws SQLException {
	closePrepareStatment();
	closeResult();
    }

    public void closePrepareStatment() throws SQLException {
	if (preparedStatment != null && !preparedStatment.isClosed()) {
	    preparedStatment.close();
	}
    }
}