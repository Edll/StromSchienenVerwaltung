package de.edlly.test.part;

import org.junit.Test;
import de.edlly.part.PartException;
import de.edlly.part.PartUtil;
import junit.framework.TestCase;

public class PartUtilTest extends TestCase {

    @Override
    public void setUp() {
    }

    @Test
    public void testcheckPartNameLeer() {
        String empty = "";
        try {
            PartUtil.checkPartName(empty);
            fail("Fehler wenn String leer");
        } catch (PartException e) {
            boolean condition = e.getLocalizedMessage()
                    .contains("Der Name darf nur A-Z, a-z und 0-9 enthalten und Maximal 30 Zeichen lang sein.");
            assertTrue("Der fehler String ist nicht korrekt: " + e.getLocalizedMessage(), condition);
        }
    }

    @Test
    public void testcheckPartNameZuLang() {
        String toLong = "abcdefghijklmnopqrstuvwxyzABCDF";
        try {
            PartUtil.checkPartName(toLong);
            fail("Fehler wenn String zu lang");
        } catch (PartException e) {
            boolean condition = e.getLocalizedMessage()
                    .contains("Der Name darf nur A-Z, a-z und 0-9 enthalten und Maximal 30 Zeichen lang sein.");
            assertTrue("Der fehler String ist nicht korrekt: " + e.getLocalizedMessage(), condition);
        }
    }

    @Test
    public void testcheckPartNameZeichenKorrekt() throws PartException {
        String klein = "abcdefghijklmnopqrstuvwxyz";
        String gross = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String zahlen = "1234567890";

        PartUtil.checkPartName(zahlen);
        PartUtil.checkPartName(klein);
        PartUtil.checkPartName(gross);
    }

    @Test
    public void testcheckPartNameSonderzeichenNichtOk() {
        String zeichen = "!\"§$%&/()=?`*'!\"§$%/()/&'";
        try {
            PartUtil.checkPartName(zeichen);
            fail("Fehler wenn zeichennicht erlaubt");
        } catch (PartException e) {
            boolean condition = e.getLocalizedMessage()
                    .contains("Der Name darf nur A-Z, a-z und 0-9 enthalten und Maximal 30 Zeichen lang sein.");
            assertTrue("Der fehler String ist nicht korrekt: " + e.getLocalizedMessage(), condition);
        }
    }

    @Test
    public void testErstellDatumFormatierenNull() {
        try {
            long date = 0;
            PartUtil.erstellDatumFormatieren(date);
            fail("Datum darf nicht Null sein.");
        } catch (PartException e) {
            boolean condition = e.getLocalizedMessage().contains("Das Datum darf nicht null sein.");
            assertTrue("Der fehler String ist nicht korrekt: " + e.getLocalizedMessage(), condition);
        }

    }

    @Test
    public void testErstellDatumFormatieren() throws PartException {
        long date = 1;
        String dateFormatet = PartUtil.erstellDatumFormatieren(date);

        boolean condition = dateFormatet.contains("01-01-1970 01:00");
        assertTrue("Der fehler String ist nicht korrekt: " + dateFormatet, condition);

    }

    @Override
    public void tearDown() {
    }

}
