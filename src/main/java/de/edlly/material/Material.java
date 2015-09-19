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
    
    public static final int MAXIMALER_X_WERT = 200;
    public static final int MAXIMALER_Z_WERT = 30;
    public static final int MAXIMALER_Y_WERT = 12000;
    
    public static final int MINIMALER_X_WERT = 1;
    public static final int MINIMALER_Z_WERT = 1;
    public static final int MINIMALER_Y_WERT = 1;

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
