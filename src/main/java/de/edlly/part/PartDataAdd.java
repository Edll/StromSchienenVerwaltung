package de.edlly.part;

import java.sql.SQLException;

import de.edlly.db.*;

public class PartDataAdd extends PartData implements IPartData {
    SQLitePreparedStatement sql;

    public PartDataAdd(SQLiteConnect sqlConnection) throws PartException {
	super(sqlConnection);
	try {
	    sql = new SQLitePreparedStatement(sqlConnection);
	} catch (IllegalArgumentException e) {

	    throw new PartException("Format Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	} catch (SQLiteException e) {

	    throw new PartException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	}
    }

    public boolean dbAdd() throws PartException {
	try {
	    SQLiteConnect.isClosed(sql);
	    sql.setQuery(
		    "INSERT INTO Werkstueck (\"materialId\",\"name\",\"projektNr\",\"erstellDatum\") VALUES (?1,?2,?3,?4)");

	    sql.preparedStatmentWithKeyVorbereiten(sql.getQuery());
	    sql.getPreparedStatment().setInt(1, getMaterialId());
	    sql.getPreparedStatment().setString(2, getName());
	    sql.getPreparedStatment().setInt(3, getProjektNr());
	    sql.getPreparedStatment().setLong(4, getErstellDatum());
	    sql.preparedStatmentWithKeyAusfuehren();

	    setId(sql.getPrimaryKey());

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
