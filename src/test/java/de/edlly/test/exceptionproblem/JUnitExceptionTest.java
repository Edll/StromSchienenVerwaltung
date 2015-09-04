package de.edlly.test.exceptionproblem;

import java.sql.Connection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.edlly.db.SQLiteConnect;
import junit.framework.TestCase;

/**
 * Test für die JUnit Exception Behandlung
 * 
 * @author Edlly java@edlly.de
 *
 */

public class JUnitExceptionTest extends TestCase {

    Connection sqlConnection;
    TestKlassefuerDenExceptionTest testObjektDasdenFehlerAusloest;

    @Override
    public void setUp() {
	sqlConnection = SQLiteConnect.dbConnection();

    }

    /*
     * Dies wird nicht als Fehler erkannt wenn die Exception nicht auftritt. Der test ist bestanden wenn kein Fehler
     * auftritt. Ich habe das jetzt so verstanden das der Fehler auftreten muss um den Test zu bestehen.
     */

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testExceptionMitRule() {
	testObjektDasdenFehlerAusloest = new TestKlassefuerDenExceptionTest(sqlConnection);

	thrown.expect(IllegalArgumentException.class);
	thrown.expectMessage("Diese Fehler soll auftreten und als richtig erkannt werden!");

	testObjektDasdenFehlerAusloest.methodeMitFehler(); // wirft eine: IllegalArgumentException
    }

    /*
     * Dies funktioniert der Test wird bestanden wenn der Fehler kommt.
     */

    @Test
    public void testExceptionMitFail() {
	testObjektDasdenFehlerAusloest = new TestKlassefuerDenExceptionTest(sqlConnection);
	try {
	    testObjektDasdenFehlerAusloest.methodeMitFehler(); // wirft eine: IllegalArgumentException

	    fail("Soll einen IllegalArgumentException ergeben. Wird mit fail erkannt");

	} catch (IllegalArgumentException expected) {
	    assertEquals(IllegalArgumentException.class, expected.getClass());
	}
    }

    @Override
    public void tearDown() {
	SQLiteConnect.closeSqlConnection(sqlConnection);
    }

}
