package de.edlly.gui.materialVerwaltung;

import java.sql.SQLException;

import javax.swing.*;
import de.edlly.gui.materialVerwaltung.AusgabeMaterialAnlegen;

/**
 * Erzeugt ein JPanel das die Material Verwaltung beinhaltet.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class TabMaterialVerwaltung {

    private AusgabeMaterialTabelle materialTabelle;
    private JPanel materialVerwaltung;

    public JPanel materialVerwaltungsPanel() throws SQLException {

	materialVerwaltung = new JPanel();
	materialVerwaltung.setLayout(null);

	materialTabelleAnzeigen();
	neuesMaterialAnlegen();
	auswahlLoeschenBereich();
	return materialVerwaltung;
    }

    public void materialTabelleAnzeigen() throws SQLException {
	
	materialTabelle = new AusgabeMaterialTabelle();
	
	materialVerwaltung.add(materialTabelle.materialTabellePanel(10, 10));

    }

    public void neuesMaterialAnlegen() throws SQLException {

	AusgabeMaterialAnlegen neuesMaterialAnlegen = new AusgabeMaterialAnlegen(materialTabelle);
	materialVerwaltung.add(neuesMaterialAnlegen.materialEingabeBereich(10, 420));
    }

    public void auswahlLoeschenBereich() {
	AusgabeMaterialLoeschen auswahlLoeschen = new AusgabeMaterialLoeschen(materialTabelle);
	materialVerwaltung.add(auswahlLoeschen.auswahlLoeschenPanel(550, 420));

    }

}
