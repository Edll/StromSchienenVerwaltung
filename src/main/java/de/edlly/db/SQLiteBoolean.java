package de.edlly.db;

/**
 * Transformiert Boolean in Integer und zurück.
 * 
 * Ist Nötig da SQLite Boolean nur als 1/0 wert kennt. Java aber mit true/false Zuständen arbeitet.
 * 
 * TODO: Raus finden wie man Methoden über Laden kann!
 * 
 * @author Edlly java@edlly.de
 *
 */
public class SQLiteBoolean {

    public static int booleanToInteger(boolean bool) {
	int integer = 0;

	if (bool) {
	    integer = 1;
	}

	return integer;
    }

    public static boolean integerToBoolean(int integer) {
	boolean bool = false;

	if (integer == 1) {
	    bool = true;
	}

	return bool;
    }
}
