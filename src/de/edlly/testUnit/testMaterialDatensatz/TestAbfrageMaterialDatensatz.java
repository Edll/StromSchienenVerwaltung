package de.edlly.testUnit.testMaterialDatensatz;

import java.sql.Connection;

import de.edlly.db.SQLiteConnect;
import de.edlly.material.AbfrageMaterialDatensatz;

public class TestAbfrageMaterialDatensatz {

    private Connection SQLConnection;

    public TestAbfrageMaterialDatensatz() {

    }

    public void callTestAbfrageDatensatz() {
	SQLConnection = SQLiteConnect.dbConnection();

	System.out.println("\n--callTestAbfrageDatensatz--\n");

	if (testmaterialIdVorhanden()) {
	    System.out.println("materialIdVorhanden\t --> passed");
	} else {
	    System.out.println("materialIdVorhanden\t --> fail");
	}

	if (testmaterialIdAbfragen()) {
	    System.out.println("materialIdAbfragen\t --> passed");
	} else {
	    System.out.println("materialIdAbfragen\t --> fail");
	}

	if (testgetMaterialDatensatz()) {
	    System.out.println("getMaterialDatensatz\t --> passed");
	} else {
	    System.out.println("getMaterialDatensatz\t --> fail");
	}

	SQLiteConnect.closeSqlConnection(SQLConnection);

    }

    private Boolean testmaterialIdVorhanden() {
	Boolean ergebnissDesTests = false;

	int[] materialIdList = new int[] { 0, 1, 2, 3, 4 };

	AbfrageMaterialDatensatz materialDatensatz = new AbfrageMaterialDatensatz(SQLConnection);

	for (int i = 0; i != materialIdList.length; i++) {

	    switch (i) {
	    case 0:
		if (!materialDatensatz.materialIdAbfragen(materialIdList[i])) {
		    ergebnissDesTests = true;
		}
		break;
	    case 1:
		if (materialDatensatz.materialIdAbfragen(materialIdList[i])) {
		    ergebnissDesTests = true;
		}
		break;
	    case 2:
		if (materialDatensatz.materialIdAbfragen(materialIdList[i])) {
		    ergebnissDesTests = true;
		}
		break;

	    case 3:
		if (materialDatensatz.materialIdAbfragen(materialIdList[i])) {
		    ergebnissDesTests = true;
		}
		break;

	    case 4:
		if (!materialDatensatz.materialIdAbfragen(materialIdList[i])) {
		    ergebnissDesTests = true;
		}
		break;
	    default:
		ergebnissDesTests = false;

	    }

	}
	return ergebnissDesTests;
    }

    private Boolean testmaterialIdAbfragen() {
	
	Boolean ergebnissDesTests = false;
	int[] materialIdList = new int[] {0,  1, 2, 3, 4 };
	

	AbfrageMaterialDatensatz materialDatensatz = new AbfrageMaterialDatensatz(SQLConnection);
	for (int i = 0; i != materialIdList.length; i++) {
	    

	try{
	    materialDatensatz.materialIdVorhanden(i);
	    ergebnissDesTests = true; 
	}catch(Exception e){
	    //e.printStackTrace();
	    if(i==0 | i==4){
	    ergebnissDesTests = true;
	    }else{
		ergebnissDesTests = false;
	    }
		
	}
    }
	return ergebnissDesTests;

    }

    private Boolean testgetMaterialDatensatz() {

	return true;
    }

}
