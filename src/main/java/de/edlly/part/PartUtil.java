package de.edlly.part;

import java.text.SimpleDateFormat;
import java.util.regex.*;

public abstract class PartUtil extends Part {

    public PartUtil() throws PartException {

    }

    /**
     * Prüft einen Werkstück Namen ob dieser wen Vorgaben entspricht.
     * 
     * @param Name
     *            Enthält den zu prüfenden String
     */
    public static void checkPartName(String Name) throws PartException {

	Pattern p = Pattern.compile("\\w*");
	Matcher m = p.matcher(Name);
	if (1 > Name.length() | !m.matches() | 30 < Name.length()) {
	    throw new PartException("Der Name darf nur A-Z, a-z und 0-9 enthalten und Maximal 30 Zeichen lang sein.");

	}
    }

    /**
     * Macht aus dem erstelltDatum einen Formatierten Datums String
     */
    public static String erstellDatumFormatieren(long date) throws PartException {

	if (date == 0L) {
	    throw new PartException("Das Datum darf nicht null sein.");
	} else {
	    java.util.Date dateActual = new java.util.Date();
	    dateActual.setTime(date);
	    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    df.format(date);

	    return df.format(date);
	}

    }

}
