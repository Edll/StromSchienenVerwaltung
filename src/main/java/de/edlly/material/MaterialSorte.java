package de.edlly.material;

import java.sql.*;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.db.SQLiteStatement;

/**
 * MaterialSorten Aus der Datenbank erfragen eine Liste mit Namen, den Name nach Id oder die Id nach Namen.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialSorte extends Material {
    private int materialSorteId;
    private String materialSorteName = "";
    private String[] materialSorteNameListe = new String[0];
    SQLiteStatement sqlLite;

    public MaterialSorte(SQLiteConnect sqlConnection) throws IllegalArgumentException, SQLiteException {
        super(sqlConnection);
        sqlLite = new SQLiteStatement(sqlConnection);
    }

    public int getMaterialSorteId(String materialSorteName) throws IllegalArgumentException, SQLiteException {
        setMaterialSorteName(materialSorteName);
        materialSorteNameToId();

        return materialSorteId;
    }

    public void setMaterialSorteId(int materialSorteId) {
        this.materialSorteId = materialSorteId;
    }

    public String getMaterialSorteName(int materialSorteId) throws SQLiteException {
        setMaterialSorteId(materialSorteId);
        materialSortenIdToName();

        return materialSorteName;
    }

    public void setMaterialSorteName(String materialSorteName) {
        this.materialSorteName = materialSorteName;
    }

    public String[] getMaterialSorteNameListeBla() {
        return materialSorteNameListe;
    }

    public void materialSortenIdToName() throws SQLiteException {
        try {
            sqlLite.setQuery("SELECT MaterialSorte FROM MaterialSorten Where id=" + materialSorteId);
            sqlLite.statmentVorbereitenUndStarten(sqlLite.getQuery());

            if (sqlLite.resultOhneErgebniss(sqlLite.getResult())) {
                sqlLite.closeStatmentAndResult();
                materialSorteName = "N/A";
            } else {

                materialSorteName = sqlLite.getResult().getString(1);
                sqlLite.closeStatmentAndResult();
            }
        } catch (SQLException sqlException) {
            throw new SQLiteException(sqlException.getLocalizedMessage());
        } finally {
            try {
                sqlLite.closeStatmentAndResult();
            } catch (SQLiteException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public void materialSorteNameToId() throws SQLiteException, IllegalArgumentException {
        try {
            if (materialSorteName == null) {
                materialSorteId = 0;
            } else {
                sqlLite.setQuery("SELECT id FROM MaterialSorten Where MaterialSorte='" + materialSorteName + "'");
                sqlLite.statmentVorbereitenUndStarten(sqlLite.getQuery());

                if (sqlLite.resultOhneErgebniss(sqlLite.getResult())) {
                    sqlLite.closeStatmentAndResult();
                    materialSorteId = 0;
                } else {
                    materialSorteId = sqlLite.getResult().getInt(1);

                    sqlLite.closeStatmentAndResult();
                }
            }
        } catch (SQLException sqlException) {

            throw new SQLiteException(sqlException.getLocalizedMessage());

        } finally {
            try {
                sqlLite.closeStatmentAndResult();
            } catch (SQLiteException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public String[] materialSorteNamensListe() throws SQLiteException {
        try {
            sqlLite.setQuery("SELECT MaterialSorte FROM MaterialSorten");
            sqlLite.statmentVorbereitenUndStarten(sqlLite.getQuery());

            int anzahlDerDatensatze = 0;
            while (sqlLite.getResult().next()) {
                anzahlDerDatensatze++;
            }

            materialSorteNameListe = new String[anzahlDerDatensatze];

            sqlLite.statmentExecute(sqlLite.getQuery()); // nötig um den Datensatz zurück zu setzten. SQLite bietet
            // hier keine
            // bessere Möglichkeit.
            int count = 0;
            while (sqlLite.getResult().next()) {
                materialSorteNameListe[count] = sqlLite.getResult().getString(1);
                count++;
            }

            sqlLite.closeStatmentAndResult();
            return materialSorteNameListe;

        } catch (SQLException sqlException) {
            throw new SQLiteException(sqlException.getLocalizedMessage());

        } finally {
            try {
                sqlLite.closeStatmentAndResult();
            } catch (SQLiteException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public boolean materialsorteIdVorhanden(int id) throws SQLiteException {
        if (getMaterialSorteName(id) == "N/A") {
            return false;
        } else {
            return true;
        }

    }

}
