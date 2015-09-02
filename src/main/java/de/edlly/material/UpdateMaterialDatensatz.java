package de.edlly.material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMaterialDatensatz {

    private int materialId;
    private int koordinateX;
    private int koordinateZ;
    private int koordinateyMax;
    private int materialSorteId;
    private int visibly;

    private Connection sqlConnection;

    public UpdateMaterialDatensatz(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;
    }

    public void setMaterialDaten(int materialId, int koordinateX, int koordinateZ, int koordinateyMax,
	    int materialSorteId, int visibly) {

	this.materialId = materialId;
	this.koordinateX = koordinateX;
	this.koordinateZ = koordinateZ;
	this.koordinateyMax = koordinateyMax;
	this.materialSorteId = materialSorteId;
	this.visibly = visibly;

    }

    public void setMaterialVisibly(int visibly) {

	this.visibly = visibly;

    }

    public void setMaterialId(int materialId) throws IllegalArgumentException {

	try {
	    AbfrageMaterialDatensatz materialIdVorhanden = new AbfrageMaterialDatensatz(this.sqlConnection);
	    materialIdVorhanden.materialIdVorhanden(materialId);
	    this.materialId = materialId;
	} catch (IllegalArgumentException e) {
	  //  e.printStackTrace();

	}

    }


    public void updateVisibly(int id, int visibly) {

	PreparedStatement pst = null;

	if (id == 0) {
	    throw new IllegalArgumentException("Die Material id darf nicht 0 sein.");
	}
	if (visibly != 0 & visibly != 1) {
	    throw new IllegalArgumentException("Die Sichtbarkeit kann nur auf 1 oder 0 gesetzt werden.");
	}

	try {
	    String query = "UPDATE \"main\".\"Material\" SET \"visibly\" = ?1 WHERE  \"id\" = " + id;
	    pst = sqlConnection.prepareStatement(query);
	    pst.setInt(1, visibly);
	    pst.executeUpdate();

	} catch (SQLException e) {

	    throw new IllegalArgumentException(e);
	} finally {
	    try {

		pst.close();
	    } catch (Exception e) {
		throw new IllegalArgumentException(e);
	    }
	}
    }


}
