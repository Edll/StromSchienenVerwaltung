package de.edlly.gui.materialVerwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.edlly.db.SQLiteConnect;
import de.edlly.gui.Formatierung;
import de.edlly.material.DbAbfrage;
import de.edlly.material.NeuerMaterialDatensatz;

public class MaterialAnlegen {

    private JTextField eingabeKoordinateX;
    private JTextField eingabeKoordinateZ;
    private JTextField eingabeKoordinatenMaxY;
    private JButton materialHinzufuegen;
    private JComboBox<String> materialSortenAuswahl;

    public JPanel materialEingabeBereich(int PositionX, int PositionY) {

	JPanel materialEingabeBereich = new JPanel();
	materialEingabeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
	materialEingabeBereich.setLayout(null);
	materialEingabeBereich.setBounds(PositionX, PositionY, 500, 100);

	headerMaterialAnlegen(materialEingabeBereich);
	labelsDerEingabeFelder(materialEingabeBereich);
	eingabeFelderKoordinaten(materialEingabeBereich);
	materialSortenListe(materialEingabeBereich);
	buttonMaterialHinzufuegen(materialEingabeBereich);
	actionMaterialHinzu(materialEingabeBereich);

	return materialEingabeBereich;
    }

    public void actionMaterialHinzu(JPanel materialEingabeBereich) {

	materialHinzufuegen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {

		try {

		    DbAbfrage materialSorteId = new DbAbfrage();
		    int MaterialSorteSelectId = materialSorteId
			    .SelectMaterialSorteString((String) materialSortenAuswahl.getSelectedItem());

		    int koordianteX = Integer.parseInt(eingabeKoordinateX.getText());
		    int koordianteZ = Integer.parseInt(eingabeKoordinateZ.getText());
		    int koordinateyMax = Integer.parseInt(eingabeKoordinatenMaxY.getText());

		    NeuerMaterialDatensatz MaterialDatensatzAnlegen = new NeuerMaterialDatensatz(
			    SQLiteConnect.dbConnection());
		    MaterialDatensatzAnlegen.setMaterialDaten(koordianteX, koordianteZ, koordinateyMax,
			    MaterialSorteSelectId);
		    if(MaterialDatensatzAnlegen.datensatzAusObjektWertenAnlegen()){
			
		    }
			 JOptionPane.showMessageDialog(null, "Das neue Material ist erfolgreich eingefügt worden.");
		    /**
		     * @TODO: Funktion in der Tabellen Erzeugung einbauen um die Daten in der Tabelle neu Laden zu
		     *        können
		     */

		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Bitte eine gültige Zahl eingeben.");

		} catch (IllegalArgumentException e) {
		    JOptionPane.showMessageDialog(null, e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
		}
		materialEingabeBereich.revalidate();
		materialEingabeBereich.repaint();

	    }
	});
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

}
