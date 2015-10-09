package de.edlly.part;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import de.edlly.db.*;

public class PartList implements IPartList {
    private SQLiteStatement sql;
    private IPart part = new Part();
    private List<IPart> datensatz = new ArrayList<IPart>();

    public PartList(SQLiteConnect sqlConnection) throws PartException, SQLiteException {
	SQLiteConnect.isClosedOrNull(sqlConnection);
	sql = new SQLiteStatement(sqlConnection);

    }

    public IPart getPart() {
	return part;
    }

    public void setPart(IPart part) {
	this.part = part;
    }

    public List<Integer> getIdList() throws SQLiteException {
	List<Integer> idList = new ArrayList<Integer>();
	
	idListeAusDbAbfragen(idList);
	return idList;
    }

    public List<IPart> getDataList() throws PartException, SQLiteException {
	for (int i = 0; i < getIdList().size(); i++) {
	    part = new Part();
	    part.getDB(getIdList().get(i), sql);

	    datensatz.add(part);
	}
	return datensatz;
    }

    public List<IPart> getDataList(int... id) throws PartException, SQLiteException {
	for (int i = 0; i < id.length; i++) {

	    part = new Part();
	    part.getDB(id[i], sql);

	    datensatz.add(part);
	}
	return datensatz;

    }

    private void idListeAusDbAbfragen(List<Integer> idList) throws SQLiteException {
	try {
	    sql.setQuery("Select id From Werkstueck");

	    int anzahlId = sql.anzahlDatenseatze();

	    if (anzahlId == 0) {

		idList.add(new Integer(0));
	    } else {

		sql.statmentExecute(sql.getQuery());

		while (sql.getResult().next()) {

		    idList.add(sql.getResult().getInt(1));
		}
	    }
	    sql.closeStatmentAndResult();

	} catch (SQLException sqlException) {
	    throw new SQLiteException(sqlException.getLocalizedMessage());

	} finally {
	    sql.closeStatmentAndResult();
	}
    }

    public boolean idVorhanden(int id) throws SQLiteException {

	if (getIdList().get(0) == 0) {

	    return false;
	} else {
	    for (int i = 0; i < getIdList().size(); i++) {

		if (getIdList().get(i) == id) {
		    return true;
		}

	    }
	    return false;

	}

    }
}
