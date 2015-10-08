package de.edlly.part;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.db.SQLitePreparedStatement;

public class PartBend implements IPartBend {
    List<IBend<?>> bendList;
    SQLiteConnect connection = new SQLiteConnect();
    SQLitePreparedStatement sqLite;
    IPart part;

    public PartBend() throws SQLiteException, PartException {
	bendList = new ArrayList<IBend<?>>();


	part = new Part();
    }

    public void addBend(IBend<?> bend) throws PartException {
	if (isBendKollision(bend)) {
	    throw new PartException("Fehler: Der minimal Abstand ist nicht eingehalten worden +- : "
		    + IBend.ABSTAND_BEND.doubleValue());
	}
	bendList.add(bend);
    }

    public void addBendSort(IBend<?> bend) throws PartException {
	addBend(bend);
	sortList();
    }

    public List<IBend<?>> getBends() {

	return bendList;
    }

    public List<IBend<?>> getBends(int id) {
	// TODO Auto-generated method stub
	return null;
    }

    public void sortList() {
	Collections.sort(bendList);
    }

    public void setPartId(int id) throws PartException, SQLiteException {
	part.setId(id);
    }

    public int getPartId() {
	// FIXME: Rückgabe darf nicht 0 sein!
	return part.getId();
    }

    public boolean addListToDB() throws PartException {

	if (bendList == null || bendList.isEmpty()) {
	    throw new PartException("Liste wurde nicht angelegt");
	}

	if (part == null) {
	    throw new PartException("Part Id nicht angelegt");
	}
	int partId = getPartId();
	try {
	    connection.dbConnect();
	    for (int i = 0; i < bendList.size(); i++) {
		sqLite = new SQLitePreparedStatement(connection);
		sqLite.setQuery("INSERT INTO \"Bend\" (\"partId\",\"angel\",\"y\") VALUES (?1,?2,?3)");
		sqLite.preparedStatmentVorbereiten(sqLite.getQuery());
		sqLite.getPreparedStatment().setDouble(1, partId);
		sqLite.getPreparedStatment().setDouble(2, bendList.get(i).getAngel().doubleValue());
		sqLite.getPreparedStatment().setDouble(3, bendList.get(i).getY().doubleValue());

		sqLite.preparedStatmentAusfuehren();
		sqLite.closePrepareStatmentAndResult();
	    }
	    connection.close();
	    return true;
	} catch (SQLiteException e) {
	    throw new PartException("Fehler beim eintragen in die Datenbank.\n" + e.getLocalizedMessage());
	} catch (SQLException e) {
	    throw new PartException("Fehler beim eintragen in die Datenbank.\n" + e.getLocalizedMessage());
	}
    }

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
}
