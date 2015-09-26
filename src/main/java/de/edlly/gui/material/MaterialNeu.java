package de.edlly.gui.material;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import de.edlly.db.*;
import de.edlly.gui.Formatierung;
import de.edlly.material.MaterialSorte;
import de.edlly.material.NeuerMaterialDatensatz;

/**
 * Erzeugt ein JPanel das die Eingabefelder für einen neuen Datensatz beinhaltet. Enthält eine ActionListener für den
 * "+" Knopf der die Daten in das Objekt f�r einen neuen Material Datensatz legt und dann ein Anlegen des Datensatz
 * auslöst.
 * 
 * TODO: Code ist Wip muss neu strukturiert werden!
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialNeu {

    private JTextField eingabeKoordinateX;
    private JTextField eingabeKoordinateZ;
    private JTextField eingabeKoordinatenMaxY;
    private JButton materialHinzufuegen;
    private JComboBox materialSortenAuswahl;
    private JPanel materialEingabeBereich = new JPanel();
    private SQLiteConnect sqlConnection = new SQLiteConnect();

    public MaterialNeu() {
    } 
    

    public JPanel materialEingabeBereich(int PositionX, int PositionY) throws SQLException {

	materialEingabeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
	materialEingabeBereich.setLayout(null);
	materialEingabeBereich.setBounds(PositionX, PositionY, 500, 100);

	headerMaterialAnlegen(materialEingabeBereich);
	labelsDerEingabeFelder(materialEingabeBereich);
	eingabeFelderKoordinaten(materialEingabeBereich);
	materialSortenListe(materialEingabeBereich);
	buttonMaterialHinzufuegen(materialEingabeBereich);

	actionMaterialHinzu();

	return materialEingabeBereich;
    }

    public void actionMaterialHinzu() {

	materialHinzufuegen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {

		try {

		    sqlConnection.dbConnect();

		    MaterialSorte materialSorteId = new MaterialSorte(sqlConnection);
		    int MaterialSorteSelectId = materialSorteId
			    .getMaterialSorteId((String) materialSortenAuswahl.getSelectedItem());

		    int koordianteX = Integer.parseInt(eingabeKoordinateX.getText());
		    int koordianteZ = Integer.parseInt(eingabeKoordinateZ.getText());
		    int koordinateyMax = Integer.parseInt(eingabeKoordinatenMaxY.getText());

		    NeuerMaterialDatensatz MaterialDatensatzAnlegen = new NeuerMaterialDatensatz(sqlConnection);
		    MaterialDatensatzAnlegen.setMaterialDaten(koordianteX, koordianteZ, koordinateyMax,
			    MaterialSorteSelectId);
		    if (MaterialDatensatzAnlegen.datensatzAusObjektWertenAnlegen()) {
			JOptionPane.showMessageDialog(null, "Das neue Material ist erfolgreich eingefügt worden.");
		    }

		    sqlConnection.close();
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Bitte eine gültige Zahl eingeben.");

		} catch (IllegalArgumentException e) {
		    JOptionPane.showMessageDialog(null, e.getMessage());

		} catch (SQLException sqlException) {
		    sqlException.printStackTrace();
		} finally {
		    try {
			sqlConnection.close();
		    } catch (SQLException e) {

			e.printStackTrace();
		    }
		}

	    }
	});
    }

    public void materialSortenListe(JPanel materialEingabeBereich) throws SQLException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	MaterialSorte materialDB = new MaterialSorte(sqlConnection);
	materialSortenAuswahl = new JComboBox(materialDB.materialSorteNamensListe());
	materialSortenAuswahl.setBounds(156, 50, 96, 20);
	materialEingabeBereich.add(materialSortenAuswahl);
	sqlConnection.close();
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

	JLabel lblMaterialGroesse = new JLabel("Größe");
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

	JLabel lblMaximaleLaenge = new JLabel("Maximale Länge");
	lblMaximaleLaenge.setFont(Formatierung.eingabeFeldLabel());
	lblMaximaleLaenge.setBounds(280, 30, 92, 14);
	materialEingabeBereich.add(lblMaximaleLaenge);
    }

}
