package de.edlly.part;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import de.edlly.db.*;

public class PartList implements IPartList {
    private SQLiteStatement sqlStatment;
    
    private IPart part = new Part();
    private List<IPart> datensatz = new ArrayList<IPart>();
    List<Integer> idList;
    

    public PartList(SQLiteConnect sqlConnection) throws PartException, SQLiteException {
	SQLiteConnect.isClosedOrNull(sqlConnection);
	sqlStatment = new SQLiteStatement(sqlConnection);

    }

    public IPart getPart() {
	return part;
    }

    public void setPart(IPart part) {
	this.part = part;
    }

    public List<Integer> getIdList() throws SQLiteException {
	idListeErstellen();
	return idList;
    }

    public List<IPart> getDataList() throws PartException, SQLiteException {
	for (int i = 0; i < getIdList().size(); i++) {
	    part = new Part();
	    part.getDB(getIdList().get(i), sqlStatment);

	    datensatz.add(part);
	}
	return datensatz;
    }

    public List<IPart> getDataList(int... id) throws PartException, SQLiteException {
	for (int i = 0; i < id.length; i++) {

	    part = new Part();
	    part.getDB(id[i], sqlStatment);

	    datensatz.add(part);
	}
	return datensatz;

    }

    public void idListeErstellen() throws SQLiteException {
	try {
	    idList = new ArrayList<Integer>();
	    sqlStatment.setQuery("Select id From Werkstueck");
	    sqlStatment.statmentVorbereitenUndStarten(sqlStatment.getQuery());
	    int anzahlId = sqlStatment.anzahlDatenseatze();

	    if (anzahlId == 0) {
		idList.add(new Integer(0));
	    } else {

		  sqlStatment.statmentVorbereitenUndStarten(sqlStatment.getQuery());
		while (sqlStatment.getResult().next()) {
		    idList.add(sqlStatment.getResult().getInt(1));
		}
	    }
	    sqlStatment.closeStatmentAndResult();

	} catch (SQLException sqlException) {
	    throw new SQLiteException(sqlException.getLocalizedMessage());

	} finally {
	    sqlStatment.closeStatmentAndResult();
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
