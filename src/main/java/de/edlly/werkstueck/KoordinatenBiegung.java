package de.edlly.werkstueck;

/** KoordinatenWinkel
 * Das Werkstück ist in XY Koordinaten aufgeteilt. Dabei ist X ist die feste Material Seite. (20x10, 30x10...)
 * 
 * Attribute:
 * int y: Lage der Biegung auf der Y Achse
 * int Winkel: Winkel der zu Biegen ist bei 0 wird nicht geprüft.
 * int BiegungsId: Die ID der zu Prüfenden Biegung in der DB. Bei 0 wird nicht geprüft.
 * 
 * Methoden:
 * Check() Prüft ob die angegeben  y werte noch auf dem Werkstück liegen und der Winkel in den Vorgaben liegt.
 * CheckBiegung() Prüft ob die Lage der Biegung ob diese innerhalb der vorgaben liegt. 
 * CheckKollision() Prüft ob der Winkel mit einem Loch oder einer Biegung im Datensatz Kollidiert.
 * 
 * 
 * 
 * @author Edlly
 *
 */


public class KoordinatenBiegung {
 public int y;
 public int Winkel;
	 int BiegungsId;
	 
	// Konstruktor
	public KoordinatenBiegung(){
		
	}
	
	public Boolean Check(){
		
		// zum Prüfen der Konstanten.
		int KonstatenCheck = 0;
		
		if(KonstatenCheck == Konstanten.MATERIAL_MAX_LAENGE){		
		throw new IllegalArgumentException("MATERIAL_MAX_LAENGE ist 0, Pr�fung nicht m�glich. Bitte die Einstellungen �berpr�fen." );
		}


		// X Prüfen der Randlage des Loch
		if(this.y < Konstanten.BIEGUNG_ABSTAND_KANTE  | this.y > Konstanten.MATERIAL_MAX_LAENGE){
			throw new IllegalArgumentException("Die Biegung ist zu nah an der Kante: " + (Konstanten.BIEGUNG_ABSTAND_KANTE) );	
		}
		
		
		// Prüft den Winkel
		if(this.Winkel < Konstanten.BIEGUNG_WINKEL_MIN | this.Winkel > Konstanten.BIEGUNG_WINKEL_MAX){
			throw new IllegalArgumentException("Der eingebene Winkel ist zu gro� oder klein er muss zwischen: " + Konstanten.BIEGUNG_WINKEL_MIN + "-" + Konstanten.BIEGUNG_WINKEL_MAX + " liegen.");	
		}
		
		
		return true;
		
	}
	
}
