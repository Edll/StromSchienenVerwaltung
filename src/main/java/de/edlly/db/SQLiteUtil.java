package de.edlly.db;

/**
 * @author Edlly java@edlly.de
 *
 */
public class SQLiteUtil {

    /**
     * Transformiert Boolean in Integer.
     * 
     * Ist nötig da SQLite Boolesche werte nicht als true/false kennt, sondern nur als 1/0 wert kennt.
     * 
     * @param bool
     * @return integer
     */

    public static int booleanToInteger(boolean bool) {
	int integer = 0;

	if (bool) {
	    integer = 1;
	}

	return integer;
    }

    /**
     * Transformiert Integer in Boolean.
     * 
     * Ist nötig da SQLite Boolesche werte nicht als true/false kennt, sondern nur als 1/0 wert kennt.
     * 
     * @param integer
     * @return boolean
     */

    public static boolean integerToBoolean(int integer) {
	boolean bool = false;

	if (integer == 1) {
	    bool = true;
	}

	return bool;
    }
}
