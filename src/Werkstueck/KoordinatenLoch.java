package Werkstueck;

import Werkstueck.Konstanten;
/** KoordinatenLoch
 * Das Werkst�ck ist in XY Koordinaten aufgeteilt. Dabei ist X ist die feste Material Seite. (20x10, 30x10...)
 * 
 * Attribute:
 * int x, y: Die �bergebenen Attribute x,y sind dabei die zu �bergebenen Werte des Aktuellen Lochs
 * int xMax: Mit xMax wird die feste Material Seite �bergeben. (20, 30, 40.....). Ist diese 0 wird x nicht gepr�ft.
 * int Durchmesser: Durchmesser ist der Durchmesser des Aktuellen Lochs.
 * int BohrId: Die ID der zu pr�fenden Bohrung. Ist diese 0 wird nicht gepr�ft.
 * 
 * Methoden:
 * CheckXY() Pr�ft ob die angegeben x, y werte noch auf dem Werkst�ck liegen.
 * CheckLoch() Pr�ft ob das Loch Innerhalb der Begrenzungen liegt. 
 * CheckKollision() Pr�ft ob die Bohrung mit einer Biegung im Datensatz Kollidiert.
 * 
 * 
 * HilfsFunktionen
 * CheckKoord() Vergleicht den eingebenden wert mit einander
 * 
 * @author Edlly
 *
 */
public class KoordinatenLoch {
	public int x, y;
	public int yMax;
	public int xMax;
	public int Durchmesser;
	public int BohrId;
	
	 public KoordinatenLoch(){}

	
	/**
	 * Pr�ft ob die Werte noch auf dem Werkst�ck liegen
	 * Ist das Klassen Attribut xMax 0 oder die Konstante MATERIAL_MAX_LAENGE 0 wird nicht gepr�ft und es gibt einen throw.
	 * @return true alle Werte okay.
	 */
	
	public Boolean CheckXY(){
		// zum pr�fen der Konstanten.
		int KonstatenCheck = 0;
		
		// Pr�fen ob die vorgaben vorhanden sind um die Fehler zu erleichtern.
		if(this.xMax == 0){		
		throw new IllegalArgumentException("yMax ist 0 pr�fung nicht m�glich." );
		}
		
		if(KonstatenCheck == yMax){		
		throw new IllegalArgumentException("yMax ist 0, pr�fung nicht m�glich. Bitte die Einstellungen �berpr�fen." );
		}
		
		// Pr�fen der X Koordinate noch auf dem Werkst�ck
		Boolean CheckX = CheckKoord(x, this.xMax);

		if(CheckX == false){
			throw new IllegalArgumentException( "X ist zu gr��e. Die Maximale gr��e: " +  this.xMax);
		}
		
		// Pr�fen der Y Koordinate noch auf dem Werkst�ck
		Boolean CheckY = CheckKoord(y, this.yMax);
		if(CheckY == false){
			throw new IllegalArgumentException( "Y ist zu gr��e. Die Maximale gr��e: " + this.yMax);
		}
		
	 
		
		return true;		
	}

	/** CheckBohrung()
	 * Pr�ft ob die Bohrung noch auf dem Werkst�ck liegt.
	 * @return true alle werte Okay
	 */
	public Boolean CheckLoch(){
		// zum pr�fen der Konstanten.
		int KonstatenCheck = 0;
		
		if(this.Durchmesser == 0){		
		throw new IllegalArgumentException("Durchmesser ist 0 pr�fung nicht m�glich." );
		} 
		
		// Pr�fen ob die vorgaben vorhanden sind um die Fehler zu erleichtern.
		if(this.xMax == 0){		
		throw new IllegalArgumentException("xMax ist 0 pr�fung nicht m�glich." );
		}
		
		if(KonstatenCheck == this.yMax){		
		throw new IllegalArgumentException("yMax ist 0, Pr�fung nicht m�glich. Bitte die Einstellungen �berpr�fen." );
		}
		
		
		// Abstand des Lochs aus der Konstanten klasse
		Konstanten LochAbstand = new Konstanten();
		LochAbstand.LochDurchmesser = this.Durchmesser;
		int MinLochAbstand = LochAbstand.LochKanteAbstand();
		
		// X Pr�fen der Randlage des Loch
		if((this.x+MinLochAbstand) > this.xMax | (MinLochAbstand) > this.x){
			throw new IllegalArgumentException("Das Loch ist zu nah am Rand auf der X Achse. Minimaler Abstand ist: " + (MinLochAbstand) );	
		}
		
		// X Pr�fen der Randlage des Loch
		if((this.y+MinLochAbstand) > this.yMax | (MinLochAbstand) > this.y){
			throw new IllegalArgumentException("Das Loch ist zu nah am Rand auf der Y Achse. Minimaler Abstand ist: " + (MinLochAbstand) );	
		}
		
		return true;
		
	}
	
	
	



	/** Vergleicht die beiden Koordinaten auf die L�nge.
	 * @param Koordinate auf dem Werkst�ck
	 * @param KoordinateMax Maximale Gr��e des Werkst�cks
	 * @return Koordinate
	 */
	private Boolean CheckKoord(int Koordinate, int KoordinateMax) {
		if(KoordinateMax <  Koordinate ){
			return false;		
		}

		return true;
	}



}
