package de.edlly.werkstueck;

/**
 * Enthält die Konstanten für die Werkstück. Diese Liegen entweder als Konstante 
 * Werte vor oder werden als solche zurück gegeben. 
 * In zweiten Ausbauschritt werden diese Dynamisch aus der Datenbank Ermittelt.
 * 
 * 
 * Attribute:
 * String Material: Das Material aus dem die Konstante gebildet werden soll
 * int LochDurchmesser: Durchmesser des Lochs aus dem die Konstante gebildet werden soll
 * 
 * Methoden:
 * Konstanten() Konstruktor der Klasse
 * MaterialGroese() Gibt den XMax und YMax Wert des Materials zurück in einem Int Array
 * LochKanteAbstand() Gibt den Abstand eines Lochs zur Kante des Werkstücks.
 * 
 * @author Edlly
 * 
 * 
 */


public class Konstanten {
	
	public String Material;
	public int LochDurchmesser;
	

	// Konstante werte auf die als standard Zurückgegriffen wird.
	public static final int MATERIAL_MAX_LAENGE = 4000;
	public static final int BOHRUNG_MIN_ABSTAND = 10;

	
	
	public Konstanten(){
		
	}
	
/**
 * Gibt anhand des eingegebenen Strings die Maximalen werte des Kupfer in einem Array zurück
 * TODO: Dynamische aus Datenbank ziehen.
 * @return result[0] = x result[1] = y
 */
	public int[] MaterialGroese() {		
		int[] result = {0, 0};
		
		switch(this.Material){
		case "20x10":
			result[0] = 20;
			result[1] = MATERIAL_MAX_LAENGE;
			break;
		case "30x10":
			result[0] = 30;
			result[1] = MATERIAL_MAX_LAENGE;
			break;
		case "40x10":
			result[0] = 40;
			result[1] = MATERIAL_MAX_LAENGE;
			break;
		case "50x10":
			result[0] = 50;
			result[1] = MATERIAL_MAX_LAENGE;
			break;
		case "60x10":
			result[0] = 60;
			result[1] = MATERIAL_MAX_LAENGE;
			break;
		case "80x10":
			result[0] = 80;
			result[1] = MATERIAL_MAX_LAENGE;
			break;
		case"100x10":
			result[0] = 100;
			result[1] = MATERIAL_MAX_LAENGE;
			break;
		default:
			throw new IllegalArgumentException( "Das Material ist nicht vorhanden in der Datenbank." );	
		}
		
		
		
		return  result;
	}
	
	
	/**
	 * Abstand des Lochs zur Kante 
	 * TODO: Dynamisch aus der Datenbank ermitteln
	 * @return Int Abstand zur Kante
	 */
	public int LochKanteAbstand(){
		int Abstand = 0;

	
		switch(this.LochDurchmesser){
		case 9: 
			 Abstand = 15;
		break;
		case 11:
			Abstand = 15;
		break;
		case 14:	
			Abstand = 15;
		break;
		case 18:
		    Abstand = 20;
		break;
		case 21:
			Abstand = 20;
		break;
		default:
			Abstand = BOHRUNG_MIN_ABSTAND;	
		}
	
	
		return Abstand;
	}
	
	
	/**
	 * Abstand für Winkel von Kante, Loch, Andere Winkel
	 * 
	 * 
	 */
	public static final int BIEGUNG_ABSTAND_KANTE = 53;
	public static final int BIEGUNG_ABSTAND_WINKEL = 75;
	public static final int BIEGUNG_ABSTAND_BOHRUNG = 30;
	/**
	 * Minimaler und Maximaler Winkel
	 * 
	 * 
	 */
	public static final int BIEGUNG_WINKEL_MIN = 0;
	public static final int BIEGUNG_WINKEL_MAX = 90;
	

}
