package de.edlly.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.edlly.db.SQLiteConnect;

public class PartBend extends Part implements IPartBend {
    List<IBend<?>> bendList;

    public PartBend(SQLiteConnect sqlConnection) throws PartException {
	super(sqlConnection);
	bendList = new ArrayList<IBend<?>>();
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

    @Override
    public void setPartId(int id) {
	// TODO Auto-generated method stub

    }

    @Override
    public int getPartId() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public boolean addListToDB() {
	return false;
	// TODO Auto-generated method stub

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
