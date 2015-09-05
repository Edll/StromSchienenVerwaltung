package de.edlly.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WerkstueckManager {

    public JPanel werkstueckVerwaltungsPanel() {

	JPanel WerkstueckManager = new JPanel();
	WerkstueckManager.setLayout(null);

	JLabel lblWerkstueckManagerTitel = new JLabel("Werkstück Manager");
	lblWerkstueckManagerTitel.setBounds(10, 8, 162, 20);
	lblWerkstueckManagerTitel.setFont(new Font("Tahoma", Font.BOLD, 16));
	WerkstueckManager.add(lblWerkstueckManagerTitel);

	/*
	 * TODO: Werkstueck DB Erstellen
	 */
	JTable tableWerkstueckDB;
	String[][] WerkstueckDBData = { { "Winkel", "1304" }, { "Winkel1", "240" }, { "Etage", "220" },
		{ "Bla", "217" }, { "Vamocon", "215" } };

	String[] WerkstueckDBColNames = { "Werkstück", "Länge" };

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 40, 747, 424);
	WerkstueckManager.add(scrollPane);

	tableWerkstueckDB = new JTable(WerkstueckDBData, WerkstueckDBColNames);
	tableWerkstueckDB.setFont(new Font("Tahoma", Font.PLAIN, 11));
	scrollPane.setViewportView(tableWerkstueckDB);

	return WerkstueckManager;
    }
}
