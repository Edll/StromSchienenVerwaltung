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

    public JPanel materialVerwaltungsPanel() {

	JPanel materialVerwaltung = new JPanel();
	materialVerwaltung.setLayout(null);

	materialTabelleeAnzeigen(materialVerwaltung);
	neuesMaterialAnlegen(materialVerwaltung);
	auswahlLoeschenBereich(materialVerwaltung);

	return materialVerwaltung;
    }

    public void materialTabelleeAnzeigen(JPanel materialVerwaltung) {

	try {
	    MaterialTabelle materialTabelle = new MaterialTabelle();
	    materialVerwaltung.add(materialTabelle.materialTabellePanel(10, 10));
	} catch (Exception e) {
	    throw e;
	}

    }

    public void neuesMaterialAnlegen(JPanel materialVerwaltung) {

	try {
	    MaterialAnlegen neuesMaterialAnlegen = new MaterialAnlegen();
	    materialVerwaltung.add(neuesMaterialAnlegen.materialEingabeBereich(10, 420));
	} catch (Exception e) {
	    throw e;
	}
    }

    public void auswahlLoeschenBereich(JPanel materialVerwaltung) {

	JPanel materialLoeschenAnzeigeBereich = new JPanel();
	materialLoeschenAnzeigeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
	materialLoeschenAnzeigeBereich.setLayout(null);
	materialLoeschenAnzeigeBereich.setBounds(550, 420, 170, 100);

	makiertesMaterialLoeschen = new JButton("Makierte Löschen");
	makiertesMaterialLoeschen.setFont(Formatierung.buttonFont());
	makiertesMaterialLoeschen.setBounds(10, 10, 150, 23);
	materialLoeschenAnzeigeBereich.add(makiertesMaterialLoeschen);

	materialVerwaltung.add(materialLoeschenAnzeigeBereich);
    }

}
