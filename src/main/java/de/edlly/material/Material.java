package de.edlly.material;

/**
 * 
 * Oberklasse für alle Objekte vom Typ Material
 * 
 * @author Edlly java@edlly.de
 *
 */
public class Material {

    public enum datensatzNamen {
	ID, MATERIALSORTE_ID, X, Z, YMAX, SICHTBARKEIT
    }

    public Material() {

    }

    static int getOrdinal(String datensatzNamen) {
	return MaterialDatensatz.datensatzNamen.valueOf(datensatzNamen).ordinal();
    }
}
