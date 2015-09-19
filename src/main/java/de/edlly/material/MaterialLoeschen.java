package de.edlly.material;

import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLitePreparedStatement;

public class MaterialLoeschen extends MaterialDatensatz {
    private SQLitePreparedStatement sqlLite;

    public MaterialLoeschen(SQLiteConnect sqlConnection) throws IllegalArgumentException, SQLException {
	super(sqlConnection);
	sqlLite = new SQLitePreparedStatement(sqlConnection);
    }

    public void loschen(int id) {
	// TODO Auto-generated method stub
	
    }

    
    
    

}
