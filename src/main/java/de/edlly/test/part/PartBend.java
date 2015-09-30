package de.edlly.test.part;

import de.edlly.db.SQLiteConnect;
import de.edlly.part.IPartBend;
import de.edlly.part.Part;
import de.edlly.part.PartException;

public class PartBend extends Part implements IPartBend {

    public PartBend(SQLiteConnect sqlConnection) throws PartException {
	super(sqlConnection);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void setBend(IPartBend bend) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public IPartBend getBend(int id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean isBendKolision(IPartBend bend) {
	// TODO Auto-generated method stub
	return false;
    }

   
}
