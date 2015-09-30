package de.edlly.db;

import java.sql.*;

public class SQLitePreparedStatement extends SQLiteQueryAndResult {
    public PreparedStatement preparedStatment;

    public SQLitePreparedStatement(SQLiteConnect sqlConnection) throws SQLiteException {
	super(sqlConnection);
    }

    public PreparedStatement getPreparedStatment() {
	return preparedStatment;
    }

    public void setPreparedStatment(PreparedStatement preparedStatment) {
	this.preparedStatment = preparedStatment;
    }

    public void preparedStatmentVorbereiten(String query) throws SQLiteException {
	queryNotNull(query);
	try {
	    preparedStatment = getSqlConnection().prepareStatement(query);
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}

    }

    public void preparedStatmentAusfuehren() throws SQLiteException {
	try {
	    preparedStatment.executeUpdate();
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}
    }

    public void closePrepareStatmentAndResult() throws SQLiteException {
	closePrepareStatment();
	closeResult();
    }

    public void closePrepareStatment() throws SQLiteException {
	try {
	    if (preparedStatment != null && !preparedStatment.isClosed()) {
		preparedStatment.close();
	    }
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}

    }
}