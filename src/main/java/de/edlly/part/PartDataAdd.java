package de.edlly.part;

import java.sql.SQLException;

import de.edlly.db.*;

public class PartDataAdd extends PartData implements IPartData {
    SQLitePreparedStatement sql;

    public PartDataAdd(SQLiteConnect sqlConnection) throws PartException, SQLiteException {
	super(sqlConnection);
	 
	sql = new SQLitePreparedStatement(sqlConnection);
    }

    public boolean dbAdd() throws PartException {
	try {
	    SQLiteConnect.isClosed(sql);
	    sql.setQuery(
		    "INSERT INTO Werkstueck (\"materialId\",\"name\",\"projektNr\",\"erstellDatum\") VALUES (?1,?2,?3,?4)");

	    sql.preparedStatmentWithKeyVorbereiten(sql.getQuery());
	    sql.getPreparedStatment().setInt(1, getPart().getMaterialId());
	    sql.getPreparedStatment().setString(2, getPart().getName());
	    sql.getPreparedStatment().setInt(3, getPart().getProjektNr());
	    sql.getPreparedStatment().setLong(4, getPart().getErstellDatum());
	    sql.preparedStatmentWithKeyAusfuehren();

	    getPart().setId(sql.getPrimaryKey());

	    sql.closePrepareStatment();
	    return true;
	} catch (SQLiteException e) {

	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage());
	} catch (SQLException e) {
	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage());
	} finally {
	    try {
		sql.closePrepareStatment();
	    } catch (SQLiteException e) {
		throw new PartException("SQL Fehler:" + e.getLocalizedMessage());
	    }
	}
    }

}
