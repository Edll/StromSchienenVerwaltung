package de.edlly.test.part;

import java.util.List;

import de.edlly.db.SQLiteConnect;
import de.edlly.part.IPartBend;
import de.edlly.part.Part;
import de.edlly.part.PartException;

public class PartBend extends Part implements IPartBend {   
	List<IPartBend> bends;
	
    public PartBend(SQLiteConnect sqlConnection) throws PartException {

	super(sqlConnection);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void addBend(IPartBend bend) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public List<IPartBend> getBends(int id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean isBendKollision(IPartBend bend) {
	// TODO Auto-generated method stub
	return false;
    }

   
}
