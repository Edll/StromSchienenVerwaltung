package de.edlly.gui.materialVerwaltung;

import java.sql.SQLException;

import javax.swing.*;

import de.edlly.gui.Formatierung;
import de.edlly.gui.materialVerwaltung.AusgabeMaterialAnlegen;

/**
 * Erzeugt ein JPanel das die Material Verwaltung beinhaltet.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class TabMaterialVerwaltung {

    private JButton makiertesMaterialLoeschen;
    protected AusgabeMaterialTabelle materialTabelle;

    public JPanel materialVerwaltungsPanel() {

	JPanel materialVerwaltung = new JPanel();
	materialVerwaltung.setLayout(null);

	materialTabelleeAnzeigen(materialVerwaltung);
	neuesMaterialAnlegen(materialVerwaltung);
	auswahlLoeschenBereich(materialVerwaltung);

	return materialVerwaltung;
    }

    public void materialTabelleeAnzeigen(JPanel materialVerwaltung) {
	materialTabelle = new AusgabeMaterialTabelle();
	try {
	    materialVerwaltung.add(materialTabelle.materialTabellePanel(10, 10));
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    public void neuesMaterialAnlegen(JPanel materialVerwaltung) {

	AusgabeMaterialAnlegen neuesMaterialAnlegen = new AusgabeMaterialAnlegen(materialTabelle);
	try {
	    materialVerwaltung.add(neuesMaterialAnlegen.materialEingabeBereich(10, 420));
	} catch (SQLException e) {
	    e.printStackTrace();
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
