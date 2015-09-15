package de.edlly.db;

import java.sql.*;

public class SQLitePreparedStatement extends SQLiteQueryAndResult {
    public PreparedStatement preparedStatment;

    public SQLitePreparedStatement(Connection sqlConnection) {
	super(sqlConnection);

    }
    
    public SQLitePreparedStatement() {
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

    public void closePrepareStatment() throws SQLException {
	if (preparedStatment != null) {
	    preparedStatment.close();
	}
    }
}