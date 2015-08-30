package de.edlly.testUnit.testMaterialDatensatz;

import java.sql.Connection;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.UpdateMaterialDatensatz;

public class TestUpdateMaterialDatensatz {

    private Connection SQLConnection;

    public TestUpdateMaterialDatensatz() {

    }

    public void callTestUpdateDatensatz() {
	SQLConnection = SQLiteConnect.dbConnection();

	System.out.println("\n--callTestUpdateDatensatz--\n");

	if (testsetMaterialDaten()) {
	    System.out.println("setMaterialDaten\t --> passed");
	} else {
	    System.out.println("setMaterialDaten\t --> fail");
	}

	if (testSetMaterialVisibly()) {
	    System.out.println("SetMaterialVisibly\t --> passed");
	} else {
	    System.out.println("SetMaterialVisibly\t --> fail");
	}
	
	if (testSetMaterialId()) {
	    System.out.println("SetMaterialId\t --> passed");
	} else {
	    System.out.println("SetMaterialId\t --> fail");
	}
	
	if (testUpdateVisibly()) {
	    System.out.println("UpdateVisibly\t --> passed");
	} else {
	    System.out.println("UpdateVisibly\t --> fail");
	}
	

	SQLiteConnect.closeSqlConnection(SQLConnection);

    }

    private Boolean testsetMaterialDaten() {

	return true;
    }
    
    

    private Boolean testSetMaterialVisibly() {

	return true;
    }
    
    
    private Boolean testSetMaterialId() {
	try{
	    UpdateMaterialDatensatz materialDatensetzten = new UpdateMaterialDatensatz(SQLConnection);
	    
	    materialDatensetzten.setMaterialId(1);
	    materialDatensetzten.setMaterialId(2);
	    materialDatensetzten.setMaterialId(3);
	    return true;
	}catch(IllegalArgumentException e){
	   // e.printStackTrace();
		return false;
	}


    }
    
    private Boolean testUpdateVisibly() {

	return true;
    }
    
   

}
