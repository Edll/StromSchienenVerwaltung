package de.edlly.gui.materialVerwaltung;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.edlly.gui.Formatierung;

public class AusgabeMaterialLoeschen {
    private  JButton makiertesMaterialLoeschen;
     @SuppressWarnings("unused")
    private AusgabeMaterialTabelle materialTabelle;

    public AusgabeMaterialLoeschen(AusgabeMaterialTabelle materialTabelle) {
	this.materialTabelle = materialTabelle;

    }

    public JPanel auswahlLoeschenPanel(int PositionX, int PositionY) {

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