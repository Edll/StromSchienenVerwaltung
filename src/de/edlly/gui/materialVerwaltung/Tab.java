package de.edlly.gui.materialVerwaltung;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
 * Enthält das UI für den Materialmanager. Sowie die ActionListener und die SQL
 * Connection
 * 
 * Attribute: Connection SqlConn SQL Connection JTextField txtMatAnlex Textfeld
 * für die Material Breite JTextField txtMatAnlez Textfeld für die Material
 * Tiefe JTextField txtMatAnleMax Textfeld für die MaxLänge
 * 
 * @author Edlly
 *
 */

public class Tab {

	private Connection sqlConn = de.edlly.db.SQLiteConnect.dbConnection();
	private JTextField eingabeKoordinateX;
	private JTextField eingabeKoordinateZ;
	private JTextField eingabeKoordinatenMaxY;
	private JButton materialHinzufuegen;
	private JComboBox<String> comboMaterialSorte;

	// Konstruktor
	public Tab() {

	}

	public JPanel verwaltungsAnzeige() {

		JPanel anzeigeBereich = new JPanel();
		anzeigeBereich.setLayout(null);

		// Anzeige zerlegen in TabHeader Tabelle und Hinzufügen
		

		try {

			anzeigeBereich.add(headerMaterialDatenbank());
			anzeigeBereich.add(bereichMaterialDatenbank());
			anzeigeBereich.add(materialEingaben());

			JButton makiertesMaterialLoeschen = new JButton("Makierte Löschen");
			makiertesMaterialLoeschen.setBounds(600, 492, 150, 23);
			anzeigeBereich.add(makiertesMaterialLoeschen);



			materialHinzufuegen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					try {

						DbAbfrage materialSorteId = new DbAbfrage();
						materialSorteId.SqlConn = sqlConn;

						int MaterialSorteSelectId = materialSorteId
								.SelectMaterialSorteString((String) comboMaterialSorte.getSelectedItem());

						DbHinzu materialHinzu = new DbHinzu();
						materialHinzu.SqlConn = sqlConn;
						materialHinzu.Add(Integer.parseInt(eingabeKoordinateX.getText()),
								Integer.parseInt(eingabeKoordinateZ.getText()),
								Integer.parseInt(eingabeKoordinatenMaxY.getText()), MaterialSorteSelectId);

						// Table erneuern
						// tableMaterialDBscrollPane.setViewportView(ManagerTable.AusgabeTable());

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

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Exception: " + e.getClass().getSimpleName() + " " + e.getMessage());

		}

		return anzeigeBereich;
	}
	
	

	public JLabel headerMaterialDatenbank() {
		JLabel header = new JLabel("Material Datenbank");
		header.setFont(Formatierung.headerFont());
		header.setBounds(Formatierung.HEADER_TAB_LABEL_X, Formatierung.HEADER_TAB_LABEL_Y, 162, 20);

		return header;
	}

	public JScrollPane bereichMaterialDatenbank() {

		// Objekt für die Material Tabelle erzeugen
		MaterialTabelle managerTable = new MaterialTabelle();
		managerTable.SqlConn = sqlConn;

		// ScrollPane erzeugen für die MaterialTaballe
		JScrollPane tabellenBereich = new JScrollPane();

		tabellenBereich.setBounds(10, 39, 738, 300);
		tabellenBereich.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tabellenBereich.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Anzeige Table Hinzufügen
		tabellenBereich.setViewportView(managerTable.AusgabeTable());

		return tabellenBereich;
	}

	public JPanel materialEingaben() {

		JPanel materialEingabeBereich = new JPanel();
		materialEingabeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
		materialEingabeBereich.setLayout(null);
		materialEingabeBereich.setBounds(10, 420, 500, 100);

		// Label über dem Hinzufügen
		JLabel header = new JLabel("Material anlegen");
		header.setFont(Formatierung.headerFont());
		header.setBounds(Formatierung.HEADER_TAB_LABEL_X, Formatierung.HEADER_TAB_LABEL_Y, 138, 20);
		materialEingabeBereich.add(header);

		// Material hinzufügen Eingabebereich
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

		eingabeKoordinateX = new JTextField();
		eingabeKoordinateX.setBounds(10, 50, 50, 20);
		materialEingabeBereich.add(eingabeKoordinateX);
		eingabeKoordinateX.setColumns(10);

		eingabeKoordinateZ = new JTextField();
		eingabeKoordinateZ.setBounds(86, 50, 50, 20);
		materialEingabeBereich.add(eingabeKoordinateZ);
		eingabeKoordinateZ.setColumns(10);

		// Objekt zum Abfragen der JCombo für die MaterialSorten
		DbAbfrage materialSortenListe = new DbAbfrage();
		materialSortenListe.SqlConn = sqlConn;

		comboMaterialSorte = new JComboBox<String>(materialSortenListe.SelectTableMaterialSorteString());
		comboMaterialSorte.setBounds(156, 50, 96, 20);
		materialEingabeBereich.add(comboMaterialSorte);

		eingabeKoordinatenMaxY = new JTextField();
		eingabeKoordinatenMaxY.setBounds(280, 50, 86, 20);
		materialEingabeBereich.add(eingabeKoordinatenMaxY);
		eingabeKoordinatenMaxY.setColumns(10);

		this.materialHinzufuegen = new JButton("+");
		this.materialHinzufuegen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.materialHinzufuegen.setBounds(392, 50, 43, 23);
		materialEingabeBereich.add(this.materialHinzufuegen);

		return materialEingabeBereich;
	}

}
