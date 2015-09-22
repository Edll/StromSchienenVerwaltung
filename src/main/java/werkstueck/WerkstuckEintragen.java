package werkstueck;

import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLitePreparedStatement;

public class WerkstuckEintragen<T> extends WerkstueckDatensatz<T> {
    SQLitePreparedStatement sql;

    public WerkstuckEintragen(SQLiteConnect sqlConnection) throws WerkstueckException {
	super(sqlConnection);
	try {
	sql = new SQLitePreparedStatement(sqlConnection);
	} catch (IllegalArgumentException e) {

	    throw new WerkstueckException("Format Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	} catch (SQLException e) {

	    throw new WerkstueckException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	}
    }
    
    public boolean dbAdd() throws WerkstueckException{
	try {
	    sql.setQuery("INSERT INTO Werkstueck (\"materialId\",\"name\",\"projektNr\",\"erstellDatum\") VALUES (?1,?2,?3,?4)");
	    
	    sql.preparedStatmentVorbereiten(sql.getQuery());
	    sql.getPreparedStatment().setInt(1, getMaterialId());
	    sql.getPreparedStatment().setString(2, getName());
	    sql.getPreparedStatment().setInt(3, getProjektNr());	    
	    sql.getPreparedStatment().setLong(4, getErstellDatum());
	    
	    sql.preparedStatmentAusfuehren();
	    sql.closePrepareStatment();
	    return true;
	} catch (SQLException e) {
	    
	    throw new WerkstueckException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	}finally{
	    try {
		sql.closePrepareStatment(); 
	    } catch (SQLException e) {
		  throw new WerkstueckException("SQL Fehler:" + e.getLocalizedMessage() + " in " + e.getClass());
	    }
	}
    }

}
