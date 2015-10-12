package de.edlly.material;

import java.sql.*;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.db.SQLitePreparedStatement;

public class UpdateMaterialDatensatz extends MaterialDatensatz {
    SQLitePreparedStatement sqlLite;

    @SuppressWarnings("unused")
    private int materialId;
    @SuppressWarnings("unused")
    private int koordinateX;
    @SuppressWarnings("unused")
    private int koordinateZ;
    @SuppressWarnings("unused")
    private int koordinateyMax;
    @SuppressWarnings("unused")
    private int materialSorteId;
    @SuppressWarnings("unused")
    private int visibly;

    public UpdateMaterialDatensatz(SQLiteConnect sqlConnection) throws IllegalArgumentException, SQLiteException {
        super(sqlConnection);
        sqlLite = new SQLitePreparedStatement(sqlConnection);
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
            // Auskommentiert da Datensatz so nicht mehr aktuell
            // MaterialDatensatz materialIdVorhanden = new MaterialDatensatz(this.sqlConnection);
            // materialIdVorhanden.materialIdVorhanden(materialId);
            // this.materialId = materialId;
        } catch (IllegalArgumentException e) {
            // e.printStackTrace();

        }

    }

    public void updateVisibly(int id, int visibly) throws SQLiteException {

        if (id == 0) {
            throw new IllegalArgumentException("Die Material id darf nicht 0 sein.");
        }
        if (visibly != 0 & visibly != 1) {
            throw new IllegalArgumentException("Die Sichtbarkeit kann nur auf 1 oder 0 gesetzt werden.");
        }

        try {
            sqlLite.setQuery("UPDATE \"main\".\"Material\" SET \"visibly\" = ?1 WHERE  \"id\" = " + id);
            sqlLite.preparedStatmentVorbereiten(sqlLite.getQuery());
            sqlLite.getPreparedStatment().setInt(1, visibly);
            sqlLite.preparedStatmentAusfuehren();
            sqlLite.closePrepareStatment();

        } catch (SQLException e) {

            throw new SQLiteException(e.getLocalizedMessage());
        } finally {
            try {
                sqlLite.closePrepareStatment();
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

}
