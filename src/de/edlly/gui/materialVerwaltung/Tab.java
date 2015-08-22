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
	private JTextField txtMatAnlex;
	private JTextField txtMatAnlez;
	private JTextField txtMatAnleMax;

	// Konstruktor
	public Tab() {

	}

	public JPanel verwaltungsAnzeige() {

		JPanel tabMaterialManager = new JPanel();

		// Objekt für die Material Tabelle erzeugen
		MaterialTabelle ManagerTable = new MaterialTabelle();
		ManagerTable.SqlConn = sqlConn;

		// Anzeige zerlegen in TabHeader Tabelle und Hinzufügen

		try {
			tabMaterialManager.setLayout(null);
			JScrollPane tableMaterialDBscrollPane = new JScrollPane();
			// ScrollPane erzeugen für die MaterialTaballe
			tableMaterialDBscrollPane.setBounds(10, 39, 738, 390);
			tableMaterialDBscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			tableMaterialDBscrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			tabMaterialManager.add(tableMaterialDBscrollPane);

			tabMaterialManager.add(headerLabel());
			
			// Anzeige Table Hinzufügen
			tableMaterialDBscrollPane.setViewportView(ManagerTable.AusgabeTable());

			// Label über dem Hinzufügen
			JLabel lblMaterialAnlegen = new JLabel("Material anlegen");
			lblMaterialAnlegen.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblMaterialAnlegen.setBounds(10, 440, 138, 20);
			tabMaterialManager.add(lblMaterialAnlegen);

			// Material hinzufügen Eingabebereich
			JLabel lblGre = new JLabel("Gr\u00F6\u00DFe");
			lblGre.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblGre.setBounds(10, 468, 34, 14);
			tabMaterialManager.add(lblGre);

			txtMatAnlex = new JTextField();
			txtMatAnlex.setText("50");
			txtMatAnlex.setBounds(10, 493, 50, 20);
			tabMaterialManager.add(txtMatAnlex);
			txtMatAnlex.setColumns(10);

			JLabel lblX_2 = new JLabel("x");
			lblX_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblX_2.setBounds(70, 495, 6, 14);
			tabMaterialManager.add(lblX_2);

			txtMatAnlez = new JTextField();
			txtMatAnlez.setText("10");
			txtMatAnlez.setBounds(86, 493, 50, 20);
			tabMaterialManager.add(txtMatAnlez);
			txtMatAnlez.setColumns(10);

			// Objekt zum Abfragen der JCombo für die MaterialSorten
			de.edlly.material.DbAbfrage materialSorteList = new de.edlly.material.DbAbfrage();
			materialSorteList.SqlConn = sqlConn;
			String[] MaterialArtList = materialSorteList.SelectTableMaterialSorteString();

			JComboBox<?> comboMaterialSorte = new JComboBox<Object>(MaterialArtList);
			comboMaterialSorte.setBounds(156, 493, 96, 20);
			tabMaterialManager.add(comboMaterialSorte);

			JLabel lblMaterialSorte = new JLabel("Material Sorte");
			lblMaterialSorte.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblMaterialSorte.setBounds(156, 468, 81, 14);
			tabMaterialManager.add(lblMaterialSorte);

			JLabel lblMaximaleLnge = new JLabel("Maximale L\u00E4nge");
			lblMaximaleLnge.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblMaximaleLnge.setBounds(280, 468, 92, 14);
			tabMaterialManager.add(lblMaximaleLnge);

			txtMatAnleMax = new JTextField();
			txtMatAnleMax.setText("4000");
			txtMatAnleMax.setBounds(280, 493, 86, 20);
			tabMaterialManager.add(txtMatAnleMax);
			txtMatAnleMax.setColumns(10);

			JButton btnMatAnle = new JButton("+");
			btnMatAnle.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnMatAnle.setBounds(392, 492, 43, 23);
			tabMaterialManager.add(btnMatAnle);

			JButton btnMarkDelet = new JButton("Makierte Löschen");
			btnMarkDelet.setBounds(600, 492, 150, 23);
			tabMaterialManager.add(btnMarkDelet);

			/**
			 * Material hinzufügen
			 */

			btnMatAnle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					try {
						// Daten Hinzufügen
						sqlConn = de.edlly.db.SQLiteConnect.dbConnection();

						DbAbfrage materialSorteId = new DbAbfrage();
						materialSorteId.SqlConn = sqlConn;
						int MaterialSorteSelectId = materialSorteId
								.SelectMaterialSorteString((String) comboMaterialSorte.getSelectedItem());

						DbHinzu materialHinzu = new DbHinzu();
						materialHinzu.SqlConn = sqlConn;
						materialHinzu.Add(Integer.parseInt(txtMatAnlex.getText()),
								Integer.parseInt(txtMatAnlez.getText()), Integer.parseInt(txtMatAnleMax.getText()),
								MaterialSorteSelectId);

						// Table erneuern
						tableMaterialDBscrollPane.setViewportView(ManagerTable.AusgabeTable());

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Bitte eine Zahl gültige Zahl eingeben.");

					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());

					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Exception: " + e.getClass().getSimpleName() + " " + e.getMessage());
					}
					tabMaterialManager.revalidate();
					tabMaterialManager.repaint();

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Exception: " + e.getClass().getSimpleName() + " " + e.getMessage());

		}

		return tabMaterialManager;
	}

	public JLabel headerLabel() {
		JLabel header = new JLabel("Material Datenbank");
		header.setFont(Formatierung.headerFont());
		header.setBounds(Formatierung.HEADER_TAB_LABEL_X, Formatierung.HEADER_TAB_LABEL_Y, 162, 20);

		return header;
	}

}
