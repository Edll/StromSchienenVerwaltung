package de.edlly.part;

public class Bend<T extends Number> implements IBend<T> {
    T angel;
    T y;
    T yMax;

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
	create(angel, y);
    }

    public void setAngel(T angel) throws PartException {
	checkAngel(angel);
	this.angel = angel;
    }

    public void setY(T y) throws PartException {
	checkY(y);
	this.y = y;
    }

    public void setYMax(T yMax) {
	this.yMax = yMax;
    }

    public T getAngel() {
	return angel;
    }

    public T getY() {
	return y;
    }

    public T getYMax() {
	return yMax;
    }

    public void create(T angel, T y) throws PartException {
	setAngel(angel);
	setY(y);
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

	} else if (y.doubleValue() > yMax.doubleValue() - IBend.ABSTAND_RAND.doubleValue()) {

	    throw new PartException("Der Angegebene Y Wert ist zu groß. Der mindest Abstand zum Rand ist:"
		    + (yMax.doubleValue() - IBend.ABSTAND_RAND.doubleValue()));

	}
    }
}
