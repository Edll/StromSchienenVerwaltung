package de.edlly.part;

import java.sql.SQLException;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.db.SQLiteStatement;

public class Bend<T extends Number & Comparable<T>> implements IBend<T>, Comparable<IBend<?>> {
    T angel;
    T y;
    T yMax;
    int id;
    IPart part;

    /**
     * @param yMax
     *            Setz den Maximalen wert auf der Y Achse
     */

    public Bend(T yMax) {
	setYMax(yMax);
    }

    /**
     * 
     * @param yMax
     *            Setz den Maximalen wert auf der Y Achse
     * @param angel
     *            Setz den Winkel
     * @param y
     *            setz die Position auf der Y Achse
     * @throws PartException
     */

    public Bend(T yMax, T angel, T y) throws PartException {
	setYMax(yMax);
	setAngelAndY(angel, y);
    }

    @Override
    public void setAngel(T angel) throws PartException {
	checkAngel(angel);
	this.angel = angel;
    }

    @Override
    public void setY(T y) throws PartException {
	checkY(y);
	this.y = y;
    }

    @Override
    public void setYMax(T yMax) {
	this.yMax = yMax;
    }

    @Override
    public T getAngel() {
	return angel;
    }

    @Override
    public T getY() {
	return y;
    }

    @Override
    public T getYMax() {
	return yMax;
    }

    @Override
    public int getId() {
	return id;
    }

    @Override
    public void setId(int id) throws PartException {
	this.id = id;
    }

    @Override
    public void setAngelAndY(T angel, T y) throws PartException {
	setAngel(angel);
	setY(y);
    }
    
    /**
     * Suppress Warning ist für für den cast von bend auf T. Insoweit ohne Problem das Numbers Objekt die verwendet
     * werden können Comparable sind.
     */
    @SuppressWarnings("unchecked")
    @Override
    public int compareTo(IBend<?> bend) {
	return this.y.compareTo((T) bend.getY());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void getDB(int id, SQLiteStatement sql) throws PartException, SQLiteException {
	try {
	    setId(id);
	    SQLiteConnect.isClosedOrNull(sql);

	    sql.setQuery("SELECT partId, angel, y FROM Bend WHERE id = " + getId());
	    sql.statmentVorbereitenUndStarten(sql.getQuery());

	    if (sql.resultOhneErgebniss(sql.getResult())) {
		throw new PartException("Abrufen des Bends aus der Datenbank fehlgeschlagen.");
	    } else {
		part = new Part();
		part.setId(sql.getResult().getInt(1));

		/**
		 * Hier wird geprüft welcher Generic Cast auf die Klasse angewendet worden ist. Das erfolgt mit der im
		 * Konstruktor übergebenen Variable yMax da diese zur Laufzeit den konkreten Klassentyp hat. Daraufhin
		 * wird je nach Unterart von Number der entsprechende Aufruf aus der Datenbank gemacht. Sollte ein nicht
		 * vorhandener Typ ausgelesen werden wird ein RuntimeExcption ausgelöst. Deswegen auch
		 * der @SuppressWarnings("unchecked") weil ein Cast auf T gemacht wird. Dieser ist jedoch geprüft und
		 * kann nicht zu einem CastException führen.
		 * 
		 * Dies ist nötig da aus der Datenbank immer ein konkreter Typ ausgelesen werden muss.
		 */
		if (sql.getResult().getObject(2) instanceof Number) {

		    if (yMax instanceof Double) {
			setAngel((T) sql.getResult().getObject(2));

		    } else if (yMax instanceof Float) {
			Float angel = sql.getResult().getFloat(2);
			setAngel((T) angel);

		    } else if (yMax instanceof Integer) {
			Integer angel = sql.getResult().getInt(2);
			setAngel((T) angel);

		    } else if (yMax instanceof Long) {
			Long angel = sql.getResult().getLong(2);
			setAngel((T) angel);

		    } else {
			throw new RuntimeException("Nicht vorhandenes Zahlenformat angefordert.");
		    }

		}

		if (sql.getResult().getObject(3) instanceof Number) {

		    if (yMax instanceof Double) {
			setY((T) sql.getResult().getObject(3));

		    } else if (yMax instanceof Float) {
			Float y = sql.getResult().getFloat(3);
			setY((T) y);

		    } else if (yMax instanceof Integer) {
			Integer y = sql.getResult().getInt(2);
			setY((T) y);

		    } else if (yMax instanceof Long) {
			Long y = sql.getResult().getLong(2);
			setY((T) y);

		    } else {
			throw new RuntimeException("Nicht vorhandenes Zahlenformat angefordert.");
		    }
		}
	    }
	    sql.closeStatmentAndResult();
	} catch (SQLException e) {
	    throw new SQLiteException(e.getLocalizedMessage());
	} finally {
	    sql.closeStatment();

	}
    }

    private void checkAngel(T angel) throws PartException {
	if (angel.doubleValue() > IBend.ANGEL_MAX.doubleValue()) {

	    throw new PartException("Der angegebene Winkel ist zu groß. Maximal:" + IBend.ANGEL_MAX);

	} else if (angel.doubleValue() < IBend.ANGEL_MIN.doubleValue()) {

	    throw new PartException("Der angegebene Winkel ist zu klein. Minimal:" + IBend.ANGEL_MAX);
	}
    }

    private void checkY(T y) throws PartException {
	if (y.doubleValue() > yMax.doubleValue()) {

	    throw new PartException("Der Angegebene Y Wert ist zu größ. Maximal:" + yMax);

	} else if (y.doubleValue() < IBend.Y_MIN.doubleValue()) {

	    throw new PartException("Der Angegebene Y Wert ist zu klein. Minimal:" + IBend.Y_MIN);

	} else if (y.doubleValue() < IBend.ABSTAND_RAND.doubleValue()) {

	    throw new PartException(
		    "Der Angegebene Y Wert ist zu klein. Der mindest Abstand zum Rand ist:" + IBend.ABSTAND_RAND);

	} else if (y.doubleValue() > yMax.doubleValue() - IBend.ABSTAND_RAND.doubleValue() + 1) {

	    throw new PartException("Der Angegebene Y Wert ist zu groß. Der mindest Abstand zum Rand ist: "
		    + (yMax.doubleValue() - IBend.ABSTAND_RAND.doubleValue()));

	}
    }

}
