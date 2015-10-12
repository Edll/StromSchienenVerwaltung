package de.edlly.material;

import de.edlly.db.SQLiteConnect;

/**
 * 
 * Oberklasse für alle Objekte vom Typ Material
 * 
 * @author Edlly java@edlly.de
 *
 */
public class Material {
    private SQLiteConnect sqlConnection;

    public static final int MAX_X = 200;
    public static final int MAX_Z = 30;
    public static final int MAX_Y = 12000;

    public static final int MIN_X = 1;
    public static final int MIN_Z = 1;
    public static final int MIN_Y = 1;

    public SQLiteConnect getSqlConnection() {
        return sqlConnection;
    }

    public Material(SQLiteConnect sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public enum datensatzNamen {
        ID, MATERIALSORTE_ID, X, Z, YMAX, SICHTBARKEIT
    }

    static int getOrdinal(String datensatzNamen) {
        return MaterialDatensatz.datensatzNamen.valueOf(datensatzNamen).ordinal();
    }

}
