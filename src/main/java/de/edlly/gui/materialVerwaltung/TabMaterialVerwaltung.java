package de.edlly.gui.materialVerwaltung;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.edlly.db.SQLiteConnect;
import de.edlly.gui.Formatierung;
import de.edlly.gui.materialVerwaltung.AusgabeMaterialAnlegen;
import de.edlly.material.MaterialTabelle;

/**
 * Erzeugt ein JPanel das die Material Verwaltung beinhaltet.
 * 
 * TODO: Code ist Wip muss neu strukturiert werden!
 * 
 * @author Edlly java@edlly.de
 *
 */

public class TabMaterialVerwaltung {

    private JButton makiertesMaterialLoeschen;
    protected AusgabeMaterialTabelle materialTabelle;
    protected JTable materialTabelleJTable;
    protected JScrollPane tabellenBereich = new JScrollPane();
    protected MaterialTabelle materialModel;

    public TabMaterialVerwaltung() {

    }

    public JPanel materialVerwaltungsPanel() {

	JPanel materialVerwaltung = new JPanel();
	materialVerwaltung.setLayout(null);

	materialTabelleeAnzeigen(materialVerwaltung);
	neuesMaterialAnlegen(materialVerwaltung);
	auswahlLoeschenBereich(materialVerwaltung);

	return materialVerwaltung;
    }

    public void materialTabelleeAnzeigen(JPanel materialVerwaltung) {

	try {
	    materialTabelle = new AusgabeMaterialTabelle(tabellenBereich);
	    materialVerwaltung.add(materialTabelle.materialTabellePanel(10, 10));
	} catch (Exception e) {
	    e.printStackTrace();

	}
    }

    public void neuesMaterialAnlegen(JPanel materialVerwaltung) {

	try {
	    AusgabeMaterialAnlegen neuesMaterialAnlegen = new AusgabeMaterialAnlegen();
	    materialVerwaltung.add(neuesMaterialAnlegen.materialEingabeBereich(10, 420));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void auswahlLoeschenBereich(JPanel materialVerwaltung) {

	JPanel materialLoeschenAnzeigeBereich = new JPanel();
	materialLoeschenAnzeigeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
	materialLoeschenAnzeigeBereich.setLayout(null);
	materialLoeschenAnzeigeBereich.setBounds(550, 420, 170, 100);

	makiertesMaterialLoeschen = new JButton("Makierte Löschen");
	makiertesMaterialLoeschen.setFont(Formatierung.buttonFont());
	makiertesMaterialLoeschen.setBounds(10, 10, 150, 23);
	materialLoeschenAnzeigeBereich.add(makiertesMaterialLoeschen);

	materialVerwaltung.add(materialLoeschenAnzeigeBereich);
    }

    /*
     * FIXME: Dies funktioniert noch nicht!
     */

    public void refreshMaterialTabelle() {
	try {
	    Connection sqlConnection;
	    sqlConnection = SQLiteConnect.dbConnection();
	    
	    materialModel = new MaterialTabelle(sqlConnection);
	    materialTabelleJTable = new JTable(materialModel.tabelleErstellen(true));
	    tabellenBereich.setViewportView(materialTabelleJTable);
	    
	    SQLiteConnect.closeSqlConnection(sqlConnection);
	    
	    System.out.println("Funktion =>" + tabellenBereich);
	    
	} catch (SQLException sqlException) {
	    sqlException.printStackTrace();
	}

    }

}
