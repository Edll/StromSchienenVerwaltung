package de.edlly.gui.materialVerwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.edlly.gui.Formatierung;

public class AusgabeMaterialLoeschen {
    private JButton makiertesMaterialLoeschen;
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

	makiertesMaterialLoeschen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {

		try {
		    materialTabelle.getSelectedMaterialId();
		} catch (IllegalArgumentException e1) {

		}
		
	    }
	});

	return materialLoeschenAnzeigeBereich;
    }
}
