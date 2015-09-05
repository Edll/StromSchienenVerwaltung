package de.edlly.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Anzeige der Werkstück Verwaltung
 * 
 * @author Edlly java@edlly.de
 *
 */
public class WerkstueckVerwaltung {

    public JPanel werkstueckVerwaltungsPanel() {

	JPanel werkstueckVerwaltung = new JPanel();
	werkstueckVerwaltung.setLayout(null);

	headerWerkstueckVerwaltung(werkstueckVerwaltung);
	tabelleWerkstuecke(werkstueckVerwaltung);

	return werkstueckVerwaltung;
    }

    private void headerWerkstueckVerwaltung(JPanel werkstueckVerwaltung) {
	JLabel headerWerkstueckVerwaltung = new JLabel("Werkst\u00FCck Verwaltung");
	headerWerkstueckVerwaltung.setBounds(Formatierung.HEADER_POSITION_X, Formatierung.HEADER_POSITION_Y, 200, 20);
	headerWerkstueckVerwaltung.setFont(Formatierung.headerFont());
	werkstueckVerwaltung.add(headerWerkstueckVerwaltung);
    }

    private void tabelleWerkstuecke(JPanel werkstueckVerwaltung) {
	JTable tableWerkstueckDB;
	String[][] werkstueckDBData = { { "Winkel", "1304" }, { "Winkel1", "240" }, { "Etage 20", "220" },
		{ "Bla", "217" }, { "Test System", "215" } };

	String[] werkstueckDBColNames = { "Werkst\u00FCck", "Länge" };

	tableWerkstueckDB = new JTable(werkstueckDBData, werkstueckDBColNames);
	tableWerkstueckDB.setFont(Formatierung.tableFont());

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 40, 747, 424);
	werkstueckVerwaltung.add(scrollPane);

	scrollPane.setViewportView(tableWerkstueckDB);
    }
}
