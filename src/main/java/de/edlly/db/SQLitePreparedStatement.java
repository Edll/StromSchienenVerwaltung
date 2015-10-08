package de.edlly.db;

import java.sql.*;

public class SQLitePreparedStatement extends SQLiteQueryAndResult {
    private PreparedStatement preparedStatment;

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

    public void preparedStatmentWithKeyVorbereiten(String query) throws SQLiteException {
	queryNotNull(query);
	try {
	    preparedStatment = getSqlConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
    
    public void preparedStatmentWithKeyAusfuehren() throws SQLiteException {
	preparedStatmentAusfuehren();
	primayKey();
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

    public void primayKey() throws SQLiteException {

	ResultSet generatedKeys;
	try {
	    generatedKeys = preparedStatment.getGeneratedKeys();

	    if (generatedKeys.next()) {
		System.out.println(generatedKeys.getInt(1));
		setPrimaryKey(generatedKeys.getLong(1));
	    } else {
		throw new SQLException("Creating user failed, no ID obtained.");
	    }
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	}
    }
}