package Werkstueck;

import Werkstueck.Konstanten;
/** KoordinatenLoch
 * Das Werkstück ist in XY Koordinaten aufgeteilt. Dabei ist X ist die feste Material Seite. (20x10, 30x10...)
 * 
 * Attribute:
 * int x, y: Die übergebenen Attribute x,y sind dabei die zu übergebenen Werte des Aktuellen Lochs
 * int xMax: Mit xMax wird die feste Material Seite übergeben. (20, 30, 40.....). Ist diese 0 wird x nicht geprüft.
 * int Durchmesser: Durchmesser ist der Durchmesser des Aktuellen Lochs.
 * int BohrId: Die ID der zu prüfenden Bohrung. Ist diese 0 wird nicht geprüft.
 * 
 * Methoden:
 * CheckXY() Prüft ob die angegeben x, y werte noch auf dem Werkstück liegen.
 * CheckLoch() Prüft ob das Loch Innerhalb der Begrenzungen liegt. 
 * CheckKollision() Prüft ob die Bohrung mit einer Biegung im Datensatz Kollidiert.
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
	 * Prüft ob die Werte noch auf dem Werkstück liegen
	 * Ist das Klassen Attribut xMax 0 oder die Konstante MATERIAL_MAX_LAENGE 0 wird nicht geprüft und es gibt einen throw.
	 * @return true alle Werte okay.
	 */
	
	public Boolean CheckXY(){
		// zum prüfen der Konstanten.
		int KonstatenCheck = 0;
		
		// Prüfen ob die vorgaben vorhanden sind um die Fehler zu erleichtern.
		if(this.xMax == 0){		
		throw new IllegalArgumentException("yMax ist 0 prüfung nicht möglich." );
		}
		
		if(KonstatenCheck == yMax){		
		throw new IllegalArgumentException("yMax ist 0, prüfung nicht möglich. Bitte die Einstellungen Überprüfen." );
		}
		
		// Prüfen der X Koordinate noch auf dem Werkstück
		Boolean CheckX = CheckKoord(x, this.xMax);

		if(CheckX == false){
			throw new IllegalArgumentException( "X ist zu größe. Die Maximale größe: " +  this.xMax);
		}
		
		// Prüfen der Y Koordinate noch auf dem Werkstück
		Boolean CheckY = CheckKoord(y, this.yMax);
		if(CheckY == false){
			throw new IllegalArgumentException( "Y ist zu größe. Die Maximale größe: " + this.yMax);
		}
		
	 
		
		return true;		
	}

	/** CheckBohrung()
	 * Prüft ob die Bohrung noch auf dem Werkstück liegt.
	 * @return true alle werte Okay
	 */
	public Boolean CheckLoch(){
		// zum prüfen der Konstanten.
		int KonstatenCheck = 0;
		
		if(this.Durchmesser == 0){		
		throw new IllegalArgumentException("Durchmesser ist 0 prüfung nicht möglich." );
		} 
		
		// Prüfen ob die vorgaben vorhanden sind um die Fehler zu erleichtern.
		if(this.xMax == 0){		
		throw new IllegalArgumentException("xMax ist 0 prüfung nicht möglich." );
		}
		
		if(KonstatenCheck == this.yMax){		
		throw new IllegalArgumentException("yMax ist 0, Prüfung nicht möglich. Bitte die Einstellungen Überprüfen." );
		}
		
		
		// Abstand des Lochs aus der Konstanten klasse
		Konstanten LochAbstand = new Konstanten();
		LochAbstand.LochDurchmesser = this.Durchmesser;
		int MinLochAbstand = LochAbstand.LochKanteAbstand();
		
		// X Prüfen der Randlage des Loch
		if((this.x+MinLochAbstand) > this.xMax | (MinLochAbstand) > this.x){
			throw new IllegalArgumentException("Das Loch ist zu nah am Rand auf der X Achse. Minimaler Abstand ist: " + (MinLochAbstand) );	
		}
		
		// X Prüfen der Randlage des Loch
		if((this.y+MinLochAbstand) > this.yMax | (MinLochAbstand) > this.y){
			throw new IllegalArgumentException("Das Loch ist zu nah am Rand auf der Y Achse. Minimaler Abstand ist: " + (MinLochAbstand) );	
		}
		
		return true;
		
	}
	
	
	



	/** Vergleicht die beiden Koordinaten auf die Länge.
	 * @param Koordinate auf dem Werkstück
	 * @param KoordinateMax Maximale Größe des Werkstücks
	 * @return Koordinate
	 */
	private Boolean CheckKoord(int Koordinate, int KoordinateMax) {
		if(KoordinateMax <  Koordinate ){
			return false;		
		}

		return true;
	}



}
