package de.edlly.gui.materialVerwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import de.edlly.material.DbAbfrage;
import de.edlly.material.DbHinzu;
import de.edlly.gui.Formatierung;

/**
 * Erzeugt ein JPanel das die Material Verwaltung beinhaltet.
 * 
 * @author Edlly java@edlly.de
 *
 */

public class Tab {

	private JTextField eingabeKoordinateX;
	private JTextField eingabeKoordinateZ;
	private JTextField eingabeKoordinatenMaxY;
	private JButton materialHinzufuegen;
	private JButton makiertesMaterialLoeschen;
	private JComboBox<String> materialSortenAuswahl;
	private JScrollPane tabellenBereich;

	public Tab() throws Exception {

	}

	public JPanel verwaltungsAnzeige() {

		JPanel anzeigeBereich = new JPanel();
		anzeigeBereich.setLayout(null);

		try {

			anzeigeBereich.add(headerMaterialDatenbank());
			anzeigeBereich.add(bereichMaterialDatenbank());
			anzeigeBereich.add(materialEingabeBereich());
			anzeigeBereich.add(auswahlLoeschenBereich());
			actionMaterialHinzu(anzeigeBereich);

		} catch (Exception e) {
			throw e;
		}

		return anzeigeBereich;
	}

	public void actionMaterialHinzu(JPanel anzeigeBereich) {
		materialHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					DbAbfrage materialSorteId = new DbAbfrage();

					int MaterialSorteSelectId = materialSorteId
							.SelectMaterialSorteString((String) materialSortenAuswahl.getSelectedItem());

					DbHinzu materialHinzu = new DbHinzu();
					materialHinzu.Add(Integer.parseInt(eingabeKoordinateX.getText()),
							Integer.parseInt(eingabeKoordinateZ.getText()),
							Integer.parseInt(eingabeKoordinatenMaxY.getText()), MaterialSorteSelectId);
					MaterialTabelle managerTable = new MaterialTabelle();
					tabellenBereich.setViewportView(managerTable.AusgabeTable());

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Bitte eine Zahl gültige Zahl eingeben.");

				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Exception: " + e.getClass().getSimpleName() + " " + e.getMessage());
				}
				anzeigeBereich.revalidate();
				anzeigeBereich.repaint();

			}
		});
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

	public JPanel materialEingabeBereich() {

		JPanel materialEingabeBereich = new JPanel();
		materialEingabeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
		materialEingabeBereich.setLayout(null);
		materialEingabeBereich.setBounds(10, 420, 500, 100);
		headerMaterialAnlegen(materialEingabeBereich);
		labelsDerEingabeFelder(materialEingabeBereich);
		eingabeFelderKoordinaten(materialEingabeBereich);
		materialSortenListe(materialEingabeBereich);
		buttonMaterialHinzufuegen(materialEingabeBereich);

		return materialEingabeBereich;
	}

	public void materialSortenListe(JPanel materialEingabeBereich) {
		DbAbfrage materialDB = new DbAbfrage();
		materialSortenAuswahl = new JComboBox<String>(materialDB.SelectTableMaterialSorteString());
		materialSortenAuswahl.setBounds(156, 50, 96, 20);
		materialEingabeBereich.add(materialSortenAuswahl);
	}

	public void buttonMaterialHinzufuegen(JPanel materialEingabeBereich) {
		materialHinzufuegen = new JButton("+");
		materialHinzufuegen.setFont(Formatierung.buttonFont());
		materialHinzufuegen.setBounds(392, 50, 43, 23);
		materialEingabeBereich.add(materialHinzufuegen);
	}

	public void headerMaterialAnlegen(JPanel materialEingabeBereich) {

		JLabel headerMaterialAnlegen = new JLabel("Material anlegen");
		headerMaterialAnlegen.setFont(Formatierung.headerFont());
		headerMaterialAnlegen.setBounds(Formatierung.HEADER_POSITION_X, Formatierung.HEADER_POSITION_Y, 138, 20);
		materialEingabeBereich.add(headerMaterialAnlegen);
	}

	public void eingabeFelderKoordinaten(JPanel materialEingabeBereich) {

		eingabeKoordinateX = new JTextField();
		eingabeKoordinateX.setBounds(10, 50, 50, 20);
		materialEingabeBereich.add(eingabeKoordinateX);
		eingabeKoordinateX.setColumns(10);

		eingabeKoordinateZ = new JTextField();
		eingabeKoordinateZ.setBounds(86, 50, 50, 20);
		materialEingabeBereich.add(eingabeKoordinateZ);
		eingabeKoordinateZ.setColumns(10);

		eingabeKoordinatenMaxY = new JTextField();
		eingabeKoordinatenMaxY.setBounds(280, 50, 86, 20);
		materialEingabeBereich.add(eingabeKoordinatenMaxY);
		eingabeKoordinatenMaxY.setColumns(10);
	}

	public void labelsDerEingabeFelder(JPanel materialEingabeBereich) {

		JLabel lblMaterialGroesse = new JLabel("Gr\u00F6\u00DFe");
		lblMaterialGroesse.setFont(Formatierung.eingabeFeldLabel());
		lblMaterialGroesse.setBounds(10, 30, 34, 14);
		materialEingabeBereich.add(lblMaterialGroesse);

		JLabel lblX = new JLabel("x");
		lblX.setFont(Formatierung.eingabeFeldLabel());
		lblX.setBounds(70, 50, 7, 14);
		materialEingabeBereich.add(lblX);

		JLabel lblMaterialSorte = new JLabel("Material Sorte");
		lblMaterialSorte.setFont(Formatierung.eingabeFeldLabel());
		lblMaterialSorte.setBounds(156, 30, 81, 14);
		materialEingabeBereich.add(lblMaterialSorte);

		JLabel lblMaximaleLaenge = new JLabel("Maximale L\u00E4nge");
		lblMaximaleLaenge.setFont(Formatierung.eingabeFeldLabel());
		lblMaximaleLaenge.setBounds(280, 30, 92, 14);
		materialEingabeBereich.add(lblMaximaleLaenge);
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
