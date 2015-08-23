package de.edlly.gui.materialVerwaltung;

import javax.swing.JButton;
import javax.swing.JPanel;


import de.edlly.gui.Formatierung;
import de.edlly.gui.materialVerwaltung.MaterialAnlegen;

/**
 * Erzeugt ein JPanel das die Material Verwaltung beinhaltet.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class TabMaterialVerwaltung {
    

    private JButton makiertesMaterialLoeschen;

    public TabMaterialVerwaltung() throws Exception {

    }

    public JPanel verwaltungsAnzeige() {

	JPanel anzeigeBereich = new JPanel();
	anzeigeBereich.setLayout(null);

	try {

	    MaterialTabelle materialTabelle = new MaterialTabelle();
	    anzeigeBereich.add(materialTabelle.tabellenAnzeigeBereich(10, 10));
	    
	    MaterialAnlegen neuesMaterialAnlegen = new MaterialAnlegen();
	    anzeigeBereich.add(neuesMaterialAnlegen.materialEingabeBereich(10, 420));
	    
	    anzeigeBereich.add(auswahlLoeschenBereich());

	} catch (Exception e) {
	    throw e;
	}

	return anzeigeBereich;
    }

    public JPanel auswahlLoeschenBereich() {

	JPanel materialLoeschenAnzeigeBereich = new JPanel();
	materialLoeschenAnzeigeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
	materialLoeschenAnzeigeBereich.setLayout(null);
	materialLoeschenAnzeigeBereich.setBounds(550, 420, 170, 100);

	makiertesMaterialLoeschen = new JButton("Makierte Löschen");
	makiertesMaterialLoeschen.setFont(Formatierung.buttonFont());
	makiertesMaterialLoeschen.setBounds(10, 10, 150, 23);
	materialLoeschenAnzeigeBereich.add(makiertesMaterialLoeschen);

	return materialLoeschenAnzeigeBereich;
    }

}
