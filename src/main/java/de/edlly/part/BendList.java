package de.edlly.part;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.db.SQLitePreparedStatement;
import de.edlly.db.SQLiteStatement;

public class BendList implements IBendList {
    List<IBend<?>> bendList;
    List<Integer> idList;
    SQLiteConnect connection = new SQLiteConnect();
    SQLitePreparedStatement sqLite;
    IPart part;

    public BendList() throws SQLiteException, PartException {
	bendList = new ArrayList<IBend<?>>();

	part = new Part();
    }

    @Override
    public void setPart(IPart part) throws PartException {
	if (part == null) {
	    throw new PartException("Part Id nicht angelegt");
	}
	this.part = part;
    }

    @Override
    public void addBend(IBend<?> bend) throws PartException {
	if (isBendKollision(bend)) {
	    throw new PartException("Fehler: Der minimal Abstand ist nicht eingehalten worden +- : "
		    + IBend.ABSTAND_BEND.doubleValue());
	}
	bendList.add(bend);
    }

    @Override
    public void addBendSort(IBend<?> bend) throws PartException {
	addBend(bend);
	sortList();
    }

    @Override
    public List<IBend<?>> getBends() {

	return bendList;
    }

    @Override
    public List<IBend<?>> getBends(int id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void sortList() {
	Collections.sort(bendList);
    }

    @Override
    public boolean addListToDB() throws PartException, SQLiteException {

	if (bendList == null || bendList.isEmpty()) {
	    throw new PartException("Liste wurde nicht angelegt");
	}
	connection.dbConnect();
	for (int i = 0; i < bendList.size(); i++) {
	    SQLitePreparedStatement sql = new SQLitePreparedStatement(connection);
	    bendList.get(i).setPart(part);
	    bendList.get(i).addDB(sql);
	}
	connection.close();
	return true;
    }

    @Override
    public boolean isBendKollision(IBend<?> bend) {
	for (int i = 0; i < bendList.size(); i++) {

	    double isY = bendList.get(i).getY().doubleValue();
	    double getY = bend.getY().doubleValue();

	    if (isY == getY) {
		return true;

	    } else if (isY > getY) {

		if (isY < getY + IBend.ABSTAND_BEND.doubleValue()) {
		    return true;
		}

	    } else {
		if (isY > getY - IBend.ABSTAND_BEND.doubleValue()) {
		    return true;
		}
	    }
	}
	return false;
    }

    @Override
    public List<Integer> getIdList(SQLiteStatement sql) throws SQLiteException, PartException {

	SQLiteConnect.isClosedOrNull(sql);

	try {
	    sql.setQuery("SELECT id FROM Bend");
	    sql.statmentVorbereitenUndStarten(sql.getQuery());

	    int anzahlDatensatze = sql.anzahlDatenseatze();

	    if (anzahlDatensatze == 0) {
		throw new PartException("Keine Datensätze in der Datenbank vorhanden.");
	    }

	    idList = new ArrayList<Integer>();

	    sql.statmentVorbereitenUndStarten(sql.getQuery());
	    while (sql.getResult().next()) {
		idList.add(sql.getResult().getInt(1));
	    }
	    sql.closeStatmentAndResult();
	    return idList;

	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	} finally {
	    try {
		sql.closeStatmentAndResult();
	    } catch (SQLiteException e) {
		e.printStackTrace();
	    }
	}
    }
}
