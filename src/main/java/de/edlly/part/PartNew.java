package de.edlly.part;

import java.sql.SQLException;
import de.edlly.db.*;

public class PartNew implements IPartNew {
    private SQLitePreparedStatement sql;
    private IPart part;

    public PartNew(SQLiteConnect sqlConnection, IPart part) throws SQLiteException, PartException {
	SQLiteConnect.isClosedOrNull(sqlConnection);
	sql = new SQLitePreparedStatement(sqlConnection);
	setPart(part);
	if (!addToDdAndSetPartId()) {
	    throw new PartException("Das eintragen ist fehlgeschlagen.");
	}
    }

    public PartNew(SQLiteConnect sqlConnection) throws SQLiteException, PartException {
	SQLiteConnect.isClosedOrNull(sqlConnection);
	sql = new SQLitePreparedStatement(sqlConnection);
    }

    @Override
    public IPart getPart() {
	return part;
    }

    @Override
    public void setPart(IPart part) throws PartException {
	if (part == null) {
	    throw new PartException("Das übergebene Part ist leer.");
	}
	this.part = part;
    }

    @Override
    public boolean addToDdAndSetPartId() throws PartException, SQLiteException {
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
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	} finally {
	    try {
		sql.closePrepareStatment();
	    } catch (SQLiteException e) {
		throw new SQLiteException(e.getLocalizedMessage());
	    }
	}
	return true;
    }

}
