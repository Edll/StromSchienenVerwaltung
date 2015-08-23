package de.edlly.gui.materialVerwaltung;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import de.edlly.gui.Formatierung;
import de.edlly.gui.materialVerwaltung.MaterialAnlegen;

/**
 * Erzeugt ein JPanel das die Material Verwaltung beinhaltet.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class TabMaterialVerwaltung {

    private JScrollPane tabellenBereich;
    private JButton makiertesMaterialLoeschen;

    public TabMaterialVerwaltung() throws Exception {

    }

    public JPanel verwaltungsAnzeige() {

	JPanel anzeigeBereich = new JPanel();
	anzeigeBereich.setLayout(null);

	try {

	    anzeigeBereich.add(headerMaterialDatenbank());
	    anzeigeBereich.add(bereichMaterialDatenbank());
	    MaterialAnlegen neuesMaterialAnlegen = new MaterialAnlegen();
	    anzeigeBereich.add(neuesMaterialAnlegen.materialEingabeBereich(anzeigeBereich));
	    anzeigeBereich.add(auswahlLoeschenBereich());

	} catch (Exception e) {
	    throw e;
	}

	return anzeigeBereich;
    }

    public JLabel headerMaterialDatenbank() {

	JLabel header = new JLabel("Material Datenbank");
	header.setFont(Formatierung.headerFont());
	header.setBounds(Formatierung.HEADER_POSITION_X, Formatierung.HEADER_POSITION_Y, 162, 20);

	return header;
    }

    public JScrollPane bereichMaterialDatenbank() {

	tabellenBereich = new JScrollPane();
	tabellenBereich.setBounds(10, 39, 738, 300);
	tabellenBereich.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	tabellenBereich.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	MaterialTabelle materialTabelle = new MaterialTabelle();
	tabellenBereich.setViewportView(materialTabelle.AusgabeTable());

	return tabellenBereich;
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
