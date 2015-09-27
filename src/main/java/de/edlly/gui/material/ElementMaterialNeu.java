package de.edlly.gui.material;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import de.edlly.db.*;
import de.edlly.gui.Element;
import de.edlly.gui.Formatierung;
import de.edlly.gui.IElement;
import de.edlly.material.MaterialSorte;
import de.edlly.material.NeuerMaterialDatensatz;
import net.miginfocom.swing.MigLayout;

/**
 * Erzeugt ein JPanel das die Eingabefelder für einen neuen Datensatz beinhaltet. Enthält eine ActionListener für den
 * "+" Knopf der die Daten in das Objekt f�r einen neuen Material Datensatz legt und dann ein Anlegen des Datensatz
 * auslöst.
 * 
 * TODO: Form Layout!
 * 
 * @author Edlly java@edlly.de
 *
 */

public class ElementMaterialNeu extends Element implements IElement {
    private JPanel panel;
    private SQLiteConnect sqlConnection;

    private JTextField eingabeKoordinateX;
    private JTextField eingabeKoordinateZ;
    private JTextField eingabeKoordinatenMaxY;
    private JButton materialHinzufuegen;
    private JComboBox materialSortenAuswahl;

    public ElementMaterialNeu() {
	panel = new JPanel();
	sqlConnection = new SQLiteConnect();
    }

    public JPanel createAndGet() {
	create();

	return panel;
    }

    @Override
    public void create() {
	panel.setLayout(new MigLayout("", Formatierung.MIG_ELEMENT_PANEL_LEFT, Formatierung.MIG_ELEMENT_PANEL_TOP));

	labelsDerEingabeFelder();
	eingabeFelderKoordinaten();
	try {
	    materialSortenListe();
	} catch (SQLException e) {
	    systemExceptionHandling(e.getLocalizedMessage());
	}
	buttonMaterialHinzufuegen();

	actionMaterialHinzu();

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

    public void materialSortenListe() throws SQLException {
	sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();
	MaterialSorte materialDB = new MaterialSorte(sqlConnection);
	materialSortenAuswahl = new JComboBox(materialDB.materialSorteNamensListe());
	materialSortenAuswahl.setBounds(156, 50, 96, 20);
	panel.add(materialSortenAuswahl);
	sqlConnection.close();
    }

    public void buttonMaterialHinzufuegen() {
	materialHinzufuegen = new JButton("+");
	materialHinzufuegen.setFont(Formatierung.buttonFont());
	materialHinzufuegen.setBounds(392, 50, 43, 23);
	panel.add(materialHinzufuegen);
    }

    public void eingabeFelderKoordinaten() {

	eingabeKoordinateX = new JTextField();
	eingabeKoordinateX.setBounds(10, 50, 50, 20);
	panel.add(eingabeKoordinateX);
	eingabeKoordinateX.setColumns(10);

	eingabeKoordinateZ = new JTextField();
	eingabeKoordinateZ.setBounds(86, 50, 50, 20);
	panel.add(eingabeKoordinateZ);
	eingabeKoordinateZ.setColumns(10);

	eingabeKoordinatenMaxY = new JTextField();
	eingabeKoordinatenMaxY.setBounds(280, 50, 86, 20);
	panel.add(eingabeKoordinatenMaxY);
	eingabeKoordinatenMaxY.setColumns(10);
    }

    public void labelsDerEingabeFelder() {

	JLabel lblMaterialGroesse = new JLabel("Größe");
	lblMaterialGroesse.setFont(Formatierung.eingabeFeldLabel());
	lblMaterialGroesse.setBounds(10, 30, 34, 14);
	panel.add(lblMaterialGroesse);

	JLabel lblX = new JLabel("x");
	lblX.setFont(Formatierung.eingabeFeldLabel());
	lblX.setBounds(70, 50, 7, 14);
	panel.add(lblX);

	JLabel lblMaterialSorte = new JLabel("Material Sorte");
	lblMaterialSorte.setFont(Formatierung.eingabeFeldLabel());
	lblMaterialSorte.setBounds(156, 30, 81, 14);
	panel.add(lblMaterialSorte);

	JLabel lblMaximaleLaenge = new JLabel("Maximale Länge");
	lblMaximaleLaenge.setFont(Formatierung.eingabeFeldLabel());
	lblMaximaleLaenge.setBounds(280, 30, 92, 14);
	panel.add(lblMaximaleLaenge);
    }
}
