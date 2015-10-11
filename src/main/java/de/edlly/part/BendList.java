package de.edlly.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.db.SQLitePreparedStatement;

public class BendList implements IBendList {
    List<IBend<?>> bendList;
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
